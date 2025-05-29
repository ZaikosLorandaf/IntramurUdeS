package ca.usherbrooke.fgen.api.service.objectServices;


import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Team;
import ca.usherbrooke.fgen.api.mapper.TeamMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/team")
public class TeamService {
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
}
