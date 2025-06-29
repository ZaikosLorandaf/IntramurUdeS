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
        switch (addItem(league)) {
            case 1:
                ListSport.addLeagueMap(league);
                LoggerUtil.info("Ajout de la ligue " + league.getName());
                return true;
            case 0:
                LoggerUtil.warning("Le id ou le nom de la ligue " + league.getName() +
                        " (" + league.getId() + ") existe déjà.");
                return false;
            default:
                return false;
        }
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
        League ligue =getItem(id);
        for (int i: ligue.getTeams().getTeamIds())
            ligue.getTeams().removeTeam(i);

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
    public League getLeague(String name) { return getItem(name); }
    public List<Integer> getLeagueIds() { return getMapIds(); }

    public int getId(League league){ return league.getId(); };
    public String getName(League league){
        return league.getName();
    };
}
