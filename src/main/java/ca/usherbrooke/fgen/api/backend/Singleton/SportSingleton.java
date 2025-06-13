package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListLeague;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import ca.usherbrooke.fgen.api.service.objectServices.SportService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SportSingleton extends OGClass {
    @Inject
    SportService sportService;
    @Inject
    SportMapper sportMapper;

    SportSingleton(){
    }

    // Gestion donnees
    public String add(String sportName) {
        if (sportList.getAllSports().contains(sportList.getSport(sportName)))
            return "Sport Error";

        ca.usherbrooke.fgen.api.backend.BdTables.Sport newSport = new ca.usherbrooke.fgen.api.backend.BdTables.Sport(sportName);
        int id = sportService.getLastId() + 1;
        newSport.setId(id);
//        int resultAdd = sportList.addSport(newSport);
        String result;
//        if (resultAdd == 0)
//            result = "<div>erreur</div>";
//        else {

        if (addDb(newSport)) {
            result = "<div>";
            result += sportName + "</div>";
        } else {
            result = "Impossible d'ajouter dans la base de données " + "<br>";
        }
//        }
        return result;
    }

    public boolean addDb(ca.usherbrooke.fgen.api.backend.BdTables.Sport sport) {
        sportService.addSport(sport);
        return true;
    }

    public String remove(String sportName) {
        if (sportList.getAllSports() == null)
            return "No Sports";

        ca.usherbrooke.fgen.api.backend.BdTables.Sport oldSport = sportList.getSport(sportName);
        if (oldSport == null)
            return "Pas de ligue appelé " + sportName;
        List<Integer> list = oldSport.getListLeague().getLeagueIds();
        for (int i : oldSport.getListLeague().getLeagueIds())
            oldSport.getListLeague().removeLeague(i);

        String result = "";
        if (oldSport != null) {
            result = "Sport retirée :" + oldSport.getName();
            ca.usherbrooke.fgen.api.backend.BdTables.Sport sport = sportList.getSport(sportName);
            int sportId = sportList.getId(sport);
            sportMapper.deleteOne(sportId);
            sportList.removeSport(oldSport);
            System.out.println("Ligue retirée: " + result);
        }

        return result;
    }

    // Getter
    public String listSport() {
        if (sportList.getAllSports().size() <= 0)
            return "No Sports";

        String result = "";
        int maxSport = sportList.getSize();
        Set<Integer> keys = sportList.getMapSports().keySet();
        for (int i : keys) {
            result += sportList.getSport(i) + "</br>";
        }

        return result;
    }

    public String getSport(String sportName) {
        if (sportList.getAllSports() == null)
            return "No Sports";

        ca.usherbrooke.fgen.api.backend.BdTables.Sport sportCheck = sportList.getSport(sportName);
        String result;
        if (sportCheck != null) {
            result = sportCheck.getName();
            System.out.println("Sport: " + result);
        } else {
            result = "No sports matching the given name: " + sportName;
        }
        return result;
    }

    public String getSports() {
        //System.out.println(sportList.getMapSports());
        String result = "";
        for (int i : sportList.getMapSports().keySet()) {
            result += sportList.getSport(i).getName() + "</br>";
        }
        return result;
    }

    /**
     * Getter pour la listeSport
     *
     * @return L'objet ListeSport
     */
    public ListSport getSportList() {
        return sportList;
    }

    public String getSportLeague() {
        JSONArray sports = new JSONArray();

        for (int i : sportList.getMapSports().keySet()) {
            Sport sport = sportList.getSport(i);
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
}
