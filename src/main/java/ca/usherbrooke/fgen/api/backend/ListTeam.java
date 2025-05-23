package ca.usherbrooke.fgen.api.backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ListTeam {
    private Vector<Team> list;
    private int maxTeam;
    private Map<String,Integer> dict = new HashMap<String,Integer>();

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
        if (team == null || dict.containsKey(team.getName()))
        {
            LoggerUtil.error("Impossible de rajouter l'équipe au vecteur");
            return false;
        }
        this.dict.put(team.getName(), list.size());
        this.list.addElement(team);
        LoggerUtil.info("Succès de l'ajout de l'équipe au vecteur");
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
        if (index >= this.list.size() || index < 0 || index >= this.maxTeam || !this.dict.containsKey(list.get(index).getName()))
        {
            LoggerUtil.error("Impossible de retirer l'équipe du vecteur");
            return false;
        }
        Map<String, Integer> tempDict = new HashMap<>();
        dict.remove(list.get(index).getName());
        this.list.remove(index);
        for (int i = 0; i < list.size(); i++) {
            if (i < index)
            {
                tempDict.put(list.get(i).getName() , i);
            }
            else{
                tempDict.put(list.get(i).getName() , i - 1);
            }
        }
        dict = tempDict;
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

    public Team getTeam(String name)
    {
        int index;
        if (dict.containsKey(name)) {
            index = dict.get(name);
        }
        else{
            return null;
        }
        return list.elementAt(index);
    }
}
