package ca.usherbrooke.fgen.api.backend.BdTables.Stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;

public class StatPlayer extends Stat {

    private int idPlayer;
    private int idLeaguePlayer;
    private int idTeam;

    public StatPlayer(){

    }

    public StatPlayer(Integer id, Integer idStatStatement, String value,
                      Integer idMatch, Integer idSeason, Integer idPlayer,
                      Integer idLeague, Integer idTeam, Integer idLeaguePlayer,
                      Integer idSport) {
        super(id, idStatStatement, value, idMatch, idSeason, idLeague, idSport);
        this.idLeaguePlayer = idLeaguePlayer;
        this.idTeam = idTeam;
        this.idPlayer = idPlayer;
    }

    public int getIdPlayer() {
        return this.idPlayer;
    }
    public int getIdLeaguePlayer() {
        return this.idLeaguePlayer;
    }
    public int getIdTeam() {
        return this.idTeam;
    }
}
