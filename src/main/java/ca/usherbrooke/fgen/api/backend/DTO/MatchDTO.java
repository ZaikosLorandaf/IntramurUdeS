package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.Match;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MatchDTO {
    private int id;
    private Date date;
    private Time beginTime;
    private Time endTime;

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.date = match.getDate();
        this.beginTime = match.getBeginTime();
        this.endTime = match.getEndTime();
    }

    public int getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Time getBeginTime() {
        return beginTime;
    }
    public Time getEndTime() {
        return endTime;
    }

    public static List<MatchDTO> mapListToDTO(List<Match> matches){
        List<MatchDTO> returnList = new ArrayList<>();
        for (Match match : matches) {
            returnList.add(new MatchDTO(match));
        }
        return returnList;
    }

}
