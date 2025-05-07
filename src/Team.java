
public class Team
{
    private int id;
    private String name;
    private League league;
    private ListPlayer listPlayer;


    /**
     * Constructeur par défaut
     */
    public Team()
    {
        this.id = -1;
        this.name = "";
        this.league = null;
        this.listPlayer = new ListPlayer(200);
    }

    /**
     * Constructeur paramétré prenant une liste de joueur déjà montée
     * @param id Id de l'équipe
     * @param name Nom de l'équipe
     * @param listPlayer Liste de joueur de l'équipe
     */
    public Team(int id, String name, League league, ListPlayer listPlayer)
    {
        this.id = id;
        this.name = "";
        this.league = league;
        this.listPlayer = listPlayer;
    }

    /**
     * Constructeur paramétré créant une liste vide de joueurs
     * @param id Id de l'équipe
     * @param name Nom de l'équipe
     * @param maxPlayers Nombre maximum de joueurs possible
     */
    public Team(int id, String name, League league, int maxPlayers)
    {
        this.id = id;
        this.name = name;
        this.league = league;
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
        this.league = null;
        this.listPlayer = listPlayer;
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
        this.league = null;
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
    public void addPlayer(Player newPlayer)
    {
        this.listPlayer.addPlayer(newPlayer);
    }
    public void removePlayer(Player player)
    {
        //this.listPlayer.removePlayer(player);
    }

    public void removePlayer(int index)
    {
        this.listPlayer.removePlayer(index);
    }
}