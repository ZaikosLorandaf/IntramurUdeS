package ca.usherbrooke.fgen.api.service;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ca.usherbrooke.fgen.api.backend.MaClasseOG;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MonAPI {

    @Inject
    MaClasseOG monService;

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
}
