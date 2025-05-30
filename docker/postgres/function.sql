SET search_path = intramurudes;



/**
  Fonction qui donne le nombre de places restantes pour le match
 */
CREATE OR REPLACE FUNCTION get_place_left_match(id_new_match INT)
    RETURNS INT
    LANGUAGE SQL
AS $$
    SELECT ((SELECT nb_team_match FROM v_league_sport vls
                WHERE id_league = (SELECT id_league FROM match_
                                    WHERE id = id_new_match))
                 -
            (SELECT COUNT(id_team) FROM match_team
                WHERE id_match = id_new_match));
$$;

/**
  Trigger pour s'assurer que toutes les équipes d'un match sont dans la même ligue
 */
CREATE OR REPLACE FUNCTION check_team_good_league(id_new_team INT, id_new_match INT)
    RETURNS BOOLEAN
    LANGUAGE SQL
AS $$
SELECT((SELECT m.id_league
        FROM match_ m
        WHERE m.id = id_new_match) IS NULL
           OR
       (SELECT vtls.id_league
        FROM v_team_league_sport vtls
        WHERE vtls.id_team = id_new_team) =
        ((SELECT m.id_league FROM match_ m
               WHERE m.id = id_new_match))
    );
$$;

/**
  Fonction pour vérifier chevauchement match
 */
CREATE OR REPLACE FUNCTION get_match_overlap(id_team_check INT, new_match_id INT)
    RETURNS SETOF INT
    LANGUAGE SQL
AS $$
SELECT id FROM match_ m
                   INNER JOIN intramurudes.match_team mt ON m.id = mt.id_match
WHERE m.date_match = (SELECT date_match FROM match_ WHERE id = new_match_id)
  AND
    (m.begin_time, m.end_time) OVERLAPS ((SELECT m2.begin_time FROM match_ m2 WHERE id = new_match_id),
                                         (SELECT m2.end_time FROM match_ m2 WHERE id = new_match_id))
  AND
    mt.id_team = id_team_check;
$$;

CREATE OR REPLACE FUNCTION check_before_insert_match_team()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS(SELECT get_match_overlap(new.id_team,
                                           new.id_match))
    THEN
        IF ((SELECT get_place_left_match(new.id_match)) > 0)
        THEN
            IF (SELECT check_team_good_league(new.id_team, new.id_match))
            THEN
                RETURN new;
            ELSE
                RAISE 'Team is not in the good league';
            END IF;
        ELSE
            RAISE 'Match is full';
        END IF;
    ELSE
        RAISE 'Match overlaps with another match of the team';
    END IF;
END;
$$;

CREATE TRIGGER trg_check_insert_match_team
    BEFORE INSERT ON match_team
    FOR EACH ROW
EXECUTE function check_before_insert_match_team();