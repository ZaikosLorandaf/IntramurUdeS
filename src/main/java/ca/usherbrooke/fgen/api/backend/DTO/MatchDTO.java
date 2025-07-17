package ca.usherbrooke.fgen.api.backend.DTO;

import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MatchDTO {
    private int id;
    private Date date;
    private Time beginTime;
    private Time endTime;
    private String place;
    private List<SimpleTeamDTO> teams;

    public MatchDTO(Match match) {
        this.id = match.getId();
        this.date = match.getDate();
        this.beginTime = match.getBeginTime();
        this.endTime = match.getEndTime();
        this.place = match.getPlace();
        this.teams = SimpleTeamDTO.mapListToSimpleDTO(match.getTeams());
    }

    /**
     *
     * Constructeur pour adapter un match en DTO pour retourner le JSON
     * Ne mets que les équipes qui ne sont pas celle donnée en paramètre
     * @param match Match à adapter
     * @param idTeam Id de l'équipe dans laquelle est affichée le match, ne pas donner l'équipe de cet ID
     */
    public MatchDTO(Match match, int idTeam) {
        this.id = match.getId();
        this.date = match.getDate();
        this.beginTime = match.getBeginTime();
        this.endTime = match.getEndTime();
        this.place = match.getPlace();
        List<Team> listTeamAgainst = new ArrayList<>();
        for (Team team : match.getTeams()) {
            if (team.getId() != idTeam) {
                listTeamAgainst.add(team);
            }
        }
        if(!listTeamAgainst.isEmpty()) {
            this.teams = SimpleTeamDTO.mapListToSimpleDTO(listTeamAgainst);
        }
    }

    public int getId() {
        return this.id;
    }
    public Date getDate() {
        return this.date;
    }
    public Time getBeginTime() {
        return this.beginTime;
    }
    public Time getEndTime() {
        return this.endTime;
    }
    public String getPlace() {
        return this.place;
    }
    public List<SimpleTeamDTO> getTeams() {
        return this.teams;
    }

    /**
     * Méthode pour adapter une liste de match en DTO
     * @param matches Liste des matchs à adapter
     * @return List des matchs adaptés
     */
    public static List<MatchDTO> mapListToDTO(List<Match> matches){
        List<MatchDTO> returnList = new ArrayList<>();
        for (Match match : matches) {
            returnList.add(new MatchDTO(match));
        }
        return returnList;
    }

    /**
     * Méthode pour adapter une liste de match en DTO
     * Ne donne pas l'équipe dont le id est donné en paramètre
     * @param matches Liste des matchs à adapter
     * @param idTeam Id de la team dans laquelle est affichée le match
     * @return List des matchs adaptés
     */
    public static List<MatchDTO> mapListToDTO(List<Match> matches, int idTeam){
        List<MatchDTO> returnList = new ArrayList<>();
        if(matches == null || matches.isEmpty()) {
            return returnList;
        }
        for (Match match : matches) {
            returnList.add(new MatchDTO(match, idTeam));
        }
        return returnList;
    }

}







