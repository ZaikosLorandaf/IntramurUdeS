package ca.usherbrooke.fgen.api.backend;
import io.quarkus.resteasy.runtime.QuarkusRestPathTemplateInterceptor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class OGClass {
    ListLeague listLeague;
    ListSport listeSport;

    public OGClass() {
        mv = new MonVecteur();
        mv.message += "password = " + mv.password;
        listLeague = new ListLeague();
        listeSport = new ListSport();
        trashData();
    }

    public String getEquipesData(String nomLigue) {
        JSONObject response = new JSONObject();

        League league = listLeague.getLeague(nomLigue);
        if (league == null) {
            return new JSONObject().put("error", "Ligue introuvable").toString();
        }

        ListTeam listTeam = league.getTeams();

        for (int i = 0; i < listTeam.getSize(); i++) {
            Team team = listTeam.getTeam(i);

            List<String> joueurs = new ArrayList<>();
            for (int j = 0; j < team.getListPlayer().getSize(); j++) {
                Player p = team.getListPlayer().getPlayer(j);
                joueurs.add(p.getName());
            }

            String joueursStr = String.join(", ", joueurs);
            String matchsStr = "Statistiques fictives";

            JSONObject infoEquipe = new JSONObject()
                    .put("joueurs", joueursStr)
                    .put("matchs", matchsStr);

            response.put(team.getName(), infoEquipe);
        }

        return response.toString();
    }


    public String getSportLeague() {
        JSONArray sports = new JSONArray();

        for (int i = 0; i < listeSport.getSize(); i++) {
            Sport sport = listeSport.getSport(i);

            // Récupère les noms des ligues de ce sport
            ListLeague leagues = sport.getListLeague();
            List<String> leagueNames = new ArrayList<>();
            for (int j = 0; j < leagues.getSize(); j++) {
                leagueNames.add(leagues.getLeagueByIndex(j).getName());
            }

            // Ajoute le sport et ses ligues dans le JSON
            JSONObject sportJson = new JSONObject()
                    .put("name", sport.getName())
                    .put("id", sport.getId())
                    .put("seasons", new JSONArray(leagueNames));

            sports.put(sportJson);
        }

        return sports.toString();
    }


    public void trashData(){
        Sport bb = new Sport("Basket Ball", -1);
        bb.addLeague(new League("Tintin au congo"));
        bb.addLeague(new League("Les pythoniste"));
        listeSport.addSport(bb);

        Sport vb = new Sport("Volley Ball", -2);
        vb.addLeague(new League("Les coucouille de Gerard"));
        vb.addLeague(new League("Les c++ teams"));
        listeSport.addSport(vb);

    }

    public String newLeague(String nom) {

        League newLeague = new League(nom);
        boolean resultAdd = listLeague.addLeague(newLeague);
        String result;
        if (!resultAdd) {
            result = "<div>erreur</div>";
        }
        else {
            result = "<div>";
            result += listLeague.getLeagueByIndex(listLeague.getSize()-1).getName();

        }
        return result;
//        return "<div> coco</div>";
    }

    public String getLeague(String nom) {
        League league = listLeague.getLeague(nom);
        String result = "";
        if(league != null) {
            result = league.getName();
            System.out.println("League: " + result);
        }
        else {
            result = "Pas de ligue appelé " + nom;
        }
        //result = "<div>" + result +  "</div>";
        //String result = "<div> aaaaa </div>";
        return result;
    }

    public String removeLeague(String nom) {
        League league = listLeague.getLeague(nom);
        String result = "";
        if(league != null) {
            result = "Ligue retirée :" + league.getName();
            listLeague.removeLeague(league);
            System.out.println("Ligue retirée: " + result);
        }
        else
        {
            result = "Pas de ligue appelé " + nom;
        }
        //result = "<div>" + result +  "</div>";
        //String result = "<div> aaaaa </div>";
        return result;
    }


    public String getMessage() {
        return mv.message;
    }

    public String addTeam(String nomLigue, String nomEquipe) {
        String result;
        League league = listLeague.getLeague(nomLigue);
        if(league == null) {
            result = "Ligue introuvable";
        } else {
            if (league.newTeam(nomEquipe)) {
                result = "Équipe ajoutée";
            }
            else {
                result = "Impossible d'ajouter l'équipe";
            }
        }

        return result;
    }

    public String listTeam(String nomLigue)
    {
        String result = "";
        League league = listLeague.getLeague(nomLigue);
        if(league == null) {
            result = "Ligue introuvable";
        }
        else{
            if (league.getTeams().getSize() <= 0) {
                result = "Pas d'équipe";
            }
            else {
                for(int i = 0; i<league.getTeams().getSize(); i++) {
                    result += league.getTeams().getTeam(i).getName() + "</br>";
                }
            }
        }
        return result;
    }


    public String listLeague() {
        String result = "";
        if(listLeague.getSize() <= 0) {
            return "<div>Pas de ligue</div>";
        }

        for (int i = 0; i < listLeague.getSize(); i++) {
            result += listLeague.getLeagueByIndex(i).getName() + "</br>";
        }

        return result;
    }



    public String removeTeam(String nomLigue, String nomEquipe) {
        if(listLeague.getSize() <= 0) {
            return "<div>Pas de ligue</div>";
        }

        Team team = listLeague.getLeague(nomLigue).getTeams().getTeam(nomEquipe);
        if (team == null) {
            return "<div>Pas d'équipe</div>";
        }

        if (listLeague.getLeague(nomLigue).removeTeam(team)) {
            return "<div>Équipe retirée</div>";
        }

        return "<div>Erreur lors du retrait d'équipe</div>";
    }

    public String addPlayer(String nomLigue, String nomEquipe,String prenomJoueur, String nomJoueur) {
        League league = listLeague.getLeague(nomLigue);
        if(league == null) {
            return "<div>Pas de ligue</div>";
        }
        Team team = league.getTeams().getTeam(nomEquipe);
        if (team == null) {
            return "<div>Pas d'équipe</div>";
        }
        if (team.newPlayer(prenomJoueur, nomJoueur)) {
            return "<div> Joueur : " + prenomJoueur + " " + nomJoueur + " ajouté</div>";
        }
        return "<div>Erreur nom</div>";
    }

    public String listPlayer(String nomLigue, String nomEquipe) {
        String result = "";
        League league = listLeague.getLeague(nomLigue);
        if(league == null) {
            result = "Ligue introuvable";
        }
        else{
            Team team = league.getTeams().getTeam(nomEquipe);
            if (team == null) {
                result = "Equipe introuvable";
            }
            else {
                if (team.getListPlayer().getSize() <= 0) {
                    result = "Pas de joueur";
                }
                for(int i = 0; i<team.getListPlayer().getSize(); i++) {
                    Player player = team.getListPlayer().getPlayer(i);
                    result += player.getName() +" "+ player.getLastName()+ "</br>";
                }
            }
        }
        return result;
    }

    public String removePlayer(String nomLigue, String nomEquipe, String prenomJoueur, String nomJoueur) {
        League league = listLeague.getLeague(nomLigue);
        if(league == null) {
            return "<div>Ligue non-trouvée</div>";
        }

        Team team = league.getTeams().getTeam(nomEquipe);
        if (team == null) {
            return "<div>Équipe non-trouvée</div>";
        }

        Player player = team.getListPlayer().getPlayer(prenomJoueur,nomJoueur);
        if(player == null) {
            return "<div>Joueur non-trouvé</div>";
        }

        if (team.removePlayer(player)) {
            return "<div>Joueur retiré</div>";
        }

        return "<div>Erreur lors du retrait du joueur</div>";
    }
    private MonVecteur mv;
}
