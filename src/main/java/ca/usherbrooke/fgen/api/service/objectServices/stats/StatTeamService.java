package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatTeam;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.DTO.stats.StatTeamDTO;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.mapper.StatTeamMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;
import ca.usherbrooke.fgen.api.service.postClass.addStatTeam;
import io.smallrye.common.constraint.NotNull;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/stat/team")
public class StatTeamService extends TemplateService<StatTeam> {
    @Inject
    StatTeamMapper statTeamMapper;
    @Inject
    OGClass ogClass;

    @GET
    public List<StatTeamDTO> getStatsTeam(){
        List<StatTeam> statsTeam = getItems();
        List<StatTeamDTO> statsDTO = StatTeamDTO.mapListToDTOStatTeam(statsTeam);
        return statsDTO;
    }

    @POST
    @Path("add")
    public String addStatTeam(@NotNull addStatTeam stat){
        return ogClass.getStatTeamSingleton().add(stat.sport,stat.ligue,stat.team,stat.key,stat.value);
    }

    public void addStatTeam(@NotNull StatTeam stat){
        add(stat);
    }

    @POST
    @Path("remove")
    public String removeStatTeam(@NotNull addStatTeam stat)
    {
        return ogClass.getStatTeamSingleton().remove(stat.sport,stat.ligue,stat.team,stat.key);
    }

    public void remove(StatTeam item)
    {
        Sport sport = item.getSport();
        League league = sport.getListLeague().getLeague(item.getIdLeagueTeam());
        Team team = league.getListTeam().getTeam(item.getIdTeam());
        team.getListStat().removeStat(item);
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
        insert(item);
    }

    @Override
    protected void insert(StatTeam item) {
        statTeamMapper.insert(item);
    }

    @Override
    protected void setName(StatTeam item) {

    }

    @Override
    public int getLastId() {
        return this.statTeamMapper.getLastId();
    }
}
