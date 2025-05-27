package ca.usherbrooke.fgen.api.service.objectServices;


import ca.usherbrooke.fgen.api.backend.Player;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
import org.jsoup.parser.Parser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/player")
public class PlayerService {
    @Inject
    PlayerMapper playerMapper;

    @GET
    public List<Player> getPlayers() {
        List<Player> players = playerMapper.selectPlayers();
        return unescapeEntities(players);
    }

    @GET
    @Path("{id}")
    public Player getPlayer(
            @PathParam("id") Integer id
    ) {
        Player player = playerMapper.selectOnePlayer(id);
        return unescapeEntities(player);
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
}