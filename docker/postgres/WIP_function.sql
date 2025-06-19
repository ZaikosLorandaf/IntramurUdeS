SET search_path = intramurudes;

DELETE FROM sport
WHERE name = 'Ultimate';


CREATE OR REPLACE FUNCTION instead_delete_sport()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE sport s
    SET archive = TRUE
    WHERE s.id = old.id;

    DELETE FROM league
    WHERE id_sport = old.id;

    RETURN NULL;
END;
$$;

CREATE TRIGGER trg_delete_sport
    BEFORE DELETE ON sport
    FOR EACH ROW
EXECUTE function instead_delete_sport();

CREATE OR REPLACE FUNCTION instead_delete_league()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE league l
    SET archive = TRUE
    WHERE l.id = old.id;

    DELETE FROM team
    WHERE id_league = old.id;

    DELETE FROM league_season
    WHERE id_league = old.id;

    DELETE FROM match_
    WHERE id_league = old.id;

    RETURN NULL;
END;
$$;

CREATE TRIGGER trg_delete_league
    BEFORE DELETE ON league
    FOR EACH ROW
EXECUTE function instead_delete_league();



CREATE OR REPLACE FUNCTION instead_delete_team()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE team t
    SET archive = TRUE
    WHERE t.id = old.id;

    DELETE FROM player p
    WHERE p.id_team = old.id;

    DELETE FROM match_team
    WHERE id_team = old.id;

    DELETE FROM team_stat
    WHERE id_team = old.id;

    RETURN NULL;
END;
$$;

CREATE TRIGGER trg_delete_team
    BEFORE DELETE ON team
    FOR EACH ROW
EXECUTE function instead_delete_team();



CREATE OR REPLACE FUNCTION instead_delete_player()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE player p
    SET archive = TRUE
    WHERE p.id = old.id;

    DELETE FROM player_stat ps
    WHERE ps.id_player = old.id;

    RETURN NULL;
END;
$$;

CREATE TRIGGER trg_delete_player
    BEFORE DELETE ON player
    FOR EACH ROW
EXECUTE function instead_delete_player();
