package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Season;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.service.postClass.addLeague;
import ca.usherbrooke.fgen.api.service.postClass.removeLeague;

import io.smallrye.common.constraint.NotNull;
import org.jsoup.parser.Parser;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@Path("/api/league")
public class LeagueService extends TemplateService<League> {
    @Inject
    LeagueMapper leagueMapper;
    @Inject
    OGClass ogClass;

    // Methodes POST
    @POST
    @Consumes("application/json")
    public void addLeague(League league) {
        addItem(league);
    }

    @POST
    @Path("addLigue/")
    public String addLeague(@NotNull addLeague league) {
        if (league.nom.length() >= nameMaxLength){
            return "Name too long";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date_debut;
        Date date_fin;
        try {
            java.util.Date date_debutParsed = dateFormat.parse(league.date_debut);
            java.util.Date date_finParsed = dateFormat.parse(league.date_fin);

            date_debut = new Date(date_debutParsed.getTime());
            date_fin = new Date(date_finParsed.getTime());
        } catch (ParseException e) {
            return "Invalid date format: " + e.getMessage();
        }
        return ogClass.getLeagueSingleton().add(league.nom_sport, league.nom,date_debut,date_fin);
    }

    @POST
    @Path("removeLeague")
    public String removeLeague(@NotNull removeLeague league ) {
        return ogClass.getLeagueSingleton().remove(league.sportName, league.leagueName);
    }

    @POST
    @Path("removeSport/{nom_sport}")
    public String removeLeague(@PathParam("nom_sport") String nomSport) {
        nomSport = nomSport.replace("%20", " ");
        return ogClass.getSportSingleton().remove(nomSport);
    }

    // Methodes GET
    @GET
    public List<League> getLeagues(){
        List<League> leagues = getItems();
        ListSport.addLeagueMap(leagues);
        for (League league : leagues){
            league.addToSeason();
        }
        List<League> listRetour = ListSport.getLeagues();
        return listRetour;
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
    @Path("getLigue/{sport}/{nom}")
    public String getLeague(
            @PathParam("sport") String sport,
            @PathParam("nom") String nom) {
        sport = sport.replace("%20", " ");
        nom = nom.replace("%20", " ");
        return ogClass.getLeagueSingleton().getLeague(sport, nom);
    }

    @GET
    @Path("listLigue/{nom_sport}")
    public String getListeLigue(@PathParam("nom_sport") String nom_sport) {
        nom_sport = nom_sport.replace("%20", " ");
        return ogClass.getLeagueSingleton().listLeague(nom_sport);
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
        Sport sport = ogClass.getSportSingleton().getSportList().getSport(league.getIdSport());
        sport.addLeague(unescapeEntities(league));
        for (int idSeason : league.getIdSeasons()){
            if(idSeason == -1) break;
            Season season = ogClass.getSeasonSingleton().getListSeasons().getSeason(idSeason);
            season.getLeagues().addLeague(unescapeEntities(league));
        }
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


