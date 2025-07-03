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
//        this.matches =

    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<StatDTO> getStats() {
        return stats;
    }
    public List<PlayerDTO> getPlayers() {
        return players;
    }


    public static List<TeamDTO> mapListToDTO(List<Team> teams){
        List<TeamDTO> teamDTOs = new ArrayList<TeamDTO>();
        for(Team team : teams){
            teamDTOs.add(new TeamDTO(team));
        }
        return teamDTOs;
    }

}
