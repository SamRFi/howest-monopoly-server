package be.howest.ti.monopoly.logic.implementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void findPosition() {
        Board b = new Board();
        assertEquals(39, b.findPosition("Boardwalk"));
    }

    @Test
    void findTileName() {
        Board b = new Board();
        assertEquals("Boardwalk", b.findTileName(39));
    }

    @Test
    void isThisTileNameDirectSale() {
        Board b = new Board();
        assertEquals(true, b.isThisTileNameDirectSale("Boardwalk"));
        assertEquals(false, b.isThisTileNameDirectSale("Free Parking"));
    }

    @Test
    void isThisPositionDirectSale() {
        Board b = new Board();
        assertEquals(true, b.isThisPositionDirectSale(39));
        assertEquals(false, b.isThisPositionDirectSale(38));
    }

    @Test
    void findStreetsOfSameColor() {
        Board b = new Board();
        assertEquals("[Mediterranean, Baltic]", b.findStreetsOfSameColor("Baltic").toString());
        assertEquals("[Saint Charles Place, States, Virginia]", b.findStreetsOfSameColor("States").toString());
    }

}