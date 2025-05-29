package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Sport;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
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

    // Redirection vers les fonctions template
    @GET
    public List<Sport> getSports() {
        return getItems();
    }

    @GET
    @Path("{id}")
    public Sport getSport(@PathParam("id") Integer id) {
        return getItem(id);
    }

    @POST
    @Consumes("application/json")
    public void addSport(Sport sport) {
        addItem(sport);
    }


    // Implementation des fonctions du template
    protected List<Sport> selectAll(){
        return sportMapper.selectAll();
    }
    protected Sport selectOne(Integer id){
        return sportMapper.selectOne(id);
    }

    protected void insert(Sport sport){
        sportMapper.insertSport(sport);
    }
    protected void add(Sport sport){
        ogClass.getSportList().addSport(sport);
    }

    protected void setName(Sport sport) {
        sport.setName(Parser.unescapeEntities(sport.getName(), true));
    }
}

/*public class SportService {
    @Inject
    SportMapper sportMapper;
    @Inject
    OGClass ogClass;


    @GET
    public List<Sport> getSports() {
        List<Sport> sports = sportMapper.selectAll();
        ogClass.getSportList().addSports(sports);
        return unescapeEntities(sports);
    }



    @GET
    @Path("{id}")
    public Sport getSport(
            @PathParam("id") Integer id
    ) {
        Sport sport = sportMapper.selectOne(id);
        ogClass.getSportList().addSport(sport);
        return unescapeEntities(sport);
    }

    @POST
    @Consumes("application/json")
    public void addSport(Sport sport) {
        // Ajouter l'équipe à la base de données via le mapper
        sportMapper.insertSport(sport);

        // Ajouter l'équipe à la ligue correspondante
        ogClass.getSportList().addSport(sport);
    }







    public static Sport unescapeEntities(Sport sport) {
        sport.setName(Parser.unescapeEntities(sport.getName(), true));
        return sport;
    }

    public static List<Sport> unescapeEntities(List<Sport> sport) {
        return sport
                .stream()
                .map(SportService::unescapeEntities)
                .collect(Collectors.toList());
    }
}*/
