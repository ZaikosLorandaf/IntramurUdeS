package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
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
    @GET
    @Path("listTeam/{nom_sport}/{nom_ligue}")
    public String listTeam(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nom_ligue) {
        nomSport = nomSport.replace("%20", " ");
        nom_ligue = nom_ligue.replace("%20", " ");
        return ogClass.getTeamSingleton().listTeam(nomSport,nom_ligue);
    }

    @GET
    public List<Team> getTeams() {
        List<Team> teams = getItems();
        ListSport.addTeamMap(teams);
        return teams;
    }

    @GET
    @Path("{id}")
    public Team getTeam(@PathParam("id") Integer id) {
        Team team = getItem(id);
        ListSport.addTeamMap(team);
        return team;
    }

    @GET
    @Path("getTeams/{nom_sport}/{ligue}")
    public String getTeams(
            @PathParam("nom_sport") String nom_sport,
            @PathParam("ligue") String ligue) {
        nom_sport = nom_sport.replace("%20", " ");
        ligue = ligue.replace("%20", " ");
        return ogClass.getTeamSingleton().getTeams(nom_sport,ligue);
    }

    // Implementation des fonctions du template
    protected List<Team> selectAll(){
        return teamMapper.selectTeams();
    }
    protected Team selectOne(Integer id){
        return teamMapper.selectOneTeam(id);
    }

    protected void insert(Team team){
        teamMapper.insert(team);
    }
    protected void add(Team team){
        ogClass.getSportSingleton().getSportList().getLeague(team.getIdLeague()).addTeam(team);
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
