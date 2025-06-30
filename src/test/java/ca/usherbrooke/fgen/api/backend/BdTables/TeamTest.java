package ca.usherbrooke.fgen.api.backend.BdTables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la classe Team.
 * Couvre les constructeurs, ajouts/retraits de joueurs, et les setters avec cas limites.
 */
class TeamTest {

    private Team team;

    @BeforeEach
    void setup() {
        team = new Team(1, "Tigres", 100, 10);
    }

    /**
     * Vérifie que le constructeur initialise correctement les valeurs.
     */
    @Test
    void testConstructorBasicValues() {
        assertEquals(1, team.getId());
        assertEquals("Tigres", team.getName());
        assertEquals(100, team.getIdLeague());
        assertNotNull(team.getListPlayer());
    }

    /**
     * Vérifie que l'ajout d'un joueur valide fonctionne correctement.
     */
    @Test
    void testAddPlayer() {
        Player player = new Player("John", "Doe", 1);
        boolean result = team.addPlayer(player);
        assertTrue(result);
        assertEquals(1, team.getListPlayer().getSize());
    }

    /**
     * Vérifie que l'ajout d'un joueur avec données invalides échoue.
     */
    @Test
    void testNewPlayerInvalid() {
        assertFalse(team.newPlayer("", "Doe", 7));
        assertFalse(team.newPlayer("Jane", "", 7));
        assertFalse(team.newPlayer("Jane", "Doe", -5));
        assertEquals(0, team.getListPlayer().getSize());
    }

    /**
     * Vérifie que l'ajout d’un joueur valide avec newPlayer fonctionne.
     */
    @Test
    void testNewPlayerValid() {
        assertTrue(team.newPlayer("Alice", "Smith", 10));
        assertEquals(1, team.getListPlayer().getSize());
    }

    /**
     * Vérifie que le setter de nom modifie correctement l’attribut.
     */
    @Test
    void testSetName() {
        team.setName("Panthères");
        assertEquals("Panthères", team.getName());
    }

    /**
     * Vérifie que setIdLeague refuse une valeur négative.
     */
    @Test
    void testSetIdLeagueNegative() {
        assertFalse(team.setIdLeague(-10));
        assertEquals(100, team.getIdLeague()); // inchangé
    }

    /**
     * Vérifie que setIdLeague accepte une valeur valide.
     */
    @Test
    void testSetIdLeagueValid() {
        assertTrue(team.setIdLeague(200));
        assertEquals(200, team.getIdLeague());
    }

    /**
     * Vérifie que setId échoue pour une valeur <= 0.
     */
    @Test
    void testSetIdInvalid() {
        assertFalse(team.setId(0));
        assertEquals(1, team.getId()); // inchangé
    }

    /**
     * Vérifie que setId met à jour l’équipe et celle de chaque joueur.
     */
    @Test
    void testSetIdValidUpdatesPlayers() {
        team.newPlayer("Emma", "Watson", 8);
        assertTrue(team.setId(42));
        assertEquals(42, team.getId());
        assertEquals(42, team.getListPlayer().getPlayer(0).getIdTeam());
    }
}
