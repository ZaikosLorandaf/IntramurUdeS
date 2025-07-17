package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.DTO.PlayerDTO;
import ca.usherbrooke.fgen.api.backend.Lists.ListPlayer;
import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
import ca.usherbrooke.fgen.api.service.postClass.addPlayer;
import ca.usherbrooke.fgen.api.service.postClass.removePlayer;

import io.quarkus.arc.Arc;
import io.smallrye.common.constraint.NotNull;
import org.jsoup.parser.Parser;
import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;


@Path("/api/player")
public class PlayerService extends TemplateService<Player> {
    PlayerMapper playerMapper;
    OGClass ogClass;

    @PostConstruct
    public void init() {
        this.ogClass = Arc.container().instance(OGClass.class).get();
        this.playerMapper = Arc.container().instance(PlayerMapper.class).get();
    }

    // Methodes POST
    @POST
    @Consumes("application/json")
    public void addPlayer(Player player) {
        addItem(player);
    }

    @POST
    @Path("addPlayer")
    public String addPlayer(@NotNull addPlayer player) {
        if (player.nom.length() >= nameMaxLength){
            return "Name too long";
        }
        return ogClass.getPlayerSingleton().add(player.nomSport, player.nomLigue, player.nomTeam, player.prenom, player.nom, player.number);
    }

    @POST
    @Path("removePlayer")
    public String removePlayer(@NotNull removePlayer player ) {
        return ogClass.getPlayerSingleton().remove(player.sportName, player.leagueName, player.teamName, player.playerNumber);
    }

    // Methode GET
    @GET
    public List<PlayerDTO> getPlayers() {
        this.getItems();
        List<PlayerDTO> returnList = new ArrayList<>();
        for(Sport sport : ogClass.getSportSingleton().getSportList().getAllSports()) {
            for (League league : sport.getListLeague().getAllItems()){
                for (Team team : league.getListTeam().getAllItems()){
                    returnList.addAll(PlayerDTO.mapListToDTO(team.getListPlayer().getAllItems()));
                }
            }
        }
        return returnList;
    }

    @GET
    @Path("{idSport}/{idLeague}/{idTeam}")
    public List<PlayerDTO> getPlayersFromTeam(
            @PathParam("idSport") int idSport,
            @PathParam("idLeague") int idLeague,
            @PathParam("idTeam") int idTeam
    ) {
        this.getItems();
        Sport sport = ogClass.getSportSingleton().getSportList().getSport(idSport);
        League league = sport.getListLeague().getLeague(idLeague);
        Team team = league.getListTeam().getTeam(idTeam);
        ListPlayer listPlayer = team.getListPlayer();
        List<PlayerDTO> playerDTOs = PlayerDTO.mapListToDTO(listPlayer.getAllItems());
        return  playerDTOs;
    }


    @GET
    @Path("{id}")
    public Player getPlayer(@PathParam("id") Integer id) {
        return getItem(id);
    }

    @GET
    @Path("listPlayer/{nom_sport}/{nom_ligue}/{nom_equipe}")
    public String listPlayer(
            @PathParam("nom_sport") String nomSport,
            @PathParam("nom_ligue") String nomLigue,
            @PathParam("nom_equipe") String nomEquipe) {
        nomSport = nomSport.replace("%20", " ");
        nomLigue = nomLigue.replace("%20", " ");
        nomEquipe = nomEquipe.replace("%20", " ");
        return ogClass.getPlayerSingleton().listPlayer(nomSport,nomLigue,nomEquipe);
    }

    // Implementation des fonctions du template
    protected List<Player> selectAll(){
        return playerMapper.selectAll();
    }
    protected Player selectOne(Integer id){
        return playerMapper.selectOne(id);
    }

    protected void insert(Player player){
        playerMapper.insert(player);
    }
    protected void add(Player player){
        player = unescapeEntities(player);
        int teamId = player.getIdTeam();
        Team team = ogClass.getSportSingleton().getSportList().getTeam(teamId);
        team.addPlayer(player);
    }

    protected void setName(Player player) {
        player.setName(Parser.unescapeEntities(player.getName(), true));
    }

    /**
     * Méthode pour aller chercher le prochain id de l'ajout
     * @return
     */
    public int getLastId()
    {
        return playerMapper.getLastId();
    }
}
