package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;

import java.util.List;

public class ListTeam extends ListTemplate<Team, String>  {
    /**
     * Constructeur par defaut vide.
     */
    public ListTeam() {
        LoggerUtil.info("Création de la liste d'équipe");
    }

    /**
     * Ajouter une équipe au vecteur
     *
     * @param team L'équipe à ajouter
     *
     * @return vrai si equipe ajoutee
     */
    public boolean addTeam(Team team) {

        switch (addItem(team)) {
            case 1:
                ListSport.addTeamMap(team);
                LoggerUtil.info("Ajout de l'équipe " + team.getName());
                return true;
            case 0:
                LoggerUtil.warning("Le id de l'équipe " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
                return false;
            default:
                return false;
        }
    }

    /**
     * Ajouter une liste équipe à la liste
     *
     * @param teams Liste des équipes à ajouter
     *
     * @return int
     */
    public int addTeam(List<Team> teams) {
        return addItems(teams);
    }

    public boolean removeTeam(int id) {
        Team team = getItem(id);
        for(int i: team.getListPlayer().getPlayerIds())
            team.getListPlayer().removePlayer(i);

        if (removeItem(id)) {
            LoggerUtil.warning("Retrait de l'équipe " + team.getName() + "(id: " + id + ").");
            ListSport.removeTeamMap(team);
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait de l'équipe " + team.getName() + "(id: " + id + ").");
            return false;
        }
    }


    /**
     * Retirer une équipe de la liste en fonction de l'équipe
     *
     * @param team Équipe à retirer
     *
     * @return true si une équipe a été retirée, sinon false
     */
    public boolean removeTeam(Team team) {
        return removeTeam(team.getId());
    }


    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    public void printListTeam() {
        if(getMapSize() <= 0) {
            System.out.println("Pas d'équipe");
            return;
        }
        System.out.println("-----base.ListTeam-----");
        for(int i : getMapIds()) {
            getTeam(i).printTeam();
        }
        System.out.println("-----Fin-----");
    }

    /**
     * Enleve toutes les équipes dans la liste
     */
    public void clearList(){
        clearMap();
    }


    // Getter
    public Team getTeam(int id) { return getItem(id); }
    public Team getTeam(String name) {return getItem(name); }
    public int getSize() {return getMapSize(); }

    public List<Integer> getTeamIds() { return getMapIds(); }

    public int getId(Team team) { return team.getId(); }
    public String getName(Team team) { return team.getName(); };
}
