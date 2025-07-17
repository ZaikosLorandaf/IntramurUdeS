package ca.usherbrooke.fgen.api.backend.BdTables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Match.
 *
 * Cette classe teste les fonctionnalités de base de la classe Match, notamment :
 * - La création de matchs avec différents constructeurs
 * - La gestion des dates et heures
 * - La gestion des équipes et leurs IDs
 * - Les valeurs par défaut et cas limites
 *
 * Les tests excluent volontairement :
 * - Les fonctionnalités nécessitant ListSport (injection)
 * - Les interactions avec la base de données
 * - Les méthodes getTeamsFromId() qui dépendent de ListSport
 *
 * @author Olivier Picard
 * @version 1.0
 * @see Match
 * @see Team
 */
class MatchTest {
    private Match match;
    private Date testDate;
    private Time beginTime;
    private Time endTime;
    private String place;
    private static final int TEST_ID = 1;
    private static final int TEST_LEAGUE_ID = 100;
    private static final int TEST_SEASON_ID = 1;

    /**
     * Configuration initiale avant chaque test.
     * Initialise un match avec des valeurs de test.
     */
    @BeforeEach
    void setUp() {
        testDate = Date.valueOf(LocalDate.now());
        beginTime = Time.valueOf(LocalTime.of(14, 0)); // 14:00
        endTime = Time.valueOf(LocalTime.of(16, 0));   // 16:00
        place = "Mustafar";
        match = new Match(TEST_ID, testDate, beginTime, endTime, TEST_LEAGUE_ID, 2);
    }

    /**
     * Teste le constructeur par défaut.
     */
    @Test
    void testDefaultConstructor() {
        Match defaultMatch = new Match();
        assertEquals(-1, defaultMatch.getId());
        assertNull(defaultMatch.getDate());
        assertNull(defaultMatch.getBeginTime());
        assertNull(defaultMatch.getEndTime());
    }

    /**
     * Teste le constructeur avec paramètres de base.
     */
    @Test
    void testBasicConstructor() {
        Match basicMatch = new Match(TEST_ID, testDate, beginTime, endTime);
        assertEquals(TEST_ID, basicMatch.getId());
        assertEquals(testDate, basicMatch.getDate());
        assertEquals(beginTime, basicMatch.getBeginTime());
        assertEquals(endTime, basicMatch.getEndTime());
        assertNotNull(basicMatch.getTeams());
        assertNotNull(basicMatch.getIdTeams());
        assertTrue(basicMatch.getTeams().isEmpty());
        assertTrue(basicMatch.getIdTeams().isEmpty());
    }

    /**
     * Teste le constructeur complet avec tous les paramètres.
     */
    @Test
    void testFullConstructor() {
        List<Integer> teamIds = Arrays.asList(1, 2);
        Match fullMatch = new Match(TEST_ID, testDate, beginTime, endTime, place,
                TEST_LEAGUE_ID, 2, teamIds, TEST_SEASON_ID);

        assertEquals(TEST_ID, fullMatch.getId());
        assertEquals(TEST_LEAGUE_ID, fullMatch.getIdLeague());
        assertEquals(2, fullMatch.getNbTeamMatch());
        assertEquals(teamIds, fullMatch.getIdTeams());
        assertEquals(TEST_SEASON_ID, fullMatch.getIdSeason());
    }

    /**
     * Teste les getters pour les dates et heures.
     */
    @Test
    void testDateTimeGetters() {
        assertEquals(testDate, match.getDate());
        assertEquals(beginTime, match.getBeginTime());
        assertEquals(endTime, match.getEndTime());
    }

    /**
     * Teste les getters pour les IDs et paramètres de base.
     */
    @Test
    void testBasicGetters() {
        assertEquals(TEST_ID, match.getId());
        assertEquals(TEST_LEAGUE_ID, match.getIdLeague());
        assertEquals(2, match.getNbTeamMatch());
    }

    /**
     * Teste l'initialisation des listes d'équipes.
     */
    @Test
    void testTeamListsInitialization() {
        assertNotNull(match.getTeams());
        assertNotNull(match.getIdTeams());
        assertTrue(match.getTeams().isEmpty());
        assertTrue(match.getIdTeams().isEmpty());
    }

    /**
     * Teste la méthode toString.
     */
    @Test
    void testToString() {
        String matchString = match.toString();
        assertTrue(matchString.contains(String.valueOf(TEST_ID)));
        assertTrue(matchString.contains(testDate.toString()));
        assertTrue(matchString.contains(beginTime.toString()));
        assertTrue(matchString.contains(endTime.toString()));
        assertTrue(matchString.contains(String.valueOf(TEST_LEAGUE_ID)));
    }

    /**
     * Teste la création d'un match avec des heures invalides.
     */
    @Test
    void testInvalidTimes() {
        Time laterBeginTime = Time.valueOf(LocalTime.of(17, 0));
        Match invalidMatch = new Match(TEST_ID, testDate, laterBeginTime, endTime);
        assertTrue(invalidMatch.getBeginTime().after(invalidMatch.getEndTime()));
    }

    /**
     * Teste la création d'un match avec nombre d'équipes différent.
     */
    @Test
    void testDifferentTeamNumber() {
        Match threeTeamMatch = new Match(TEST_ID, testDate, beginTime, endTime, TEST_LEAGUE_ID, 3);
        assertEquals(3, threeTeamMatch.getNbTeamMatch());
    }
}