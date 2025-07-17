package ca.usherbrooke.fgen.api.backend.DTO.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatPlayer;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatTeam;

import java.util.ArrayList;
import java.util.List;

public class StatTeamDTO extends StatDTO {

    private int idLeagueTeam;
    private int idTeam;

    public StatTeamDTO(StatTeam statTeam) {
        super(statTeam);
        this.idLeagueTeam = statTeam.getIdLeagueTeam();
        this.idTeam = statTeam.getIdTeam();
    }

    public int getIdLeagueTeam() {
        return this.idLeagueTeam;
    }
    public int getIdTeam() {
        return this.idTeam;
    }



    public static List<StatTeamDTO> mapListToDTOStatTeam(List<StatTeam> listStat){
        List<StatTeamDTO> listStatDTO = new ArrayList<>();
        for (StatTeam stat : listStat) {
            listStatDTO.add(new StatTeamDTO(stat));
        }
        return listStatDTO;
    }
}
