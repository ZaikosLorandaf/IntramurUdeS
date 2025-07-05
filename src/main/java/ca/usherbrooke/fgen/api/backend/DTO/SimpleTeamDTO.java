package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.Team;

import java.util.ArrayList;
import java.util.List;

public class SimpleTeamDTO {
    protected int id;
    protected String name;


    public SimpleTeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }


    public static List<SimpleTeamDTO> mapListToSimpleDTO(List<Team> teams){
        List<SimpleTeamDTO> simpleTeamDTOs = new ArrayList<SimpleTeamDTO>();
        for(Team team : teams){
            simpleTeamDTOs.add(new SimpleTeamDTO(team));
        }
        return simpleTeamDTOs;
    }

}
