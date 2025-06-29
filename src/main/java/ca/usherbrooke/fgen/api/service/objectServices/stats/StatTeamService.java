package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;

import javax.ws.rs.Path;
import java.util.List;

@Path("/api/stat/team")
public class StatTeamService extends TemplateService<StatStatement> {
    @Override
    protected List<StatStatement> selectAll() {
        return List.of();
    }

    @Override
    protected StatStatement selectOne(Integer id) {
        return null;
    }

    @Override
    protected void add(StatStatement item) {

    }

    @Override
    protected void insert(StatStatement item) {

    }

    @Override
    protected void setName(StatStatement item) {

    }
}
