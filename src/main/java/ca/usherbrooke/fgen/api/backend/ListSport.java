package ca.usherbrooke.fgen.api.backend;

import java.util.*;

public class ListSport {
    private Map<Integer, Sport> mapId;
    private Map<String, Integer> mapNomId;

    public ListSport() {
        mapId = new HashMap<>();
        mapNomId = new HashMap<>();
        LoggerUtil.info("Création du vecteur de sport");
    }

    public boolean addSport(Sport sport) {

        if (!mapId.containsKey(sport.getId())) {
            mapId.put(sport.getId(), sport);
            mapNomId.put(sport.getName(), sport.getId());
            LoggerUtil.info("Ajout du sport " + sport.getName());
            return true;
        }
        else {
            LoggerUtil.warning("Le id du sport " + sport.getName() + " (" + sport.getId() + ") est déjà dans présent.");
            return false;
        }
    }


    public boolean addSports(List<Sport> sports) {
        for (Sport sport : sports) {
            addSport(sport);
        }
        return true;
    }



    public boolean removeSport(int id) {
        if(mapId.containsKey(id)) {
            LoggerUtil.warning("Retrait du sport " + mapId.get(id).getName() + "(id: " + id + ").");
            mapNomId.remove(mapId.get(id).getName());
            mapId.remove(id);
            return true;
        }
        else {
            LoggerUtil.warning("Échec du retrait du sport " + mapId.get(id).getName() + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Méthode pour retirer un sport
     * @param sport Sport à retirer
     * @return Vrai si le joueur est retiré, sinon false
     */
    public boolean removeSport(Sport sport) {
        return removeSport(sport.getId());
    }


    /**
     * Méthode pour aller chercher un sport dans la liste selon son id
     * @param id Id du sport à aller chercher
     * @return Le sport s'il a été trouvé, sinon null
     */
    public Sport getSport(int id) {
        return mapId.getOrDefault(id, null);
    }



    public Sport getSport(String nom) {
        return mapId.getOrDefault(mapNomId.get(nom), null);
    }



    public int getSize() {
        return mapId.size();
    }

    public Map<Integer, Sport> getMapSports() {
        return mapId;
    }

    public boolean checkExistSport(Sport sport) {
        return mapId.containsKey(sport.getId());
    }
}
