package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.Lists.ListMatch;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.service.objectServices.MatchService;
import io.quarkus.arc.Arc;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MatchSingleton {
    ListSport sportList;
    MatchService matchService;

    MatchSingleton(){
        this.sportList = Arc.container().instance(ListSport.class).get();
    }

    // Gestion donnees
    public String add(String sportName, String leagueName, String teamsNames, String date, String heureDebut, String heureFin) {
        if (sportName == null || leagueName == null || teamsNames == null) {
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
            Team team = league.getTeams().getTeam(teamsNames);
            if (team == null) {
                result = "Equipe introuvable";
            } else {
                int idMatch = matchService.getLastId() + 1;
                int idLeague = league.getId();
                Date dateMatch = new Date(Long.parseLong(date));
                Time beginMatch = new Time(Long.parseLong(heureDebut));
                Time endMatch = new Time(Long.parseLong(heureFin));
                int numberTeam = teamsNames

                ca.usherbrooke.fgen.api.backend.BdTables.Match match = new ca.usherbrooke.fgen.api.backend.BdTables.Match(idMatch, dateMatch, beginMatch, endMatch, idLeague)

                if (addDb(match)) {
                    result = "Équipe ajoutée";
                } else {
                    result = "Impossible d'ajouter l'équipe";
                }
            }
        }
        return result;
    }

    public boolean addDb(ca.usherbrooke.fgen.api.backend.BdTables.Match match) {
        matchService.addMatch(match);
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
    /**
     * Fonction qui crée le JSON pour les data des matchs
     *
     * @return JSON contenant les matchs sous forme de tableau de matchs
     */
    public String getMatchesData(String sportName, String leagueName) {
        JSONObject response = new JSONObject();

        Sport sport = sportList.getSport(sportName);
        if (sport == null)
            return new JSONObject().put("error", "Sport introuvable").toString();

        League league = sport.getListLeague().getLeague(leagueName);
        if (league == null)
            return new JSONObject().put("error", "Ligue introuvable").toString();

        ListMatch listMatch = league.getListMatch();
        if (listMatch == null || listMatch.getMapSize() == 0)
            return new JSONObject().put("error", "Aucun match trouvé").toString();

        Map<String, JSONArray> matchsParDate = new HashMap<>();

        for (Match match : listMatch.getAllItems()) {
            match.init();

            StringBuilder equipesBuilder = new StringBuilder();
            for (int i = 0; i < match.getTeams().size(); i++) {
                equipesBuilder.append(match.getTeams().get(i).getName());
                if (i < match.getTeams().size() - 1) {
                    equipesBuilder.append(" vs ");
                }
            }

            String date = match.getDate().toString();
            String heure = match.getBeginTime().toString() + " - " + match.getEndTime().toString();

            JSONObject matchJson = new JSONObject()
                    .put("id", match.getId())
                    .put("heure", heure)
                    .put("equipes", equipesBuilder.toString());

            matchsParDate.computeIfAbsent(date, k -> new JSONArray()).put(matchJson);
        }

        for (Map.Entry<String, JSONArray> entry : matchsParDate.entrySet()) {
            response.put(entry.getKey(), entry.getValue());
        }

        return response.toString(2);
    }
}
