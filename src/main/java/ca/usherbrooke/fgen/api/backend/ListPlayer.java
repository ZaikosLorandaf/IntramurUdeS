package ca.usherbrooke.fgen.api.backend;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPlayer {
    private Map<Integer, Player> mapId;
    private Map<Integer, Integer> mapNumberId;



    /**
     * Constructeur par défaut
     */
    public ListPlayer() {
        mapId = new HashMap<Integer, Player>();
        mapNumberId = new HashMap<>();
        LoggerUtil.info("Création de la liste de joueur");
    }

    /**
     * Ajout d'un joueur dans le veteur
     * @param player Objet de classe Joueur à ajouter
     * @return false if list over max size
     */
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
     */
    public boolean removePlayer(int id)
    {
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
     */
    public boolean removePlayer(Player player)
    {
        return removePlayer(player.getId());
    }

    public boolean removePlayerByNumber(int number)
    {
        return this.removePlayer(mapNumberId.getOrDefault(number, null));
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

    public Player getPlayerByNumber(int number)
    {
        return this.getPlayer(mapNumberId.getOrDefault(number, null));
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



