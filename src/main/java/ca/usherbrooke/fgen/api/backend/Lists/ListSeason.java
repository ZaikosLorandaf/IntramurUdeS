package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.BdTables.Season;

import java.util.List;

public class ListSeason extends ListTemplate<Season, String>
{
    public ListSeason() {
        LoggerUtil.info("Création de la liste de saisons");
    }

    /**
     * Ajout d'un joueur dans le veteur
     *
     * @param season Objet de classe Joueur a ajouter
     *
     * @return false if list over max size
     */
    public boolean addSeason(Season season)
    {
        return addItem(season);
    }

    /**
     * Ajout plusieurs joueur dans le veteur a partir d'une liste de joueur
     *
     * @param seasons liste d'Objet de classe Joueur a ajouter
     *
     * @return Le nombre de saisons ajoutées
     */
    public int addSeason(List<Season> seasons) {
        return addItems(seasons);
    }

    /**
     * Retire un joueur du vecteur à partir de l'index
     *
     * @param id Id du joueur à retirer
     *
     * @return faux si index out of bound sinon vrai
     */
    public boolean removeSeason(int id) {
        return removeItem(id);
    }

    /**
     * Retire un joueur du vecteur à partir de l'objet
     *
     * @param season Joueur à retirer
     *
     * @return faux si Joueur n'est pas dans le vecteur sinon vrai
     */
    public boolean removeSeason(Season season)
    {
        return removeSeason(season.getId());
    }

    // Getter
    public int getSize()
    {
        return getMapSize();
    }
    public Season getSeason(int id)
    {
        return getItem(id);
    }

    public List<Integer> getSeasonIds() { return getMapIds(); }

    public Season getSeasonByNumber(Integer number) { return getItem(number); }

    public int getId(Season season) { return season.getId(); }
    public String getName(Season season) {
        return season.toString();
    }


    // Methodes affichage
    @Override
    void logAddSuccess(Season season) {
        LoggerUtil.info("Ajout de la saison " + season.toString());
    }

    @Override
    void logAddFailure(Season season){
        LoggerUtil.warning("Le id de la saison " + season.toString() + " (" + season.getId() + ") est déjà dans présent.");
    }

    @Override
    void logRemoveSuccess(int id){
        LoggerUtil.warning("Retrait de la saison " + getItem(id).toString() + "(id: " + id + ").");
    }

    @Override
    void logRemoveFailure(int id){
        LoggerUtil.warning("Échec du retrait de la saison " + getItem(id).toString() + "(id: " + id + ").");
    }

    /**
     * Affiche ce que contient la liste dans la console pour tester
     */
    public void printList() {
        if (this.getSize() <= 0)
            System.out.println("Liste vide");
        else {
            System.out.println("------LISTE------");
            System.out.printf("Size = %d\n",getSize());
            for (int i : getMapIds()) {
                getSeason(i).printSeason();
            }
            System.out.println("------FIN------");
        }
    }

    /**
     * Affiche les equipes de la liste dans la console. Fonction test de la classe Team
     */
    @Override
    void printItem(int index){
    }
}
