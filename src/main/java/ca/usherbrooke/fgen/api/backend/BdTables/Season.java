package ca.usherbrooke.fgen.api.backend.BdTables;

import ca.usherbrooke.fgen.api.backend.Lists.ListLeague;

public class Season
{

    private int id;
    private int seasonYear;
    private String timePrecision;
    private ListLeague leagues;


    public Season(){
        this.id = -1;
        this.leagues = new ListLeague();
    }

    public Season(int id, int seasonYear, String timePrecision)
    {
        this.id = id;
        this.seasonYear = seasonYear;
        this.timePrecision = timePrecision;
        this.leagues = new ListLeague();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getTimePrecision() {
        return timePrecision;
    }

    public void setTimePrecision(String timePrecision) {
        this.timePrecision = timePrecision;
    }

    public ListLeague getLeagues() {
        return leagues;
    }


    @Override
    public String toString(){

        return timePrecision + " " + seasonYear;
    }


    public void printSeason() {
        System.out.printf(this.toString());
    }
}
