package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArenaTest {
    private Arena arena;

    @Test
    void testMakeCheckeredArena() {
        this.arena = new Arena("checker");

        assertEquals(arena.getArena().get(1).get(1), " ");
        assertEquals(arena.getArena().get(0).get(0), "W");
        assertEquals(arena.getTile(Constants.G_WIDTH - 1, Constants.G_HEIGHT - 1), "W");
    }

    @Test
    void testMakeOpenArena() {
        this.arena = new Arena("open");

        assertEquals(arena.getArena().get(1).get(1), " ");
        assertEquals(arena.getArena().get(0).get(0), "W");
        assertEquals(arena.getTile(Constants.G_WIDTH - 1, Constants.G_HEIGHT - 1), "W");
    }

    @Test
    void testMakeLinesArena() {
        this.arena = new Arena("lines");

        assertEquals(arena.getArena().get(1).get(1), " ");
        assertEquals(arena.getArena().get(0).get(0), "W");
        assertEquals(arena.getTile(Constants.G_WIDTH - 1, Constants.G_HEIGHT - 1), "W");
    }
}