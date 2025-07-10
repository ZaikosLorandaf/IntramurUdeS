package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatStatement;
import ca.usherbrooke.fgen.api.backend.BdTables.Stats.StatTeam;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.backend.LoggerUtil;
import ca.usherbrooke.fgen.api.service.objectServices.stats.StatStatementService;
import ca.usherbrooke.fgen.api.service.objectServices.stats.StatTeamService;
import io.quarkus.arc.Arc;

import javax.inject.Inject;

public class StatTeamSingleton {
    @Inject
    OGClass ogClass;
    @Inject
    StatTeamService statTeamService;
    @Inject
    StatStatementService statStatementService;



    public StatTeamSingleton() {
        ogClass = Arc.container().instance(OGClass.class).get();
        statTeamService = Arc.container().instance(StatTeamService.class).get();
    }

    public String add(String sportName, String leagueName, String teamName, String statement, String value)
    {
        if(sportName.isBlank() || leagueName.isBlank() || teamName.isBlank() || statement.isBlank()|| value.isBlank()) return "Invalid Parameters";

        ListSport sportList = ogClass.getSportSingleton().getSportList();
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        ca.usherbrooke.fgen.api.backend.BdTables.League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName,sportName);
        if (league == null)
            return "Pas de ligue";

        ca.usherbrooke.fgen.api.backend.BdTables.Team team = league.getListTeam().getTeam(teamName);
        if (team == null)
            return "Pas d'équipe";

        Integer id = statTeamService.getLastId() + 1;
        Integer idStatStament;
        try {
            StatStatement statStament = ogClass.getStatStatementSingleton().getStatStatement().getItem(statement);
            if(statStament == null) return "Statement introuvable";
            idStatStament = statStament.getId();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "Erreur Statement "+ e.getMessage();
        }


        Integer idSport = sportList.getSport(sportName).getId();
        Integer idLeague = league.getId();
        Integer idTeam = team.getId();
        Integer idTeamLeague = team.getIdLeague();
        if(!idTeamLeague.equals(idLeague)) return "Erreur Ligue-Team";

        StatTeam statTeam = new StatTeam(id,idStatStament,value,-1,-1,idLeague,idTeam,idTeamLeague,idSport);

        statTeamService.addStatTeam(statTeam);

        return "Stat: " + statement + " = " + value + " ajoutée";
    }
}
