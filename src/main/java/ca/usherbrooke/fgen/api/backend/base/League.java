package ca.usherbrooke.fgen.api.backend.base;

public class League {
  private ListTeam listTeam;
  private String leagueName;
  private int leagueID;
  private boolean done = false;
  /**
   * Base Constructor, creates an empty league
   */
  public League() {
    listTeam = new ListTeam();
    leagueName = "Generic base.League";
    leagueID = -1;
    LoggerUtil.info("Création d'une ligue");
  }

  /**
   * Constructor for a League based on a list of teams
   *
   * @param teams List of teams [ListTeam]
   * @param name  Name of the league [String]
   * @param id    League ID [int]
   */
  public League(ListTeam teams, String name, int id) {
    listTeam = teams;
    leagueName = name;
    leagueID = id;
    LoggerUtil.info("Création d'une ligue");
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
    LoggerUtil.info("Tentative de création d'équipe");
    return this.addTeam(newTeam);
  }


  public boolean newTeam(int id, String name) {
    ListPlayer listPlayer = new ListPlayer();
    Team newTeam = new Team(id, name, listPlayer);
    LoggerUtil.info("Tentative de création d'équipe");
    return this.addTeam(newTeam);
  }


  /**
   * Adds a new team in the league
   *
   * @param team Team: A Team item to be added to the league
   * @return Boolean
   */
  public boolean addTeam(Team team) {
    return listTeam.addTeam(team);
  }

  /**
   * Remove a team from the league using the Team item
   *
   * @param team Team: Team item to be removed
   * @return Boolean
   */
  public boolean removeTeam(Team team)
  {
    LoggerUtil.info("Tentative de retrait d'équipe");
    return listTeam.removeTeam(team);
  }

  /**
   * Remove a team from the league using the Team ID
   *
   * @param index index de l'équipe à retirer
   * @return Boolean
   */
  public boolean removeTeam(int index)
  {
    LoggerUtil.info("Tentative de retrait d'équipe");
    return listTeam.removeTeam(index);
  }

  /**
   * Get the list of teams in the league
   *
   * @return ListTeam: Vector containg teams
   */
  public ListTeam getTeams() {
    return listTeam;
  }

  /**
   * Get the list of teams in the league
   *
   * @return String: League name
   */
  public String getName() {
    return leagueName;
  }

  /**
   * Returns the league's ID
   *
   * @return int: League's ID
   */
  public int getID() {
    return leagueID;
  }


  public boolean setLeagueID(int idLeague)
  {
    if (idLeague < 0)
    {
      LoggerUtil.error("Impossible de changer l'id de la ligue");
      return false;
    }

    this.leagueID = idLeague;
    for (int i = 0; i < getTeams().getSize(); i++)
    {
      this.getTeams().getTeam(i).setIdLeague(idLeague);
    }
    LoggerUtil.info("Changement de l'id de la ligue");
    return true;
  }

  public boolean setName(String leagueName)
  {
    if(leagueName == null)
    {
      LoggerUtil.error("Impossible de changer le nom de la ligue");
      return false;
    }
    this.leagueName = leagueName;
    LoggerUtil.info("Changement du nom de la ligue");
    return true;
  }

  public void printLeague()
  {
    System.out.printf("------Ligue------\nNom: %s\nId: %d\nDone : %b\n",leagueName,leagueID,done);

    if (listTeam.getSize() > 0)
    {
      listTeam.printListTeam();
      return;
    }
    System.out.println("Pas d'équipe");
    return;
  }

  public boolean getDone()
  {
    return done;
  }

  public void setDone(boolean bool)
  {
    this.done = bool;
  }
}
