package ca.usherbrooke.fgen.api.backend.BdTables.Stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;

public class StatTeam extends Stat {

    private int idTeam;
    private int idLeagueTeam;

    public StatTeam() {

    }

    public StatTeam(Integer id, Integer idStatStatement, String value,
                    Integer idMatch, Integer idSeason,
                    Integer idLeague, Integer idTeam, Integer idLeagueTeam,
                    Integer idSport) {
        super(id, idStatStatement, value, idMatch, idSeason, idLeague, idSport);
        this.idTeam = idTeam;
        this.idLeagueTeam = idLeagueTeam;

    }

    public int getIdTeam() {
        return idTeam;
    }
    public int getIdLeagueTeam() {
        return idLeagueTeam;
    }

    @Override
    public int getIdStatStatement() {
        return super.getIdStatStatement();
    }
}
