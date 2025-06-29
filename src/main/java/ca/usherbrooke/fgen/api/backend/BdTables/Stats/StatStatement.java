package ca.usherbrooke.fgen.api.backend.BdTables.Stats;

import java.util.List;

public class StatStatement {
    private int id;
    private String statement;
    private String acronym;
    private List<Integer> idSports;

    public StatStatement(Integer id, String statement, String acronym, List<Integer> idSports) {
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
    public List<Integer> getIdSports() {
        return idSports;
    }

    @Override
    public String toString(){
        return "(" + this.acronym + ")" + this.statement;
    }
}


