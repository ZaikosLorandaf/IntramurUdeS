package ca.usherbrooke.fgen.api.service;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.OGClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

@Path("/api/dashboard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetDashboard {
    @Inject
    OGClass ogclass;

    @GET
    @Path("nothing")
    public String nothing() {
        return "---";
    }

    @GET
    @Path("equipes")
    public String getEquipesData(
            @QueryParam("sport") String nomSport,
            @QueryParam("ligue") String nomLigue
    ) {
        LoggerUtil.info("Sport reçu : " + nomSport);
        LoggerUtil.info("Ligue reçue : " + nomLigue);

        return "{\n" +
                "  \"A\": {\"joueurs\": \"Jo, Axel, Ana\", \"matchs\": \"3 gagnés, 1 perdu\"},\n" +
                "  \"B\": {\"joueurs\": \"Bruno, Béatrice, Basile\", \"matchs\": \"2 gagnés, 2 perdus\"},\n" +
                "  \"C\": {\"joueurs\": \"Carla, Charles, Chloé\", \"matchs\": \"1 gagné, 3 perdus\"},\n" +
                "  \"D\": {\"joueurs\": \"David, Daphnée, Damien\", \"matchs\": \"4 gagnés, 0 perdu\"}\n" +
                "}";
    }

}
