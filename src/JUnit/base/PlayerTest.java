package base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {
        Player player = new Player("John", "Doe", -1);
        assertEquals("John", player.getName());
        assertNotEquals("john", player.getName());
        assertNotEquals(1343, player.getName());
        Player player2 = new Player("1234", "Doe", -1);
        assertNotEquals(1343, player2.getName());
        assertEquals("1234", player2.getName());
    }

    @Test
    void getLastName() {
        Player player = new Player("John", "Doe", -1);
        assertEquals("John", player.getName());
        assertNotEquals("john", player.getName());
        assertNotEquals(1343, player.getName());
        Player player2 = new Player("1234", "Doe", -1);
        assertNotEquals(1343, player2.getName());
        assertEquals("1234", player2.getName());
    }

    @Test
    void setName() {
    }

    @Test
    void setLastName() {
    }

    @Test
    void setIdTeam() {
    }

    @Test
    void getIdTeam() {
    }
}