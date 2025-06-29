package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.mapper.MatchMapper;
import ca.usherbrooke.fgen.api.service.postClass.addMatch;
import ca.usherbrooke.fgen.api.service.postClass.removeMatch;

import io.smallrye.common.constraint.NotNull;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/match")
public class MatchService extends TemplateService<Match> {
    @Inject
    MatchMapper matchMapper;
    @Inject
    OGClass ogClass;

    // Mehtodes POST
    @POST
    @Consumes("application/json")
    public void addMatch(Match match) {
        addItem(match);
    }

    @POST
    @Path("add")
    public String addMatch(@NotNull addMatch match) {
        return ogClass.getMatchSingleton().add(match.sport, match.ligue, match.equipe1, match.equipe2, match.date, match.heure_debut, match.heure_fin);
    }

    @POST
    @Path("remove")
    public String removeMatch(@NotNull removeMatch match) {
        return ogClass.getMatchSingleton().remove(match.sport, match.ligue, match.date, match.team1, match.team2);
    }

    // Methodes GET
    @GET
    public List<Match> getMatches(){
        return getItems();
    }

    @GET
    @Path("{id}")
    public Match getMatch(@PathParam("id") Integer id) {
        Match match = getItem(id);
        if(match == null){
            return new Match();
        }
        return match;
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
        if(match == null){
            return null;
        }
        match.init();
        return match;
    }

    @Override
    protected void add(Match item) {
        if(item != null){
            List<League> leagues = ListSport.getLeagues();
            League league = ListSport.getLeagueById(item.getIdLeague());
            league.getListMatch().addMatch(item);
        }
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
