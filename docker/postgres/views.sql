SET search_path = intramurudes;


CREATE OR REPLACE VIEW v_match_teams AS
SELECT m.id AS id_match, m.date_match, m.begin_time, m.end_time,
       COUNT(mt.id_team) AS nb_teams, ARRAY_AGG(id_team) AS list_teams
FROM match_ m
         INNER JOIN intramurudes.match_team mt ON m.id = mt.id_match
GROUP BY m.id, m.date_match, m.begin_time, m.end_time;



CREATE OR REPLACE VIEW v_player_team_league_sport
AS
SELECT p.id AS id_player, p.name AS player_name, p.last_name, p.number,
       t.id AS id_team, t.name AS team_name,
       l.id AS id_league, l.name AS league_name, l.begin_date, l.end_date,
       s.id AS sport_id, s.name AS sport_name, s.nb_team_match
FROM intramurudes.player p
         INNER JOIN intramurudes.team t ON t.id = p.id_team
         INNER JOIN intramurudes.league l ON l.id = t.id_league
         INNER JOIN intramurudes.sport s ON s.id = l.id_sport;



CREATE OR REPLACE VIEW v_team_league_sport
AS
SELECT t.id AS id_team, t.name AS team_name,
       l.id AS id_league, l.name AS league_name, l.begin_date, l.end_date,
       s.id AS sport_id, s.name AS sport_name, s.nb_team_match
FROM intramurudes.team t
         INNER JOIN intramurudes.league l ON l.id = t.id_league
         INNER JOIN intramurudes.sport s ON s.id = l.id_sport;


CREATE OR REPLACE VIEW v_league_sport
AS
SELECT l.id AS id_league, l.name AS name_league, l.begin_date, l.end_date, l.done,
       s.id AS id_sport, s.name AS name_soprt, s.nb_team_match
FROM league AS l
INNER JOIN intramurudes.sport s ON l.id_sport = s.id;




