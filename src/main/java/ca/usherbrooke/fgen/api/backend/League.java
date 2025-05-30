package ca.usherbrooke.fgen.api.backend;

import java.sql.Date;
import java.sql.Time;

public class League {
  private int id;
  private ListTeam listTeam;
  private String name;
  private Date beginDate;
  private Date endDate;
  private int idSport;
  private boolean done = false;
  private java.util.Random rand = new java.util.Random();

  // Constructeurs
  /**
   * Fonction pour initialiser la classe league
   *
   * @param id      id de la ligue
   * @param name    nom de la ligue
   */
  public void initLeague(int id, String name) {
    this.id = id;
    this.name = name;
    this.listTeam = new ListTeam();
    LoggerUtil.info("Création d'une ligue");
  }

  /**
   * Fonction pour initialiser la classe league avec liste deja creee
   *
   * @param id        id de la ligue
   * @param name      nom de la ligue
   * @param listTeam  liste d'objet team
   */
  public void initLeague(int id, String name, ListTeam listTeam) {
    this.id = id;
    this.name = name;
    this.listTeam = listTeam;
    LoggerUtil.info("Création d'une ligue");
  }

  /**
   * Constructeur vide. Initialise la classe avec des parametres par defaut
   */
  public League() { initLeague(-1, "Generic base.League"); }

  /**
   * Constructeur avec liste d'objet equipe deja creee, nom de la ligue et id de la ligue
   *
   * @param teams List of teams [ListTeam]
   * @param name  Name of the league [String]
   * @param id    League ID [int]
   */
  public League(ListTeam teams, String name, int id) { initLeague(id, name, teams); }

  /**
   * Constructeur avec nom de la ligue
   *
   * @param name  nom de la ligue [String]
   */
  public League(String name) { initLeague(rand.nextInt(1,1000), name); }

  /**
   * Constructeur sans liste d'objet equipe
   */
  /*public League(int id, String name, Date beginDate, Date endDate, boolean done, int idSport) {
    this.id = id;
    this.name = name;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.done = done;
    this.idSport = idSport;
  }*/

  /**
   * Constructeur avec date de debut et date de fin
   *
   * @param id        Id of the league
   * @param name      Name of the league
   * @param beginDate Date where the league begins
   * @param endDate   Date where the league ends
   * @param idSport   The id of the sport of the league
   */
  public League(int id, String name, Date beginDate, Date endDate, int idSport) {
    initLeague(id, name);
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.idSport = idSport;
    this.done = false;
  }

  // Methodes
  /**
   * Creer et ajouter l'equipe a une ligue. L'equipe a deja une liste de joueur
   *
   * @param id         id de l'equipe
   * @param name       nom de l'equipe
   * @param listPlayer Liste des joueurs de l'equipe
   *
   * @return l'equipe est ajoutee? [Boolean]
   */
  public boolean newTeam(int id, String name, ListPlayer listPlayer) {
    Team newTeam = new Team(id, name, listPlayer);
    LoggerUtil.info("Tentative de création d'équipe " + name);
    return listTeam.addTeam(newTeam);
  }

  /**
   * Creer et ajouter une equipe a une ligue.
   *
   * @param id         int: Team ID
   * @param name       String: Team name
   *
   * @return l'equipe est ajoutee? [Boolean]
   */
  public boolean newTeam(int id, String name) {
    ListPlayer listPlayer = new ListPlayer();
    return newTeam(id, name, listPlayer);
  }

  /**
   * Creer et ajouter une equipe a une ligue avec un nom.
   *
   * @param name       String: Team name
   *
   * @return l'equipe est ajoutee? [Boolean]
   */
  public boolean newTeam(String name) {
    ListPlayer listPlayer = new ListPlayer();
    return newTeam(-1, name, listPlayer);
  }


  /**
   * Ajouter une equipe a une ligue
   *
   * @param team    l'equipe a ajouter a la ligue
   *
   * @return l'equipe est ajoutee? [Boolean]
   */
  public boolean addTeam(Team team) {
    return listTeam.addTeam(team);
  }

  /**
   * Retirer une equipe d'une ligue a partir de l'objet Team
   *
   * @param team    objet Team a retirer
   *
   * @return l'equipe est retiree? [Boolean]
   */
  public boolean removeTeam(Team team) {
    LoggerUtil.info("Tentative de retrait de l'équipe: " + team.getName());
    return listTeam.removeTeam(team);
  }

  /**
   * Retirer une equipe d'une ligue avec son ID
   *
   * @param id id de l'équipe à retirer
   *
   * @return l'equipe est retiree? [Boolean]
   */
  public boolean removeTeam(int id) {
    LoggerUtil.info("Tentative de retrait d'équipe");
    return listTeam.removeTeam(id);
  }

  /**
   * Afficher dans la console la liste des equipes de la ligue
   */
  public void printLeague() {
    System.out.printf("------Ligue------\nNom: %s\nId: %d\nDone : %b\n", name, id,done);
    if (listTeam.getSize() > 0) {
      listTeam.printListTeam();
      return;
    }
    System.out.println("Pas d'équipe");
    return;
  }

  // Setter
  public void setDone(boolean bool)
  {
    this.done = bool;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }
  public void setIdSport(int idSport) {
    this.idSport = idSport;
  }

  public boolean setName(String leagueName) {
    if(leagueName == null) {
      LoggerUtil.error("Impossible de changer le nom de la ligue");
      return false;
    }
    LoggerUtil.info("Changement du nom de la ligue: " + this.name + " -> " + leagueName);
    this.name = leagueName;
    return true;
  }

  public boolean setLeagueID(int idLeague) {
    if (idLeague < 0) {
      LoggerUtil.error("Impossible de changer l'id de la ligue");
      return false;
    }

    this.id = idLeague;
    for (int i = 0; i < getTeams().getSize(); i++) {
      this.getTeams().getTeam(i).setIdLeague(idLeague);
    }
    LoggerUtil.info("Changement de l'id de la ligue");
    return true;
  }

  // Getter
  public boolean getDone()
  {
    return this.done;
  }
  public Date getEndDate() {
    return this.endDate;
  }
  public Date getBeginDate() {
    return this.beginDate;
  }
  public int getIdSport() {
    return this.idSport;
  }
  public int getId() {
    return this.id;
  }
  public String getName() {
    return this.name;
  }
  public ListTeam getTeams() { return this.listTeam; }
}