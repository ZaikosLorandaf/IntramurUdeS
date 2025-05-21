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
        assertEquals("Doe", player.getLastName());
        assertNotEquals("doe", player.getLastName());
        assertNotEquals(1343, player.getLastName());
        Player player2 = new Player("John", "1234", -1);
        assertNotEquals(1343, player2.getLastName());
        assertEquals("1234", player2.getLastName());
    }

    @Test
    void setName() {
        Player player = new Player("John", "Doe", -1);
        player.setName("bob");
        assertEquals("bob", player.getName());
        assertEquals("Doe", player.getLastName());
        player.setName("");
        assertNotEquals("", player.getName());
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