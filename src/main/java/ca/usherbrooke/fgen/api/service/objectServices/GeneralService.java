package ca.usherbrooke.fgen.api.service.objectServices;

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
        public String getAllData() {
            sportService.getSports();
            leagueService.getLeagues();
            teamService.getTeams();
            playerService.getPlayers();
            return "All data gathered";
        }


}
