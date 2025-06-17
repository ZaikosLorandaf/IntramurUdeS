package ca.usherbrooke.fgen.api.service;
import ca.usherbrooke.fgen.api.service.postClass.addLeague;
import ca.usherbrooke.fgen.api.service.postClass.addSport;
import ca.usherbrooke.fgen.api.service.postClass.addTeam;
import ca.usherbrooke.fgen.api.service.postClass.addPlayer;
import io.smallrye.common.constraint.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyAPI {

    @Inject
    OGClass ogClass;



    /*@GET
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
     *
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
    }*/

    // ~~~~~~~~~~~~ Sports ~~~~~~~~~~ //
//    @GET
//    @Path("listSport/")
//    public String getListSport() {
//        return ogClass.listSport();
//    }

    @POST
    @Path("addSport")
    public String addSport(@NotNull addSport sport) {
        return ogClass.sportSingleton().add(sport.nom);
    }

    @GET
    @Path("getSport/{nom_sport}")
    public String getSport(
            @PathParam("nom_sport") String nom_sport) {
        return ogClass.sportSingleton().getSport(nom_sport);
    }

    @POST
    @Path("removeSport/{nom_sport}")
    public String removeLeague(
            @PathParam("nom_sport") String nomSport) {
        nomSport = nomSport.replace("%20", " ");
        return ogClass.sportSingleton().remove(nomSport);
    }

    // ~~~~~~~~~~~ Leagues ~~~~~~~~~~ //
    @GET
    @Path("listLigue/{nom_sport}")
    public String getListeLigue(@PathParam("nom_sport") String nom_sport) {
        nom_sport = nom_sport.replace("%20", " ");
        return ogClass.leagueSingleton().listLeague(nom_sport);
    }


    @POST
    @Path("addLigue/")
    public String addLeague(@NotNull addLeague league) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_debut;
        Date date_fin;
        try {
            java.util.Date date_debutParsed = dateFormat.parse(league.date_debut);
            java.util.Date date_finParsed = dateFormat.parse(league.date_fin);

            date_debut = new Date(date_debutParsed.getTime());
            date_fin = new Date(date_finParsed.getTime());
        } catch (ParseException e) {
            return "Invalid date format: " + e.getMessage();
        }
        return ogClass.leagueSingleton().add(league.nom_sport, league.nom,date_debut,date_fin);
   }

    @GET
    @Path("getLigue/{sport}/{nom}")
    public String getLeague(
            @PathParam("sport") String sport,
            @PathParam("nom") String nom) {
        sport = sport.replace("%20", " ");
        nom = nom.replace("%20", " ");
        return ogClass.leagueSingleton().getLeague(sport, nom);
    }

    @GET
    @Path("removeLigue/{nom_sport}/{nom_ligue}")
    public String removeLeague(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue) {
        nomSport = nomSport.replace("%20", " ");
        nomLigue = nomLigue.replace("%20", " ");
        return ogClass.leagueSingleton().remove(nomSport, nomLigue);
    }


    // ~~~~~~~~~~~~ Teams ~~~~~~~~~~~ //
    @POST
    @Path("addTeam")
    public String getAddTeam(@NotNull addTeam team) {
        return ogClass.teamSingleton().add(team.nomSport, team.nomLigue, team.nomTeam);
    }

    @GET
    @Path("getTeams/{nom_sport}/{ligue}")
    public String getTeams(
            @PathParam("nom_sport") String nom_sport,
            @PathParam("ligue") String ligue) {
        nom_sport = nom_sport.replace("%20", " ");
        ligue = ligue.replace("%20", " ");
        return ogClass.teamSingleton().getTeams(nom_sport,ligue);
    }

    @GET
    @Path("listTeam/{nom_sport}/{nom_ligue}")
    public String listTeam(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nom_ligue) {
        nomSport = nomSport.replace("%20", " ");
        nom_ligue = nom_ligue.replace("%20", " ");
        return ogClass.teamSingleton().listTeam(nomSport,nom_ligue);
    }

    @GET
    @Path("removeTeam/{nom_sport}/{nom_ligue}/{nom_equipe}")
    public String removeTeam(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nom_ligue,
            @PathParam("nom_equipe") String nom_equipe) {
        nomSport = nomSport.replace("%20", " ");
        nom_ligue = nom_ligue.replace("%20", " ");
        nom_equipe = nom_equipe.replace("%20", " ");
        return ogClass.teamSingleton().remove(nomSport, nom_ligue,nom_equipe);
    }


    // ~~~~~~~~~~~ Players ~~~~~~~~~~ //
    @POST
    @Path("addPlayer")
    public String addPlayer(@NotNull addPlayer player) {
        int number = player.number;
        String result =  ogClass.playerSingleton().add(player.nomSport,player.nomLigue,player.nomTeam,player.prenom, player.nom, player.number);
        return result;
    }

    @GET
    @Path("listPlayer/{nom_sport}/{nom_ligue}/{nom_equipe}")
    public String listPlayer(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe) {
        nomSport = nomSport.replace("%20", " ");
        nomLigue = nomLigue.replace("%20", " ");
        nomEquipe = nomEquipe.replace("%20", " ");
        return ogClass.playerSingleton().listPlayer(nomSport,nomLigue,nomEquipe);
    }

    @GET
    @Path("removePlayer/{nom_sport}/{nom_ligue}/{nom_equipe}/{number_player}")
    public String removePlayer(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe,
            @PathParam("number_player") int numberPlayer) {
        nomSport = nomSport.replace("%20", " ");
        nomLigue = nomLigue.replace("%20", " ");
        nomEquipe = nomEquipe.replace("%20", " ");
        return ogClass.playerSingleton().remove(nomSport,nomLigue,nomEquipe,numberPlayer);
    }

    

    @GET
    @Path("listSport")
    public String listSport() {
        return ogClass.sportSingleton().getSports();
    }

}
