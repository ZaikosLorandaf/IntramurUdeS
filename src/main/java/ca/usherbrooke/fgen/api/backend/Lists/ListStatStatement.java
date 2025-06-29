package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

import java.util.List;

public class ListStatStatement extends ListTemplate<StatStatement, String>{
    public ListStatStatement() {
        LoggerUtil.info("Création de la liste des énoncés de statistiques.");
    }

    public boolean addStatStatement(StatStatement statStatement) {

        switch (addItem(statStatement)) {
            case 1:
                LoggerUtil.info("Ajout de l'énoncé: " + statStatement);
                return true;
            case 0:
                LoggerUtil.warning("L'énoncé " + statStatement + " est déjà dans présent.");
                return false;
            default:
                return false;
        }
    }

    /**
     * Ajout plusieurs énoncés de stat dans le veteur à partir d'une liste
     *
     * @param statStatements liste d'Objet à ajouter
     *
     * @return Le nombre d'énoncés ajoutées
     */
    public int addStatStatement(List<StatStatement> statStatements){
        return addItems(statStatements);
    }


    /**
     * Retire un énoncé du vecteur à partir de l'index
     *
     * @param id Id de l'énoncé à retirer
     *
     * @return faux si index out of bound sinon vrai
     */
    public boolean removeStatStatement(int id) {
        if (removeItem(id)){
            LoggerUtil.warning("Retrait de l'énoncé " + getItem(id) + "(id: " + id + ").");
            return true;
        } else {
            LoggerUtil.warning("Échec du retrait de l'énoncé " + getItem(id) + "(id: " + id + ").");
            return false;
        }
    }

    /**
     * Retire un énoncé du vecteur à partir de l'objet
     *
     * @param statStatement Énoncé à retirer
     *
     * @return faux si l'énoncé n'est pas dans le vecteur sinon vrai
     */
    public boolean removeStatStatement(StatStatement statStatement)
    {
        return removeStatStatement(statStatement.getId());
    }



    @Override
    int getId(StatStatement item) {
        return item.getId();
    }

    @Override
    String getName(StatStatement item) {
        return item.getStatement();
    }
}
