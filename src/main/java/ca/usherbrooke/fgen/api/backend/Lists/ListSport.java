package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class ListSport extends ListTemplate<Sport, String> {
    private static Map<Integer, Team> mapTeamLeague = new HashMap<>(); // Convertion de id_Team -> id_League
    private static Map<Integer, League> mapLeagueSport = new HashMap<>(); // Convertion de id_League -> id_Sport


    /**
     * Constructeur par defaut vide.
     */
    public ListSport() {
        LoggerUtil.info("Création du vecteur de sport");
    }

    /**
     * Méthode pour ajouter un sport à la liste
     *
     * @param sport Le sport à ajouter
     *
     * @return Le nombre de sports ajoutés
     */
    public boolean addSport(Sport sport) {
        return addItem(sport);
    }

    /**
     * Ajouter une liste de sports à la liste
     *
     * @param sports La liste des sports à ajouter
     *
     * @return Le nombre de sports réelement ajoutés
     */
    public int addSports(List<Sport> sports) {
        return addItems(sports);
    }

    /**
     * Ajouter une liste de sports à la liste
     *
     * @param league Ajouter un ligue dans le map qui converti league en sport
     *
     * @return Le nombre de sports reellement ajoutés
     */
    public static boolean addLeagueMap(League league) {
        if (!ListSport.mapLeagueSport.containsKey(league.getId())) {
            ListSport.mapLeagueSport.put(league.getId(), league);
            LoggerUtil.info("Ajout du league " + league.getName());
            return true;
        }
        else {
            LoggerUtil.warning("Le id du league " + league.getName() + " (" + league.getId() + ") est déjà dans présent.");
            return false;
        }
    }

    public static int addLeagueMap(List<League> leagues) {
        int counter = 0;
        for (League league : leagues) {
            if(addLeagueMap(league)){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Méthode pour aller chercher un objet league à partir de son id dans la map générale
     * @param id Id de la ligue
     * @return L'objet ligue
     */
    public static League getLeagueById(int id) {
        return ListSport.mapLeagueSport.get(id);
    }

    public static List<League> getLeagues() {
        return new ArrayList<League>(ListSport.mapLeagueSport.values());
    }


    /**
     * retirer une ligue du map qui converti ligue en sport
     *
     * @param league objet ligue a retirer
     *
     * @return l'objet retire
     */
    public static League removeLeagueMap(League league)
    {
        return ListSport.mapLeagueSport.remove(league.getId());
    }

    /**
     * Ajouter une equipe dans le map qui converti equipe en ligue
     *
     * @param team objet equipe a ajouter
     *
     * @return insertion reussi?
     */
    public static boolean addTeamMap(Team team) {
        if (!ListSport.mapTeamLeague.containsKey(team.getId())) {
            ListSport.mapTeamLeague.put(team.getId(), team);
            return true;
        }
        else {
            LoggerUtil.warning("Le id de la ligue " + team.getName() + " (" + team.getId() + ") est déjà dans présent.");
            return false;
        }
    }


    public static Team getTeamById(int id) {
        return ListSport.mapTeamLeague.get(id);
    }


    public static int addTeamMap(List<Team> teams) {
        int counter = 0;
        for (Team team : teams) {
            addTeamMap(team);
            counter++;
        }
        return counter;
    }


    /**
     * retirer une equipe du map qui converti equipe en ligue
     *
     * @param team objet team a retirer
     *
     * @return l'objet retire
     */
    public static Team removeTeamMap(Team team)
    {
        return ListSport.mapTeamLeague.remove(team.getId());
    }

    /**
     * retirer un sport de la liste
     *
     * @param id id du sport a retirer
     *
     * @return retrait reussi?
     */
    public boolean removeSport(int id) {
        return removeItem(id);
    }

    /**
     * Méthode pour retirer un sport
     *
     * @param sport Sport à retirer
     *
     * @return Vrai si le joueur est retiré, sinon false
     */
    public boolean removeSport(Sport sport) {
        return this.removeSport(sport.getId());
    }


    // Getter
    public Sport getSport(int id) { return getItem(id); }
    public Sport getSport(String nom) { return getItem(nom); }
    public List<Sport> getAllSports() { return getAllItems(); }
    public int getSize() { return getMapSize(); }
    public Map<Integer, Sport> getMapSports() { return getMapItems(); }
    public boolean checkExistSport(Sport sport) { return checkItemExist(sport); }

    public League getLeague(int id) { return mapLeagueSport.getOrDefault(id, null); }
    public Team getTeam(int id) { return mapTeamLeague.getOrDefault(id, null); }

    public int getId(Sport sport){ return sport.getId(); }
    public String getName(Sport sport) { return sport.getName(); }

    // Methodes affichage
    @Override
    void logAddSuccess(Sport sport) {
        LoggerUtil.warning("Le id ou le nom du sport " + sport.getName() + " (" + sport.getId() + ") existe déjà.");;
    }
    @Override
    void logAddFailure(Sport sport){
        LoggerUtil.info("Ajout du sport " + sport.getName());
    }

    @Override
    void logRemoveSuccess(int id){
        LoggerUtil.info("Retrait du sport avec succès");
        //        LoggerUtil.warning("Retrait du sport " + getSport(id).getName() + "(id: " + id + ").");
    }

    @Override
    void logRemoveFailure(int id){
        LoggerUtil.warning("Échec du retrait du sport " + getSport(id).getName() + "(id: " + id + ").");
    }

    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    @Override
    void printItem(int index){
    }
}
