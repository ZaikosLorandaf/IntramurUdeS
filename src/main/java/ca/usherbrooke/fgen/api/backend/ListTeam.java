package ca.usherbrooke.fgen.api.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ListTeam {

    private Map<Integer, Team> mapId;

    /**
     * Constructeur de base
     */
    public ListTeam() {
        this.mapId = new HashMap<>();
        LoggerUtil.info("Création de la liste d'équipe");
    }




    /**
     * Ajouter une équipe au vecteur
     *
     * @param team L'équipe à ajouter
     */
    public boolean addTeam(Team team) {
        if (!mapId.containsKey(team.getId())) {
            mapId.put(team.getId(), team);
            LoggerUtil.info("Ajout de l'équipe " + team.getName());
            return true;
        }
        else {
            LoggerUtil.warning("Le id de l'équipe " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
            return false;
        }
    }

    /**
     * Ajouter une liste équipe à la liste
     * @param teams Liste des équipes à ajouter
     * @return
     */
    public boolean addTeam(List<Team> teams) {
        for (Team team : teams) {
            addTeam(team);
        }
        return true;
    }



    public boolean removeTeam(int id) {
        if(mapId.containsKey(id)) {
            LoggerUtil.warning("Retrait de l'équipe " + mapId.get(id).getName() + "(id: " + id + ").");
            mapId.remove(id);
            return true;
        }
        else {
            LoggerUtil.warning("Échec du retrait de l'équipe " + mapId.get(id).getName() + "(id: " + id + ").");
            return false;
        }
    }


    /**
     * Retirer une équipe de la liste en fonction de l'équipe
     *
     * @param team Équipe à retirer
     * @return true si une équipe a été retirée, sinon false
     */
    public boolean removeTeam(Team team) {
        return removeTeam(team.getId());
    }



    /**
     * GetTeam
     * @param id id de l'équipe
     * @return Objet de type Team si l'index est valide sinon retourne null
     */
    public Team getTeam(int id)
    {
        return mapId.getOrDefault(id, null);
    }

    /**
     * Fonction test de la classe Team
     */
    public void printListTeam()
    {
        if(mapId.size() <= 0)
        {
            System.out.println("Pas d'équipe");
            return;
        }
        System.out.println("-----base.ListTeam-----");
        for(int i : mapId.keySet())
        {
            getTeam(i).printTeam();
        }
        System.out.println("-----Fin-----");
    }


    /**
     * Enleve toutes les équipes dans la liste
     */
    public void clearList(){
        this.mapId.clear();
    }

    public int getSize()
    {
        return mapId.size();
    }
}
