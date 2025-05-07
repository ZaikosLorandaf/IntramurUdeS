public class Player {
    public Player(String fn, String ln, int idTeam)
    {
        setName(fn);
        setLastName(ln);
        setIdTeam(idTeam);
    }

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


    private int idName;
    private String name;
    private int idLastName;
    private String lastName;

    private int idTeam;
}
