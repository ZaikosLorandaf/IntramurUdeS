SET search_path = intramurudes;

CREATE OR REPLACE FUNCTION intramurudes.insert_v_match_teams()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS $$
    DECLARE
        id_new_match INT;
BEGIN
    IF(new.place IS NULL)
    THEN
        new.place = '--';
    END IF;
    INSERT INTO intramurudes.match_ (date_match, begin_time, end_time, id_league, id_season, place)
    VALUES(new.date_match, new.begin_time, new.end_time, new.id_league, new.id_season, new.place);
    id_new_match = (SELECT last_value FROM intramurudes.match__id_seq);
    IF (SELECT ARRAY_LENGTH(new.list_teams, 1)) > 0
    THEN
        INSERT INTO intramurudes.match_team (id_team, id_match)
        VALUES (UNNEST(new.list_teams), id_new_match);
    END IF;
    RETURN NULL;
END
$$;


CREATE TRIGGER trg_insert_v_stat_statement
    INSTEAD OF INSERT ON intramurudes.v_match_teams
    FOR EACH ROW
EXECUTE function intramurudes.insert_v_match_teams();