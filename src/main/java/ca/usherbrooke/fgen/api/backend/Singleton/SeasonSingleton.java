package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.Lists.ListSeason;

public class SeasonSingleton {
    ListSeason listSeasons;

    SeasonSingleton() {
        listSeasons = new ListSeason();
    }

    public ListSeason getListSeasons() {
        return this.listSeasons;
    }
}
