SET search_path = intramurudes;


CREATE OR REPLACE FUNCTION get_league_sport()
    RETURNS TABLE (sport_id INT, sport_name VARCHAR(127), league_id INT, league_name VARCHAR(127))
    LANGUAGE SQL
AS $$
SELECT l.id, l.name, s.id, s.name
FROM league l
INNER JOIN sport s ON l.id_sport = s.id;
$$;

SELECT sport_id, sport_name, league_id, league_name FROM get_league_sport();

