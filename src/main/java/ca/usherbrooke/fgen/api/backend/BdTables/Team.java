package ca.usherbrooke.fgen.api.backend.BdTables;

import ca.usherbrooke.fgen.api.backend.Lists.ListPlayer;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;

public class Team {
    private int id;
    private String name;
    private int idLeague;
    private ListPlayer listPlayer;

    // Constructeurs
    /**
     * Fonction pour initialiser la classe league
     *
     * @param id            id de la ligue
     * @param name          nom de la ligue
     * @param idLeague      id de la ligue parent
     * @param listPlayer    list des joueurs
     */
    public void initTeam(int id, String name, int idLeague, ListPlayer listPlayer) {
        this.id = id;
        this.name = name;
        this.idLeague = idLeague;
        this.listPlayer = listPlayer;
        LoggerUtil.info("Création d'une équipe: " + name + " (id: " + id + ")");
    }

    /**
     * Fonction pour initialiser la classe league
     *
     * @param id            id de la ligue
     * @param name          nom de la ligue
     * @param idLeague      id de la ligue parent
     * @param maxPlayers    nombre de joueurs maximal dans la liste
     */
    public void initTeam(int id, String name, int idLeague, int maxPlayers) {
        this.id = id;
        this.name = name;
        this.idLeague = idLeague;
        this.listPlayer = new ListPlayer();
        LoggerUtil.info("Création d'une équipe: " + name + " (id: " + id + ")");
    }

    /**
     * Constructeur vide. Initialise la classe avec des parametres par defaut
     */
    public Team() {
        initTeam(-1, "", -1, 200);
    }

    /**
     * Constructeur avec liste de joueur deja creer
     *
     * @param id            id de la ligue
     * @param name          nom de la ligue
     * @param idLeague      id de la ligue parent
     * @param listPlayer    list des joueurs
     */
    public Team(int id, String name, int idLeague, ListPlayer listPlayer) { initTeam(id, name, idLeague, listPlayer); }

    /**
     * Constructeur avec creation d'une liste de joueur avec nombre maximal de joueur
     *
     * @param id            id de la ligue
     * @param name          nom de la ligue
     * @param idLeague      id de la ligue parent
     * @param maxPlayers    nombre de joueur maximal dans la liste des joueurs
     */
    public Team(int id, String name, int idLeague, int maxPlayers) {
        initTeam(id, name, idLeague, maxPlayers);
    }

    /**
     * Constructeur avec une liste de joueur deja creee
     *
     * @param id         Id de l'équipe
     * @param name       Nom de l'équipe
     * @param listPlayer Liste de joueurs de l'équipe
     */
    public Team(int id, String name, ListPlayer listPlayer) {
        initTeam(id, name, -1, listPlayer);
    }

    /**
     * Constructeur paramétré créant une liste vide de joueurs sans ligue
     *
     * @param id   Id de l'équipe
     * @param name Nom de l'équipe
     */
    public Team(int id, String name, int idLeague) {
        this.id = id;
        this.name = name;
        this.idLeague = idLeague;
        this.listPlayer = new ListPlayer();
    }

    /**
     * Constructeur avec liste de joueur deja creee
     *
     * @param name     Nom de l'équipe
     * @param idLeague l'id de la ligue
     */
    public Team(String name, int idLeague, ListPlayer listPlayer) {
        initTeam(-1, name, idLeague, listPlayer);
    }

    // Methodes
    /**
     * Ajouter un joueur a la liste
     *
     * @param newPlayer Objet joueur a ajouter
     *
     * @return le joueur est ajoutee? [Boolean]
     */
    public boolean addPlayer(Player newPlayer) {
        LoggerUtil.info("Tentative d'insertion de joueur" + "(" + newPlayer.getName() + " " + newPlayer.getLastName() +  ")" + " dans une équipe" + "(" + this.name +  ")" );
        return this.listPlayer.addPlayer(newPlayer);
    }

    /**
     * Retirer un joueur de l'équipe à partir de l'objet
     *
     * @param player Objet de type Joueur
     *
     * @return l'equipe est retiree? [Boolean]
     */
    public boolean removePlayer(Player player) {
        LoggerUtil.warning("Tentative de retrait du joueur " + player.getName() + " " + player.getLastName() + " de l'équipe");
        return this.listPlayer.removePlayer(player);
    }

    /**
     * Retirer un joueur de l'équipe à partir de l'objet
     *
     * @param index index du joueur à retirer
     *
     * @return l'equipe est retiree? [Boolean]
     */
    public boolean removePlayer(int index) {
        LoggerUtil.warning("Tentative de retrait de joueur de l'équipe par l'index " + index);
        return this.listPlayer.removePlayer(index);
    }

    /**
     * Crée un nouveau joueur et l'insère dans la liste
     *
     * @param fn Prénom
     * @param ln Nom de famille
     * @return Faux si le nom est invalide sinon vrai
     */
    public boolean newPlayer(String fn, String ln, int number) {
        if (fn.isEmpty() || ln.isEmpty() || number < 0) {
            LoggerUtil.error("Impossible de créer le joueur " +fn + " " + ln + " " + number + " pour l'ajouter dans l'équipe");
            return false;
        }
        Player player = new Player(fn, ln, getId(), number);
        if (this.addPlayer(player)) {
            LoggerUtil.info("L'ajout du joueur " + fn + " " + ln + " " + number + " dans l'équipe fut un succès");
            return true;
        }
        LoggerUtil.error("L'ajout du joueur " + fn + " " + ln + " " + number + " dans l'équipe ne fut complété");

        return false;
    }

    /**
     * Afficher dans la console les joueurs d'une equipe
     */
    public void printTeam() {
        System.out.printf("base.Team : %s\nid = %d\nidLeague = %d\nList base.Player: \n", getName(), getId(),
                getIdLeague());
        for (int i = 0; i < getListPlayer().getSize(); i++) {
            getListPlayer().getPlayer(i).printPlayer();
        }
    }

    // Setter
    public boolean setIdLeague(int idLeague) {
        if (idLeague < 0) {
            LoggerUtil.error("Impossible de changer l'id de la ligue " + this.idLeague + " -X-> " + idLeague);
            return false;
        }
        LoggerUtil.info("Changement de l'id de la ligue avec succès" + this.idLeague + " --> " + idLeague );
        this.idLeague = idLeague;

        return true;
    }

    public boolean setId(int id) {
        if (id <= 0) {
            LoggerUtil.error("Impossible de changer l'id de l'équipe " + this.name + ": " + this.id + " -X-> " + id);
            return false;
        }
        for (int i = 0; i < getListPlayer().getSize(); i++) {
            getListPlayer().getPlayer(i).setIdTeam(id);
        }
        LoggerUtil.info("Le changement de l'id de l'équipe " + this.name + " fut un succès" + this.id + " --> " + id);
        this.id = id;
        return true;
    }

    public void setName(String newName) {
        LoggerUtil.info("Changement du nom de l'équipe: " + this.name + " --> " + newName);
        this.name = newName;
    }

    // Getter
    public int getIdLeague() { return this.idLeague; }
    public ListPlayer getListPlayer() {
        return this.listPlayer;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}