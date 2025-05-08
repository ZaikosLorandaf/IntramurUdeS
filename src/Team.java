
public class Team
{
    private int id;
    private String name;
    private int idLeague;
    private ListPlayer listPlayer;


    /**
     * Constructeur par défaut
     */
    public Team()
    {
        this.id = -1;
        this.name = "";
        this.idLeague = -1;
        this.listPlayer = new ListPlayer(200);
    }

    /**
     * Constructeur paramétré prenant une liste de joueur déjà montée
     * @param id Id de l'équipe
     * @param name Nom de l'équipe
     * @param listPlayer Liste de joueur de l'équipe
     */
    public Team(int id, String name, int idLeague, ListPlayer listPlayer)
    {
        this.id = id;
        this.name = "";
        this.idLeague = idLeague;
        this.listPlayer = new ListPlayer();
    }

    /**
     * Constructeur paramétré créant une liste vide de joueurs
     * @param id Id de l'équipe
     * @param name Nom de l'équipe
     * @param maxPlayers Nombre maximum de joueurs possible
     */
    public Team(int id, String name, int idLeague, int maxPlayers)
    {
        this.id = id;
        this.name = name;
        this.idLeague = idLeague;
        this.listPlayer = new ListPlayer(maxPlayers);
    }

    /**
     * Constructeur paramétré prenant une liste de joueurs déjà montée sans ligue
     * @param id Id de l'équipe
     * @param name Nom de l'équipe
     * @param listPlayer Liste de joueurs de l'équipe
     */
    public Team(int id, String name, ListPlayer listPlayer)
    {
        this.id = id;
        this.name = "";
        this.idLeague = -1;
        this.listPlayer = new ListPlayer();
    }

    /**
     * Constructeur paramétré créant une liste vide de joueurs sans ligue
     * @param id Id de l'équipe
     * @param name Nom de l'équipe
     * @param maxPlayers Nombre maximal de joueurs
     */
    public Team(int id, String name, int maxPlayers)
    {
        this.id = id;
        this.name = name;
        this.idLeague = -1;
        this.listPlayer = new ListPlayer(maxPlayers);
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String newName)
    {
        this.name = newName;
    }

    public ListPlayer getListPlayer()
    {
        return this.listPlayer;
    }
    public boolean addPlayer(Player newPlayer)
    {
        return this.listPlayer.addPlayer(newPlayer);
    }
    public boolean removePlayer(Player player)
    {
        return this.listPlayer.removePlayer(player);
    }

    public boolean removePlayer(int index)
    {
        return this.listPlayer.removePlayer(index);
    }

    public boolean newPlayer(String fn, String ln)
    {
        if (fn.isEmpty() || ln.isEmpty()) return false;
        Player player = new Player(fn,ln,getId());
        this.addPlayer(player);
        return true;
    }
}