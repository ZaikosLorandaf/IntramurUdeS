package ca.usherbrooke.fgen.api.service;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ca.usherbrooke.fgen.api.backend.OGClass;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonAPI {

    @Inject
    OGClass monService;

    @GET
    @Path("monendpoint")
    public String getMonMessage() {
        return monService.getMessage();
    }

    @GET
    @Path("monendpointJojojo")
    public String getMonMessageJojojo() {
        return "<div>Coucou</div>";
    }


    @GET
    @Path("liste_equipe")
    public String getListeEquipe() {
        return " ";
    }

    /*
    * Retourne les informations sur une équipe
     */
    @GET
    @Path("data_equipe")
    public String getDataEquipe() {
        //Passer en parametre L'equipe
        return "coucouille";

    }

    @GET
    @Path("liste_date_match")
    public String getListeDateMatch() {
        return "";
    }


    @GET
    @Path("liste_match_pour_date")
    public String getListeMatchPourDate() {

        return "coucouille";
    }


    @GET
    @Path("addLigue/{nom}")
    public String addLeague(@PathParam("nom") String nom) {
        return monService.newLeague(nom);
    }

    @GET
    @Path("listLigue")
    public String getLeague() {
        return monService.getLeague();
    }


    @GET
    @Path("get_sport_ligue")
    public String getSportLeague() {
        JSONArray sports = new JSONArray();

        sports.put(new JSONObject()
                .put("name", "Volleyball")
                .put("id", "volleyball")
                .put("seasons", new JSONArray(List.of("Été 2025", "Hiver 2025", "Saison précédente")))
        );

        sports.put(new JSONObject()
                .put("name", "Soccer")
                .put("id", "soccer")
                .put("seasons", new JSONArray(List.of("Été 2025", "Hiver 2025", "Saison précédente")))
        );

        sports.put(new JSONObject()
                .put("name", "Basketball")
                .put("id", "basket")
                .put("seasons", new JSONArray(List.of("Été 2025", "Hiver 2025", "Saison précédente")))
        );

        return sports.toString();
    }
}
