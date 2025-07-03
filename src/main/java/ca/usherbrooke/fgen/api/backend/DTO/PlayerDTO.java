package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.Player;
import ca.usherbrooke.fgen.api.backend.DTO.stats.StatDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe adapter pour donner juste ce qu'il faut pour le json retour
 */
public class PlayerDTO {
    private String name;
    private int number;
    private List<StatDTO> stats;

    public PlayerDTO(Player player){
        this.name = player.getFullName();
        this.number = player.getNumber();
        this.stats = StatDTO.mapListToDTO(player.getListStat().getAllItems());
    }

    public static List<PlayerDTO> mapListToDTO(List<Player> players){
        List<PlayerDTO> returnList = new ArrayList<PlayerDTO>();
        for(Player player : players){
            returnList.add(new PlayerDTO(player));
        }
        return returnList;
    }

    public String getName() {
        return this.name;
    }
    public int getNumber() {
        return this.number;
    }
    public List<StatDTO> getStats() {
        return this.stats;
    }
}
