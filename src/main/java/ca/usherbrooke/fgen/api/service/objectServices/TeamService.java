package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.mapper.TeamMapper;
import ca.usherbrooke.fgen.api.service.postClass.removeTeam;
import io.smallrye.common.constraint.NotNull;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;


@Path("/api/team")
public class TeamService extends TemplateService<Team> {
    @Inject
    TeamMapper teamMapper;
    @Inject
    OGClass ogClass;

    // Redirection vers les fonctions template
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

    @POST
    @Consumes("application/json")
    public void addTeam(Team team) {
        addItem(team);
    }

    @POST
    @Path("removeTeam")
    public String removeTeam(@NotNull removeTeam team ) {
        return ogClass.removeTeam(team.sportName, team.leagueName, team.teamName);
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
        ogClass.getSportList().getLeague(team.getIdLeague()).addTeam(team);
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

/*public class TeamService {
    @Inject
    TeamMapper teamMapper;
    @Inject
    OGClass ogClass;

    @GET
    public List<Team> getTeams() {
        List<Team> teams = teamMapper.selectTeams();
        for (Team team : teams) {
            ogClass.getSportList().getLeague(team.getIdLeague()).addTeam(team);
        }
        return unescapeEntities(teams);
    }

    @GET
    @Path("{id}")
    public Team getTeam(
            @PathParam("id") Integer id
    ) {
        Team team = teamMapper.selectOneTeam(id);
        ogClass.getSportList().getLeague(team.getIdLeague()).addTeam(team);
        return unescapeEntities(team);
    }

    @POST
    @Consumes("application/json")
    public void addTeam(Team team) {
        // Ajouter l'équipe à la base de données via le mapper
        teamMapper.insertTeam(team);

        // Ajouter l'équipe à la ligue correspondante
        ogClass.getSportList().getLeague(team.getIdLeague()).addTeam(team);
    }


    public static Team unescapeEntities(Team team) {
        team.setName(Parser.unescapeEntities(team.getName(), true));
        return team;
    }

    public List<Team> unescapeEntities(List<Team> team) {
        return team
                .stream()
                .map(TeamService::unescapeEntities)
                .collect(Collectors.toList());
    }
}*/
