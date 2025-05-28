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
    @Path("listLigue/{nom_sport}")
    public String getListeLigue(@PathParam("nom_sport") String nom_sport) {
        return monService.listLeague(nom_sport);
    }


    @GET
    @Path("addLigue/{sport}/{nom}")
    public String addLeague(
            @PathParam("sport") String sport,
            @PathParam("nom") String nom) {
        return monService.newLeague(sport, nom);
    }

    @GET
    @Path("getLigue/{sport}/{nom}")
    public String getLeague(
            @PathParam("sport") String sport,
            @PathParam("nom") String nom) {
        return monService.getLeague(sport, nom);
    }

    @GET
    @Path("removeLigue/{nom_sport}/{nom_ligue}")
    public String removeLeague(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue) {
        return monService.removeLeague(nomSport, nomLigue);
    }


    @GET
    @Path("addTeam/{nom_sport}/{nom_ligue}/{nom_equipe}")
    public String getAddTeam(
            @PathParam("nom_sport") String sport,
            @PathParam("nom_ligue") String nom_ligue,
            @PathParam("nom_equipe") String nom_equipe) {
        return monService.addTeam(sport, nom_ligue,nom_equipe);
    }

    @GET
    @Path("listTeam/{nom_sport}/{nom_ligue}")
    public String listTeam(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nom_ligue) {
        return monService.listTeam(nomSport,nom_ligue);
    }

    @GET
    @Path("removeTeam/{nom_sport}/{nom_ligue}/{nom_equipe}")
    public String removeTeam(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nom_ligue,
            @PathParam("nom_equipe") String nom_equipe)
    {
        return monService.removeTeam(nomSport, nom_ligue,nom_equipe);
    }


    @GET
    @Path("addPlayer/{nom_sport}/{nom_ligue}/{nom_equipe}/{prenom_joueur}/{nom_joueur}")
    public String addPlayer(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe,
            @PathParam("nom_joueur") String nomJoueur,
            @PathParam("prenom_joueur") String prenomJoueur
    )
    {
        return monService.addPlayer(nomSport,nomLigue,nomEquipe,prenomJoueur, nomJoueur);
    }

    @GET
    @Path("listPlayer/{nom_sport}/{nom_ligue}/{nom_equipe}")
    public String listPlayer(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe)
    {
        return monService.listPlayer(nomSport,nomLigue,nomEquipe);
    }

    @GET
    @Path("removePlayer/{nom_sport}/{nom_ligue}/{nom_equipe}/{number_player}")
    public String removePlayer(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe,
            @PathParam("number_player") int numberPlayer)
    {
        return monService.removePlayer(nomSport,nomLigue,nomEquipe,numberPlayer);
    }


    @GET
    @Path("getTeams/{sport}/{ligue}")
    public String getTeams(
            @PathParam("sport") String sport,
            @PathParam("ligue") String ligue)
    {
        return monService.getTeams(sport,ligue);
    }
}
