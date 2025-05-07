import java.util.Vector;

public class ListTeam
{
    private Vector<Team> list = new Vector<Team>();

    /**
     * Ajouter une équipe au vecteur
     * @param team L'équipe à ajouter
     */
    public void addTeam(Team team)
    {
        this.list.addElement(team);
    }

    /**
     * Retirer une équipe au vecteur en fonction de l'équipe
     * @param team Équipe à retirer
     * @return true si une équipe a été retirée, sinon false
     */
    public Boolean removeTeam(Team team)
    {
        return this.list.removeElement(team);
    }

    /**
     * Retirer une équipe au vecteur selon son index
     * @param index L'index de l'équipe à retirer
     * @return L'équipe qui a été retirée
     */
    public Team removeTeam(int index)
    {
        return this.list.remove(index);
    }
}
