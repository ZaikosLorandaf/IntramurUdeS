package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListPlayerTest {
    ListPlayer list;

    @BeforeEach
    void before()
    {
        list = new ListPlayer(2);
    }

    @Test
    void addPlayer() {
        Player joueur = new Player("John","Doe",12);
        assertTrue(list.addPlayer(joueur));
        assertTrue(list.addPlayer(joueur));
        assertFalse(list.addPlayer(joueur));
    }

    @Test
    void removePlayer() {
        Player joueur = new Player("John","Doe",12);
        Player joueur2 = new Player("Johny","Dos",12);
        list.addPlayer(joueur);
        list.addPlayer(joueur2);
        assertTrue();
    }

    @Test
    void testRemovePlayer() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void getIndex() {
    }

    @Test
    void getMaxPlayer() {
    }

    @Test
    void setMaxPlayer() {
    }

    @Test
    void getSize() {
    }
}