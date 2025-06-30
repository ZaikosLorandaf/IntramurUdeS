package ca.usherbrooke.fgen.api.backend.BdTables;

import ca.usherbrooke.fgen.api.backend.Lists.ListLeague;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Season.
 *
 * Cette classe teste les fonctionnalités de base de la classe Season, notamment :
 * - La création de saisons avec différents constructeurs
 * - La gestion des attributs (année, précision temporelle)
 * - La manipulation des ligues associées
 * - Les méthodes utilitaires (toString, etc.)
 *
 * Les tests se concentrent sur :
 * - La validation des valeurs par défaut
 * - La manipulation des attributs via getters/setters
 * - L'initialisation correcte des listes
 * - Le formatage de l'affichage
 *
 * @author Olivier Picard
 * @version 1.0
 * @see Season
 * @see ListLeague
 */
class SeasonTest {
    private Season season;
    private static final int TEST_ID = 1;
    private static final int TEST_YEAR = 2025;
    private static final String TEST_TIME_PRECISION = "Automne";

    /**
     * Configuration initiale avant chaque test.
     * Initialise une saison avec des valeurs de test.
     */
    @BeforeEach
    void setUp() {
        season = new Season(TEST_ID, TEST_YEAR, TEST_TIME_PRECISION);
    }

    /**
     * Teste le constructeur par défaut.
     */
    @Test
    void testDefaultConstructor() {
        Season defaultSeason = new Season();
        assertEquals(-1, defaultSeason.getId());
        assertNotNull(defaultSeason.getLeagues());
        assertEquals(0, defaultSeason.getLeagues().getSize());
    }

    /**
     * Teste le constructeur avec paramètres.
     */
    @Test
    void testParameterizedConstructor() {
        assertEquals(TEST_ID, season.getId());
        assertEquals(TEST_YEAR, season.getSeasonYear());
        assertEquals(TEST_TIME_PRECISION, season.getTimePrecision());
        assertNotNull(season.getLeagues());
    }

    /**
     * Teste la modification de l'ID.
     */
    @Test
    void testSetId() {
        int newId = 42;
        season.setId(newId);
        assertEquals(newId, season.getId());
    }

    /**
     * Teste la modification de l'année.
     */
    @Test
    void testSetSeasonYear() {
        int newYear = 2026;
        season.setSeasonYear(newYear);
        assertEquals(newYear, season.getSeasonYear());
    }

    /**
     * Teste la modification de la précision temporelle.
     */
    @Test
    void testSetTimePrecision() {
        String newPrecision = "Hiver";
        season.setTimePrecision(newPrecision);
        assertEquals(newPrecision, season.getTimePrecision());
    }

    /**
     * Teste l'initialisation de la liste des ligues.
     */
    @Test
    void testLeaguesListInitialization() {
        assertNotNull(season.getLeagues());
        assertEquals(0, season.getLeagues().getSize());
    }

    /**
     * Teste la méthode toString.
     */
    @Test
    void testToString() {
        String expectedString = TEST_TIME_PRECISION + " " + TEST_YEAR;
        assertEquals(expectedString, season.toString());
    }

    /**
     * Teste la création avec une année invalide.
     */
    @Test
    void testInvalidYear() {
        Season invalidSeason = new Season(TEST_ID, -1, TEST_TIME_PRECISION);
        assertEquals(-1, invalidSeason.getSeasonYear());
    }

    /**
     * Teste la création avec une précision temporelle null.
     */
    @Test
    void testNullTimePrecision() {
        Season nullPrecisionSeason = new Season(TEST_ID, TEST_YEAR, null);
        assertNull(nullPrecisionSeason.getTimePrecision());
    }

    /**
     * Teste la création avec une précision temporelle vide.
     */
    @Test
    void testEmptyTimePrecision() {
        Season emptyPrecisionSeason = new Season(TEST_ID, TEST_YEAR, "");
        assertEquals("", emptyPrecisionSeason.getTimePrecision());
    }

    /**
     * Teste l'égalité des objets Season.
     */
    @Test
    void testSeasonEquality() {
        Season sameSeason = new Season(TEST_ID, TEST_YEAR, TEST_TIME_PRECISION);
        assertEquals(season.toString(), sameSeason.toString());
    }
}