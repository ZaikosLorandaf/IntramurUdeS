package ca.usherbrooke.fgen.api.backend;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.sql.Array;
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
    private List<Integer> idTeams;
    private List<Team> teams;


    public Match(int id, Date date, Time beginTime, Time endTime, int idLeague, int nbTeamMatch)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.idLeague = idLeague;
        this.nbTeamMatch = nbTeamMatch;
        this.idTeams = new ArrayList<>();
        this.teams = new ArrayList<Team>();
    }

    public Match(Integer id, Date date, Time beginTime, Time endTime, Integer idLeague, Integer nbTeamMatch, List<Integer> idTeams)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.idLeague = idLeague;
        this.nbTeamMatch = nbTeamMatch;
        this.idTeams = idTeams;
        teams = new ArrayList<>();
    }

    public Match(int id, Date date, Time beginTime, Time endTime)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        teams = new ArrayList<Team>();
        this.idTeams = new ArrayList<>();
    }


    public int getId(){
        return this.id;
    }

    public Date getDate(){
        return this.date;
    }

    public Time getBeginTime(){
        return this.beginTime;
    }

    public Time getEndTime(){
        return this.endTime;
    }

    public int getIdLeague(){
        return this.idLeague;
    }

    public int getNbTeamMatch(){
        return this.nbTeamMatch;
    }

    public List<Integer> getIdTeams(){
        return this.idTeams;
    }
    public List<Team> getTeams(){
        return this.teams;
    }

    /**
     * Méthode qui prend la liste des id du match, va chercher son équipe et l'ajoute à la liste
     */
    public void getTeamsFromId() {
        for (int idTeam : this.idTeams) {
            Team team = ListSport.getTeamById(idTeam);
            if(!this.teams.contains(team)) {
                this.teams.add(team);
            }
        }
    }

    /**
     * Méthode pour initialiser un match
     */
    public void init() {
        this.getTeamsFromId();
    }


    @Override
    public String toString()
    {
        return "Match{" + "id=" + id +
                "\n date=" + date + ", begin time=" + beginTime + ", end time=" + endTime + "\nidLeague: " + idLeague + "}";
    }

}
