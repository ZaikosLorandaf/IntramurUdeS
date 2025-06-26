package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.StatStatement;
import ca.usherbrooke.fgen.api.mapper.stats.StatStatementMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/statStatement")
public class StatStatementService extends TemplateService<StatStatement> {
    @Inject
    StatStatementMapper statStatementMapper;


    // Redirection vers les fonctions template
    @GET
    public List<StatStatement> getStatStatements(){
        List<StatStatement> statStatements = getItems();
        return statStatements;
    }

    // Implementation des fonctions du template
    @Override
    protected List<StatStatement> selectAll() {
        List<StatStatement> listStatStatements = statStatementMapper.selectAll();
        return listStatStatements;
    }

    @Override
    protected StatStatement selectOne(Integer id) {
        return statStatementMapper.selectOne(id);
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
