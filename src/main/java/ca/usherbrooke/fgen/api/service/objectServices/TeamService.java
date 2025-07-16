package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.DTO.TeamDTO;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.mapper.TeamMapper;
import ca.usherbrooke.fgen.api.service.postClass.addTeam;
import ca.usherbrooke.fgen.api.service.postClass.removeTeam;

import io.quarkus.arc.Arc;
import io.smallrye.common.constraint.NotNull;
import org.jsoup.parser.Parser;
import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;


@Path("/api/team")
public class TeamService extends TemplateService<Team> {
    TeamMapper teamMapper;
    OGClass ogClass;

    @PostConstruct
    public void init() {
        this.ogClass = Arc.container().instance(OGClass.class).get();
        this.teamMapper = Arc.container().instance(TeamMapper.class).get();
    }

    // Methode POST
    @POST
    @Consumes("application/json")
    public void addTeam(Team team) {
        addItem(team);
    }

    @POST
    @Path("addTeam")
    public String addTeam(@NotNull addTeam team) {
        return ogClass.getTeamSingleton().add(team.nomSport, team.nomLigue, team.nomTeam);
    }

    @POST
    @Path("removeTeam")
    public String removeTeam(@NotNull removeTeam team ) {
        return ogClass.getTeamSingleton().remove(team.sportName, team.leagueName, team.teamName);
    }

    // Methode GET

    /**
     * Méthode pour aller chercher la liste des toutes les équipes d'une ligue
     * @param nomSport Nom du sport à aller chercher
     * @param nomLigue Nom de la ligue
     * @return La liste des DTO des équipes
     */
    @GET
    @Path("{nom_sport}/{nom_ligue}")
    public List<TeamDTO> getTeams(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue) {
        this.getItems();
        nomSport = nomSport.replace("%20", " ");
        nomLigue = nomLigue.replace("%20", " ");
        Sport sport = this.ogClass.getSportSingleton().getSportList().getSport(nomSport);
        League league = sport.getListLeague().getLeague(nomLigue, nomSport);
        List<TeamDTO> returnList = TeamDTO.mapListToDTO(league.getListTeam().getAllItems());
        return returnList;
    }

    @GET
    public List<TeamDTO> getTeams() {
        this.getItems();
        List<TeamDTO> returnList = new ArrayList<>();
        for(Sport sport : ogClass.getSportSingleton().getSportList().getAllSports()) {
            for (League league : sport.getListLeague().getAllItems()){
                returnList.addAll(TeamDTO.mapListToDTO(league.getListTeam().getAllItems()));
            }
        }
        return returnList;
    }





    // Implementation des fonctions du template
    protected List<Team> selectAll(){
        List<Team> returnList = teamMapper.selectTeams();
        return returnList;
    }

    protected Team selectOne(Integer id){
        return teamMapper.selectOneTeam(id);
    }

    protected void insert(Team team){
        teamMapper.insert(team);
    }
    protected void add(Team team){
        League ligue = ogClass.getSportSingleton().getSportList().getLeague(team.getIdLeague());
        ligue.addTeam(team);
    }

    protected void setName(Team team) {
        team.setName(Parser.unescapeEntities(team.getName(), true));
    }

    /**
     * Méthode pour aller chercher le prochain id de l'ajout
     * @return
     */
    public int getLastId()
    {
        return teamMapper.getLastId();
    }
}
