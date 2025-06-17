package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import ca.usherbrooke.fgen.api.mapper.TeamMapper;
import ca.usherbrooke.fgen.api.service.objectServices.LeagueService;
import ca.usherbrooke.fgen.api.service.objectServices.PlayerService;
import ca.usherbrooke.fgen.api.service.objectServices.SportService;
import ca.usherbrooke.fgen.api.service.objectServices.TeamService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OGClass {
    @Inject
    ListSport sportList;

    @Inject
    SportSingleton sportSingleton;
    @Inject
    LeagueSingleton leagueSingleton;
    @Inject
    MatchSingleton matchSingleton;
    @Inject
    TeamSingleton teamSingleton;
    @Inject
    PlayerSingleton playerSingleton;

    public OGClass() {
        LoggerUtil.info("Création de OGClass terminée.");
    }

    public SportSingleton sportSingleton(){
        return sportSingleton;
    }
    public LeagueSingleton leagueSingleton(){
        return leagueSingleton;
    }
    public MatchSingleton matchSingleton(){
        return matchSingleton;
    }
    public TeamSingleton teamSingleton(){
        return teamSingleton;
    }
    public PlayerSingleton playerSingleton(){
        return playerSingleton;
    }
}
