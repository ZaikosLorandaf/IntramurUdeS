package base;

public class League {
  private ListTeam listTeam;
  private String leagueName;
  private int leagueID;

  public League() {
    listTeam = new ListTeam();
    leagueName = "Generic base.League";
    leagueID = -1;
  }

  public League(ListTeam teams, String name, int id) {
    listTeam = teams;
    leagueName = name;
    leagueID = id;
  }

  public boolean newTeam(int id, String name, ListPlayer listPlayer) {
    Team newTeam = new Team(id, name, listPlayer);
    this.addTeam(newTeam);
    return true;
  }

  public boolean addTeam(Team team) {
    return listTeam.addTeam(team);
  }

  public boolean removeTeam(Team team) {
    return listTeam.removeTeam(team);
  }

  public boolean removeTeam(int index) {
    return listTeam.removeTeam(index);
  }

  public ListTeam getTeams() {
    return listTeam;
  }

  public String getName() {
    return leagueName;
  }

  public int getID() {
    return leagueID;
  }

}
