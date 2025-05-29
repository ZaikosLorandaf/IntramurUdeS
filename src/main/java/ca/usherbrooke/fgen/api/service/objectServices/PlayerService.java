package ca.usherbrooke.fgen.api.service.objectServices;

import ca.usherbrooke.fgen.api.backend.OGClass;
import ca.usherbrooke.fgen.api.backend.Player;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
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

    // Redirection vers les fonctions template
    @GET
    public List<Player> getPlayers() {
        return getItems();
    }

    @GET
    @Path("{id}")
    public Player getPlayer(@PathParam("id") Integer id) {
        return getItem(id);
    }

    @POST
    @Consumes("application/json")
    public void addPlayer(Player player) {
        addItem(player);
    }


    // Implementation des fonctions du template
    protected List<Player> selectAll(){
        return playerMapper.selectPlayers();
    }
    protected Player selectOne(Integer id){
        return playerMapper.selectOnePlayer(id);
    }

    protected void insert(Player player){
        playerMapper.insertPlayer(player);
    }
    protected void add(Player player){
        ogClass.getSportList().getTeam(player.getIdTeam()).addPlayer(player);
    }

    protected void setName(Player player) {
        player.setName(Parser.unescapeEntities(player.getName(), true));
    }
}

/*public class PlayerService {
    @Inject
    PlayerMapper playerMapper;
    @Inject
    OGClass ogClass;

    @GET
    public List<Player> getPlayers() {
        List<Player> players = playerMapper.selectPlayers();
        for (Player player : players) {
            ogClass.getSportList().getTeam(player.getIdTeam()).addPlayer(player);
        }

        return unescapeEntities(players);
    }

    @GET
    @Path("{id}")
    public Player getPlayer(
            @PathParam("id") Integer id
    ) {
        Player player = playerMapper.selectOnePlayer(id);
        ogClass.getSportList().getTeam(player.getIdTeam()).addPlayer(player);
        return unescapeEntities(player);
    }

    @POST
    @Consumes("application/json")
    public void addPlayer(Player player) {
        // Ajouter l'équipe à la base de données via le mapper
        playerMapper.insertPlayer(player);

        // Ajouter l'équipe à la ligue correspondante
        ogClass.getSportList().getTeam(player.getIdTeam()).addPlayer(player);
    }

    public static Player unescapeEntities(Player player) {
        player.setName(Parser.unescapeEntities(player.getName(), true));
        return player;
    }

    public List<Player> unescapeEntities(List<Player> player) {
        return player
                .stream()
                .map(PlayerService::unescapeEntities)
                .collect(Collectors.toList());
    }
}*/
