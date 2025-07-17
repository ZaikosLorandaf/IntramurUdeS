package ca.usherbrooke.fgen.api.backend.BdTables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe League.
 * Cette classe teste les fonctionnalités principales de la classe League
 * sans dépendre du container Quarkus.
 */
class LeagueTest {
    private League league;
    private Date beginDate;
    private Date endDate;

    @BeforeEach
    void setUp() {
        beginDate = Date.valueOf(LocalDate.now());
        endDate = Date.valueOf(LocalDate.now().plusDays(30));
        // Utilisons le constructeur qui n'utilise pas Arc.container()
        league = new League("Test League", beginDate, endDate);
    }

    /**
     * Teste la création d'une ligue avec des dates valides.
     */
    @Test
    void testValidDates() {
        assertEquals(beginDate, league.getBeginDate());
        assertEquals(endDate, league.getEndDate());
    }

    /**
     * Teste l'ajout d'une équipe à la ligue.
     */
    @Test
    void testAddTeam() {
        Team team = new Team(1, "Test Team",1);
        assertTrue(league.addTeam(team));
        assertEquals(1, league.getListTeam().getSize());
    }

    /**
     * Teste l'ajout de plusieurs équipes.
     */
    @Test
    void testMultipleTeams() {
        for (int i = 0; i < 5; i++) {
            Team team = new Team(i, "Team " + i,1);
            league.addTeam(team);
        }
        assertEquals(5, league.getListTeam().getSize());
    }


    /**
     * Teste la modification du nom de la ligue.
     */
    @Test
    void testChangeName() {
        assertTrue(league.setName("New League Name"));
        assertEquals("New League Name", league.getName());
    }

    /**
     * Teste le rejet d'un nom null.
     */
    @Test
    void testRejectNullName() {
        assertFalse(league.setName(null));
        assertNotNull(league.getName());
    }

    /**
     * Teste l'ID par défaut de la ligue.
     */
    @Test
    void testDefaultId() {
        assertEquals(-1, league.getId());
    }

    /**
     * Teste la création d'une nouvelle équipe dans la ligue.
     */
    @Test
    void testNewTeamCreation() {
        assertTrue(league.newTeam(1, "New Team"));
        assertEquals(1, league.getListTeam().getSize());
    }

    /**
     * Teste l'état initial de done.
     */
    @Test
    void testInitialDoneState() {
        assertFalse(league.getDone());
    }

    /**
     * Teste la modification de l'état done.
     */
    @Test
    void testSetDone() {
        league.setDone(true);
        assertTrue(league.getDone());
    }

    /**
     * Teste la liste des matchs initiale.
     */
    @Test
    void testInitialMatchList() {
        assertNotNull(league.getListMatch());
    }

    /**
     * Teste la liste des saisons initiale.
     */
    @Test
    void testInitialSeasonsList() {
        assertNotNull(league.getIdSeasons());
        assertTrue(league.getIdSeasons().isEmpty());
    }
}