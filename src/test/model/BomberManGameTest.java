package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BomberManApp;
import ui.Drawer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BomberManGameTest {
    private BomberManGame bomberManGame;
    private BomberManApp app;

    @BeforeEach
    void runBefore() {
        app = new BomberManApp(false);
        bomberManGame = new BomberManGame("open", "easy", app);
    }

    @Test
    void testGameSetUp() {
        assertEquals(bomberManGame.getArena().getTile(1, 1), " ");
        assertEquals(bomberManGame.getArena().getTile(2, 2), " ");
        assertEquals(bomberManGame.getArena().getTile(3, 3), " ");

        bomberManGame.getPlayer().setPosX(1);
        bomberManGame.getPlayer().setPosY(1);
        assertEquals(bomberManGame.getPlayer().getPosX(), 1);
        assertEquals(bomberManGame.getPlayer().getPosY(), 1);

        assertEquals(bomberManGame.getEnemies().size(), 3);
        assertEquals(bomberManGame.getExplosions().size(), 0);
    }

    @Test
    void testUpdate() {
        bomberManGame.getPlayer().dropBomb();
        for (Enemy e : bomberManGame.getEnemies()) {
            e.setAct(0);
            e.dropBomb();
        }
        bomberManGame.update();

        assertEquals(bomberManGame.getArena().getTile(1, 1), " ");
        assertEquals(bomberManGame.getArena().getTile(2, 2), " ");
        assertEquals(bomberManGame.getArena().getTile(3, 3), " ");

        bomberManGame.getPlayer().setPosX(1);
        bomberManGame.getPlayer().setPosY(1);
        assertEquals(bomberManGame.getPlayer().getPosX(), 1);
        assertEquals(bomberManGame.getPlayer().getPosY(), 1);

        assertEquals(bomberManGame.getEnemies().size(), 3);
        assertEquals(bomberManGame.getExplosions().size(), 0);

        for (int i = 0; i < 9; i++) {
            for (Enemy e :bomberManGame.getEnemies()){
                e.setAct(0);
            }
            bomberManGame.update();
        }

        assertEquals(bomberManGame.getExplosions().size(), 7);

        bomberManGame.update();
        bomberManGame.update();

        assertEquals(bomberManGame.getExplosions().size(), 0);

        for (int i = 0; i < 3; i++) {
            for (Enemy e :bomberManGame.getEnemies()){
                e.setAct(4);
            }
            bomberManGame.update();
        }
        bomberManGame.update();

        assertTrue(app.isWin());
        assertFalse(app.isInGame());
    }

    @Test
    void testRemoveEnemies() {
        assertEquals(bomberManGame.getEnemies().size(), 3);

        ArrayList<Enemy> remove = new ArrayList<>();

        remove.add(bomberManGame.getEnemies().get(0));
        bomberManGame.removeEnemies(remove);

        assertEquals(bomberManGame.getEnemies().size(), 2);
    }

    @Test
    void testGetDrawnMap() {
        ArrayList<String> map = this.bomberManGame.getDrawnMap();
        assertEquals(357, map.size());
    }

    @Test
    void testGetDifficulty() {
        assertEquals("easy", this.bomberManGame.getDifficulty());
    }

    @Test
    void testGetTurns() {
        assertEquals(0, this.bomberManGame.getTurns());
    }
}