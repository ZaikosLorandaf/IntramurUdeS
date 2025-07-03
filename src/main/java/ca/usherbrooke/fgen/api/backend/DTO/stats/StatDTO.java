package ca.usherbrooke.fgen.api.backend.DTO.stats;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Season;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.Stat;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe adapter pour donner juste ce qu'il faut pour le json retour
 */
public class StatDTO {
    private int id;
    private int idStatStatement;
    private int idSeason;
    private int idMatch;
    private String value;
    private int idLeague;
    private int idSport;
    private String statement;
    private String acronym;

    public StatDTO(Stat stat) {
        this.id = stat.getId();
        this.value = stat.getValue();
        this.idStatStatement = stat.getStatStatement().getId();
        this.statement = stat.getStatStatement().getStatement();
        this.acronym = stat.getStatStatement().getAcronym();
        this.idSeason = stat.getSeason() != null ? stat.getSeason().getId() : -1;
        this.idMatch = stat.getMatch() != null ? stat.getMatch().getId() : -1;
        this.idLeague = stat.getLeague() != null ? stat.getLeague().getId() : -1;
        this.idSport = stat.getSport() != null ? stat.getSport().getId() : -1;
    }

    public int getId() {
        return this.id;
    }
    public int getIdStatStatement() {
        return this.idStatStatement;
    }
    public String getStatement(){
        return this.statement;
    }
    public String getAcronym() {
        return this.acronym;
    }
    public int getIdSeason() {
        return this.idSeason;
    }
    public int getIdMatch() {
        return this.idMatch;
    }
    public String getValue() {
        return this.value;
    }

    /**
     * Méthode qui prend une liste de stat et la transforme en liste de statDTO
     * @param listStat Liste de stat à transformer
     * @return La liste des stats DTO
     */
    public static List<StatDTO> mapListStatDTO(List<Stat> listStat){
        List<StatDTO> listStatDTO = new ArrayList<>();
        for (Stat stat : listStat) {
            listStatDTO.add(new StatDTO(stat));
        }
        return listStatDTO;
    }

}
