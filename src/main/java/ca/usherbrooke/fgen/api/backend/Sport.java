package ca.usherbrooke.fgen.api.backend;

public class Sport {
    private int id;
    private String name;
    private ListLeague listLeague;

    public Sport(String name, int id) {
        this.id = id;
        this.name = name;
        listLeague = new ListLeague();
    }

    public Sport() {
        this.id = -1;
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListLeague getListLeague() {
        return listLeague;
    }

    public void addLeague(League league) {
        listLeague.addLeague(league);
    }
}
