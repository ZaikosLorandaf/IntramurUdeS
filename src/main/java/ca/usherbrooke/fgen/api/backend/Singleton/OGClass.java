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

    private SportSingleton sportSingleton;
    private LeagueSingleton leagueSingleton;
    private MatchSingleton matchSingleton;
    private TeamSingleton teamSingleton;
    private PlayerSingleton playerSingleton;

    public OGClass() {
        LoggerUtil.info("Création de OGClass terminée.");
    }

    @PostConstruct
    public void init() {
        sportSingleton = new SportSingleton(sportList);
        leagueSingleton = new LeagueSingleton(sportList);
        matchSingleton = new MatchSingleton(sportList);
        teamSingleton = new TeamSingleton(sportList);
        playerSingleton = new PlayerSingleton(sportList);
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
}
