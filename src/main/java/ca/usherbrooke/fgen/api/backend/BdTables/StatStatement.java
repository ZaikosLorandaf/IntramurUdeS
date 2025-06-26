package ca.usherbrooke.fgen.api.backend.BdTables;

import java.util.ArrayList;
import java.util.List;

public class StatStatement {
    private int id;
    private String statement;
    private String acronym;
    private List<Integer> idSports;

    public StatStatement(int id, String statement, String acronym, List<Integer> idSports) {
        this.id = id;
        this.statement = statement;
        this.acronym = acronym;
        this.idSports = idSports;
    }

    public int getId() {
        return id;
    }
    public String getStatement() {
        return statement;
    }
    public String getAcronym() {
        return acronym;
    }

    @Override
    public String toString(){
        return "(" + this.acronym + ")" + this.statement;
    }
}


