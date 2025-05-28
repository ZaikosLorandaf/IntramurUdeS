package ca.usherbrooke.fgen.api.service.objectServices;


import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Player;
import ca.usherbrooke.fgen.api.backend.Sport;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;


@Path("/api/sport")
public class SportService {
    @Inject
    SportMapper sportMapper;
    @Inject
    OGClass ogClass;


    @GET
    public List<Sport> getSports() {
        List<Sport> sports = sportMapper.selectAll();
        ogClass.getListeSport().addSports(sports);
        return unescapeEntities(sports);
    }

    @GET
    @Path("{id}")
    public Sport getSport(
            @PathParam("id") Integer id
    ) {
        Sport sport = sportMapper.selectOne(id);
        ogClass.getListeSport().addSport(sport);
        return unescapeEntities(sport);
    }

    @POST
    @Consumes("application/json")
    public void addSport(Sport sport) {
        // Ajouter l'équipe à la base de données via le mapper
        sportMapper.insertSport(sport);

        // Ajouter l'équipe à la ligue correspondante
        ogClass.getListeSport().addSport(sport);
    }

    public static Sport unescapeEntities(Sport sport) {
        sport.setName(Parser.unescapeEntities(sport.getName(), true));
        return sport;
    }

    public List<Sport> unescapeEntities(List<Sport> sport) {
        return sport
                .stream()
                .map(SportService::unescapeEntities)
                .collect(Collectors.toList());
    }
}
