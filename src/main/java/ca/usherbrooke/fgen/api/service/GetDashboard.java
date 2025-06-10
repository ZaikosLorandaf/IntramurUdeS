package ca.usherbrooke.fgen.api.service;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.OGClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

/*@Path("/api/dashboard")
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

        return ogClass.getEquipesData(nomSport, nomLigue);
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
    @Path("matchs")
    public String getAllMatchs(
            @QueryParam("sport") String nomSport,
            @QueryParam("ligue") String nomLigue
    ) {
        LoggerUtil.info("Sport reçu : " + nomSport);
        return """
        {
          "2026-05-21": [
            {
              "id": 1,
              "heure": "10:00",
              "equipes": "Équipe A vs Équipe B",
              "lieu": "Gymnase 1"
            },
            {
              "id": 2,
              "heure": "14:30",
              "equipes": "Équipe C vs Équipe D",
              "lieu": "Gymnase 2"
            }
          ],
          "2025-05-25": [
            {
              "id": 3,
              "heure": "09:00",
              "equipes": "Équipe A vs Équipe D",
              "lieu": "Gymnase 1"
            },
            {
              "id": 4,
              "heure": "11:00",
              "equipes": "Équipe B vs Équipe C",
              "lieu": "Gymnase 3"
            },
            {
              "id": 5,
              "heure": "16:00",
              "equipes": "Équipe A vs Équipe C",
              "lieu": "Extérieur"
            }
          ],
          "2025-05-28": [
            {
              "id": 6,
              "heure": "13:00",
              "equipes": "Équipe D vs Équipe B",
              "lieu": "Gymnase 2"
            }
          ]
        }
        """;
    }

}*/
