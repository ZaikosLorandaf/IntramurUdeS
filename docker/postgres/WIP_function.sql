SET search_path = intramurudes;



CREATE OR REPLACE VIEW v_player_team_league_sport
AS
    SELECT p.id AS id_player, p.name AS player_name, p.last_name, p.number,
           t.id AS id_team, t.name AS team_name,
           l.id AS id_league, l.name AS league_name, l.begin_date, l.end_date,
           s.id AS sport_id, s.name AS sport_name, s.nb_team_match
    FROM intramurudes.player p
    INNER JOIN intramurudes.team t ON t.id = p.id_team
    INNER JOIN intramurudes.league l ON l.id = t.id_league
    INNER JOIN intramurudes.sport s ON l.id_sport = s.id;




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
        IF (SELECT count(mt.id_team)
            FROM match_team mt
            WHERE mt.id_match = new.match_id) > (
                SELECT s.nb_team_match
                FROM sport s
                WHERE s.id = )
        THEN

        END IF;
    ELSE
        RETURN new;
    END IF;
END;
$$;



/**
  Trigger pour s'assurer que deux équipes d'un match sont dans la même ligue
 */
CREATE OR REPLACE FUNCTION check_team_same_league()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF (SELECT count(mt.id_team)
        FROM match_team mt
        WHERE mt.id_match = 1) > 1
END;
$$;



/**
  Trigger pour vérifier chevauchement match
 */
SELECT id_team FROM match_team mt
    INNER JOIN intramurudes.match_ m ON m.id = mt.id_match;


CREATE VIEW v_match_team AS
    SELECT m.id id_match, m.date_match, m.begin_time, m.end_time FROM match_ m
