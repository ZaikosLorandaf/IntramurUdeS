SET search_path = intramurudes;

--Méthode pour mettre la saison selon le match de la stat s'il y a un match
CREATE OR REPLACE FUNCTION intramurudes.create_stat(stat_type1 VARCHAR)
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS $$
BEGIN
    --Vérifier si la stat existe déjà, si c'est le cas, archiver l'ancienne valeur
    IF(stat_type1 = 'team')
    THEN
        DELETE FROM intramurudes.v_all_stats vas
        WHERE vas.stat_type = stat_type1 AND
            vas.id_match = new.id_match AND
            vas.id_stat_statement = new.id_stat_statement AND
            vas.id_season = new.id_season AND
            vas.id_owner = new.id_team AND
            vas.archive_stat IS NULL;
    ELSE IF (stat_type1 = 'player')
        THEN
            DELETE FROM v_all_stats vas
            WHERE vas.stat_type = stat_type1 AND
                vas.id_match = new.id_match AND
                vas.id_stat_statement = new.id_stat_statement AND
                vas.id_season = new.id_season AND
                vas.id_owner = new.id_player AND
                vas.archive_stat IS NULL;
        END IF;
    END IF;

    --Mettre la bonne saison
    IF new.id_match IS NOT NULL
    THEN
        new.id_season = (SELECT id_season FROM intramurudes.match_
                         WHERE id = new.id_match);
    END IF;
    RETURN new;
END;
$$;






CREATE OR REPLACE FUNCTION intramurudes.delete_v_all_stats()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF(new.stat_type = 'team')
    THEN
        DELETE FROM team_stat ts
        WHERE ts.id_season = new.id_season AND
              ts.id_match = new.id_match AND
              ts.id_stat_statement = new.id_stat_statement AND
              ts.id_team = new.id_owner;
    END IF;

    IF(new.stat_type = 'player')
    THEN
        DELETE FROM intramurudes.player_stat ts
        WHERE ts.id_season = new.id_season AND
            ts.id_match = new.id_match AND
            ts.id_stat_statement = new.id_stat_statement AND
            ts.id_player = new.id_owner;
    END IF;

END;
$$;


CREATE TRIGGER trg_delete_v_all_stats
    INSTEAD OF DELETE ON intramurudes.v_all_stats
    FOR EACH ROW
EXECUTE function intramurudes.delete_v_all_stats();