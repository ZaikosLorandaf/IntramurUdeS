package ca.usherbrooke.fgen.api.service.objectServices;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/api/general")
public class GeneralService {
        @Inject
        SportService sportService;
        @Inject
        LeagueService leagueService;
        @Inject
        TeamService teamService;
        @Inject
        PlayerService playerService;
        @Inject
        MatchService matchService;
        @Inject
        SeasonService seasonService;


        @GET
        public String getAllData() {
            seasonService.getSeasons();
            sportService.getSports();
            leagueService.getLeagues();
            teamService.getTeams();
            playerService.getPlayers();
            matchService.getMatches();
            return "All data gathered";
        }

        public String getAllDataStartup(@Observes StartupEvent event)
        {
            return this.getAllData();
        }


}
