package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatTeam;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.mapper.StatTeamMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/stat/team")
public class StatTeamService extends TemplateService<StatTeam> {
    @Inject
    StatTeamMapper statTeamMapper;

    @GET
    public List<StatTeam> getStatsTeam(){
        List<StatTeam> statsTeam = getItems();
        return statsTeam;
    }

    @POST
    public StatTeam addStatTeam(){
        return new StatTeam();
    }

    @Override
    protected List<StatTeam> selectAll() {
        List<StatTeam> listStat = statTeamMapper.selectAll();
        for(StatTeam stat : listStat){
            stat.initFromId();
        }
        return listStat;
    }

    @Override
    protected StatTeam selectOne(Integer id) {
        StatTeam statTeam = statTeamMapper.selectOne(id);
        statTeam.initFromId();
        return statTeam;
    }

    @Override
    protected void add(StatTeam item) {
        Sport sport = item.getSport();
        League league = sport.getListLeague().getLeague(item.getIdLeagueTeam());
        Team team = league.getListTeam().getTeam(item.getIdTeam());
        team.getListStat().addStat(item);
    }

    @Override
    protected void insert(StatTeam item) {

    }

    @Override
    protected void setName(StatTeam item) {

    }

    @Override
    public int getLastId() {
        return this.statTeamMapper.getLastId();
    }
}
