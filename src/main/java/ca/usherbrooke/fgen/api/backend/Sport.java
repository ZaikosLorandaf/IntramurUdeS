package ca.usherbrooke.fgen.api.backend;


public class Sport {
    private int id;
    private String name;
    private int nbTeamMatch;
    private ListLeague listLeague;
    java.util.Random rand = new java.util.Random();

    public Sport(String name, int id) {
        this.id = id;
        this.name = name;
        this.nbTeamMatch = 2;
        listLeague = new ListLeague();
    }

    public Sport(int id, String name, int nbTeamMatch) {
        this.id = id;
        this.name = name;
        this.nbTeamMatch = nbTeamMatch;
        listLeague = new ListLeague();
    }


    public  Sport(String name)
    {
        this.name = name;
        this.listLeague = new ListLeague();
        this.nbTeamMatch = 2;
        this.id = rand.nextInt(1,32);
    }

    public Sport() {
        this.id = -1;
        this.name = "";
        this.nbTeamMatch = 2;
        listLeague = new ListLeague();
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

    public boolean addLeague(League league) {
        return listLeague.addLeague(league);
    }
}
