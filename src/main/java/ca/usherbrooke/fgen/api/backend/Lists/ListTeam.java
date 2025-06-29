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
        return addItem(team);
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
        return removeItem(id);
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

    // Methodes affichage
    @Override
    void logAddSuccess(Team team) {
        ListSport.addTeamMap(team);
        LoggerUtil.info("Ajout de l'équipe " + team.getName());
    }

    @Override
    void logAddFailure(Team team){
        LoggerUtil.warning("Le id de l'équipe " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
    }

    @Override
    void logRemoveSuccess(int id){
        Team team = getItem(id);
        for(int i: team.getListPlayer().getPlayerIds())
            team.getListPlayer().removePlayer(i);

        LoggerUtil.warning("Retrait de l'équipe " + team.getName() + "(id: " + id + ").");
    }

    @Override
    void logRemoveFailure(int id){
        Team team = getItem(id);
        for(int i: team.getListPlayer().getPlayerIds())
            team.getListPlayer().removePlayer(i);

        LoggerUtil.warning("Échec du retrait de l'équipe " + team.getName() + "(id: " + id + ").");
    }

    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    @Override
    void printItem(int index){
        getTeam(index).printTeam();
    }
}
