package ca.usherbrooke.fgen.api.service.objectServices.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.mapper.StatStatementMapper;
import ca.usherbrooke.fgen.api.service.objectServices.TemplateService;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/stat/statement")
public class StatStatementService extends TemplateService<StatStatement> {
    @Inject
    StatStatementMapper statStatementMapper;
    @Inject
    OGClass ogClass;


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
        ogClass.getStatStatementSingleton().getStatStatement().addStatStatement(item);
    }

    @Override
    protected void insert(StatStatement item) {

    }

    @Override
    protected void setName(StatStatement item) {

    }

    @Override
    protected int getLastId() {
        return this.statStatementMapper.getLastId();
    }
}
