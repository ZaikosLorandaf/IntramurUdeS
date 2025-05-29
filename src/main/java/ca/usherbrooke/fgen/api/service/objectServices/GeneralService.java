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


        @GET
        public String getAllData(@Observes StartupEvent event) {
            sportService.getSports();
            leagueService.getLeagues();
            teamService.getTeams();
            playerService.getPlayers();
            return "All data gathered";
        }


}
