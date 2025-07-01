package ca.usherbrooke.fgen.api.backend.Lists;

import ca.usherbrooke.fgen.api.backend.BdTables.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe ListMatch.
 *
 * Cette classe teste les fonctionnalités de la classe ListMatch, notamment :
 * - L'ajout et la suppression de matchs
 * - La recherche de matchs par différents critères
 * - La gestion des IDs
 * - Les cas limites et erreurs potentielles
 *
 * @author Olivier Picard
 * @version 1.0
 * @see ListMatch
 * @see Match
 */
class ListMatchTest {
    private ListMatch listMatch;
    private Match testMatch1;
    private Match testMatch2;
    private static final int TEST_ID_1 = 1;
    private static final int TEST_ID_2 = 2;
    private static final int TEST_LEAGUE_ID = 100;
    private static final Date TEST_DATE = Date.valueOf(LocalDate.now());
    private static final Time TEST_BEGIN_TIME = Time.valueOf(LocalTime.of(14, 0));
    private static final Time TEST_END_TIME = Time.valueOf(LocalTime.of(16, 0));

    @BeforeEach
    void setUp() {
        listMatch = new ListMatch();
        testMatch1 = new Match(TEST_ID_1, TEST_DATE, TEST_BEGIN_TIME, TEST_END_TIME, TEST_LEAGUE_ID, 2);
        testMatch2 = new Match(TEST_ID_2, TEST_DATE, TEST_BEGIN_TIME, TEST_END_TIME, TEST_LEAGUE_ID, 2);
        testMatch1.getIdTeams().addAll(Arrays.asList(1, 2));
        testMatch2.getIdTeams().addAll(Arrays.asList(3, 4));
    }

    /**
     * Teste l'ajout d'un match unique.
     */
    @Test
    void testAddMatch() {
        assertTrue(listMatch.addMatch(testMatch1));
        assertEquals(1, listMatch.getMapSize());
        assertEquals(testMatch1, listMatch.getMatch(TEST_ID_1));
    }

    /**
     * Teste l'ajout d'un match en double.
     */
    @Test
    void testAddDuplicateMatch() {
        listMatch.addMatch(testMatch1);
        assertFalse(listMatch.addMatch(testMatch1));
        assertEquals(1, listMatch.getMapSize());
    }

    /**
     * Teste la récupération d'un match par ID.
     */
    @Test
    void testGetMatchById() {
        listMatch.addMatch(testMatch1);
        Match retrievedMatch = listMatch.getMatch(TEST_ID_1);
        assertNotNull(retrievedMatch);
        assertEquals(TEST_ID_1, retrievedMatch.getId());
    }

    /**
     * Teste la récupération d'un match par objet Match.
     */
    @Test
    void testGetMatchByObject() {
        listMatch.addMatch(testMatch1);
        Match retrievedMatch = listMatch.getMatch(testMatch1);
        assertNotNull(retrievedMatch);
        assertEquals(testMatch1.getId(), retrievedMatch.getId());
    }

    /**
     * Teste la récupération d'un match par date et équipes.
     */
    @Test
    void testGetMatchByDateAndTeams() {
        listMatch.addMatch(testMatch1);
        Match retrievedMatch = listMatch.getMatch(TEST_DATE, 1, 2);
        assertNotNull(retrievedMatch);
        assertEquals(testMatch1.getId(), retrievedMatch.getId());
    }

    /**
     * Teste la récupération d'un match inexistant.
     */
    @Test
    void testGetNonExistentMatch() {
        assertNull(listMatch.getMatch(999));
        assertNull(listMatch.getMatch(TEST_DATE, 999, 888));
    }

    /**
     * Teste la suppression d'un match par ID.
     */
    @Test
    void testRemoveMatchById() {
        listMatch.addMatch(testMatch1);
        assertTrue(listMatch.removeMatch(TEST_ID_1));
        assertEquals(0, listMatch.getMapSize());
    }

    /**
     * Teste la suppression d'un match par objet Match.
     */
    @Test
    void testRemoveMatchByObject() {
        listMatch.addMatch(testMatch1);
        assertTrue(listMatch.removeMatch(testMatch1));
        assertEquals(0, listMatch.getMapSize());
    }

    /**
     * Teste la suppression d'un match inexistant.
     */
    @Test
    void testRemoveNonExistentMatch() {
        assertFalse(listMatch.removeMatch(999));
        Match nonExistentMatch = new Match(999, TEST_DATE, TEST_BEGIN_TIME, TEST_END_TIME);
        assertFalse(listMatch.removeMatch(nonExistentMatch));
    }

    /**
     * Teste l'obtention de l'ID d'un match.
     */
    @Test
    void testGetId() {
        assertEquals(TEST_ID_1, listMatch.getId(testMatch1));
    }

    /**
     * Teste l'obtention du nom (toString) d'un match.
     */
    @Test
    void testGetName() {
        String matchString = listMatch.getName(testMatch1);
        assertTrue(matchString.contains(String.valueOf(TEST_ID_1)));
        assertTrue(matchString.contains(TEST_DATE.toString()));
    }

    /**
     * Teste la recherche de match avec des équipes spécifiques.
     */
    @Test
    void testGetMatchWithSpecificTeams() {
        listMatch.addMatch(testMatch1);
        listMatch.addMatch(testMatch2);
        Match foundMatch = listMatch.getMatch(TEST_DATE, 1, 2);
        assertNotNull(foundMatch);
        assertEquals(testMatch1.getId(), foundMatch.getId());
        assertNull(listMatch.getMatch(TEST_DATE, 1, 3));
    }
}