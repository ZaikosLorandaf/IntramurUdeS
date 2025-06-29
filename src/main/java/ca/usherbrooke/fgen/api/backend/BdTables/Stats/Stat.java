package ca.usherbrooke.fgen.api.backend.BdTables.Stats;

import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Season;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import io.quarkus.arc.Arc;

import java.util.Map;

public class Stat {
    private int id;
    private StatStatement statStatement;
    private Season season;
    private Match match;
    private String value;

    protected OGClass ogClass;

    public Stat(Integer id, Integer idStatStatement, String value, Integer idMatch, Integer idSeason, Integer idLeague) {
        this.ogClass = Arc.container().instance(OGClass.class).get();
        this.id = id;
        this.statStatement = this.ogClass.getStatStatementSingleton().getStatStatement().getItem(idStatStatement);
        this.value = value;
        if (idSeason != -1) {
            this.season = this.ogClass.getSeasonSingleton().getListSeasons().getSeason(idSeason);
            if (idMatch != -1) {
                this.match = this.season.getLeagues().getLeague(idLeague).getListMatch().getMatch(idMatch);
            }
        }
    }

    public int getId() {
        return this.id;
    }

    public StatStatement getStatStatement() {
        return this.statStatement;
    }

    public Season getSeason() {
        return this.season;
    }

    public Match getMatch() {
        return this.match;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Stat: Id: " + this.id + "; " + this.statStatement + ": " + this.value + "; " + this.season + " " + this.match;
    }
}
