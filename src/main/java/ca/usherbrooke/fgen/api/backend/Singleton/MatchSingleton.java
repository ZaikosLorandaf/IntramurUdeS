package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListMatch;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MatchSingleton {
    @Inject
    ListSport sportList;

    MatchSingleton(){
    }

    // Gestion donnees

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
