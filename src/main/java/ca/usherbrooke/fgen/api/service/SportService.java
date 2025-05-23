package ca.usherbrooke.fgen.api.service;


import ca.usherbrooke.fgen.api.backend.Sport;
import ca.usherbrooke.fgen.api.business.Message;
import ca.usherbrooke.fgen.api.mapper.MessageMapper;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Path("sport")
public class SportService {
    @Inject
    SportMapper sportMapper;


    @GET
    @Path("getAll")
    public List<Sport> getSports() {
        List<Sport> sports = sportMapper.selectAll();
        return unescapeEntities(sports);
    }

    @GET
    @Path("get/{id}")
    public Sport getSport(
            @PathParam("id") Integer id
    ) {
        Sport sport = sportMapper.selectOne(id);
        return unescapeEntities(sport);
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
