package ca.usherbrooke.fgen.api.backend;

public class Sport {
    private String id;
    private String name;

    public Sport(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sport() {
        this.id = "";
        this.name = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
