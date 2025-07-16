package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import java.util.*;

public class ListLeague extends ListTemplate<League, String> {

    /**
     * Constructeur par defaut vide.
     *
     */
    public ListLeague() {
        LoggerUtil.info("Création de la liste de league");
    }

    /**
     * Ajouter une ligue dans la liste
     *
     * @param league Ligue a ajouter de type Ligue
     *
     * @return True si la ligue a bien été ajoutée, sinon false
     */
    public boolean addLeague(League league) {
        if (league.getId() <= 0) {
            return false;
        }
        return addItem(league);
    }

    /**
     * Ajouter plusieurs ligues dans la liste à partir d'une liste de ligue
     *
     * @param leagues Ligue a ajouter de type Ligue
     *
     * @return Le nombre de ligues ajoutées
     */
    public int addLeague(List<League> leagues) {
        return addItems(leagues);
    }

    /**
     * Retirer une ligue du vecteur par son id
     *
     * @param id id de la ligue à retirer
     *
     * @return faux si le vecteur ne contient pas le
     */
    public boolean removeLeague(int id) {
        League ligue = getItem(id);
        if (ligue == null) {
            return false;
        }
        for (int i: ligue.getListTeam().getTeamIds())
            ligue.getListTeam().removeTeam(i);


        if (removeItem(id)) {
            LoggerUtil.warning("Retrait de la ligue " + ligue.getName() + "(id: " + id + ").");
            ListSport.removeLeagueMap(ligue);
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait de la ligue " + ligue.getName() + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Retirer une ligue du vecteur par son objet
     *
     * @param league objet league a retirer
     *
     * @return faux si le vecteur ne contient pas le
     */
    public boolean removeLeague(League league) {
        return removeLeague(league.getId());
    }


    // Getter
    public int getSize() { return getMapSize(); }
    public League getLeague(int id) { return getItem(id); }

    /**
     * Méthode pour aller chercher une ligue de la liste selon son nom unique
     * @param uniqueName Nom unique de la ligue (sport-ligue)
     * @return La ligue trouvée
     */
    public League getLeague(String uniqueName) {
        return getItem(uniqueName);
    }

    /**
     * Méthode pour aller chercher une ligue selon le nom de son sport et de la ligue
     * Construit le nom unique automatiquement
     * @param leagueName Nom de la ligue
     * @param sportName Nom du sport
     * @return La ligue trouvée
     */
    public League getLeague(String leagueName, String sportName) {
        return getItem(League.getUniqueName(leagueName, sportName));
    }
    public List<Integer> getLeagueIds() { return getMapIds(); }

    public int getId(League league){ return league.getId(); };
    public String getName(League league){
        return league.getUniqueName();
    };

    @Override
    protected boolean addItem(League league) {
        if(super.addItem(league)){
            ListSport.addLeagueMap(league);
            return true;
        }
        return false;
    }

    @Override
    protected boolean removeItem(int id){
        League league = getItem(id);
        if(super.removeItem(id)) {
            if (league == null) {
                LoggerUtil.error("La ligue " + id + " n'existe pas.");
                return false;
            }
            for (int i: league.getListTeam().getTeamIds()){
                league.getListTeam().removeTeam(i);
            }
            ListSport.removeLeagueMap(league);
            return true;
        }
        return false;
    }


    // Methodes affichage
    @Override
    void logAddSuccess(League league) {
        LoggerUtil.info("Ajout de la ligue " + league.getName());
    }
    @Override
    void logAddFailure(League league){
        LoggerUtil.warning("Le id ou le nom de la ligue " + league.getName() +
                " (" + league.getId() + ") existe déjà.");
    }

    @Override
    void logRemoveSuccess(int id){
        LoggerUtil.warning("Retrait de la ligue (id: " + id + ").");

    }

    @Override
    void logRemoveFailure(int id){
        LoggerUtil.warning("Échec du retrait de la ligue (id: " + id + ").");
    }

    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    @Override
    void printItem(int index){
    }
}
