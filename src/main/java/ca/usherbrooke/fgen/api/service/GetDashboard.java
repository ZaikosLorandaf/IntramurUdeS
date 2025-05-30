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
    OGClass ogClass;

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
                "  \"A\": {\n" +
                "    \"joueurs\": \"Olivier, Remi, Ana\",\n" +
                "    \"matchs\": \"3 gagnés, 1 perdu\",\n" +
                "    \"stats\": {\n" +
                "      \"matchsJoues\": 4,\n" +
                "      \"victoires\": 3,\n" +
                "      \"defaites\": 1,\n" +
                "      \"pointsMarques\": 89,\n" +
                "      \"pointsEncaisses\": 65,\n" +
                "      \"differenceDePoints\": 24\n" +
                "    }\n" +
                "  },\n" +
                "  \"B\": {\n" +
                "    \"joueurs\": \"Bruno, Béatrice, Basile\",\n" +
                "    \"matchs\": \"2 gagnés, 2 perdus\",\n" +
                "    \"stats\": {\n" +
                "      \"matchsJoues\": 4,\n" +
                "      \"victoires\": 3,\n" +
                "      \"defaites\": 1,\n" +
                "      \"pointsMarques\": 89,\n" +
                "      \"pointsEncaisses\": 65,\n" +
                "      \"differenceDePoints\": 24\n" +
                "    }\n" +
                "  },\n" +
                "  \"C\": {\n" +
                "    \"joueurs\": \"Carla, Charles, Chloé\",\n" +
                "    \"matchs\": \"1 gagné, 3 perdus\",\n" +
                "    \"stats\": {\n" +
                "      \"matchsJoues\": 4,\n" +
                "      \"victoires\": 3,\n" +
                "      \"defaites\": 1,\n" +
                "      \"pointsMarques\": 89,\n" +
                "      \"pointsEncaisses\": 65,\n" +
                "      \"differenceDePoints\": 24\n" +
                "    }\n" +
                "  },\n" +
                "  \"D\": {\n" +
                "    \"joueurs\": \"David, Daphnée, Damien\",\n" +
                "    \"matchs\": \"4 gagnés, 0 perdu\",\n" +
                "    \"stats\": {\n" +
                "      \"matchsJoues\": 4,\n" +
                "      \"victoires\": 3,\n" +
                "      \"defaites\": 1,\n" +
                "      \"pointsMarques\": 89,\n" +
                "      \"pointsEncaisses\": 65,\n" +
                "      \"differenceDePoints\": 24\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

}
