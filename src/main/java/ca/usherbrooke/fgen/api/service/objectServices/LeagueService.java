package ca.usherbrooke.fgen.api.service.objectServices;


import ca.usherbrooke.fgen.api.backend.League;
import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Sport;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/league")
public class LeagueService {
    @Inject
    LeagueMapper leagueMapper;
    @Inject
    OGClass ogClass;


    @GET
    public List<League> getLeagues() {
        List<League> leagues = leagueMapper.selectAll();
        for (League league : leagues) {
            int leagueId = league.getIdSport();
            Sport sport = ogClass.getSportList().getSport(leagueId);
            sport.addLeague(unescapeEntities(league));
        }
        return unescapeEntities(leagues);
    }


    @GET
    @Path("{id}")
    public League getLeague(
            @PathParam("id") Integer id
    ) {
        League league = leagueMapper.selectOne(id);
        ogClass.getSportList().getSport(league.getIdSport()).addLeague(unescapeEntities(league));
        return unescapeEntities(league);
    }

    @GET
    @Path("sport/{sportid}")
    public List<League> getLeaguesBySportId(
            @PathParam("sportid") Integer sportId
    ) {
        List<League> leagues = leagueMapper.selectFromSport(sportId);
        for (League league : leagues) {
            ogClass.getSportList().getSport(league.getIdSport()).addLeague(unescapeEntities(league));
        }
        return unescapeEntities(leagues);
    }

    @POST
    @Consumes("application/json")
    public void addLeague(League league) {
        // Ajouter l'équipe à la base de données via le mapper
        leagueMapper.insertLeague(league);

        // Ajouter l'équipe à la ligue correspondante
        ogClass.getSportList().getSport(league.getIdSport()).addLeague(unescapeEntities(league));
    }

    public static League unescapeEntities(League league) {
        league.setName(Parser.unescapeEntities(league.getName(), true));
        return league;
    }

    public List<League> unescapeEntities(List<League> leagues) {
        return leagues
                .stream()
                .map(LeagueService::unescapeEntities)
                .collect(Collectors.toList());
    }
}
