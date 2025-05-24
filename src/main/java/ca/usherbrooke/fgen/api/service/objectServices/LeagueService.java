package ca.usherbrooke.fgen.api.service.objectServices;


import ca.usherbrooke.fgen.api.backend.League;
import ca.usherbrooke.fgen.api.backend.Sport;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.mapper.SportMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/league")
public class LeagueService {
    @Inject
    LeagueMapper leagueMapper;
    @Inject
    SportService sportService;


    @GET
    public List<League> getLeagues() {
        List<League> leagues = leagueMapper.selectAll();
        return unescapeEntities(leagues);
    }

    @GET
    @Path("{id}")
    public League getLeague(
            @PathParam("id") Integer id
    ) {
        League league = leagueMapper.selectOne(id);
        return unescapeEntities(league);
    }

    @GET
    @Path("sport/{sportid}")
    public List<League> getLeaguesBySportId(
            @PathParam("sportid") Integer sportId
    ) {
        List<League> leagues = leagueMapper.selectFromSport(sportId);
        return unescapeEntities(leagues);
    }



    public static League unescapeEntities(League league) {
        league.setName(Parser.unescapeEntities(league.getName(), true));
        league.setWeekDay(Parser.unescapeEntities(league.getWeekDay(), false));
        return league;
    }

    public List<League> unescapeEntities(List<League> leagues) {
        return leagues
                .stream()
                .map(LeagueService::unescapeEntities)
                .collect(Collectors.toList());
    }
}
