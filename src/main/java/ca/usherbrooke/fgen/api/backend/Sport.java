package ca.usherbrooke.fgen.api.backend;

public class Sport {
    private int id;
    private String name;
    private int nbTeamMatch;
    private ListLeague listLeague;
    java.util.Random rand = new java.util.Random();

    // Constructeurs
    /**
     * Fonction d'initialisation de la classe Sport
     *
     * @param id          Id du sport [integer]
     * @param name        Nom du sport [string]
     * @param nbTeamMatch nombre de partie de l'equipe [integer]
     */
    private void initSport(int id, String name, int nbTeamMatch){
        this.id = id;
        this.name = name;
        this.nbTeamMatch = nbTeamMatch;
        listLeague = new ListLeague();
        LoggerUtil.info("Création d'un sport");
    }

    /**
     * Constructeur avec id et nom du sport
     *
     * @param id         Id du sport [integer]
     * @param name       Nom du sport [string]
     */
    public Sport(String name, int id) { initSport(id, name, 2); }

    /**
     * Constructeur avec id et nom du sport et nombre de partie de l'equipe
     *
     * @param id          Id du sport [integer]
     * @param name        Nom du sport [string]
     * @param nbTeamMatch nombre de partie de l'equipe [integer]
     */
    public Sport(int id, String name, int nbTeamMatch) { initSport(id, name, nbTeamMatch); }

    /**
     * Constructeur avec nom du sport
     *
     * @param name      Nom du sport [string]
     */
    public Sport(String name) { initSport(rand.nextInt(1, 32), name, 2); }

    /**
     * Constructeur vide. Initialise la classe avec des parametres par defaut
     */
    public Sport() { initSport(-1, "", 2); }

    // Methodes
    /**
     * Ajoute une ligue dans la liste des ligues
     *
     * @param league    objet ligue a ajouter [League]
     *
     * @return la ligue est ajoutee? [Boolean]
     */
    public boolean addLeague(League league) {
        return this.listLeague.addLeague(league);
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public int getId() {
        return this.id;
    }
    public String getName() { return this.name; }
    public ListLeague getListLeague() {
        return this.listLeague;
    }
}
