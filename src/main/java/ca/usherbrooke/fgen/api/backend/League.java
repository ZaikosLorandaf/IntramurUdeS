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
  public void initLeague(int id, String name) {
    this.id = id;
    this.name = name;
    this.listTeam = new ListTeam();
    LoggerUtil.info("Création d'une ligue");
  }
  public void initLeague(int id, String name, ListTeam listTeam) {
    this.id = id;
    this.name = name;
    this.listTeam = listTeam;
    LoggerUtil.info("Création d'une ligue");
  }

  /**
   * Base Constructor, creates an empty league
   */
  public League() {
    initLeague(-1, "Generic base.League");
  }

  /**
   * Constructor for a League based on a list of teams
   *
   * @param teams List of teams [ListTeam]
   * @param name  Name of the league [String]
   * @param id    League ID [int]
   */
  public League(ListTeam teams, String name, int id) {
    initLeague(id, name, teams);
  }
  public League(String name) {
    initLeague(rand.nextInt(1,1000), name);
  }

  public League(int id, String name, Date beginDate, Date endDate, boolean done, int idSport) {
    this.id = id;
    this.name = name;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.done = done;
    this.idSport = idSport;
  }

  /**
   * Constructor for a league
   * @param id Id of the league
   * @param name Name of the league
   * @param beginDate Date where the league begins
   * @param endDate Date where the league ends
   * @param idSport The id of the sport of the league
   */
  public League(int id, String name, Date beginDate, Date endDate, int idSport) {
    this.id = id;
    this.name = name;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.idSport = idSport;
    this.done = false;
    this.listTeam = new ListTeam();
  }



  /**
   * Creates and add a new team in the league
   *
   * @param id         int: Team ID
   * @param name       String: Team name
   * @param listPlayer ListPlayer: List of the players in the team
   * @return Boolean
   */
  public boolean newTeam(int id, String name, ListPlayer listPlayer) {
    Team newTeam = new Team(id, name, listPlayer);
    LoggerUtil.info("Tentative de création d'équipe " + name);
    return listTeam.addTeam(newTeam);
  }


  public boolean newTeam(int id, String name) {
    ListPlayer listPlayer = new ListPlayer();
    Team newTeam = new Team(id, name, listPlayer);
    LoggerUtil.info("Tentative de création de l'équipe " + name);
    return listTeam.addTeam(newTeam);
  }


  public boolean newTeam(String name) {
    ListPlayer listPlayer = new ListPlayer();
    Team newTeam = new Team(-1, name, listPlayer);
    LoggerUtil.info("Tentative de création d'équipe");
    return listTeam.addTeam(newTeam);
  }


  /**
   * Adds a new team in the league
   *
   * @param team Team: A Team item to be added to the league
   * @return Boolean
//   */
  public boolean addTeam(Team team) {
    return listTeam.addTeam(team);
  }

  /**
   * Remove a team from the league using the Team item
   *
   * @param team Team: Team item to be removed
   * @return Boolean
   */
  public boolean removeTeam(Team team) {
    LoggerUtil.info("Tentative de retrait de l'équipe: " + team.getName());
    return listTeam.removeTeam(team);
  }

  /**
   * Remove a team from the league using the Team ID
   *
   * @param index index de l'équipe à retirer
   * @return Boolean
   */
  public boolean removeTeam(int index) {
    LoggerUtil.info("Tentative de retrait d'équipe");
    return listTeam.removeTeam(index);
  }

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
    return done;
  }
  public Date getEndDate() {
    return endDate;
  }
  public Date getBeginDate() {
    return beginDate;
  }
  public int getIdSport() {
    return idSport;
  }
  /**
   * Returns the league's ID
   *
   * @return int: League's ID
   */
  public int getId() {
    return id;
  }
  /**
   * Get the list of teams in the league
   *
   * @return String: League name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the list of teams in the league
   *
   * @return ListTeam: Vector containg teams
   */
  public ListTeam getTeams() {
    return listTeam;
  }
}
