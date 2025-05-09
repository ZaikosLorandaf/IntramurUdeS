import java.util.Vector;

public class ListTeam {
    private Vector<Team> list;
    private int maxTeam;

    /**
     * Constructeur de base
     */
    public ListTeam() {
        maxTeam = 100;
        list = new Vector<Team>();
    }

    /**
     * Constructeur paramétré avec la quantité d'équipe maximum
     *
     * @param max quantité d'équipe maximum
     */
    public ListTeam(int max) {
        maxTeam = max;
        list = new Vector<Team>();
    }


    /**
     * Ajouter une équipe au vecteur
     *
     * @param team L'équipe à ajouter
     */
    public boolean addTeam(Team team) {
        if (team == null) return false;
        this.list.addElement(team);
        return true;
    }

    /**
     * Retirer une équipe au vecteur en fonction de l'équipe
     *
     * @param team Équipe à retirer
     * @return true si une équipe a été retirée, sinon false
     */
    public boolean removeTeam(Team team) {
        return this.list.removeElement(team);
    }

    /**
     * Retirer une équipe au vecteur selon son index
     *
     * @param index L'index de l'équipe à retirer
     * @return L'équipe qui a été retirée
     */
    public boolean removeTeam(int index) {
        if (index >= this.list.size()) return false;
        this.list.remove(index);
        return true;
    }

    public Team getTeam(int index)
    {
        try{
            return list.get(index);
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public void printListTeam()
    {
        if(list.size() <= 0)
        {
            System.out.println("Pas d'équipe");
            return;
        }
        System.out.println("-----ListTeam-----");
        for(int i = 0; i < list.size(); i++)
        {
            getTeam(i).printTeam();
        }
        System.out.println("-----Fin-----");
    }




    public void wipeList(){
        this.list.removeAllElements();
    }
}
