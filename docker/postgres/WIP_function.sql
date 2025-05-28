SET search_path = intramurudes;



/**
  Trigger pour vérifier qu'il n'y a pas trop d'équipe dans match_team
 */
CREATE OR REPLACE FUNCTION check_place_left_match()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF EXISTS()
    END IF;
    IF (SELECT count(mt.id_team)
        FROM match_team mt
        WHERE mt.id_match = 1) > 1
    THEN

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
