package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListMatch;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.mapper.MatchMapper;
import ca.usherbrooke.fgen.api.service.objectServices.MatchService;

import io.quarkus.arc.Arc;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.sql.Date;
import java.util.*;

public class MatchSingleton {
    ListSport sportList;
    MatchService matchService;
    MatchMapper matchMapper;

    MatchSingleton(){
        this.sportList = Arc.container().instance(ListSport.class).get();
        this.matchService = Arc.container().instance(MatchService.class).get();
        this.matchMapper = Arc.container().instance(MatchMapper.class).get();
    }

    // Gestion donnees
    public String add(String sportName, String leagueName, String team1, String team2, String date, String heureDebut, String heureFin, String place) {
        if (sportName == null || leagueName == null || team1 == null || team2 == null) {
            return "Erreur noms";
        }
        Sport sport = sportList.getSport(sportName);
        if (sport == null) {
            return "Sport Error";
        }

        ca.usherbrooke.fgen.api.backend.BdTables.League league = sport.getListLeague().getLeague(leagueName);
        if (league == null) {
             return "Ligue introuvable";
        }

        List<Integer> matchTeams = new ArrayList<>();
        matchTeams.add(league.getListTeam().getTeam(team1).getId());
        matchTeams.add(league.getListTeam().getTeam(team2).getId());

        int idMatch = matchService.getLastId() + 1;
        int idLeague = league.getId();
        Date dateMatch = Date.valueOf(date);
        Time beginMatch = Time.valueOf(heureDebut);
        Time endMatch = Time.valueOf(heureFin);
        Match match = new Match(idMatch, dateMatch, beginMatch, endMatch, place, idLeague, matchTeams.size(), matchTeams, 11); // 11 = valeur par defaut. A modifier quand sera implemente

        if (addDb(match)) {
            sportList.getLeague(idLeague).getListMatch().addMatch(match); // TODO: verifier le retour
            return "Match ajouté avec succès";
        } else {
            return "Erreur lors de l'ajout du match";
        }
    }

    public boolean addDb(ca.usherbrooke.fgen.api.backend.BdTables.Match match) {
        matchService.addMatch(match);
        return true;
    }

    public String remove(String sportName, String leagueName, String date, String team1, String team2) {
        if (sportName == null || leagueName == null || team1 == null|| team2 == null ) {
            return "Erreur noms";
        }
        Sport sport = sportList.getSport(sportName);
        if (sport == null) {
            return "Sport Error";
        }

        ca.usherbrooke.fgen.api.backend.BdTables.League league = sport.getListLeague().getLeague(leagueName);
        if (league == null) {
            return "Ligue introuvable";
        }

        int idTeam1 = league.getListTeam().getTeam(team1).getId();
        int idTeam2 = league.getListTeam().getTeam(team2).getId();
        Date dateMatch = Date.valueOf(date);
        ca.usherbrooke.fgen.api.backend.BdTables.Match match = league.getListMatch().getMatch(dateMatch, idTeam1, idTeam2);
        if (match == null)
            return "<div>Pas de match</div>";

        matchMapper.deleteOne(match.getId());
        if (league.getListMatch().removeMatch(match))
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
