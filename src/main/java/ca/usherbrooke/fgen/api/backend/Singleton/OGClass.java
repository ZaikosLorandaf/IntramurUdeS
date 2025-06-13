package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.Lists.ListSeason;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OGClass {
    @Inject
    ListSport sportList;

    ListSeason listSeasons;

    public final SportSingleton sportSingleton = new SportSingleton();
    public final LeagueSingleton leagueSingleton = new LeagueSingleton();
    public final MatchSingleton matchSingleton = new MatchSingleton();
    public final TeamSingleton teamSingleton = new TeamSingleton();
    public final PlayerSingleton playerSingleton = new PlayerSingleton();

    public OGClass() {
        sportList = new ListSport();
        LoggerUtil.info("Création de OGClass terminée.");
    }
}
