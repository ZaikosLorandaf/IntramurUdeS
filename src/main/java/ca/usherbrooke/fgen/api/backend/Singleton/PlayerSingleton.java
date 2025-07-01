package ca.usherbrooke.fgen.api.backend.Singleton;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import ca.usherbrooke.fgen.api.backend.BdTables.Team;
import ca.usherbrooke.fgen.api.backend.Lists.ListSport;
import ca.usherbrooke.fgen.api.mapper.PlayerMapper;
import ca.usherbrooke.fgen.api.service.objectServices.PlayerService;
import io.quarkus.arc.Arc;

import java.util.Map;

public class PlayerSingleton {
    ListSport sportList;
    PlayerService playerService;
    PlayerMapper playerMapper;

    PlayerSingleton() {
        this.sportList = Arc.container().instance(ListSport.class).get();
        this.playerService = Arc.container().instance(PlayerService.class).get();
        this.playerMapper = Arc.container().instance(PlayerMapper.class).get();
    }

    // Gestion donnees
    public String add(String sportName, String leagueName, String teamName, String playerFirsName, String playerLastName, int number) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        ca.usherbrooke.fgen.api.backend.BdTables.League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            return "<div>Pas de ligue</div>";

        ca.usherbrooke.fgen.api.backend.BdTables.Team team = league.getListTeam().getTeam(teamName);
        if (team == null)
            return "<div>Pas d'équipe</div>";

        int id = playerService.getLastId() + 1;
        int idTeam = team.getId();
        ca.usherbrooke.fgen.api.backend.BdTables.Player player = new ca.usherbrooke.fgen.api.backend.BdTables.Player(id, playerFirsName, playerLastName, number, idTeam);
        Map<Integer, Integer> map = team.getListPlayer().getMapNameId();
        if (team.getListPlayer().getMapNameId().containsKey(number))
            return "Erreur numero";
        if (team.getListPlayer().getMapItems().containsKey(id))
            return "Erreur getting id";
        if (addDb(player))
            return "Ajout du joueur";

        return "<div>Erreur nom</div>";
    }

    public boolean addDb(ca.usherbrooke.fgen.api.backend.BdTables.Player player) {
        playerService.addPlayer(player);
        return true;
    }

    public String remove(String sportName, String leagueName, String teamName, int playerNumber) {
        if (sportList.getSport(sportName) == null)
            return "Erreur Sport";

        League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            return "<div>Ligue non-trouvée</div>";

        Team team = league.getListTeam().getTeam(teamName);
        if (team == null)
            return "<div>Équipe non-trouvée</div>";

        ca.usherbrooke.fgen.api.backend.BdTables.Player player = team.getListPlayer().getPlayerByNumber(playerNumber);
        if (player == null)
            return "<div>Joueur non-trouvé</div>";
        int playerId = team.getListPlayer().getId(player);
        //playerMapper.deleteOne(playerId);
        if (team.removePlayer(player))
            return "<div>Joueur retiré</div>";

        return "<div>Erreur lors du retrait du joueur</div>";
    }

    // Getter
    public String listPlayer(String sportName, String leagueName, String teamName) {
        if (sportList.getSport(sportName) == null) {
            return "Erreur Sport";
        }

        String result = "";
        ca.usherbrooke.fgen.api.backend.BdTables.League league = sportList.getSport(sportName).getListLeague().getLeague(leagueName);
        if (league == null)
            result = "Ligue introuvable";
        else {
            ca.usherbrooke.fgen.api.backend.BdTables.Team team = league.getListTeam().getTeam(teamName);
            if (team == null)
                result = "Equipe introuvable";
            else {
                if (team.getListPlayer().getSize() <= 0)
                    result = "Pas de joueur";
                for (int i : team.getListPlayer().getMapNameId().keySet()) {
                    ca.usherbrooke.fgen.api.backend.BdTables.Player player = team.getListPlayer().getPlayerByNumber(i);
                    result += player.getNumber() + ": " + player.getName() + " " + player.getLastName() + "</br>";
                }
            }
        }
        return result;
    }
}
