package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.Stat;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import java.util.List;

public class ListStat extends ListTemplate<Stat, String>{
    public ListStat() {
        LoggerUtil.info("Création de la liste des énoncés de statistiques.");
    }

    public boolean addStat(Stat stat) {

        switch (addItem(stat)) {
            case 1:
                LoggerUtil.info("Ajout de la statistique: " + stat);
                return true;
            case 0:
                LoggerUtil.warning("La statistique " + stat + " est déjà dans présente.");
                return false;
            default:
                return false;
        }
    }

    /**
     * Ajout plusieurs stats dans le vecteur à partir d'une liste
     *
     * @param stats liste d'Objet à ajouter
     *
     * @return Le nombre de stats ajoutées
     */
    public int addStat(List<Stat> stats){
        return addItems(stats);
    }


    /**
     * Retire une stat du vecteur à partir de l'id
     *
     * @param id Id de la stat à retirer
     *
     * @return faux si id pas présent sinon vrai
     */
    public boolean removeStat(int id) {
        if (removeItem(id)){
            LoggerUtil.warning("Retrait de la statistique " + getItem(id) + "(id: " + id + ").");
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait de la statistique " + getItem(id) + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Retire une stat du vecteur à partir de l'objet
     *
     * @param stat Stat à retirer
     *
     * @return faux si la stat n'est pas dans le vecteur sinon vrai
     */
    public boolean removeStat(Stat stat)
    {
        return removeStat(stat.getId());
    }



    @Override
    int getId(Stat item) {
        return item.getId();
    }

    @Override
    String getName(Stat item) {
        return item.getStatStatement().getAcronym() + ": " + item.getValue() + "; Match: " + item.getMatch().getDate() + " " + item.getMatch().getBeginTime();
    }
}
