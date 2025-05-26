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
    @Path("listLigue")
    public String getListeLigue() {
        return monService.listLeague();
    }


    @GET
    @Path("addLigue/{nom}")
    public String addLeague(@PathParam("nom") String nom) {
        return monService.newLeague(nom);
    }

    @GET
    @Path("getLigue/{nom}")
    public String getLeague(@PathParam("nom") String nom) {
        return monService.getLeague(nom);
    }

    @GET
    @Path("removeLigue/{nom}")
    public String removeLeague(@PathParam("nom") String nom) {
        return monService.removeLeague(nom);
    }


    @GET
    @Path("addTeam/{nom_ligue}/{nom_equipe}")
    public String getAddTeam(
            @PathParam("nom_ligue") String nom_ligue,
            @PathParam("nom_equipe") String nom_equipe) {
        return monService.addTeam(nom_ligue,nom_equipe);
    }

    @GET
    @Path("listTeam/{nom_ligue}")
    public String listTeam(@PathParam("nom_ligue") String nom_ligue) {
        return monService.listTeam(nom_ligue);
    }

    @GET
    @Path("removeTeam/{nom_ligue}/{nom_equipe}")
    public String removeTeam(
            @PathParam("nom_ligue") String nom_ligue,
            @PathParam("nom_equipe") String nom_equipe)
    {
        return monService.removeTeam(nom_ligue,nom_equipe);
    }


    @GET
    @Path("addPlayer/{nom_ligue}/{nom_equipe}/{prenom_joueur}/{nom_joueur}")
    public String addPlayer(
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe,
            @PathParam("nom_joueur") String nomJoueur,
            @PathParam("prenom_joueur") String prenomJoueur
    )
    {
        return monService.addPlayer(nomLigue,nomEquipe,prenomJoueur, nomJoueur);
    }

    @GET
    @Path("listPlayer/{nom_ligue}/{nom_equipe}")
    public String listPlayer(
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe)
    {
        return monService.listPlayer(nomLigue,nomEquipe);
    }

    @GET
    @Path("removePlayer/{nom_ligue}/{nom_equipe}/{prenom_joueur}/{nom_joueur}")
    public String removePlayer(
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe,
            @PathParam("nom_joueur") String nomJoueur,
            @PathParam("prenom_joueur") String prenomJoueur)
    {
        return monService.removePlayer(nomLigue,nomEquipe,prenomJoueur,nomJoueur);
    }
}
