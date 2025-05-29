package ca.usherbrooke.fgen.api.backend;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OGClass {
    @Inject
    ListSport sportList;

    public OGClass() {

        sportList = new ListSport();
        LoggerUtil.info("Création de OGClass terminée.");
    }

    /**
     * Getter pour la listeSport
     *
     * @return L'objet ListeSport
     */
    public ListSport getSportList() {
        return this.sportList;
    }

    public String getSports() {
        System.out.println(sportList.getMapSports());
        String result = "";
        for (int i : sportList.getMapSports().keySet()) {
            result += sportList.getSport(i).getName() + "</br>";
        }
        return result;
    }

    public String getEquipesData(String sportName, String leagueName) {
        JSONObject response = new JSONObject();

        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null) {
            return new JSONObject().put("error", "Ligue introuvable").toString();
        }

        ListTeam listTeam = league.getTeams();

        for (int i = 0; i < listTeam.getSize(); i++) {
            Team team = listTeam.getTeam(i);

            List<String> player = new ArrayList<>();
            for (int j = 0; j < team.getListPlayer().getSize(); j++) {
                Player p = team.getListPlayer().getPlayer(j);
                player.add(p.getName());
            }

            String playerStr = String.join(", ", player);
            String matchsStr = "Statistiques fictives";

            JSONObject teamInfo = new JSONObject()
                .put("joueurs", playerStr)
                .put("matchs", matchsStr);

            response.put(team.getName(), teamInfo);
        }

        return response.toString();
    }

    public String getSportLeague() {
        JSONArray sports = new JSONArray();

        for (int i : sportList.getMapSports().keySet()) {
            Sport sport = sportList.getSport(i);
            if (sport == null)
                return "error sport dans la liste";
            // Récupère les noms des ligues de ce sport
            ListLeague leagues = sport.getListLeague();
            List<String> leagueNames = new ArrayList<>();
            for (int j : leagues.getLeagueIds()) {
                leagueNames.add(leagues.getLeague(j).getName());
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

    public void trashData() {
        Sport bb = new Sport("Basket Ball");
        bb.addLeague(new League("Tintin au congo"));
        bb.addLeague(new League("Les pythoniste"));
        sportList.addSport(bb);

        Sport vb = new Sport("Volley Ball");
        vb.addLeague(new League("Les coucouille de Gerard"));
        vb.addLeague(new League("Les c++ teams"));
        sportList.addSport(vb);

    }

    // ~~~~~~~~~~~~ Sports ~~~~~~~~~~ //
    public String newSport(String sportName) {
        if (sportList.getAllSports().contains(sportList.getSport(sportName)))
            return "Sport Error";

        Sport newSport = new Sport(sportName);
        int resultAdd = sportList.addSport(newSport);
        String result;
        if (resultAdd == 0)
            result = "<div>erreur</div>";
        else {
            result = "<div>";
            result += sportName + "</div>";
        }
        return result;
    }

    public String listSport() {
        if (sportList.getAllSports().size() <= 0)
            return "No Sports";

        String result = "";
        int maxSport = sportList.getSize();
        for (int i = 0; i < maxSport; i++) {
            result += sportList.getSport(i) + "</br>";
        }

        return result;
    }

    public String getSport(String sportName) {
        if (sportList.getAllSports() == null)
            return "No Sports";

        Sport sportCheck = sportList.getSport(sportName);
        String result;
        if (sportCheck != null) {
            result = sportCheck.getName();
            System.out.println("Sport: " + result);
        } else {
            result = "No sports matching the given name: " + sportName;
        }
        return result;
    }

    public String removeSport(String sportName) {
        if (sportList.getAllSports() == null)
            return "No Sports";

        Sport oldSport = sportList.getSport(sportName);
        if (oldSport == null)
            return "Pas de ligue appelé " + sportName;

        for (int i = 0; i < oldSport.getListLeague().getSize(); i++)
            oldSport.getListLeague().removeLeague(i);

        String result = "";
        if (oldSport != null) {
            result = "Sport retirée :" + oldSport.getName();
            sportList.removeSport(oldSport);
            System.out.println("Ligue retirée: " + result);
        }

        return result;
    }

    // ~~~~~~~~~~~ Leagues ~~~~~~~~~~ //

    public String newLeague(String sport, String nom) {
        if (sportList.getAllSports().contains(sportList.getSport(nom))) {
            return "Sport Error";
        }

        League newLeague = new League(nom);
        boolean resultAdd = sportList.getSport(sport).addLeague(newLeague);
        String result;
        if (!resultAdd) {
            result = "<div>erreur</div>";
        } else {
            result = "<div>";
            result += nom + "</div>";
        }

        return result;
    }

    public String getLeague(String sport, String nom) {
        if (!sportList.getAllSports().contains(sportList.getSport(sport)))
            return "Sport Error";

        League league = sportList.getSport(sport).getListLeague().getLeague(nom);
        String result;
        if (league != null) {
            result = league.getName();
            System.out.println("League: " + result);
        } else {
            result = "Pas de ligue appelé " + nom;
        }
        return result;
    }

    // public String removeLeague(String nom) {
    // League league = listLeague.getLeague(nom);
    // String result = "";
    // if(league != null) {
    // result = "Ligue retirée :" + league.getName();
    // listLeague.removeLeague(league);
    // System.out.println("Ligue retirée: " + result);
    // }
    // else
    // {
    // result = "Pas de ligue appelé " + nom;
    // }
    // //result = "<div>" + result + "</div>";
    // //String result = "<div> aaaaa </div>";
    // return result;
    // }

    public String removeLeague(String sport, String nom) {
        Sport getsport = sportList.getSport(sport);
        if (sport == null)
            return "Sport Error";

        League league = getsport.getListLeague().getLeague(nom);
        String result = "";
        if (league != null) {
            result = "Ligue retirée :" + league.getName();
            sportList.getSport(sport).getListLeague().removeLeague(league);
            System.out.println("Ligue retirée: " + result);
        } else {
            result = "Pas de ligue appelé " + nom;
        }
        return result;
    }

    // ~~~~~~~~~~~~ Teams ~~~~~~~~~~~ //
    public String getTeams(String sport, String league) {
        return "";
    }

    public String addTeam(String sportName, String leagueName, String teamName) {
        String result;
        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null) {
            result = "Ligue introuvable";
        } else {
            if (league.newTeam(teamName)) {
                result = "Équipe ajoutée";
            } else {
                result = "Impossible d'ajouter l'équipe";
            }
        }

        return result;
    }

    public String listTeam(String sportName, String leagueName) {
        if (sportList.getSport(sportName) == null) {
            return "Erreur Sport";
        }
        String result = "";
        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            result = "Ligue introuvable";
        else {
            if (league.getTeams().getSize() <= 0) {
                result = "Pas d'équipe";
            } else {
                for (int i = 0; i < league.getTeams().getSize(); i++) {
                    result += league.getTeams().getTeam(i).getName() + "</br>";
                }
            }
        }
        return result;
    }

    // public String listLeague() {
    // String result = "";
    // if(listLeague.getSize() <= 0) {
    // return "<div>Pas de ligue</div>";
    // }
    // for (int i = 0; i < listLeague.getSize(); i++) {
    // result += listLeague.getLeagueByIndex(i).getName() + "</br>";
    // }
    //
    // return result;
    // }

    public String listLeague(String sportName) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        String result = "";
        if (sportList.getSport(sportName).getListLeague().getSize() <= 0)
            return "<div>Pas de ligue</div>";

        for (int i : sportList.getSport(sportName).getListLeague().getLeagueIds())
            result += sportList.getSport(sportName).getListLeague().getLeague(i).getName() + "</br>";

        return result;
    }

    public String removeTeam(String sportName, String leagueName, String teamName) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        if (sportList.getSport(sportName).getListLeague().getSize() <= 0)
            return "<div>Pas de ligue</div>";

        Team team = sportList.getSport(sportName).getListLeague().getLeague(leagueName).getTeams().getTeam(teamName);
        if (team == null)
            return "<div>Pas d'équipe</div>";

        if (sportList.getSport(sportName).getListLeague().getLeague(leagueName).removeTeam(team))
            return "<div>Équipe retirée</div>";

        return "<div>Erreur lors du retrait d'équipe</div>";
    }

    public String addPlayer(String sportName, String leagueName, String teamName, String playerFirsName,
            String playerLastName) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            return "<div>Pas de ligue</div>";

        Team team = league.getTeams().getTeam(teamName);
        if (team == null)
            return "<div>Pas d'équipe</div>";

        if (team.newPlayer(playerFirsName, playerLastName))
            return "<div> Joueur : " + playerFirsName + " " + playerLastName + " ajouté</div>";

        return "<div>Erreur nom</div>";
    }

    public String listPlayer(String sportName, String leagueName, String teamName) {
        if (sportList.getSport(sportName) == null) {
            return "Erreur Sport";
        }

        String result = "";
        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            result = "Ligue introuvable";
        else {
            Team team = league.getTeams().getTeam(teamName);
            if (team == null)
                result = "Equipe introuvable";
            else {
                if (team.getListPlayer().getSize() <= 0)
                    result = "Pas de joueur";
                for (int i = 0; i < team.getListPlayer().getSize(); i++) {
                    Player player = team.getListPlayer().getPlayer(i);
                    result += player.getName() + " " + player.getLastName() + "</br>";
                }
            }
        }
        return result;
    }

    public String removePlayer(String sportName, String leagueName, String teamName, int playerNumber) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            return "<div>Ligue non-trouvée</div>";

        Team team = league.getTeams().getTeam(teamName);
        if (team == null)
            return "<div>Équipe non-trouvée</div>";

        Player player = team.getListPlayer().getPlayer(playerNumber);
        if (player == null)
            return "<div>Joueur non-trouvé</div>";

        if (team.removePlayer(player))
            return "<div>Joueur retiré</div>";

        return "<div>Erreur lors du retrait du joueur</div>";
    }

}
