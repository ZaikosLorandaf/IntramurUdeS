package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.Stat;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import java.util.List;

public class ListStat extends ListTemplate<Stat, String>{
    public ListStat() {
        LoggerUtil.info("Création de la liste des énoncés de statistiques.");
    }

    public boolean addStat(Stat stat) {
        return this.addItem(stat);
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
        return this.removeItem(id);
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
        String returnString = item.getStatStatement().getStatement();
//                .getAcronym() + ": " + item.getValue() + "; Match: ";
//        if(item.getMatch() != null){
//            returnString += item.getMatch().getDate() + " " + item.getMatch().getBeginTime();
//        }
//        else{
//            returnString += "General";
//        }
        return returnString;
    }

    public String getUniqueName(Stat item) {
        String returnString = item.getStatStatement().getAcronym() + ": " + item.getValue() + "; Match: ";
        if(item.getMatch() != null){
            returnString += item.getMatch().getDate() + " " + item.getMatch().getBeginTime();
        }
        else{
            returnString += "General";
        }
        return returnString;
    }

    @Override
    void printItem(int indexItem) {
    }

    @Override
    void logAddSuccess(Stat item) {
        LoggerUtil.info("Ajout de la statistique: " + item);
    }

    @Override
    void logAddFailure(Stat item) {
        LoggerUtil.warning("La statistique " + item + " est déjà dans présente.");
    }

    @Override
    void logRemoveSuccess(int id) {
        LoggerUtil.warning("Retrait de la statistique " + getItem(id) + "(id: " + id + ").");
    }

    @Override
    void logRemoveFailure(int id) {
        LoggerUtil.warning("Échec du retrait de la statistique " + getItem(id) + "(id: " + id + ").");
    }
}
