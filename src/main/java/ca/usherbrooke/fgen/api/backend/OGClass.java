package ca.usherbrooke.fgen.api.backend;

import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import ca.usherbrooke.fgen.api.mapper.TeamMapper;
import ca.usherbrooke.fgen.api.service.objectServices.LeagueService;
import ca.usherbrooke.fgen.api.service.objectServices.SportService;
import ca.usherbrooke.fgen.api.service.objectServices.TeamService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class OGClass {
    @Inject
    ListSport sportList;
    @Inject
    SportService sportService;
    @Inject
    SportMapper sportMapper;
    @Inject
    LeagueService leagueService;
    @Inject
    LeagueMapper leagueMapper;
    @Inject
    TeamService teamService;
    @Inject
    TeamMapper teamMapper;
    @Inject
    PlayerMapper playerMapper;

    public OGClass() {

        sportList = new ListSport();
        LoggerUtil.info("Création de OGClass terminée.");
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
        // Verifier si le sport existe deja
        if (sportList.getAllSports().contains(sportList.getSport(sportName)))
            return "Sport Error";

        // Creer le sport
        String result;
        Sport newSport = new Sport(sportName);
        newSport.setId(sportService.getLastId() + 1); // Assigner l'Id

        // Creer la reponse
        if (sportList.addSport(newSport) == 0)
            result = "<div>erreur</div>";
        else {
            if(ajoutSportDb(newSport))
                result = "<div>" + sportName + "</div>";
            else
                result = "Impossible d'ajouter dans la base de données "+ "<br>";
        }
        return result;
    }

    public String listSport() {
        if (sportList.getAllSports().size() <= 0)
            return "No Sports";

        // Liste tous les sports dans une chaine de caractere
        String result = "";
        for (int i: sportList.getMapSports().keySet()) {
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
            sportMapper.deleteOne(sportList.getId(sportList.getSport(sportName)));
            System.out.println("Ligue retirée: " + result);
        }

        return result;
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

    public String getSportLeague() {
        JSONArray sports = new JSONArray();
        Sport sport;
        ListLeague leagues;
        List<String> leagueNames;

        for (int i : sportList.getMapSports().keySet()) {
            sport = sportList.getSport(i);
            if (sport == null)
                return "error sport dans la liste";
            // Récupère les noms des ligues de ce sport
            leagues = sport.getListLeague();
            leagueNames = new ArrayList<>();
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

    // ~~~~~~~~~~~ Leagues ~~~~~~~~~~ //

    public String newLeague(String nom_sport, String nom, Date dateDebut, Date dateFin) {
        Sport sport = sportList.getSport(nom_sport);
        if (sport == null) {
            return "Sport Error";
        }
        if (dateFin == null || dateDebut == null || dateFin.before(dateDebut))
        {
            return "Erreur dans les dates";
        }
        League newLeague = new League(nom, dateDebut, dateFin);
        int id = leagueService.getLastId() + 1;
        int sportId = sport.getId();
        newLeague.setLeagueID(id);
        newLeague.setIdSport(sportId);

        String result;
        if(ajoutLigueDb(newLeague))
        {
            result = "<div>";
            result += nom + "</div>";
        }
        else {
            result = "Impossible d'ajouter la ligue dans la base de données "+ "<br>";
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

    public String removeLeague(String sport, String nom) {
        Sport getsport = sportList.getSport(sport);
        if (sport == null)
            return "Sport Error";

        League league = getsport.getListLeague().getLeague(nom);
        String result = "";
        if (league != null) {
            result = "Ligue retirée :" + league.getName();
            getsport.getListLeague().removeLeague(league);
            leagueMapper.deleteOne(getsport.getListLeague().getId(league));
            System.out.println("Ligue retirée: " + result);
        } else {
            result = "Pas de ligue appelé " + nom;
        }
        return result;
    }

    public String listLeague(String sportName) {
        Sport sport = sportList.getSport(sportName);
        if (sport == null)
            return "Erreur Sport";

        String result = "";
        if (sport.getListLeague().getSize() <= 0)
            return "<div>Pas de ligue</div>";

        for (int i : sport.getListLeague().getLeagueIds())
            result += sport.getListLeague().getLeague(i).getName() + "</br>";

        return result;
    }

    // ~~~~~~~~~~~~ Teams ~~~~~~~~~~~ //
    public String getTeams(String sport, String league) {
        return "";
    }


    public String addTeam(String sportName, String leagueName, String teamName) {
        if (sportName == null || leagueName == null || teamName == null)
        {
            return "Erreur noms";
        }
        Sport sport = sportList.getSport(sportName);
        if (sport == null) {
            return "Sport Error";
        }

        String result;
        League league = sport.getListLeague().getLeague(leagueName);
        if (league == null) {
            result = "Ligue introuvable";
        } else {
            int id = teamService.getLastId() + 1;
            int idLeague = league.getId();
            Team team = new Team(id, teamName, idLeague);
            if (ajoutTeamDb(team)) {
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
                for (int i : league.getTeams().getTeamIds()) {
                    result += league.getTeams().getTeam(i).getName() + "</br>";
                }
            }
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
            Team team = listTeam.getTeam(listTeam.getTeamIds().get(i));

            JSONObject joueurs = new JSONObject();

            for (int j = 0; j < team.getListPlayer().getSize(); j++) {
                Player p = team.getListPlayer().getPlayer(team.getListPlayer().getPlayerIds().get(j));

                JSONObject stats = new JSONObject()
                        .put("role", "Joueur") // à adapter selon ton modèle
                        .put("matchsJoues", 4)
                        .put("buts", new Random().nextInt(4))
                        .put("passes", new Random().nextInt(4))
                        .put("cartonsJaunes", new Random().nextInt(2))
                        .put("cartonsRouges", new Random().nextInt(1))
                        .put("arrets", new Random().nextInt(3))
                        .put("blessures", new Random().nextInt(2))
                        .put("remarques", ""); // tu peux mettre p.getRemark() si dispo

                joueurs.put(p.getName(), stats);
            }

            // Génère des stats fictives d’équipe
            JSONObject teamStats = new JSONObject()
                    .put("matchsJoues", 4)
                    .put("victoires", new Random().nextInt(5))
                    .put("defaites", new Random().nextInt(5))
                    .put("pointsMarques", new Random().nextInt(100))
                    .put("pointsEncaisses", new Random().nextInt(100))
                    .put("differenceDePoints", new Random().nextInt(50));

            JSONObject teamInfo = new JSONObject()
                    .put("joueurs", joueurs)
                    .put("stats", teamStats);

            response.put(team.getName(), teamInfo);
        }

        return response.toString(2); // indente pour lisibilité
    }

    public String removeTeam(String sportName, String leagueName, String teamName) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        ListLeague league = sportList.getSport(sportName).getListLeague();

        if (league.getSize() <= 0)
            return "<div>Pas de ligue</div>";

        Team team = league.getLeague(leagueName).getTeams().getTeam(teamName);
        if (team == null)
            return "<div>Pas d'équipe</div>";

        teamMapper.deleteOneTeam(league.getLeague(leagueName).getTeams().getId(team));

        if (league.getLeague(leagueName).removeTeam(team))
            return "<div>Équipe retirée</div>";

        return "<div>Erreur lors du retrait d'équipe</div>";
    }

    // ~~~~~~~~~~~~ Player ~~~~~~~~~~~ //
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
                for (int i: team.getListPlayer().getPlayerIds()) {
                    Player player = team.getListPlayer().getPlayerByNumber(i);
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

        playerMapper.deleteOnePlayer(team.getListPlayer().getId(player));
        if (team.removePlayer(player))
            return "<div>Joueur retiré</div>";

        return "<div>Erreur lors du retrait du joueur</div>";
    }


    public boolean ajoutSportDb(Sport sport)
    {
        sportService.addSport(sport);
        return true;
    }

    public boolean ajoutLigueDb(League league)
    {
        leagueService.addLeague(league);
        return true;
    }

    public boolean ajoutTeamDb(Team team)
    {
        teamService.addTeam(team);
        return true;
    }

}
