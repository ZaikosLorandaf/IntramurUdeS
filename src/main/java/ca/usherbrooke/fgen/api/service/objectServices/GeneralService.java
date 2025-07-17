package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.service.objectServices.stats.StatPlayerService;
import ca.usherbrooke.fgen.api.service.objectServices.stats.StatStatementService;
import ca.usherbrooke.fgen.api.service.objectServices.stats.StatTeamService;
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
        @Inject
        StatStatementService statStatementService;
        @Inject
        StatPlayerService statPlayerService;
        @Inject
        StatTeamService statTeamService;


        @GET
        public String getAllData() {
            statStatementService.getItems();
            seasonService.getItems();
            sportService.getItems();
            leagueService.getItems();
            teamService.getItems();
            playerService.getItems();
            matchService.getItems();
            playerService.getItems();
            statPlayerService.getItems();
            statTeamService.getItems();
            return "All data gathered";
        }

        public String getAllDataStartup(@Observes StartupEvent event)
        {
            return this.getAllData();
        }


}
