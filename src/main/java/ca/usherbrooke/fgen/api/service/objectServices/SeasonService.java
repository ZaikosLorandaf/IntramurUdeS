package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.BdTables.Season;
import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.mapper.SeasonMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/season")
public class SeasonService extends TemplateService<Season> {
    @Inject
    SeasonMapper seasonMapper;
    @Inject
    OGClass ogClass;

    // Redirection vers les fonctions template
    @GET
    public List<Season> getSeasones(){
        List<Season> seasons = getItems();
        return seasons;
    }

    @GET
    @Path("{id}")
    public Season getSeason(@PathParam("id") Integer id) {
        Season season = getItem(id);
        return season;
    }


    @POST
    @Consumes("application/json")
    public void addSeason(Season season) {
        addItem(season);
    }


    // Implementation des fonctions du template
    protected List<Season> selectAll(){
        return seasonMapper.selectAll();
    }
    protected Season selectOne(Integer id){
        return seasonMapper.selectOne(id);
    }

    @Override
    protected void add(Season item) {

    }

    protected void insert(Season season){
        seasonMapper.insert(season);
    }

    @Override
    protected void setName(Season item) {

    }

    /**
     * Méthode pour aller chercher le prochain id de l'ajout
     * @return
     */
    public int getLastId()
    {
        return seasonMapper.getLastId();
    }
}