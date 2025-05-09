package base;

public class League {
  private ListTeam listTeam;
  private String leagueName;
  private int leagueID;

  /**
   * Base Constructor, creates an empty league
   */
  public League() {
    listTeam = new ListTeam();
    leagueName = "Generic base.League";
    leagueID = -1;
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
  public boolean removeTeam(Team team) {
    return listTeam.removeTeam(team);
  }

  /**
   * Remove a team from the league using the Team ID
   *
   * @param team int: ID of the team to delete
   * @return Boolean
   */
  public boolean removeTeam(int index) {
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

}
