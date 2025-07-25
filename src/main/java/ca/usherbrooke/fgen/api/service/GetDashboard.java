package ca.usherbrooke.fgen.api.service;

import ca.usherbrooke.fgen.api.backend.DTO.TeamDTO;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.service.objectServices.TeamService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import java.util.List;

@Path("/api/dashboard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetDashboard {
    @Inject
    OGClass ogClass;
    @Inject
    TeamService teamService;

    @GET
    @Path("nothing")
    public String nothing() {
        return "---";
    }

    @GET
    @Path("equipes")
    public List<TeamDTO> getEquipesData(
            @QueryParam("sport") String nomSport,
            @QueryParam("ligue") String nomLigue
    ) {
//        LoggerUtil.info("Sport reçu : " + nomSport + " pour les équipes");
//        LoggerUtil.info("Ligue reçue : " + nomLigue + " pour les équipes");

        return teamService.getTeams(nomSport, nomLigue);
        //return ogClass.getTeamSingleton().getEquipesData(nomSport, nomLigue);
//        return "{\n" +
//                "  \"A\": {\n" +
//                "    \"joueurs\": {\n" +
//                "      \"Olivier\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 0, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"},\n" +
//                "      \"Remi\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 1, \"passes\": 3, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"},\n" +
//                "      \"Ana\": {\"role\": \"Gardienne\", \"matchsJoues\": 4, \"buts\": 0, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 2, \"blessures\": 0, \"remarques\": \"\"}\n" +
//                "    },\n" +
//                "    \"matchs\": \"3 gagnés, 1 perdu\",\n" +
//                "    \"stats\": {\n" +
//                "      \"matchsJoues\": 4,\n" +
//                "      \"victoires\": 3,\n" +
//                "      \"defaites\": 1,\n" +
//                "      \"pointsMarques\": 89,\n" +
//                "      \"pointsEncaisses\": 65,\n" +
//                "      \"differenceDePoints\": 24\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"B\": {\n" +
//                "    \"joueurs\": {\n" +
//                "      \"Bruno\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 1, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"},\n" +
//                "      \"Béatrice\": {\"role\": \"Capitaine\", \"matchsJoues\": 4, \"buts\": 0, \"passes\": 2, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"Capitaine\"},\n" +
//                "      \"Basile\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 1, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 1, \"remarques\": \"\"}\n" +
//                "    },\n" +
//                "    \"matchs\": \"2 gagnés, 2 perdus\",\n" +
//                "    \"stats\": {\n" +
//                "      \"matchsJoues\": 4,\n" +
//                "      \"victoires\": 3,\n" +
//                "      \"defaites\": 1,\n" +
//                "      \"pointsMarques\": 89,\n" +
//                "      \"pointsEncaisses\": 65,\n" +
//                "      \"differenceDePoints\": 24\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"C\": {\n" +
//                "    \"joueurs\": {\n" +
//                "      \"Carla\": {\"role\": \"Joueuse\", \"matchsJoues\": 4, \"buts\": 1, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"},\n" +
//                "      \"Charles\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 0, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 1, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"},\n" +
//                "      \"Chloé\": {\"role\": \"Joueuse\", \"matchsJoues\": 4, \"buts\": 0, \"passes\": 2, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"}\n" +
//                "    },\n" +
//                "    \"matchs\": \"1 gagné, 3 perdus\",\n" +
//                "    \"stats\": {\n" +
//                "      \"matchsJoues\": 4,\n" +
//                "      \"victoires\": 3,\n" +
//                "      \"defaites\": 1,\n" +
//                "      \"pointsMarques\": 89,\n" +
//                "      \"pointsEncaisses\": 65,\n" +
//                "      \"differenceDePoints\": 24\n" +
//                "    }\n" +
//                "  },\n" +
//                "  \"D\": {\n" +
//                "    \"joueurs\": {\n" +
//                "      \"David\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 3, \"passes\": 0, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"Meilleur joueur\"},\n" +
//                "      \"Daphnée\": {\"role\": \"Joueuse\", \"matchsJoues\": 4, \"buts\": 1, \"passes\": 1, \"cartonsJaunes\": 0, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"},\n" +
//                "      \"Damien\": {\"role\": \"Joueur\", \"matchsJoues\": 4, \"buts\": 2, \"passes\": 0, \"cartonsJaunes\": 1, \"cartonsRouges\": 0, \"arrets\": 0, \"blessures\": 0, \"remarques\": \"\"}\n" +
//                "    },\n" +
//                "    \"matchs\": \"4 gagnés, 0 perdu\",\n" +
//                "    \"stats\": {\n" +
//                "      \"matchsJoues\": 1,\n" +
//                "      \"victoires\": 2,\n" +
//                "      \"defaites\": 3,\n" +
//                "      \"pointsMarques\": 4,\n" +
//                "      \"pointsEncaisses\": 65,\n" +
//                "      \"differenceDePoints\": 24\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
    }


    @GET
    @Path("matchs/{sport}/{ligue}")

    public String getAllMatches(
            @PathParam("sport") String nomSport,
            @PathParam("ligue") String nomLigue
    ) {
        LoggerUtil.info("Sport reçu : " + nomSport + " pour les matchs");
        LoggerUtil.info("Ligue reçu : " + nomLigue + " pour les matchs");
        return ogClass.getMatchSingleton().getMatchesData(nomSport, nomLigue);
    }

}
