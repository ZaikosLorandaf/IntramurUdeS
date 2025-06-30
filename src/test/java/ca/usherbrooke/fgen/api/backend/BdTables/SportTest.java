package ca.usherbrooke.fgen.api.backend.BdTables;

import ca.usherbrooke.fgen.api.backend.Lists.ListLeague;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests unitaires pour la classe Sport.
 * Vérifie les constructeurs, l'ajout de ligues et les getters/setters.
 */
class SportTest {

    private Sport sport;

    @BeforeEach
    void setUp() {
        sport = new Sport(10, "Soccer", 3);
    }

    /**
     * Vérifie l'initialisation correcte avec tous les paramètres.
     */
    @Test
    void testFullConstructor() {
        assertEquals(10, sport.getId());
        assertEquals("Soccer", sport.getName());
        assertEquals(3, sport.getNbTeamMatch());
        assertNotNull(sport.getListLeague());
    }

    /**
     * Vérifie le constructeur avec nom uniquement.
     */
    @Test
    void testConstructorWithNameOnly() {
        Sport s = new Sport("Hockey");
        assertEquals("Hockey", s.getName());
        assertEquals(-1, s.getId());
        assertEquals(2, s.getNbTeamMatch()); // valeur par défaut
    }

    /**
     * Vérifie le constructeur avec nom et id.
     */
    @Test
    void testConstructorWithNameAndId() {
        Sport s = new Sport("Basket", 42);
        assertEquals("Basket", s.getName());
        assertEquals(42, s.getId());
        assertEquals(2, s.getNbTeamMatch());
    }

    /**
     * Vérifie le constructeur par défaut.
     */
    @Test
    void testDefaultConstructor() {
        Sport s = new Sport();
        assertEquals("", s.getName());
        assertEquals(-1, s.getId());
        assertEquals(2, s.getNbTeamMatch());
        assertNotNull(s.getListLeague());
    }

    /**
     * Vérifie que le setter d'id fonctionne correctement.
     */
    @Test
    void testSetId() {
        sport.setId(999);
        assertEquals(999, sport.getId());
    }

    /**
     * Vérifie que le setter de nom fonctionne correctement.
     */
    @Test
    void testSetName() {
        sport.setName("Volleyball");
        assertEquals("Volleyball", sport.getName());
    }

    /**
     * Vérifie que le getter du nombre de matchs retourne la bonne valeur.
     */
    @Test
    void testGetNbTeamMatch() {
        assertEquals(3, sport.getNbTeamMatch());
    }

    /**
     * Vérifie que la liste des ligues est bien initialisée.
     */
    @Test
    void testListLeagueInitialization() {
        ListLeague list = sport.getListLeague();
        assertNotNull(list);
        assertEquals(0, list.getSize());
    }
}
