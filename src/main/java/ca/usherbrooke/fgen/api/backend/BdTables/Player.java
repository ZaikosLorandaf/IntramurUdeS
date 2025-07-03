package ca.usherbrooke.fgen.api.backend.BdTables;

import ca.usherbrooke.fgen.api.backend.Lists.ListStat;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

public class Player {
    private int id;
    private String name;
    private String lastName;
    private int number;
    private int idTeam;
    private ListStat listStat;

    // Constructeurs
    /**
     * Constructeur de la classe
     *
     * @param fn     Prénom
     * @param ln     Nom
     * @param idTeam id de l'équipe dans la db
     */
    public Player(String fn, String ln, int idTeam) {
        setName(fn);
        setLastName(ln);
        setIdTeam(idTeam);
        this.listStat = new ListStat();
        LoggerUtil.info("Création du joueur: " + fn + " " + ln);
    }

    public Player(String fn, String ln, int idTeam, int number) {
        setName(fn);
        setLastName(ln);
        setIdTeam(idTeam);
        this.number = number;
        this.listStat = new ListStat();
        LoggerUtil.info("Création du joueur: " + fn + " " + ln);
    }

    public Player(int id, String name, String lastName, int number, int idTeam) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.idTeam = idTeam;
        this.listStat = new ListStat();
    }

    // Methodes
    /**
     * Afficher le nom complet du joueur dans la console
     */
    public void printPlayer() {
        System.out.printf("%s %s, I am on team %d\n", name, lastName, idTeam);
    }

    // Setter
    public void setIdTeam(int id) {
        idTeam = id;
        LoggerUtil.info("Changement de l'id d'équipe du joueur: " + getName() + " " + getLastName());
    }
    public void setLastName(String n) {
        LoggerUtil.info("Changement du nom de famille du joueur: " + this.lastName + " --> " + n );
        lastName = n;
    }
    public void setName(String n) {
        LoggerUtil.info("Changement du prenom du joueur: " + this.name + " --> " + n );
        name = n;
    }

    // Getter
    public int getNumber() {
        return this.number;
    }
    public int getIdTeam() {
        return this.idTeam;
    }
    public String getName() {
        return this.name;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getFullName() {
        return this.name + " " + this.lastName;
    }
    public ListStat getListStat() {
        return this.listStat;
    }
    public int getId() {
        return this.id;
    }
}
