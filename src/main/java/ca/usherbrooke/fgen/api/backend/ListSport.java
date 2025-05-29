package ca.usherbrooke.fgen.api.backend;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class ListSport {
    private Map<Integer, Sport> mapId;
    private Map<String, Integer> mapNomId;
    private static Map<Integer, Team> mapTeamLeague = new HashMap<>(); // Convertion de id_Team -> id_League
    private static Map<Integer, League> mapLeagueSport = new HashMap<>(); // Convertion de id_League -> id_Sport


    public ListSport() {
        this.mapId = new HashMap<>();
        this.mapNomId = new HashMap<>();
        LoggerUtil.info("Création du vecteur de sport");
    }

    /**
     * Méthode pour ajouter un sport à la liste
     * @param sport Le sport à ajouter
     * @return Le nombre de sports ajoutés
     */
    public int addSport(Sport sport) {
        if (!this.mapId.containsKey(sport.getId()) && !this.mapNomId.containsKey(sport.getName())) {
            this.mapId.put(sport.getId(), sport);
            this.mapNomId.put(sport.getName(), sport.getId());
            LoggerUtil.info("Ajout du sport " + sport.getName());
            return 1;
        } else {
            LoggerUtil.warning("Le id ou le nom du sport " + sport.getName() + " (" + sport.getId() + ") existe déjà.");
            return 0;
        }
    }


    /**
     * Ajouter une liste de sports à la liste
     * @param sports La liste des sports à ajouter
     * @return Le nombre de sports réelement ajoutés
     */
    public int addSports(List<Sport> sports) {
        int counter = 0;
        for (Sport sport : sports)
            counter += this.addSport(sport);

        return counter;
    }

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

    public static League removeLeagueMap(League league)
    {
        return ListSport.mapLeagueSport.remove(league.getId());
    }

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

    public static Team removeTeamMap(Team team)
    {
         return ListSport.mapTeamLeague.remove(team.getId());
    }

    public boolean removeSport(int id) {
        if(this.mapId.containsKey(id) && this.mapNomId.containsKey(this.mapId.get(id).getName())) {
            LoggerUtil.warning("Retrait du sport " + this.mapId.get(id).getName() + "(id: " + id + ").");
            this.mapNomId.remove(this.mapId.get(id).getName());
            this.mapId.remove(id);
            return true;
        }
        else {
            LoggerUtil.warning("Échec du retrait du sport " + this.mapId.get(id).getName() + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Méthode pour retirer un sport
     * @param sport Sport à retirer
     * @return Vrai si le joueur est retiré, sinon false
     */
    public boolean removeSport(Sport sport) {
        return this.removeSport(sport.getId());
    }


    /**
     * Méthode pour aller chercher un sport dans la liste selon son id
     * @param id Id du sport à aller chercher
     * @return Le sport s'il a été trouvé, sinon null
     */
    public Sport getSport(int id) {
        Sport sport = this.mapId.getOrDefault(id, null);
        return sport;
    }

    public Sport getSport(String nom) {
        return this.mapId.getOrDefault(this.mapNomId.get(nom), null);
    }

    public List<Sport> getAllSports() {
        return new ArrayList<>(this.mapId.values());
    }



    public int getSize() {
        return this.mapId.size();
    }

    public Map<Integer, Sport> getMapSports() {
        return this.mapId;
    }

    public boolean checkExistSport(Sport sport) {
        return this.mapId.containsKey(sport.getId());
    }

    public League getLeague(int id) {
        return mapLeagueSport.getOrDefault(id, null);
    }

    public Team getTeam(int id) {
        return mapTeamLeague.getOrDefault(id, null);
    }
}
