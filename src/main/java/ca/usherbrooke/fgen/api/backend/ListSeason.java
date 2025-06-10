package ca.usherbrooke.fgen.api.backend;

import org.postgresql.core.Tuple;

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

        switch (addItem(season)){
            case 0:
                LoggerUtil.info("Ajout de la saison " + season.toString());
                return true;
            case 1:
                LoggerUtil.warning("Le id de la saison " + season.toString() + " (" + season.getId() + ") est déjà dans présent.");
                return false;
            default:
                return false;
        }
    }

    /**
     * Ajout plusieurs joueur dans le veteur a partir d'une liste de joueur
     *
     * @param seasons liste d'Objet de classe Joueur a ajouter
     *
     * @return false if list over max size
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
        if (removeItem(id)){
            LoggerUtil.warning("Retrait de la saison " + getItem(id).toString() + "(id: " + id + ").");
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait de la saison " + getItem(id).toString() + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Retire un joueur du vecteur à partir de l'index
     *
     * @param season Joueur à retirer
     *
     * @return faux si Joueur n'est pas dans le vecteur sinon vrai
     */
    public boolean removeSeason(Season season)
    {
        return removeSeason(season.getId());
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

}
