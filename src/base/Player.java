package base;

public class Player {
    private int idName;
    private String name;
    private int idLastName;
    private String lastName;

    private int idTeam;

    /**
     * Constructeur de la classe
     * @param fn Prénom
     * @param ln Nom
     * @param idTeam id de l'équipe dans la db
     */
    public Player(String fn, String ln, int idTeam)
    {
        setName(fn);
        setLastName(ln);
        setIdTeam(idTeam);
    }

    /**
     * Get le nom du joueur
     * @return nom
     */
    public String getName()
    {
        return name;
    }

    public String getLastName()
    {
        return lastName;
    }
    public void setName(String n)
    {
        name = n;

        //METTRE LE LIEN AVEC L'ID
    }
    public void setLastName(String n)
    {
        lastName = n;
        //METTRE LE LIEN AVEC L'ID
    }
    public void setIdTeam(int id)
    {
        idTeam = id;
    }
    public int getIdTeam()
    {
        return idTeam;
    }

    /**
     * Print dans la console le joueur pour les tests
     */
    public void printPlayer()
    {
        System.out.printf("%s %s, I am on team %d\n",name,lastName,idTeam);
    }
}
