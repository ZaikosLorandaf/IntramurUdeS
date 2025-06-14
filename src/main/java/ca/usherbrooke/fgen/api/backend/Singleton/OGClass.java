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

    public SportSingleton sportSingleton;
    public LeagueSingleton leagueSingleton;
    public MatchSingleton matchSingleton;
    public TeamSingleton teamSingleton;
    public PlayerSingleton playerSingleton;

    public OGClass() {
        sportList = new ListSport();
        sportSingleton = new SportSingleton(sportList);
        leagueSingleton = new LeagueSingleton(sportList);
        matchSingleton = new MatchSingleton(sportList);
        teamSingleton = new TeamSingleton(sportList);
        playerSingleton = new PlayerSingleton(sportList);
        LoggerUtil.info("Création de OGClass terminée.");
    }
}
