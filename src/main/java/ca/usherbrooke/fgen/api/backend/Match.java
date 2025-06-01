package ca.usherbrooke.fgen.api.backend;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Match
{
    @Inject
    ListSport listSport;

    private int id;
    private Date date;
    private Time beginTime;
    private Time endTime;
    private int idLeague;
    private int nbTeamMatch = 2;
    private List<Team> teams;


    public Match(int id, Date date, Time beginTime, Time endTime, int idLeague, int nbTeamMatch)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.idLeague = idLeague;
        this.nbTeamMatch = nbTeamMatch;
        teams = new ArrayList<Team>();
    }

    public Match(int id, Date date, Time beginTime, Time endTime)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        teams = new ArrayList<Team>();
    }


    public int getId(){
        return id;
    }

    public Date getDate(){
        return date;
    }

    public Time getBeginTime(){
        return beginTime;
    }

    public Time getEndTime(){
        return endTime;
    }

    public int getIdLeague(){
        return idLeague;
    }

    public int getNbTeamMatch(){
        return nbTeamMatch;
    }

}
