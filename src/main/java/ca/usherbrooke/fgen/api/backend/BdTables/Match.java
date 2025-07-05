package ca.usherbrooke.fgen.api.backend.BdTables;

import ca.usherbrooke.fgen.api.backend.Lists.ListSport;

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
    private String place;
    private int idLeague;
    private int nbTeamMatch = 2;
    private List<Integer> idTeams;
    private List<Team> teams;
    private int idSeason;


    public Match() {
        this.id = -1;
    }

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

    public Match(Integer id, Date date, Time beginTime, Time endTime, String place, Integer idLeague, Integer nbTeamMatch, List<Integer> idTeams, Integer idSeason)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.place = place;
        this.idLeague = idLeague;
        this.nbTeamMatch = nbTeamMatch;
        this.idTeams = idTeams;
        this.teams = new ArrayList<>();
        this.idSeason = idSeason;
    }

    public Match(int id, Date date, Time beginTime, Time endTime)
    {
        this.id = id;
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.teams = new ArrayList<Team>();
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

    public String getPlace(){
        return this.place;
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

    public int getIdSeason(){
        return this.idSeason;
    }

    /**
     * Méthode qui prend la liste des id du match, va chercher son équipe et l'ajoute à la liste
     */
    public void getTeamsFromId() {
        if(this.idTeams != null && !this.idTeams.isEmpty()){
            for (int idTeam : this.idTeams) {
                if(idTeam != -1) {
                    Team team = ListSport.getTeamById(idTeam);
                    if (!this.teams.contains(team)) {
                        this.teams.add(team);
                    }
                }
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
