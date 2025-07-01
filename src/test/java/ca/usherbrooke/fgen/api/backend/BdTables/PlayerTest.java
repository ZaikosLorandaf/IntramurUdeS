package ca.usherbrooke.fgen.api.backend.BdTables;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests pour la classe Player.
 * Teste les constructeurs, les accesseurs, les modificateurs, ainsi que certains cas limites.
 */
class PlayerTest {

    /**
     * Vérifie la création d'un joueur avec prénom, nom et id d'équipe.
     */
    @Test
    void testSimpleConstructor() {
        Player player = new Player("Alice", "Smith", 1);
        assertEquals("Alice", player.getName());
        assertEquals("Smith", player.getLastName());
        assertEquals(1, player.getIdTeam());
    }

    /**
     * Vérifie la création d'un joueur avec numéro de dossard.
     */
    @Test
    void testConstructorWithNumber() {
        Player player = new Player("Bob", "Johnson", 2, 10);
        assertEquals("Bob", player.getName());
        assertEquals("Johnson", player.getLastName());
        assertEquals(2, player.getIdTeam());
        assertEquals(10, player.getNumber());
    }

    /**
     * Vérifie la création complète avec tous les champs.
     */
    @Test
    void testFullConstructor() {
        Player player = new Player(42, "Charlie", "Brown", 9, 3);
        assertEquals(42, player.getId());
        assertEquals("Charlie", player.getName());
        assertEquals("Brown", player.getLastName());
        assertEquals(9, player.getNumber());
        assertEquals(3, player.getIdTeam());
    }

    /**
     * Vérifie que le nom peut être modifié correctement.
     */
    @Test
    void testSetName() {
        Player player = new Player("Alice", "Smith", 1);
        player.setName("Ally");
        assertEquals("Ally", player.getName());
    }

    /**
     * Vérifie que le nom de famille peut être modifié correctement.
     */
    @Test
    void testSetLastName() {
        Player player = new Player("Alice", "Smith", 1);
        player.setLastName("Williams");
        assertEquals("Williams", player.getLastName());
    }

    /**
     * Vérifie que l'id d’équipe peut être modifié.
     */
    @Test
    void testSetIdTeam() {
        Player player = new Player("Alice", "Smith", 1);
        player.setIdTeam(5);
        assertEquals(5, player.getIdTeam());
    }

    /**
     * Cas limite : création avec nom vide.
     */
    @Test
    void testEmptyName() {
        Player player = new Player("", "Smith", 1);
        assertEquals("", player.getName());
    }

    /**
     * Cas limite : création avec id d'équipe négatif.
     */
    @Test
    void testNegativeTeamId() {
        Player player = new Player("Alice", "Smith", -1);
        assertEquals(-1, player.getIdTeam());
    }

    /**
     * Cas limite : modification du nom vers null (non géré ici mais possible en Java).
     */
    @Test
    void testSetNameToNull() {
        Player player = new Player("Alice", "Smith", 1);
        player.setName(null);
        assertNull(player.getName());
    }
}
