package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OGClass {
    @Inject
    ListSport sportList;

    private SportSingleton sportSingleton;
    private LeagueSingleton leagueSingleton;
    private MatchSingleton matchSingleton;
    private TeamSingleton teamSingleton;
    private PlayerSingleton playerSingleton;
    private SeasonSingleton seasonSingleton;

    public OGClass() {
        LoggerUtil.info("Création de OGClass terminée.");
    }

    @PostConstruct
    public void init() {
        sportSingleton = new SportSingleton();
        leagueSingleton = new LeagueSingleton();
        matchSingleton = new MatchSingleton();
        teamSingleton = new TeamSingleton();
        playerSingleton = new PlayerSingleton();
        seasonSingleton = new SeasonSingleton();
    }

    public SportSingleton getSportSingleton(){
        return sportSingleton;
    }
    public LeagueSingleton getLeagueSingleton(){
        return leagueSingleton;
    }
    public MatchSingleton getMatchSingleton(){
        return matchSingleton;
    }
    public TeamSingleton getTeamSingleton(){
        return teamSingleton;
    }
    public PlayerSingleton getPlayerSingleton(){
        return playerSingleton;
    }
    public SeasonSingleton getSeasonSingleton(){
        return seasonSingleton;
    }
}
