package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListLeague;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.mapper.SportMapper;

import io.quarkus.arc.Arc;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SportSingleton {
    ListSport sportList;
    SportMapper sportMapper;

    SportSingleton(){
        this.sportList = Arc.container().instance(ListSport.class).get();
        this.sportMapper = Arc.container().instance(SportMapper.class).get();
    }

    // Gestion donnees
    public String add(String sportName) {
        if (sportList.getAllSports().contains(sportList.getSport(sportName)))
            return "Sport Error";

        ca.usherbrooke.fgen.api.backend.BdTables.Sport newSport = new ca.usherbrooke.fgen.api.backend.BdTables.Sport(sportName);
        int id = sportMapper.getLastId() + 1;
        newSport.setId(id);

        String result;

        if (addDb(newSport)) {
            result = "<div>";
            result += sportName + "</div>";
        } else {
            result = "Impossible d'ajouter dans la base de données " + "<br>";
        }
        return result;
    }

    public boolean addDb(ca.usherbrooke.fgen.api.backend.BdTables.Sport sport) {
        getSportList().addSport(sport);
        sportMapper.insert(sport);
        return true;
    }

    public String remove(String sportName) {
        List<Sport> listee = sportList.getAllSports();
        if (listee.isEmpty())
            return "No Sports";

        ca.usherbrooke.fgen.api.backend.BdTables.Sport oldSport = sportList.getSport(sportName);
        if (oldSport == null)
            return "Pas de ligue appelé " + sportName;
        List<Integer> list = oldSport.getListLeague().getLeagueIds();
        for (int i : list)
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
