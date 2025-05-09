import java.util.List;

public class Main {
    public static void main(String[] args) {

        ListTeam list = new ListTeam();
        Team team = new Team();
        list.printListTeam();
        list.addTeam(team);
        list.printListTeam();
        list.addTeam(team);
        list.printListTeam();
        list.removeTeam(0);
        list.printListTeam();
    }
}