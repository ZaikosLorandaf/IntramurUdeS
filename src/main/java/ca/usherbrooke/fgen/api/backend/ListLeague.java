package ca.usherbrooke.fgen.api.backend;

import javax.inject.Inject;
import java.util.*;

public class ListLeague {
    private Map<Integer, League> mapId;
    private Map<String, Integer> mapNomId;


    /**
     * Constructeur paramétré avec la taille maximale
     *
     */
    public ListLeague() {
        this.mapId = new HashMap<>();
        this.mapNomId = new HashMap<>();
        LoggerUtil.info("Création de la liste de league");
    }


    /**
     * Ajouter une ligue dans la liste
     *
     * @param league Ligue à ajouter de type Ligue
     * @return Le nombre de ligues ajoutées
     */

    public boolean addLeague(League league) {

        if (!mapId.containsKey(league.getId()) && !mapNomId.containsKey(league.getName())) {
            mapId.put(league.getId(), league);
            mapNomId.put(league.getName(), league.getId());
            ListSport.addLeagueMap(league);

            LoggerUtil.info("Ajout de la ligue " + league.getName());
            return true;
        }
        else {
            LoggerUtil.warning("Le id ou le nom de la ligue " + league.getName() +
                    " (" + league.getId() + ") existe déjà.");
            return false;
        }
    }

    public int addLeague(List<League> leagues) {
        int counter = 0;
        for (League league : leagues) {
            if(addLeague(league)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Retirer une ligue du vecteur par son id
     *
     * @param id id de la ligue à retirer
     * @return faux si le vecteur ne contient pas le
     */
    public boolean removeLeague(int id) {

        if(mapId.containsKey(id) && mapNomId.containsKey(mapId.get(id).getName())) {
            LoggerUtil.warning("Retrait de la ligue " + mapId.get(id).getName() + "(id: " + id + ").");
            ListSport.removeLeagueMap(mapId.get(id));
            mapNomId.remove(mapId.get(id).getName());
            mapId.remove(id);

            return true;
        }
        else {
            LoggerUtil.warning("Échec du retrait de la ligue " + mapId.get(id).getName() + "(id: " + id + ").");
            return false;
        }
    }

    public boolean removeLeague(League league) {
        return removeLeague(league.getId());
    }


    // Getter
    public int getSize() {
        return mapId.size();
    }
    public League getLeague(int id) {
        return mapId.getOrDefault(id, null);
    }
    public League getLeague(String name) {
        return getLeague(mapNomId.getOrDefault(name, null));
    }
    public List<Integer> getLeagueIds() {
        return new ArrayList<>(mapId.keySet());
    }


}



