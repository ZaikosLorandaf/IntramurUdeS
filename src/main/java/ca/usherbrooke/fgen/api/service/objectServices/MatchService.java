package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.*;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.mapper.MatchMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;
import java.util.List;

@Path("/api/match")
public class MatchService extends TemplateService<Match> {
    @Inject
    MatchMapper matchMapper;
    @Inject
    OGClass ogClass;

    // Redirection vers les fonctions template
    @GET
    public List<Match> getMatches(){
        List<Match> matches = getItems();
        return matches;
    }

    @GET
    @Path("{id}")
    public Match getMatch(@PathParam("id") Integer id) {
        Match match = getItem(id);
        return match;
    }


    @POST
    @Consumes("application/json")
    public void addMatch(Match match)
    {
        addItem(match);
    }


    // Implementation des fonctions du template
    protected List<Match> selectAll(){
        List<Match> matches = matchMapper.selectAll();
        for (Match match : matches) {
            match.init();
        }
        return matches;
    }
    protected Match selectOne(Integer id){
        Match match = matchMapper.selectOne(id);
        match.init();
        return match;
    }

    @Override
    protected void add(Match item) {
        Collection<League> leagues = ListSport.getLeagues();
        League league = ListSport.getLeagueById(item.getIdLeague());
        league.getListMatch().addMatch(item);
    }

    protected void insert(Match match){
        matchMapper.insert(match);
    }

    @Override
    protected void setName(Match item) {

    }

    /**
     * Méthode pour aller chercher le prochain id de l'ajout
     * @return
     */
    public int getLastId()
    {
        return matchMapper.getLastId();
    }
}
