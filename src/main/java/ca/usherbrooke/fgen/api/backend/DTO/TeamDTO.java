package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.DTO.stats.StatDTO;
import ca.usherbrooke.fgen.api.backend.DTO.stats.StatPlayerDTO;

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

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        stats = StatDTO.mapListStatDTO(team.getListStat().getAllItems());
        players = PlayerDTO.mapListPlayerDTO(team.getListPlayer().getAllItems());
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


    public static List<TeamDTO> mapListTeamDTO(List<Team> teams){
        List<TeamDTO> teamDTOs = new ArrayList<TeamDTO>();
        for(Team team : teams){
            teamDTOs.add(new TeamDTO(team));
        }
        return teamDTOs;
    }

}
