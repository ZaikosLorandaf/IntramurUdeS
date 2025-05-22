package ca.usherbrooke.fgen.api.backend;

import java.util.Vector;

public class ListPlayer {
    private int maxPlayer;
    private Vector<Player> list;

    /**
     * Constructeur avec un max
     * @param max Quantité max de joueur dans la liste
     */
    public ListPlayer(int max) {
        maxPlayer = max;
        list = new Vector<Player>();
        LoggerUtil.info("Création du vecteur de joueur");
    }

    /**
     * Constructeur par défaut
     */
    public ListPlayer() {
        maxPlayer = 100;
        list = new Vector<Player>();
        LoggerUtil.info("Création du vecteur de joueur");
    }

    /**
     * Ajout d'un joueur dans le veteur
     * @param obj Objet de classe Joueur à ajouter
     * @return false if list over max size
     */
    public boolean addPlayer(Player obj)
    {
        if (list.size() >= maxPlayer) {
            LoggerUtil.error("Erreur lors de l'ajout du joueur dans le vecteur");
            return false;
        }
        list.addElement(obj);
        LoggerUtil.info("L'ajout du joueur dans le vecteur fut un succès!");
        return true;
    }

    /**
     * Retire un joueur du vecteur à partir de l'index
     * @param index Index du joueur à retirer
     * @return faux si index out of bound sinon vrai
     */
    public boolean removePlayer(int index)
    {
        if (list.isEmpty() || list.size() <= index)
        {
            LoggerUtil.error("Erreur lors du retrait du joueur dans le vecteur");
            return false;
        }
        LoggerUtil.info("Le retrait du joueur dans le vecteur fut un succès");
        list.remove(index);
        return true;
    }

    /**
     * Retire un joueur du vecteur à partir de l'index
     * @param player Joueur à retirer
     * @return faux si Joueur n'est pas dans le vecteur sinon vrai
     */
    public boolean removePlayer(Player player)
    {
        if (list.isEmpty())
        {
            LoggerUtil.error("Erreur lors du retrait du joueur dans le vecteur");
            return false;
        }
        LoggerUtil.info("Le retrait du joueur dans le vecteur fut un succès");
        return list.remove(player);
    }

    /**
     * Get le joueur dans le vecteur
     * @param index index du vecteur
     * @return retourne le joueur ou null si index out of bounds
     */
    public Player getPlayer(int index)
    {
        try
        {
            return list.get(index);
        }
        catch (Exception e)
        {
            LoggerUtil.error("Erreur lors du getPlayer");
            return null;
        }
    }

    /**
     * Get l'index du Joueur dans le vecteur
     * @param player Joueur ou l'on veut l'index
     * @return l'index du joueur ou -1 si pas trouvé
     */
    public int getIndex(Player player)
    {
        return list.indexOf(player);
    }

    /**
     * Affiche ce que contient la liste pour tester
     */
    public void printList() {
        if (list.size() <= 0) System.out.println("Liste vide");
        else {
            System.out.println("------LISTE------");
            System.out.printf("Size = %d\nMax = %d\n",getSize(),getMaxPlayer());
            for (int i = 0; i < list.size(); i++) {
                getPlayer(i).printPlayer();
            }
            System.out.println("------FIN------");
        }
    }

    /**
     *  Get la quantité max de joueur possible dans le vecteur
     * @return quantité max de joueur possible dans le vecteur
     */
    public int getMaxPlayer() {
        return maxPlayer;
    }

    /**
     * Donne une nouvelle taille de joueur maximum dans le vecteur
     * @param max quantité de joueur maximum
     * @return faux si max est plus petit que la taille du vecteur sinon vrai
     */
    public boolean setMaxPlayer(int max) {
        if(max >= getSize())
        {
            maxPlayer = max;
            LoggerUtil.info("Changement du nombre maximum de joueur dans le vecteur");
            return true;
        }
        LoggerUtil.error("Erreur dans le changeent du nombre maximum de joueur dans le vecteur");
        return false;
    }

    /**
     * Get la quantité de Joueur dans le vecteur
     * @return quantité de joueur dans le vecteur
     */
    public int getSize()
    {
        return list.size();
    }
}



