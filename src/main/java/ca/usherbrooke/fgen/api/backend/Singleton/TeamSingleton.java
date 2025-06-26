package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.Lists.ListTeam;
import ca.usherbrooke.fgen.api.mapper.TeamMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TeamException;
import ca.usherbrooke.fgen.api.service.objectServices.TeamService;
import io.quarkus.arc.Arc;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;

public class TeamSingleton {
    ListSport sportList;
    TeamService teamService;
    TeamMapper teamMapper;

    TeamSingleton() {
        this.sportList = Arc.container().instance(ListSport.class).get();
        this.teamService = Arc.container().instance(TeamService.class).get();
        this.teamMapper = Arc.container().instance(TeamMapper.class).get();
    }

    // Gestion donnees
    public String add(String sportName, String leagueName, String teamName) {
        if (sportName == null || leagueName == null || teamName == null) {
            return "Erreur noms";
        }
        Sport sport = sportList.getSport(sportName);
        if (sport == null) {
            return "Sport Error";
        }

        String result;
        ca.usherbrooke.fgen.api.backend.BdTables.League league = sport.getListLeague().getLeague(leagueName);
        if (league == null) {
            result = "Ligue introuvable";
        } else {
            int id = teamService.getLastId() + 1;
            int idLeague = league.getId();
            if (teamName.isEmpty() || league.getTeams().getMapNameId().containsKey(teamName))
                return "nom impossible";
            ca.usherbrooke.fgen.api.backend.BdTables.Team team = new ca.usherbrooke.fgen.api.backend.BdTables.Team(id, teamName, idLeague);
            if (addDb(team)) {
                result = "Équipe ajoutée";
            } else {
                result = "Impossible d'ajouter l'équipe";
            }
        }

        return result;
    }

    public boolean addDb(ca.usherbrooke.fgen.api.backend.BdTables.Team team) {
        teamService.addTeam(team);
        return true;
    }

    public String remove(String sportName, String leagueName, String teamName) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        if (sportList.getSport(sportName).getListLeague().getSize() <= 0)
            return "<div>Pas de ligue</div>";

        ca.usherbrooke.fgen.api.backend.BdTables.Team team = sportList.getSport(sportName).getListLeague().getLeague(leagueName).getTeams().getTeam(teamName);
        if (team == null)
            return "<div>Pas d'équipe</div>";

        teamMapper.deleteOneTeam(sportList.getSport(sportName).getListLeague().getLeague(leagueName).getTeams().getId(team));

        if (sportList.getSport(sportName).getListLeague().getLeague(leagueName).removeTeam(team))
            return "<div>Équipe retirée</div>";

        return "<div>Erreur lors du retrait d'équipe</div>";
    }

    // Getter
    public String getTeams(String sport, String league) {
        return "";
    }

    public JSONObject listTeam(String sportName, String leagueName) {
        if (sportList.getSport(sportName) == null) {
            throw new TeamException("Erreur Sport");
        }
        JSONObject result = new JSONObject();
        ca.usherbrooke.fgen.api.backend.BdTables.League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            throw new TeamException("Erreur Ligue");
        else {
            if (league.getTeams().getSize() <= 0) {

            } else {
                for (int i : league.getTeams().getTeamIds()) {
                    result.put("nom",league.getTeams().getTeam(i).getName());
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

    public String getEquipesData(String sportName, String leagueName) {
        JSONObject response = new JSONObject();

        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null) {
            return new JSONObject().put("error", "Ligue introuvable").toString();
        }

        ListTeam listTeam = league.getTeams();

        for (int i = 0; i < listTeam.getSize(); i++) {
            ca.usherbrooke.fgen.api.backend.BdTables.Team team = listTeam.getTeam(listTeam.getTeamIds().get(i));

            JSONObject joueurs = new JSONObject();

            for (int j = 0; j < team.getListPlayer().getSize(); j++) {
                Player p = team.getListPlayer().getPlayer(team.getListPlayer().getPlayerIds().get(j));

                JSONObject stats = new JSONObject()
                        .put("role", "Joueur")
                        .put("matchsJoues", 4)
                        .put("buts", new Random().nextInt(4))
                        .put("passes", new Random().nextInt(4))
                        .put("cartonsJaunes", new Random().nextInt(2))
                        .put("cartonsRouges", new Random().nextInt(1))
                        .put("arrets", new Random().nextInt(3))
                        .put("blessures", new Random().nextInt(2))
                        .put("remarques", "");

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

        return response.toString(2);
    }
}
