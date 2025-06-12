package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;

public class ListMatch extends ListTemplate<Match, String> {

    public boolean addMatch(Match match) {
        switch (addItem(match)) {
            case 0:
                LoggerUtil.info("Ajout du match " + match.getId());
                return true;
            case 1:
                LoggerUtil.warning("Le id du match " + match.getId() + " est déjà dans présent.");
                return false;
            default:
                return false;
        }

    }

    public Match getMatch(int id) {
        return getItem(id);
    }

    public Match getMatch(Match match) {
        return getItem(match.getId());
    }

    public boolean removeMatch(Match match) {
        return this.removeItem(match.getId());
    }

    public boolean removeMatch(int id) {
        return this.removeItem(id);
    }


    @Override
    public int getId(Match item) {
        return item.getId();
    }

    @Override
    public String getName(Match item) {
        return item.toString();
    }
}
