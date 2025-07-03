package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.Stat;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatPlayer;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.DTO.stats.StatPlayerDTO;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.mapper.StatPlayerMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Path("/api/stat/player")
public class StatPlayerService extends TemplateService<StatPlayer> {
    @Inject
    StatPlayerMapper statPlayerMapper;
    @Inject
    OGClass ogClass;


    @GET
    public List<StatPlayerDTO> getStatsPlayer(){
        List<StatPlayer> statsPlayer = getItems();
        List<StatPlayerDTO> statsDTO = StatPlayerDTO.getListStatPlayerDTO(statsPlayer);
        return statsDTO;
    }

    @Override
    protected List<StatPlayer> selectAll() {
        List<StatPlayer> listStat = statPlayerMapper.selectAll();
        for (StatPlayer statPlayer : listStat) {
            statPlayer.initFromId();
        }
        return listStat;
    }

    @Override
    protected StatPlayer selectOne(Integer id) {
        StatPlayer statPlayer = statPlayerMapper.selectOne(id);
        statPlayer.initFromId();
        return statPlayer;
    }

    @Override
    protected void add(StatPlayer item) {
        Sport sport = item.getSport();
        League league = sport.getListLeague().getLeague(item.getIdLeaguePlayer());
        Team team = league.getListTeam().getTeam(item.getIdTeam());
        Player player = team.getListPlayer().getPlayer(item.getIdPlayer());
        player.getListStat().addStat(item);
    }

    @Override
    protected void insert(StatPlayer item) {

    }

    @Override
    protected void setName(StatPlayer item) {

    }
}
