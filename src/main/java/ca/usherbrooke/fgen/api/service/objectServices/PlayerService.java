package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
import ca.usherbrooke.fgen.api.service.postClass.removePlayer;
import io.smallrye.common.constraint.NotNull;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;


@Path("/api/player")
public class PlayerService extends TemplateService<Player> {
    @Inject
    PlayerMapper playerMapper;
    @Inject
    OGClass ogClass;

    // Methodes POST
    @POST
    @Consumes("application/json")
    public void addPlayer(Player player) {
        addItem(player);
    }

    @POST
    @Path("removePlayer")
    public String removePlayer(@NotNull removePlayer player ) {
        return ogClass.removePlayer(player.sportName, player.leagueName, player.teamName, player.playerNumber);
    }

    // Methode GET
    @GET
    public List<Player> getPlayers() {
        return getItems();
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
        return ogClass.listPlayer(nomSport,nomLigue,nomEquipe);
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
        Team team = ogClass.getSportList().getTeam(teamId);
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
