package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Sport;
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

    // Requetes POST
    @POST
    @Consumes("application/json")
    public void addSport(Sport sport) { addItem(sport); }

    @POST
    @Path("addSport")
    public String addSport(@NotNull addSport sport ) {
        return ogClass.newSport(sport.nom);
    }

    @POST
    @Path("removeSport")
    public String removeSport(@NotNull removeSport sport ) { return ogClass.removeSport(sport.name); }

    // Requetes GET

    @GET
    public List<Sport> getSports() { return getItems(); }

    @GET
    @Path("{id}")
    public Sport getSport(@PathParam("id") Integer id) {
        return getItem(id);
    }
    public int getLastId() { return sportMapper.getLastId(); }

    @GET
    @Path("listSport/")
    public String getListSport() {
        return ogClass.listSport();
    }

    @GET
    @Path("getSport/{nom_sport}")
    public String getSport( @PathParam("nom_sport") String nom_sport) { return ogClass.getSport(nom_sport); }

    // Implementation des fonctions du template
    protected List<Sport> selectAll(){
        return sportMapper.selectAll();
    }
    protected Sport selectOne(Integer id){
        return sportMapper.selectOne(id);
    }

    protected void insert(Sport sport){ sportMapper.insert(sport); }
    protected void add(Sport sport){
        ogClass.getSportList().addSport(sport);
    }

    protected void setName(Sport sport) {
        sport.setName(Parser.unescapeEntities(sport.getName(), true));
    }
}
