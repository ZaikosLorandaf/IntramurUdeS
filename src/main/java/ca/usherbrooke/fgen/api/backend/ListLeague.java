package ca.usherbrooke.fgen.api.backend;

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
     * @return vrai si l'objet est non null et que la
     * taille est plus petite que le max sinon faux
     */
    public boolean addLeague(League league) {

        if (!mapId.containsKey(league.getId())) {
            mapId.put(league.getId(), league);
            mapNomId.put(league.getName(), league.getId());
            LoggerUtil.info("Ajout de la ligue " + league.getName());
            return true;
        }
        else {
            LoggerUtil.warning("Le id de la ligue " + league.getName() + " (" + league.getId() + ") est déjà dans présent.");
            return false;
        }
    }

    public boolean addLeague(List<League> leagues) {
        for (League league : leagues) {
            addLeague(league);
        }
        return true;
    }

    /**
     * Retirer une ligue du vecteur par son id
     *
     * @param id id de la ligue à retirer
     * @return faux si le vecteur ne contient pas le
     */
    public boolean removeLeague(int id) {

        if(mapId.containsKey(id)) {
            LoggerUtil.warning("Retrait de la ligue " + mapId.get(id).getName() + "(id: " + id + ").");
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



    public int getSize() {
        return mapId.size();
    }

    /**
     * Méthode pour aller chercher une league selon son nom
     * @param id
     * @return
     */
    public League getLeague(int id) {
        return mapId.getOrDefault(id, null);
    }

    public List<Integer> getLeagueIds() {
        return new ArrayList<>(mapId.keySet());
    }


}



