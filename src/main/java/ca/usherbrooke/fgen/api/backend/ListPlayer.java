package ca.usherbrooke.fgen.api.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ListPlayer {
    private Map<Integer, Player> mapId = new HashMap<>();



    /**
     * Constructeur par défaut
     */
    public ListPlayer() {
        mapId = new HashMap<Integer, Player>();
        LoggerUtil.info("Création de la liste de joueur");
    }

    /**
     * Ajout d'un joueur dans le veteur
     * @param player Objet de classe Joueur à ajouter
     * @return false if list over max size
     */
    public boolean addPlayer(Player player)
    {
        if (!mapId.containsKey(player.getId())) {
            mapId.put(player.getId(), player);
            LoggerUtil.info("Ajout du joueur " + player.getName());
            return true;
        }
        else {
            LoggerUtil.warning("Le id du joueur " + player.getName() + " (" + player.getId() + ") est déjà dans présent.");
            return false;
        }
    }


    public boolean addPlayer(List<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
        return true;
    }


    /**
     * Retire un joueur du vecteur à partir de l'index
     * @param id Id du joueur à retirer
     * @return faux si index out of bound sinon vrai
     */
    public boolean removePlayer(int id)
    {
        if(mapId.containsKey(id)) {
            LoggerUtil.warning("Retrait du joueur " + mapId.get(id).getName() + "(id: " + id + ").");
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
     */
    public boolean removePlayer(Player player)
    {
        return removePlayer(player.getId());
    }

    /**
     * Get le joueur dans la liste
     * @param id id du joueur
     * @return Retourne le joueur s'il existe, sinon null
     */
    public Player getPlayer(int id)
    {
        return mapId.getOrDefault(id, null);
    }



    /**
     * Affiche ce que contient la liste pour tester
     */
    public void printList() {
        if (this.getSize() <= 0) System.out.println("Liste vide");
        else {
            System.out.println("------LISTE------");
            System.out.printf("Size = %d\n",getSize());
            for (int i : this.mapId.keySet()) {
                getPlayer(i).printPlayer();
            }
            System.out.println("------FIN------");
        }
    }


    /**
     * Get la quantité de Joueur dans le vecteur
     * @return quantité de joueur dans le vecteur
     */
    public int getSize()
    {
        return mapId.size();
    }
}



