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



CREATE OR REPLACE VIEW v_match_league_sport
AS
SELECT m.id AS match_id, m.date_match, m.begin_time, m.end_time,
       l.id AS id_league, l.name AS name_league, l.begin_date, l.end_date, l.done,
       s.id AS id_sport, s.name AS name_sport, s.nb_team_match, ARRAY_AGG(mt.id_team) AS list_teams
FROM match_ m
INNER JOIN league l ON l.id = m.id_league
INNER JOIN sport s ON s.id = l.id_sport
INNER JOIN match_team mt ON mt.id_match = m.id
GROUP BY m.id, m.date_match, m.id, m.begin_time, m.end_time, l.id, l.name, l.begin_date, l.end_date, l.done, s.id, s.name, s.nb_team_match;



CREATE OR REPLACE VIEW v_league
AS
SELECT l.id as id,
       l.name as name,
       l.begin_date as begin_date,
       l.end_date as end_date,
       l.done,
       l.id_sport,
       ARRAY_AGG(ls.id_season) AS id_seasons
FROM intramurudes.league l
INNER JOIN intramurudes.league_season ls ON l.id = ls.id_league
GROUP BY l.id, l.name, l.begin_date, l.end_date, l.done, l.id_sport;