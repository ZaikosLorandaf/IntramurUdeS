package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.StatStatement;
import ca.usherbrooke.fgen.api.backend.Lists.ListStatStatement;

public class StatStatementSingleton {
    ListStatStatement listStatStatement;

    StatStatementSingleton(){
        this.listStatStatement = new ListStatStatement();
    };

    public ListStatStatement getStatStatement() {
        return this.listStatStatement;
    }
}
