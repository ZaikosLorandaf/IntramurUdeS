package ca.usherbrooke.fgen.api.backend;


public class Sport {
    private int id;
    private String name;
    private ListLeague listLeague;
    java.util.Random rand = new java.util.Random();

    public Sport(String name, int id) {
        this.id = id;
        this.name = name;
        listLeague = new ListLeague();
    }


    public  Sport(String name)
    {
        this.name = name;
        listLeague = new ListLeague();
        this.id = rand.nextInt(1,32);
    }

    public Sport() {
        this.id = -1;
        this.name = "";
        listLeague = new ListLeague();
    }

    public Sport(String name) {
        this.id = -1;
        this.name = name;
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

    public int addLeague(League league) {
        return listLeague.addLeague(league);
    }
}
