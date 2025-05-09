package base;

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

    /**
     * Constructeur paramétré créant une équipe avec une liste et un id de base
     * @param name Nom de l'équipe
     * @param idLeague l'id de la ligue
     */
    public Team(String name, int idLeague)
    {
        this.id = -1;
        this.name = name;
        this.idLeague = idLeague;
        this.listPlayer = new ListPlayer();
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

    /**
     * Ajouter un objet de type joueur dans la liste
     * @param newPlayer Objet de type joueur
     * @return vrai si l'objet est non-null
     */
    public boolean addPlayer(Player newPlayer)
    {
        return this.listPlayer.addPlayer(newPlayer);
    }

    /**
     * Retirer un joueur de l'équipe à partir de l'objet
     * @param player Objet de type Joueur
     * @return vrai si le joueur est retiré
     */
    public boolean removePlayer(Player player)
    {
        return this.listPlayer.removePlayer(player);
    }

    /**
     * Retirer un joueur de l'équipe à partir de l'objet
     * @param index index du joueur à retirer
     * @return vrai si un joueur est retiré adéquatement
     */
    public boolean removePlayer(int index)
    {
        return this.listPlayer.removePlayer(index);
    }

    /**
     * Crée un nouveau joueur et l'insère dans la liste
     * @param fn Prénom
     * @param ln Nom de famille
     * @return Faux si le nom est invalide sinon vrai
     */
    public boolean newPlayer(String fn, String ln)
    {
        if (fn.isEmpty() || ln.isEmpty()) return false;
        Player player = new Player(fn,ln,getId());
        this.addPlayer(player);
        return true;
    }

    /**
     * Fontion de tests de la classe Team
     */
    public void printTeam()
    {
        System.out.printf("base.Team : %s\nid = %d\nidLeague = %d\nList base.Player: \n",getName(),getId(),getIdLeague());
        for(int i = 0; i < getListPlayer().getSize();i++)
        {
            getListPlayer().getPlayer(i).printPlayer();
        }
    }

    public boolean setId(int id)
    {
        if(id <= 0) return false;
        for(int i = 0; i<getListPlayer().getSize();i++)
        {
            getListPlayer().getPlayer(i).setIdTeam(id);
        }
        this.id = id;
        return true;
    }

    public int getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(int idLeague)
    {
        this.idLeague = idLeague;
    }
}