package ca.usherbrooke.fgen.api.backend.BdTables.Stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Season;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListStatStatement;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.arc.Arc;

import javax.annotation.PostConstruct;
import java.util.Map;

public class Stat {
    private int id;
    protected int idStatStatement;
    private StatStatement statStatement;
    private int idSeason;
    private Season season;
    private int idMatch;
    private Match match;
    private String value;
    private int idLeague;
    private League league;
    private int idSport;



    protected OGClass ogClass;


    public Stat() {
        this.ogClass = Arc.container().instance(OGClass.class).get();
    }


    public Stat(Integer id, Integer idStatStatement, String value, Integer idMatch, Integer idSeason, Integer idLeague,
                Integer idSport) {
        this.ogClass = Arc.container().instance(OGClass.class).get();
        this.id = id;
        this.statStatement = this.ogClass.getStatStatementSingleton().getStatStatement().getItem(idStatStatement);
        this.value = value;
        this.idMatch = idMatch;
        this.idSeason = idSeason;
        this.idLeague = idLeague;
        this.idSport = idSport;
        if (idSeason != -1) {
            this.season = this.ogClass.getSeasonSingleton().getListSeasons().getSeason(idSeason);
            if (idMatch != -1) {
                this.match = this.season.getLeagues().getLeague(idLeague).getListMatch().getMatch(idMatch);
            }
        }
    }

    @PostConstruct
    public void initFromId(){
        ListStatStatement listTemp = this.ogClass.getStatStatementSingleton().getStatStatement();
        this.statStatement = this.ogClass.getStatStatementSingleton().getStatStatement().getItem(idStatStatement);
        if (this.idSeason != -1) {
            this.season = this.ogClass.getSeasonSingleton().getListSeasons().getSeason(this.idSeason);
            if(this.idLeague != -1) {
                this.league = this.season.getLeagues().getLeague(idLeague);
                if (idMatch != -1) {
                    this.match = this.league.getListMatch().getMatch(idMatch);
                }
            }
        }
    }


    public int getId() {
        return this.id;
    }


    public StatStatement getStatStatement() {
        return this.statStatement;
    }

    @JsonIgnore
    public Season getSeason() {
        return this.season;
    }

    @JsonIgnore
    public Match getMatch() {
        return this.match;
    }

    @JsonIgnore
    public Sport getSport(){
        return this.ogClass.getSportSingleton().getSportList().getSport(this.idSport);
    }

    @JsonIgnore
    public League getLeague() {
        return this.league;
    }


    public String getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return "Stat: Id: " + this.id + "; " + this.statStatement + ": " + this.value + "; " + this.season + " " + this.match;
    }

    public int getIdStatStatement() {
        return this.idStatStatement;
    }


}
