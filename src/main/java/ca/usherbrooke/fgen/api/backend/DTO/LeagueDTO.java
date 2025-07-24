package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LeagueDTO {
    private int id;
    private String name;
    private Date beginDate;
    private Date endDate;
    private boolean done;
    private List<SimpleTeamDTO> teams;
    private List<MatchDTO> matches;


    public LeagueDTO(League league) {
        this.id = league.getId();
        this.name = league.getName();
        this.beginDate = league.getBeginDate();
        this.endDate = league.getEndDate();
        this.teams = SimpleTeamDTO.mapListToSimpleDTO(league.getListTeam().getAllItems());
        this.matches = MatchDTO.mapListToDTO(league.getListMatch().getAllItems());
        this.done = league.getDone();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public List<SimpleTeamDTO> getTeams() {
        return this.teams;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public boolean getDone() {
        return this.done;
    }

    public List<MatchDTO> getMatches() {
        return this.matches;
    }

    public static List<LeagueDTO> mapListToDTO(List<League> leagues){
        List<LeagueDTO> returnList = new ArrayList<LeagueDTO>();
        for(League league : leagues){
            returnList.add(new LeagueDTO(league));
        }
        return returnList;
    }
}
