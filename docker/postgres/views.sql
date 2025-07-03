SET search_path = intramurudes;


CREATE OR REPLACE VIEW v_match_teams AS
SELECT m.id AS id_match, m.date_match, m.begin_time, m.end_time,
       COUNT(mt.id_team) AS nb_teams,
       COALESCE(NULLIF(
                                ARRAY_AGG(mt.id_team) FILTER (WHERE mt.id_team IS NOT NULL),
                                '{}'
                ),
                ARRAY[-1]
       ) AS list_teams,
       m.archive AS archive_match
FROM match_ m
         LEFT JOIN intramurudes.match_team mt ON m.id = mt.id_match
GROUP BY m.id, m.date_match, m.begin_time, m.end_time;



CREATE OR REPLACE VIEW v_player_team_league_sport
AS
SELECT p.id AS id_player, p.name AS player_name, p.last_name, p.number,
       t.id AS id_team, t.name AS team_name,
       l.id AS id_league, l.name AS league_name, l.begin_date, l.end_date,
       s.id AS id_sport, s.name AS sport_name, s.nb_team_match,
       p.archive AS archive_player
FROM intramurudes.player p
         LEFT JOIN intramurudes.team t ON t.id = p.id_team
         LEFT JOIN intramurudes.league l ON l.id = t.id_league
         LEFT JOIN intramurudes.sport s ON s.id = l.id_sport;



CREATE OR REPLACE VIEW v_team_league_sport
AS
SELECT t.id AS id_team, t.name AS team_name,
       l.id AS id_league, l.name AS league_name, l.begin_date, l.end_date,
       s.id AS id_sport, s.name AS sport_name, s.nb_team_match,
       s.archive AS archive_sport
FROM intramurudes.team t
         LEFT JOIN intramurudes.league l ON l.id = t.id_league
         LEFT JOIN intramurudes.sport s ON s.id = l.id_sport;


CREATE OR REPLACE VIEW v_league_sport
AS
SELECT l.id AS id_league, l.name AS name_league, l.begin_date, l.end_date, l.done,
       s.id AS id_sport, s.name AS name_soprt, s.nb_team_match,
       l.archive AS archive_league
FROM league AS l
         LEFT JOIN intramurudes.sport s ON l.id_sport = s.id;



CREATE OR REPLACE VIEW v_match_league_sport
AS
SELECT m.id AS match_id, m.date_match, m.begin_time, m.end_time,
       l.id AS id_league, l.name AS name_league, l.begin_date, l.end_date, l.done,
       s.id AS id_sport, s.name AS name_sport, s.nb_team_match,
       COALESCE(NULLIF(
               ARRAY_AGG(mt.id_team) FILTER (WHERE mt.id_team IS NOT NULL),
               '{}'
               ),
               ARRAY[-1]
       ) AS list_teams,

       m.id_season AS id_season, m.archive AS archive_match
FROM intramurudes.match_ m
         LEFT JOIN intramurudes.league l ON l.id = m.id_league
         LEFT JOIN intramurudes.sport s ON s.id = l.id_sport
         LEFT JOIN intramurudes.match_team mt ON mt.id_match = m.id
GROUP BY m.id, m.date_match, m.id, m.begin_time, m.end_time, l.id, l.name, l.begin_date, l.end_date, l.done, s.id, s.name, s.nb_team_match
ORDER BY s.name, l.name, l.begin_date;


CREATE OR REPLACE VIEW v_league
AS
SELECT l.id as id,
       l.name as name,
       l.begin_date as begin_date,
       l.end_date as end_date,
       l.done,
       l.id_sport,
       COALESCE(NULLIF(
                                ARRAY_AGG(ls.id_season) FILTER (WHERE ls.id_season IS NOT NULL),
                                '{}'
                ),
                ARRAY[-1]
       ) AS id_seasons,
       l.archive AS archive_league
FROM intramurudes.league l
LEFT JOIN intramurudes.league_season ls ON l.id = ls.id_league
GROUP BY l.id, l.name, l.begin_date, l.end_date, l.done, l.id_sport;



CREATE OR REPLACE VIEW v_stat_statement
AS
SELECT s.id as id,
       s.statement as statement,
       s.acronym as acronym,
       COALESCE(NULLIF(
                ARRAY_AGG(sss.id_sport) FILTER (WHERE sss.id_sport IS NOT NULL),
                '{}'
                ),
                ARRAY[-1]
       ) AS id_sports
FROM intramurudes.stat_statement s
         LEFT JOIN intramurudes.stat_statement_sport sss ON s.id = sss.id_stat_statement
GROUP BY s.id, s.statement, s.acronym;



CREATE OR REPLACE VIEW v_player_stat
AS
SELECT ps.id AS id,
       ps.value_ AS value,
       CASE
           WHEN ps.id_match IS NULL THEN -1
           ELSE ps.id_match END
           AS id_match,
       ps.id_stat_statement AS id_stat_statement,
       CASE
           WHEN ps.id_season IS NULL THEN -1
           ELSE ps.id_season END
           AS id_season,
       ps.id_player AS id_player,
       CASE
           WHEN m.id_league IS NULL THEN -1
           ELSE m.id_league END AS id_league,
       vptls.id_team AS id_team,
       vptls.id_league AS id_league_player,
       vptls.id_sport AS id_sport
FROM intramurudes.player_stat ps
LEFT JOIN match_ m ON m.id = ps.id_match
LEFT JOIN intramurudes.v_player_team_league_sport vptls ON vptls.id_player = ps.id_player;




CREATE OR REPLACE VIEW v_team_stat
AS
SELECT ts.id AS id,
       ts.value_ AS value,
       CASE
           WHEN ts.id_match IS NULL THEN -1
           ELSE ts.id_match END
           AS id_match,
       ts.id_stat_statement AS id_stat_statement,
       CASE
           WHEN ts.id_season IS NULL THEN -1
           ELSE ts.id_season END
           AS id_season,
       CASE
           WHEN m.id_league IS NULL THEN -1
           ELSE m.id_league END AS id_league,
       vtls.id_team AS id_team,
       vtls.id_league AS id_league_team,
       vtls.id_sport AS id_sport
FROM intramurudes.team_stat ts
         LEFT JOIN match_ m ON m.id = ts.id_match
         LEFT JOIN intramurudes.v_team_league_sport vtls ON vtls.id_team = ts.id_team;



