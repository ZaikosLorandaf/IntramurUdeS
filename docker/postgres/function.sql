SET search_path = intramurudes;



/**
  Trigger pour vérifier qu'il n'y a pas trop d'équipe dans match_team
 */
CREATE OR REPLACE FUNCTION check_place_left_match()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF EXISTS(SELECT mt.id_team
              FROM match_team mt
              WHERE mt.id_match = new.id_match)
    THEN
        IF (SELECT nb_teams
            FROM intramurudes.v_match_teams vmt
            WHERE vmt.id_match = new.id_match) < (
               SELECT vptls.nb_team_match
               FROM v_player_team_league_sport vptls
               WHERE vptls.id_team = new.id_team)
        THEN
            RETURN TRUE;
        ELSE
            RAISE EXCEPTION 'The match is full.';
        END IF;
    ELSE
        RETURN TRUE;
    END IF;
END;
$$;


/**
  Trigger pour s'assurer que toutes les équipes d'un match sont dans la même ligue
 */
CREATE OR REPLACE FUNCTION check_team_same_league()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF (SELECT vtls.id_league
        FROM v_team_league_sport vtls
        WHERE vtls.id_team = new.id_team) = ALL
       (
           (SELECT vtls.id_league
            FROM v_team_league_sport vtls
            WHERE vtls.id_team IN (SELECT unnest(vmt.list_teams)
                                   FROM v_match_teams vmt
                                   WHERE vmt.id_match = new.id_match))
       )
    THEN
        RETURN TRUE;
    ELSE
        RAISE EXCEPTION 'Other teams are not in the same league';
    END IF;
END;
$$;



/**
  Fonction pour vérifier chevauchement match
 */
CREATE OR REPLACE FUNCTION get_match_overlap(id_team_check INT, nouveau_match match_)
    RETURNS SETOF INT
    LANGUAGE SQL
AS $$
SELECT id FROM match_ m
                   INNER JOIN intramurudes.match_team mt ON m.id = mt.id_match
WHERE m.date_match = nouveau_match.date_match
  AND
    (m.begin_time, m.end_time) OVERLAPS (nouveau_match.begin_time, nouveau_match.end_time)
  AND
    mt.id_team = id_team_check;
$$;

CREATE OR REPLACE FUNCTION check_match_overlap()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS(SELECT get_match_overlap(new.id_team,
                                           (SELECT * FROM match_
                                            WHERE id = new.id_match)
                         )
    )
    THEN
        RETURN TRUE;
    ELSE
        RAISE EXCEPTION 'The match overlaps another match of the team';
    END IF;
END;
$$;


CREATE OR REPLACE FUNCTION check_before_insert_match_team()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF (check_place_left_match()
        AND
        check_team_same_league()
        AND
        check_match_overlap())
    THEN
        RETURN new;
    END IF;
END;
$$;

CREATE TRIGGER trg_check_insert_match_team
    BEFORE INSERT ON match_team
    FOR EACH ROW
EXECUTE function check_before_insert_match_team();