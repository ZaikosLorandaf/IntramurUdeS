package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMatch extends ListTemplate<Match, String> {

    private Map<Integer, List<Match>> mapMatchTeam = new HashMap<>();

    public boolean addMatch(Match match) {

        return addItem(match);
    }

    @Override
    public boolean addItem(Match item) {
        for (Team team : item.getTeams()) {
            if(!mapMatchTeam.containsKey(team.getId())) {
                mapMatchTeam.put(team.getId(), new ArrayList<>());
                mapMatchTeam.get(team.getId()).add(item);
            }
            else {
                List<Match> list = mapMatchTeam.get(team.getId());
                if(!list.contains(item)) {
                    list.add(item);
                }
            }
        }
        return super.addItem(item);
    }

    public Match getMatch(int id) {
        return getItem(id);
    }
    public Match getMatch(Match match) {
        return getItem(match.getId());
    }

    public Match getMatch(Date date, Integer idTeam1, Integer idTeam2) {
//        Date dateTest = Date.valueOf(date.toLocalDate().plusDays(1)); // Incrementer la date de 1 jour
        for (Match match : this.getAllItems()) {
            if (match.getDate().equals(date) && match.getIdTeams().contains(idTeam1) && match.getIdTeams().contains(idTeam2)) {
                return match;
            }
        }
        return null;
    }

    public boolean removeMatch(Match match) {
        return this.removeItem(match.getId());
    }

    public boolean removeMatch(int id) {
        return this.removeItem(id);
    }

    @Override
    public boolean removeItem(int id) {
        Match match = getItem(id);
        for (Team team : match.getTeams()) {
            this.mapMatchTeam.get(team.getId()).remove(id);
        }
        return super.removeItem(id);
    }


    @Override
    public int getId(Match item) {
        return item.getId();
    }

    @Override
    public String getName(Match item) {
        return item.toString();
    }

    // Methodes affichage
    @Override
    void logAddSuccess(Match match) {
        LoggerUtil.info("Ajout du match " + match.getId());
    }

    @Override
    void logAddFailure(Match match){
        LoggerUtil.warning("Le id du match " + match.getId() + " est déjà dans présent.");
    }

    @Override
    void logRemoveSuccess(int id){
        LoggerUtil.warning("Retrait match reussi");
    }

    @Override
    void logRemoveFailure(int id){
        LoggerUtil.warning("Échec du retrait du match");
    }

    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    @Override
    void printItem(int index){
    }
}
