package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Sport;
import ca.usherbrooke.fgen.api.backend.Lists.ListSeason;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.mapper.LeagueMapper;
import ca.usherbrooke.fgen.api.service.objectServices.LeagueService;
import io.quarkus.arc.Arc;

import java.sql.Date;

public class LeagueSingleton {
    ListSport sportList;
    LeagueService leagueService;
    LeagueMapper leagueMapper;

    LeagueSingleton(){
        this.sportList = Arc.container().instance(ListSport.class).get();
        this.leagueService = Arc.container().instance(LeagueService.class).get();
        this.leagueMapper = Arc.container().instance(LeagueMapper.class).get();
    }

    // Gestion donnees
    public String add(String nom_sport, String nom, Date dateDebut, Date dateFin) {
        Sport sport = sportList.getSport(nom_sport);
        if (sport == null) {
            return "Sport Error";
        }
        if (dateFin == null || dateDebut == null || dateFin.before(dateDebut)) {
            return "Erreur dans les dates";
        }
        League newLeague = new League(nom, dateDebut, dateFin);
        int id = leagueService.getLastId() + 1;
        newLeague.setLeagueID(id);
        int sportId = sport.getId();
        newLeague.setIdSport(sportId);

        String result;

        if (addDb(newLeague)) {
            result = "<div>";
            result += nom + "</div>";
        } else {
            result = "Impossible d'ajouter la ligue dans la base de données " + "<br>";
        }


        return result;
    }

    public boolean addDb(League league) {
        leagueService.addLeague(league);
        return true;
    }

    public String remove(String sport, String nom) {
        Sport getsport = sportList.getSport(sport);
        if (sport == null){
            return "Sport Error";
        }


        League league = getsport.getListLeague().getLeague(nom);
        String result = "";
        if (league != null) {
            result = "Ligue retirée :" + league.getName();
            sportList.getSport(sport).getListLeague().removeLeague(league);
            leagueMapper.deleteOne(sportList.getSport(sport).getListLeague().getId(league));
            System.out.println("Ligue retirée: " + result);
        } else {
            result = "Pas de ligue appelé " + nom;
        }
        return result;
    }

    // public String removeLeague(String nom) {
    // League league = listLeague.getLeague(nom);
    // String result = "";
    // if(league != null) {
    // result = "Ligue retirée :" + league.getName();
    // listLeague.removeLeague(league);
    // System.out.println("Ligue retirée: " + result);
    // }
    // else
    // {
    // result = "Pas de ligue appelé " + nom;
    // }
    // //result = "<div>" + result + "</div>";
    // //String result = "<div> aaaaa </div>";
    // return result;
    // }


    // Getter
    public String listLeague(String sportName) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        String result = "";
        if (sportList.getSport(sportName).getListLeague().getSize() <= 0)
            return "<div>Pas de ligue</div>";

        for (int i : sportList.getSport(sportName).getListLeague().getLeagueIds())
            result += sportList.getSport(sportName).getListLeague().getLeague(i).getName() + "</br>";

        return result;
    }

    public String getLeague(String sport, String nom) {
        if (!sportList.getAllSports().contains(sportList.getSport(sport)))
            return "Sport Error";

        ca.usherbrooke.fgen.api.backend.BdTables.League league = sportList.getSport(sport).getListLeague().getLeague(nom);
        String result;
        if (league != null) {
            result = league.getName();
            System.out.println("League: " + result);
        } else {
            result = "Pas de ligue appelé " + nom;
        }
        return result;
    }
}
