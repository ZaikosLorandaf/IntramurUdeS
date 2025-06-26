SET search_path = intramurudes;


CREATE OR REPLACE FUNCTION insert_v_stat_statement()
    RETURNS TRIGGER
    LANGUAGE PLPGSQL
AS $$
DECLARE
    id_sport_add int;
    id_stat int;
BEGIN
    IF (new.statement NOT IN (SELECT statement FROM v_stat_statement))
    THEN
        INSERT INTO stat_statement(statement, acronym)
        VALUES (new.statement, new.acronym);
    END IF;
    id_stat = (SELECT id FROM stat_statement
               WHERE statement = new.statement);
    FOREACH id_sport_add IN ARRAY new.id_sports
        LOOP
            IF(NOT id_sport_add = ANY (SELECT unnest(id_sports) FROM v_stat_statement
                                WHERE id = id_stat))
            THEN
                INSERT INTO stat_statement_sport(id_sport, id_stat_statement)
                VALUES (id_sport_add, id_stat);
            END IF;

        END LOOP;
    RETURN new;
END
$$;

INSERT INTO v_stat_statement (statement, acronym, id_sports) VALUES
    ('Interceptions', 'INT', ARRAY[1,2,3]);

INSERT INTO v_stat_statement (statement, acronym, id_sports) VALUES
    ('Passes complétées', 'PASS', ARRAY[1,2,3]);