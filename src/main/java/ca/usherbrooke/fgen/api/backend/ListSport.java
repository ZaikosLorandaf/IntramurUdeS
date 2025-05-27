package ca.usherbrooke.fgen.api.backend;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class ListSport {
    private int maxSize;
    private Vector<Sport> list;
    private Map<String, Integer> dict = new HashMap<>();

    public ListSport(int maxSize) {
        if (maxSize < 1) {
            LoggerUtil.error("Impossible de créer le vecteur de sport");
            return;
        }
        this.maxSize = maxSize;
        list = new Vector<Sport>();
        LoggerUtil.info("Création du vecteur de sport");
    }

    public ListSport() {
        this.maxSize = 100;
        list = new Vector<Sport>();
        LoggerUtil.info("Création du vecteur de sport");
    }

    public boolean addSport(Sport sport) {
        if (list.size() >= maxSize || sport == null || dict.containsKey(sport.getName())) {
            LoggerUtil.error("Erreur d'ajout de Sport dans le vecteur");
            return false;
        }
        dict.put(sport.getName(), list.size());
        list.addElement(sport);
        LoggerUtil.info("Ajout de Sport dans le vecteur");
        return true;
    }

    public boolean removeSport(int index) {
        if (index < 0 || index >= list.size()) {
            LoggerUtil.error("Impossible de retirer le sport à cet index");
            return false;
        }
        Map<String, Integer> tempDict = new HashMap<>();
        list.removeElementAt(index);
        for (int i = 0; i < list.size(); i++) {
            tempDict.put(list.get(i).getName(), i);
        }
        dict = tempDict;
        LoggerUtil.info("Retrait de Sport dans le vecteur");
        return true;
    }

    public boolean removeSport(Sport sport) {
        if (!list.contains(sport)) {
            LoggerUtil.error("Impossible de retirer le sport à cet index");
            return false;
        }
        Map<String, Integer> tempDict = new HashMap<>();
        list.removeElement(sport);
        for (int i = 0; i < list.size(); i++) {
            tempDict.put(list.get(i).getName(), i);
        }
        dict = tempDict;
        LoggerUtil.info("Retrait de Sport dans le vecteur");
        return true;
    }

    public Sport getSport(int index) {
        if (index < 0 || index >= list.size())
            return null;
        return list.elementAt(index);
    }

    public int getIndex(Sport sport) {
        return list.indexOf(sport);
    }

    public Sport getSport(String name){
        if (dict.containsKey(name))
        {
            int index = dict.get(name);
            return this.getSport(index);
        }
        else{
            return null;
        }
    }

    public int getMaxSport() {
        return maxSize;
    }

    public boolean setMaxSport(int max) {
        if (max >= getSize()) {
            maxSize = max;
            LoggerUtil.info("Changement du nombre maximum de Sport dans le vecteur");
            return true;
        }
        LoggerUtil.error("Erreur dans le changement du nombre maximum de Sport dans le vecteur");
        return false;
    }

    public int getSize() {
        return list.size();
    }

    public Vector<Sport> getAllSports() {
        return list;
    }

    public boolean getSport(Sport sport) {
        return list.contains(sport);
    }
}
