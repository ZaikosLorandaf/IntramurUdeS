package ca.usherbrooke.fgen.api.backend.base;

import java.util.Vector;

public class ListTeam {
    private Vector<Team> list;
    private int maxTeam;

    /**
     * Constructeur de base
     */
    public ListTeam() {
        maxTeam = 100;
        list = new Vector<Team>();
        LoggerUtil.info("Création du vecteur d'équipe");
    }

    /**
     * Constructeur paramétré avec la quantité d'équipe maximum
     *
     * @param max quantité d'équipe maximum
     */
    public ListTeam(int max) {
        maxTeam = max;
        list = new Vector<Team>();
        LoggerUtil.info("Création du vecteur d'équipe");
    }


    /**
     * Ajouter une équipe au vecteur
     *
     * @param team L'équipe à ajouter
     */
    public boolean addTeam(Team team) {
        if (team == null)
        {
            LoggerUtil.error("Impossible de rajouter l'équipe au vecteur");
            return false;
        }
        LoggerUtil.info("Succès de l'ajout de l'équipe au vecteur");
        this.list.addElement(team);
        return true;
    }

    /**
     * Retirer une équipe au vecteur en fonction de l'équipe
     *
     * @param team Équipe à retirer
     * @return true si une équipe a été retirée, sinon false
     */
    public boolean removeTeam(Team team) {
        LoggerUtil.warning("Tentative de retrait d'une équipe");
        return this.list.removeElement(team);
    }

    /**
     * Retirer une équipe au vecteur selon son index
     *
     * @param index L'index de l'équipe à retirer
     * @return L'équipe qui a été retirée
     */
    public boolean removeTeam(int index) {
        if (index >= this.list.size())
        {
            LoggerUtil.error("Impossible de retirer l'équipe du vecteur");
            return false;
        }
        this.list.remove(index);
        LoggerUtil.info("Retrait de l'équipe du vecteur avec succès");
        return true;
    }

    /**
     * GetTeam
     * @param index index de l'équipe
     * @return Objet de type Team si l'index est valide sinon retourne null
     */
    public Team getTeam(int index)
    {
        try{
            return list.get(index);
        }
        catch (Exception e)
        {
            LoggerUtil.error("Impossible de getTeam");
            return null;
        }
    }

    /**
     * Fonction test de la classe Team
     */
    public void printListTeam()
    {
        if(list.size() <= 0)
        {
            System.out.println("Pas d'équipe");
            return;
        }
        System.out.println("-----base.ListTeam-----");
        for(int i = 0; i < list.size(); i++)
        {
            getTeam(i).printTeam();
        }
        System.out.println("-----Fin-----");
    }


    /**
     * Enleve toutes les équipes dans la liste
     */
    public void wipeList(){
        this.list.removeAllElements();
    }

    public int getSize()
    {
        return list.size();
    }
}
