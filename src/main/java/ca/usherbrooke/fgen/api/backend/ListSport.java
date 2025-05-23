package ca.usherbrooke.fgen.api.backend;

import java.util.Vector;

public class ListSport {
    private int maxSize;
    private Vector<Sport> list;

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
        if (list.size() >= maxSize || sport == null) {
            LoggerUtil.error("Erreur d'ajout de Sport dans le vecteur");
            return false;
        }
        list.addElement(sport);
        LoggerUtil.info("Ajout de Sport dans le vecteur");
        return true;
    }

    public boolean removeSport(int index) {
        try {
            list.removeElementAt(index);
        } catch (Exception e) {
            LoggerUtil.error("Impossible de retirer le sport à cet index");
            return false;
        }
        LoggerUtil.info("Retrait de Sport dans le vecteur");
        return true;
    }

    public boolean removeSport(Sport sport) {
        try {
            list.removeElement(sport);
        } catch (Exception e) {
            LoggerUtil.error("Impossible de retirer le sport à cet index");
            return false;
        }
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
}
