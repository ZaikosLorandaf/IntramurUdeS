package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import ca.usherbrooke.fgen.api.service.postClass.addSport;
import ca.usherbrooke.fgen.api.service.postClass.removeSport;
import io.smallrye.common.constraint.NotNull;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;


@Path("/api/sport")
public class SportService extends TemplateService<Sport> {
    @Inject
    SportMapper sportMapper;
    @Inject
    OGClass ogClass;

    // Methodes POST
    @POST
    @Consumes("application/json")
    public void addSport(Sport sport) {
        addItem(sport);
    }

    @POST
    @Path("addSport")
    public String addSport(@NotNull addSport sport) {
        return ogClass.sportSingleton().add(sport.nom);
    }

    @POST
    @Path("removeSport")
    public String removeSport(@NotNull removeSport sport ) {
        return ogClass.sportSingleton().remove(sport.name);
    }

    // Methodes GET
    @GET
    public List<Sport> getSports() {
        List<Sport> sports = getItems();
        return sports;
    }

    @GET
    @Path("{id}")
    public Sport getSport(@PathParam("id") Integer id) {
        return getItem(id);
    }

    @GET
    @Path("getSport/{nom_sport}")
    public String getSport(
            @PathParam("nom_sport") String nom_sport) {
        return ogClass.sportSingleton().getSport(nom_sport);
    }

    @GET
    @Path("get_sport_ligue")
    public String getSportLeague() {

        return ogClass.sportSingleton().getSportLeague();
    }

    @GET
    @Path("listSport")
    public String listSport() {
        return ogClass.sportSingleton().getSports();
    }

    // Implementation des fonctions du template
    protected List<Sport> selectAll(){
        return sportMapper.selectAll();
    }
    protected Sport selectOne(Integer id){
        return sportMapper.selectOne(id);
    }

    protected void insert(Sport sport){ sportMapper.insert(sport); }
    protected void add(Sport sport){
        ogClass.sportSingleton().getSportList().addSport(sport);
    }

    protected void setName(Sport sport) {
        sport.setName(Parser.unescapeEntities(sport.getName(), true));
    }

    /**
     * Méthode pour aller chercher le prochain id de l'ajout
     * @return
     */
    public int getLastId()
    {
        return sportMapper.getLastId();
    }
}
