package ca.usherbrooke.fgen.api.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ListLeague {
    private int maxSize;
    private Vector<League> list;
    private Map<String, Integer> dictName = new HashMap<String, Integer>();
    private Map<Integer, Integer> dictId = new HashMap<Integer, Integer>();
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
        if (list.size() >= maxSize || obj == null || dictName.containsKey(obj.getName())) {
            LoggerUtil.error("Erreur d'ajout de Ligue dans le vecteur");
            return false;
        }
        int index = list.size();
        dictName.put(obj.getName(),index);
        dictId.put(obj.getID(), index);
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

        if (index < 0 || index >= list.size() ||
                !dictName.containsKey(list.elementAt(index).getName()) ||
                !dictId.containsKey(list.elementAt(index).getID())) {
            LoggerUtil.error("Impossible de retirer la ligue à cet index");
            return false;
        }
        Map<String, Integer> tempDictName = new HashMap<>();
        Map<Integer, Integer> tempDictId = new HashMap<>();
        dictName.remove(list.get(index).getName());
        dictId.remove(list.get(index).getID());
        this.list.remove(index);
        for (int i = 0; i < list.size(); i++) {
            if (i < index)
            {
                tempDictName.put(list.get(i).getName() , i);
                tempDictId.put(list.get(i).getID() , i);
            }
            else{
                tempDictName.put(list.get(i).getName() , i - 1);
                tempDictId.put(list.get(i).getID() , i - 1);
            }
        }
        dictName = tempDictName;
        dictId = tempDictId;
        LoggerUtil.info("Retrait de Ligue dans le vecteur");
        return true;
    }

    public boolean removeLeague(League league) {
        if (league == null ||
                !dictName.containsKey(league.getName()) ||
                !dictId.containsKey(league.getID())) {
            LoggerUtil.error("Impossible de retirer la ligue à cet index");
            return false;
        }
        int index = list.indexOf(league);

        Map<String, Integer> tempDictName = new HashMap<>();
        Map<Integer, Integer> tempDictId = new HashMap<>();

        dictName.remove(league.getName());
        dictId.remove(league.getID());
        this.list.remove(league);
        for (int i = 0; i < list.size(); i++) {
            if (i<index)
            {
                tempDictName.put(list.get(i).getName() , i);
                tempDictId.put(list.get(i).getID() , i);
            }
            else
            {
                tempDictName.put(list.get(i).getName() , i - 1);
                tempDictId.put(list.get(i).getID() , i - 1);
            }
        }
        dictName = tempDictName;
        dictId = tempDictId;
        LoggerUtil.info("Retrait de Ligue dans le vecteur");
        return true;
    }

    /**
     * Méthode pour aller chercher une league selon son index dans le vecteur
     * @param index Index de la league
     * @return La league trouvée
     */
    public League getLeagueByIndex(int index) {
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

    /**
     * Méthode pour aller chercher une league selon son nom
     * @param name
     * @return
     */
    public League getLeague(String name) {
        int index;
        if (dictName.containsKey(name)) {
            index = dictName.get(name);
        }
        else{
            return null;
        }
        return list.elementAt(index);
    }

    /**
     * Méthode pour aller chercher une league selon son Id
     * @param id Id de la ligue à trouver
     * @return La league trouvée
     */
    public League getLeague(int id) {
        int index;
        if (dictId.containsKey(id)) {
            index = dictId.get(id);
        }
        else{
            return null;
        }
        return list.elementAt(index);
    }
}



