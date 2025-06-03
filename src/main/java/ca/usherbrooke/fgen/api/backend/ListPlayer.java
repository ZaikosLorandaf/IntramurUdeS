package ca.usherbrooke.fgen.api.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPlayer extends ListTemplate<Player, Integer> {
    private Map<Integer, Integer> mapNumberId = new HashMap<Integer, Integer>();

    /**
     * Constructeur vide par defaut. Class herite de ListTemplate
     */
    public ListPlayer() {
        LoggerUtil.info("Création de la liste de joueur");
    }


    /**
     * Ajout d'un joueur dans le veteur
     *
     * @param player Objet de classe Joueur a ajouter
     * @return false if list over max size
     */
    public boolean addPlayer(Player player) {
        int id = player.getId();
        int number = player.getNumber();
        if (!mapId.containsKey(player.getId()) && !mapNumberId.containsKey(player.getNumber())) {
            mapId.put(player.getId(), player);
            mapNumberId.put(player.getNumber(), player.getId());

            switch (addItem(player)) {
                case 0:
                    LoggerUtil.info("Ajout du joueur " + player.getName());
                    return true;
                case 1:
                    LoggerUtil.warning("Le id du joueur " + player.getName() + " (" + player.getId() + ") est déjà dans présent.");
                    return false;
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * Ajout plusieurs joueur dans le veteur a partir d'une liste de joueur
     *
     * @param players liste d'Objet de classe Joueur a ajouter
     * @return false if list over max size
     */
    public int addPlayer(List<Player> players) {
        return addItems(players);
    }

    /**
     * Retire un joueur du vecteur à partir de l'index
     *
     * @param id Id du joueur à retirer
     * @return faux si index out of bound sinon vrai
     */
    public boolean removePlayer(int id) {
        if (removeItem(id)) {
            LoggerUtil.warning("Retrait du joueur " + getItem(id).getName() + "(id: " + id + ").");
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait du sport " + getItem(id).getName() + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Retire un joueur du vecteur à partir de l'index
     *
     * @param player Joueur à retirer
     * @return faux si Joueur n'est pas dans le vecteur sinon vrai
     */
    public boolean removePlayer(Player player) {
        return removePlayer(player.getId());
    }

    /**
     * Retire un joueur du vecteur à partir de son numero
     *
     * @param number numero du Joueur à retirer
     * @return faux si Joueur n'est pas dans le vecteur sinon vrai
     */
    public boolean removePlayerByNumber(int number) {
        return this.removePlayer(getItem(number));
    }

    /**
     * Affiche ce que contient la liste dans la console pour tester
     */
    public void printList() {
        if (this.getSize() <= 0)
            System.out.println("Liste vide");
        else {
            System.out.println("------LISTE------");
            System.out.printf("Size = %d\n", getSize());
            for (int i : getMapIds()) {
                getPlayer(i).printPlayer();
            }
            System.out.println("------FIN------");
        }
    }

    // Getter
    public int getSize() {
        return getMapSize();
    }

    public Player getPlayer(int id) {
        return getItem(id);
    }

    public List<Integer> getPlayerIds() {
        return getMapIds();
    }

    public Player getPlayerByNumber(Integer number) {
        return getItem(number);
    }

    public int getId(Player player) {
        return player.getId();
    }

    public Integer getName(Player player) {
        return player.getNumber();
    }

    public Map<Integer, Integer> getMapNumberId()
    {
        return mapNumberId;
    }
/*
public class ListPlayer {
    private Map<Integer, Player> mapId;
    private Map<Integer, Integer> mapNumberId;



    /**
     * Constructeur par défaut
     *
public ListPlayer() {
    mapId = new HashMap<Integer, Player>();
    mapNumberId = new HashMap<>();
    LoggerUtil.info("Création de la liste de joueur");
}

/**
 * Ajout d'un joueur dans le veteur
 * @param player Objet de classe Joueur à ajouter
 * @return false if list over max size
 *
public boolean addPlayer(Player player)
{
    if (!mapId.containsKey(player.getId()) && !mapNumberId.containsKey(player.getNumber())) {
        mapId.put(player.getId(), player);
        mapNumberId.put(player.getNumber(), player.getId());

        LoggerUtil.info("Ajout du joueur " + player.getName());
        return true;
    }
    else {
        LoggerUtil.warning("Le id du joueur " + player.getName() + " (" + player.getId() + ") est déjà dans présent.");
        return false ;
    }
}


public int addPlayer(List<Player> players) {
    int counter = 0;
    for (Player player : players) {
        if (addPlayer(player))
        {
            counter++;
        }
    }
    return counter;
}


/**
 * Retire un joueur du vecteur à partir de l'index
 * @param id Id du joueur à retirer
 * @return faux si index out of bound sinon vrai
 *
public boolean removePlayer(int id) {
    if(mapId.containsKey(id) && mapNumberId.containsKey(mapId.get(id).getNumber())) {
        LoggerUtil.warning("Retrait du joueur " + mapId.get(id).getName() + "(id: " + id + ").");
        mapNumberId.remove(mapId.get(id).getNumber());
        mapId.remove(id);
        return true;
    }
    else {
        LoggerUtil.warning("Échec du retrait du sport " + mapId.get(id).getName() + "(id: " + id + ").");
        return false;
    }
}

/**
 * Retire un joueur du vecteur à partir de l'index
 * @param player Joueur à retirer
 * @return faux si Joueur n'est pas dans le vecteur sinon vrai
 *
public boolean removePlayer(Player player)
{
    return removePlayer(player.getId());
}

public boolean removePlayerByNumber(int number) {
    return this.removePlayer(mapNumberId.getOrDefault(number, null));
}

/**
 * Affiche ce que contient la liste pour tester
 *
public void printList() {
    if (this.getSize() <= 0)
        System.out.println("Liste vide");
    else {
        System.out.println("------LISTE------");
        System.out.printf("Size = %d\n",getSize());
        for (int i : this.mapId.keySet()) {
            getPlayer(i).printPlayer();
        }
        System.out.println("------FIN------");
    }
}

// Getter
public int getSize()
{
    return mapId.size();
}
public Player getPlayer(int id)
{
    return mapId.getOrDefault(id, null);
}
*/

    public Player getPlayerByNumber(int number)
    {
        int numb = mapNumberId.getOrDefault(number, null);
        Player p = this.getPlayer(numb);
        return p;
    }
}
