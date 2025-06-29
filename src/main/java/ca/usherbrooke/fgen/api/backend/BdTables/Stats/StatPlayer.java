package ca.usherbrooke.fgen.api.backend.BdTables.Stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;

public class StatPlayer extends Stat {
    public StatPlayer(Integer id, Integer idStatStatement, String value,
                      Integer idMatch, Integer idSeason, Integer idPlayer,
                      Integer idLeague, Integer idTeam, Integer idLeaguePlayer,
                      Integer idSport) {
        super(id, idStatStatement, value, idMatch, idSeason, idLeague);
        Sport sport = this.ogClass.getSportSingleton().getSportList().getSport(idSport);
        League league = sport.getListLeague().getLeague(idLeaguePlayer);
        Team team = league.getListTeam().getTeam(idTeam);
        Player player = team.getListPlayer().getPlayer(idPlayer);
        player.getListStat().addStat(this);
    }
}
