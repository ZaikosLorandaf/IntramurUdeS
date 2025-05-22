package ca.usherbrooke.fgen.api.backend;

import io.quarkus.logging.Log;

import java.util.Vector;
import java.util.logging.Logger;

public class ListLeague {
    private int maxSize;
    private Vector<League> list;

    /**
     * Constructeur paramétré avec la taille maximale
     *
     * @param maxSize taille maximale du vecteur
     */
    public ListLeague(int maxSize) {
        if (maxSize < 1) {
            LoggerUtil.error("Impossible de créer le vecteur de ligue");
            return;
        }
        this.maxSize = maxSize;
        list = new Vector<League>();
        LoggerUtil.info("Création du vecteur de league");
    }

    /**
     * Constructeur par défaut
     */
    public ListLeague() {
        this.maxSize = 100;
        list = new Vector<League>();
        LoggerUtil.info("Création du vecteur de league");
    }

    /**
     * Ajouter une ligue dans la liste
     *
     * @param obj Ligue à ajouter de type Ligue
     * @return vrai si l'objet est non null et que la
     * taille est plus petite que le max sinon faux
     */
    public boolean addLeague(League obj) {
        if (list.size() >= maxSize || obj == null) {
            LoggerUtil.error("Erreur d'ajout de Ligue dans le vecteur");
            return false;
        }
        list.addElement(obj);
        LoggerUtil.info("Ajout de Ligue dans le vecteur");
        return true;
    }

    /**
     * Retirer une ligue du vecteur par l'index
     *
     * @param index index de la ligue à retirer
     * @return faux si le vecteur ne contient pas le
     */
    public boolean removeLeague(int index) {
        try {
            list.removeElementAt(index);
        } catch (Exception e) {
            LoggerUtil.error("Impossible de retirer la ligue à cet index");
            return false;
        }
        LoggerUtil.info("Retrait de Ligue dans le vecteur");
        return true;
    }

    public boolean removeLeague(League league) {
        try {
            list.removeElement(league);
        } catch (Exception e) {
            LoggerUtil.error("Impossible de retirer la ligue à cet index");
            return false;
        }
        LoggerUtil.info("Retrait de Ligue dans le vecteur");
        return true;
    }

    public League getLeague(int index) {
        if (index < 0 || index >= list.size())
            return null;
        return list.elementAt(index);
    }

    public int getIndex(League league) {
        return list.indexOf(league);
    }

    public int getMaxLeague() {
        return maxSize;
    }

    public boolean setMaxLeague(int max) {
        if (max >= getSize()) {
            maxSize = max;
            LoggerUtil.info("Changement du nombre maximum de Ligue dans le vecteur");
            return true;
        }
        LoggerUtil.error("Erreur dans le changeent du nombre maximum de Ligue dans le vecteur");
        return false;
    }

    public int getSize() {
        return list.size();
    }

}



