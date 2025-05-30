package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.League;
import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Sport;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;


@Path("/api/league")
public class LeagueService extends TemplateService<League> {
    @Inject
    LeagueMapper leagueMapper;
    @Inject
    OGClass ogClass;

    // Redirection vers les fonctions template
    @GET
    public List<League> getLeagues(){
        return getItems();
    }

    public League getLeague(@PathParam("id") Integer id) {
        return getItem(id);
    }


    @GET
    @Path("sport/{sportid}")
    public List<League> getLeaguesBySportId( @PathParam("sportid") Integer sportId ) {
        List<League> leagues = leagueMapper.selectFromSport(sportId);
        for (League league : leagues) {
            add(league);
        }
        return unescapeEntities(leagues);
    }

    @POST
    @Consumes("application/json")
    public void addLeague(League league) {
        addItem(league);
    }


    // Implementation des fonctions du template
    protected List<League> selectAll(){
        return leagueMapper.selectAll();
    }
    protected League selectOne(Integer id){
        return leagueMapper.selectOne(id);
    }

    protected void insert(League league){
        leagueMapper.insertLeague(league);
    }
    protected void add(League league){
        ogClass.getSportList().getSport(league.getIdSport()).addLeague(unescapeEntities(league));
    }

    protected void setName(League league) {
        league.setName(Parser.unescapeEntities(league.getName(), true));
    }

    /**
     * Méthode pour aller chercher le prochain id de l'ajout
     * @return
     */
    public int getLastId()
    {
        return leagueMapper.getLastId();
    }
}


