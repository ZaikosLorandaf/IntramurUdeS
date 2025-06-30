package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.Stat;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatPlayer;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.mapper.StatPlayerMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Path("/api/stat/player")
public class StatPlayerService extends TemplateService<StatPlayer> {
    @Inject
    StatPlayerMapper statPlayerMapper;


    @GET
    public List<StatPlayer> getStatsPlayer(){
        List<StatPlayer> statsPlayer = getItems();
        return statsPlayer;
    }

    @Override
    protected List<StatPlayer> selectAll() {
        List<StatPlayer> listStat = statPlayerMapper.selectAll();

        return listStat;
    }

    @Override
    protected StatPlayer selectOne(Integer id) {
        StatPlayer statPlayer = statPlayerMapper.selectOne(id);
        return statPlayer;
    }

    @Override
    protected void add(StatPlayer item) {

    }

    @Override
    protected void insert(StatPlayer item) {

    }

    @Override
    protected void setName(StatPlayer item) {

    }
}
