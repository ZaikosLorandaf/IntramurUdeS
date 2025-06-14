package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.service.postClass.removeLeague;
import io.smallrye.common.constraint.NotNull;
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
        List<League> leagues = getItems();
        ListSport.addLeagueMap(leagues);
        for (League league : leagues){
            league.addToSeason();
        }
        return ListSport.getLeagues();
    }

    @GET
    @Path("{id}")
    public League getLeague(@PathParam("id") Integer id) {
        League league = getItem(id);
        ListSport.addLeagueMap(league);
        league.addToSeason();
        return ListSport.getLeagueById(league.getId());
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

    @POST
    @Path("removeLeague")
    public String removeLeague(@NotNull removeLeague league ) {
        return ogClass.removeLeague(league.sportName, league.leagueName);
    }

    // Implementation des fonctions du template
    protected List<League> selectAll(){
        return leagueMapper.selectAll();
    }
    protected League selectOne(Integer id){
        return leagueMapper.selectOne(id);
    }

    protected void insert(League league){
        leagueMapper.insert(league);
    }
    protected void add(League league){
        Sport sport = ogClass.getSportList().getSport(league.getIdSport());
        sport.addLeague(unescapeEntities(league));
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


