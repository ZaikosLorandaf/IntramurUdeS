package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.Lists.ListSeason;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class OGClass {
    @Inject
    ListSport sportList;

    ListSeason listSeasons;

    public final SportSingleton sportSingleton = new SportSingleton();
    public final LeagueSingleton leagueSingleton = new LeagueSingleton();
    public final TeamSingleton teamSingleton = new TeamSingleton();
    public final PlayerSingleton playerSingleton = new PlayerSingleton();

    public OGClass() {
        sportList = new ListSport();
        LoggerUtil.info("Création de OGClass terminée.");
    }

    /**
     * Fonction qui crée le JSON pour les data des matchs
     *
     * @return JSON contenant les matchs sous forme de tableau de matchs
     */
    public String getMatchesData(String sportName, String leagueName) {
        JSONObject response = new JSONObject();

        SportSingleton sport = sportList.getSport(sportName);
        if (sport == null)
            return new JSONObject().put("error", "Sport introuvable").toString();

        LeagueSingleton league = sport.getListLeague().getLeague(leagueName);
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

    public String getSportLeague() {
        JSONArray sports = new JSONArray();

        for (int i : sportList.getMapSports().keySet()) {
            SportSingleton sport = sportList.getSport(i);
            if (sport == null)
                return "error sport dans la liste";
            ListLeague leagues = sport.getListLeague();
            List<String> leagueNames = new ArrayList<>();
            for (int j : leagues.getLeagueIds()) {
                leagueNames.add(leagues.getLeague(j).getName());
            }

            JSONObject sportJson = new JSONObject()
                    .put("name", sport.getName())
                    .put("id", sport.getId())
                    .put("seasons", new JSONArray(leagueNames));

            sports.put(sportJson);
        }

        return sports.toString();
    }

    public void trashData() {
        SportSingleton bb = new SportSingleton("Basket Ball");
        bb.addLeague(new LeagueSingleton("Tintin au congo"));
        bb.addLeague(new LeagueSingleton("Les pythoniste"));
        sportList.addSport(bb);

        SportSingleton vb = new SportSingleton("Volley Ball");
        vb.addLeague(new LeagueSingleton("Les coucouille de Gerard"));
        vb.addLeague(new LeagueSingleton("Les c++ teams"));
        sportList.addSport(vb);

    }
}
