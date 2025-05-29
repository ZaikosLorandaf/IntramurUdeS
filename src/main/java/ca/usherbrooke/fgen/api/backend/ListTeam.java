package ca.usherbrooke.fgen.api.backend;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ListTeam {

    @Inject
    OGClass ogClass;

    private Map<Integer, Team> mapId;
    private Map<String, Integer> mapNomId;

    /**
     * Constructeur de base
     */
    public ListTeam() {
        this.mapId = new HashMap<>();
        this.mapNomId = new HashMap<>();
        LoggerUtil.info("Création de la liste d'équipe");
    }

    /**
     * Ajouter une équipe au vecteur
     *
     * @param team L'équipe à ajouter
     */
    public boolean addTeam(Team team) {
        if (!this.mapId.containsKey(team.getId()) && !this.mapNomId.containsKey(team.getName())) {
            this.mapId.put(team.getId(), team);
            ListSport.addTeamMap(team);
            LoggerUtil.info("Ajout de l'équipe " + team.getName());
            return true;
        } else {
            LoggerUtil.warning("Le id de l'équipe " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
            return false;
        }
    }

    /**
     * Ajouter une liste équipe à la liste
     * @param teams Liste des équipes à ajouter
     * @return
     */
    public int addTeam(List<Team> teams) {
        int counter = 0;
        for (Team team : teams) {
            if (addTeam(team)) {
                counter++;
            }
        }
        return counter;
    }



    public boolean removeTeam(int id) {
        if(mapId.containsKey(id) && mapNomId.containsKey(mapId.get(id).getName())) {
            LoggerUtil.warning("Retrait de l'équipe " + mapId.get(id).getName() + "(id: " + id + ").");
            ListSport.removeTeamMap(mapId.get(id));
            mapNomId.remove(mapId.get(id).getName());
            mapId.remove(id);

            return true;
        } else {
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
     * Fonction test de la classe Team
     */
    public void printListTeam() {
        if(mapId.size() <= 0) {
            System.out.println("Pas d'équipe");
            return;
        }
        System.out.println("-----base.ListTeam-----");
        for(int i : mapId.keySet()) {
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


    // Getter
    public Team getTeam(int id)
    {
        return mapId.getOrDefault(id, null);
    }
    public Team getTeam(String name)
    {
        return this.getTeam(this.mapNomId.getOrDefault(name, null));
    }
    public int getSize()
    {
        return mapId.size();
    }
}
