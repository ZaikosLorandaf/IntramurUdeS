package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;

import java.util.List;

public class ListPlayer extends ListTemplate<Player, Integer> {


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
        if (!mapId.containsKey(player.getId()) && !mapNameId.containsKey(player.getNumber())) {
            mapId.put(player.getId(), player);
            mapNameId.put(player.getNumber(), player.getId());

//            switch (addItem(player)) {
//                case 1:
                    LoggerUtil.info("Ajout du joueur " + player.getName() + " " + player.getLastName() + " " + player.getNumber());
                    return true;
//                case 0:
//                    LoggerUtil.warning("Le id du joueur " + player.getName() + " (" + player.getId() + ") est déjà dans présent.");
//                    return false;
//                default:
//                    return false;
//            }
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
        Player player = getItem(id);
        if (removeItem(id)) {
            LoggerUtil.warning("Retrait du joueur " + player.getName()+ " " + player.getLastName() + "#" +player.getNumber()  + "(id: " + id + ").");
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait du sport " +  player.getName()+ " " + player.getLastName() + "#" +player.getNumber()  + "(id: " + id + ").");
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

    public Player getPlayerByNumber(int number)
    {
        int numb = mapNameId.getOrDefault(number, null);
        Player p = this.getPlayer(numb);
        return p;
    }
}
