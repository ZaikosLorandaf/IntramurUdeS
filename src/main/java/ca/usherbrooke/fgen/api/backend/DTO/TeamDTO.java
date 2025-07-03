package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.DTO.stats.StatDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe adapter pour donner juste ce qu'il faut pour le json retour
 */
public class TeamDTO {

    private int id;
    private String name;
    private List<StatDTO> stats;
    private List<PlayerDTO> players;
    private List<MatchDTO> matches;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.stats = StatDTO.mapListToDTO(team.getListStat().getAllItems());
        this.players = PlayerDTO.mapListToDTO(team.getListPlayer().getAllItems());
        this.matches = MatchDTO.mapListToDTO(team.getMatchTeam());

    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public List<StatDTO> getStats() {
        return this.stats;
    }
    public List<PlayerDTO> getPlayers() {
        return this.players;
    }

    public List<MatchDTO> getMatches(){
        return this.matches;
    }


    public static List<TeamDTO> mapListToDTO(List<Team> teams){
        List<TeamDTO> teamDTOs = new ArrayList<TeamDTO>();
        for(Team team : teams){
            teamDTOs.add(new TeamDTO(team));
        }
        return teamDTOs;
    }

}
