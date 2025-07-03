package ca.usherbrooke.fgen.api.backend.DTO.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatPlayer;

import java.util.ArrayList;
import java.util.List;

public class StatPlayerDTO extends StatDTO {

    private int idPlayer;
    private int idLeaguePlayer;
    private int idTeam;

    public StatPlayerDTO(StatPlayer statPlayer) {
        super(statPlayer);
        this.idPlayer = statPlayer.getIdPlayer();
        this.idLeaguePlayer = statPlayer.getIdLeaguePlayer();
        this.idTeam = statPlayer.getIdTeam();
    }

    public int getIdPlayer() {
        return this.idPlayer;
    }
    public int getIdLeaguePlayer() {
        return this.idLeaguePlayer;
    }
    public int getIdTeam() {
        return this.idTeam;
    }



    public static List<StatPlayerDTO> mapListToDTOStatPlayer(List<StatPlayer> listStat){
        List<StatPlayerDTO> listStatDTO = new ArrayList<>();
        for (StatPlayer stat : listStat) {
            listStatDTO.add(new StatPlayerDTO(stat));
        }
        return listStatDTO;
    }
}
