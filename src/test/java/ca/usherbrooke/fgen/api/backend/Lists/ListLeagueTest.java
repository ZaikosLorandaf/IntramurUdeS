package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.League;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe ListLeague.
 *
 * Cette classe teste les fonctionnalités de la classe ListLeague, notamment :
 * - L'ajout et la gestion des ligues
 * - Les opérations sur la collection
 * - Les méthodes de recherche et d'accès
 * - La gestion des identifiants
 *
 * @author Olivier Picard
 * @version 1.0
 * @see ListLeague
 * @see League
 */
class ListLeagueTest {
    private ListLeague listLeague;
    private League testLeague1;
    private League testLeague2;
    private static final int TEST_ID_1 = 1;
    private static final int TEST_ID_2 = 2;
    private static final String TEST_NAME_1 = "Test League 1";
    private static final String TEST_NAME_2 = "Test League 2";

    /**
     * Configuration initiale avant chaque test.
     */
    @BeforeEach
    void setUp() {
        listLeague = new ListLeague();
        Date beginDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now().plusDays(30));
        testLeague1 = new League(TEST_NAME_1, beginDate, endDate);
        testLeague2 = new League(TEST_NAME_2, beginDate, endDate);
        testLeague1.setLeagueID(TEST_ID_1);
        testLeague2.setLeagueID(TEST_ID_2);
    }

    /**
     * Teste l'ajout d'une ligue unique.
     */
    @Test
    void testAddSingleLeague() {
        assertTrue(listLeague.addLeague(testLeague1));
        assertEquals(1, listLeague.getSize());
        assertEquals(testLeague1, listLeague.getLeague(TEST_ID_1));
    }

    /**
     * Teste l'ajout de plusieurs ligues.
     */
    @Test
    void testAddMultipleLeagues() {
        List<League> leagues = Arrays.asList(testLeague1, testLeague2);
        assertEquals(2, listLeague.addLeague(leagues));
        assertEquals(2, listLeague.getSize());
    }

    /**
     * Teste l'ajout d'une ligue en double.
     */
    @Test
    void testAddDuplicateLeague() {
        listLeague.addLeague(testLeague1);
        assertFalse(listLeague.addLeague(testLeague1));
        assertEquals(1, listLeague.getSize());
    }

    /**
     * Teste la récupération par ID.
     */
    @Test
    void testGetLeagueById() {
        listLeague.addLeague(testLeague1);
        League retrievedLeague = listLeague.getLeague(TEST_ID_1);
        assertNotNull(retrievedLeague);
        assertEquals(TEST_NAME_1, retrievedLeague.getName());
    }

    /**
     * Teste la récupération par nom.
     */
    @Test
    void testGetLeagueByName() {
        listLeague.addLeague(testLeague1);
        League retrievedLeague = listLeague.getLeague(TEST_NAME_1);
        assertNotNull(retrievedLeague);
        assertEquals(TEST_ID_1, retrievedLeague.getId());
    }

    /**
     * Teste la récupération d'une ligue inexistante.
     */
    @Test
    void testGetNonExistentLeague() {
        assertNull(listLeague.getLeague(999));
        assertNull(listLeague.getLeague("Non Existent League"));
    }

    /**
     * Teste la récupération des IDs des ligues.
     */
    @Test
    void testGetLeagueIds() {
        listLeague.addLeague(testLeague1);
        listLeague.addLeague(testLeague2);
        List<Integer> ids = listLeague.getLeagueIds();
        assertTrue(ids.contains(TEST_ID_1));
        assertTrue(ids.contains(TEST_ID_2));
        assertEquals(2, ids.size());
    }

    /**
     * Teste la taille de la liste.
     */
    @Test
    void testListSize() {
        assertEquals(0, listLeague.getSize());
        listLeague.addLeague(testLeague1);
        assertEquals(1, listLeague.getSize());
    }

    /**
     * Teste l'ajout d'une ligue avec ID négatif.
     */
    @Test
    void testAddLeagueWithNegativeId() {
        League invalidLeague = new League(TEST_NAME_1, Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(30)));
        invalidLeague.setLeagueID(-1);
        assertFalse(listLeague.addLeague(invalidLeague));
    }

    /**
     * Teste l'ajout d'une ligue avec nom null.
     */
    @Test
    void testAddLeagueWithNullName() {
        League nullNameLeague = new League(null, Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(30)));
        nullNameLeague.setLeagueID(3);
        assertTrue(listLeague.addLeague(nullNameLeague));
    }

    /**
     * Teste la récupération de l'ID d'une ligue.
     */
    @Test
    void testGetId() {
        assertEquals(TEST_ID_1, listLeague.getId(testLeague1));
    }

    /**
     * Teste la récupération du nom d'une ligue.
     */
    @Test
    void testGetName() {
        assertEquals(TEST_NAME_1, listLeague.getName(testLeague1));
    }
}