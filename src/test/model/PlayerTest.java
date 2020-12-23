package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BomberManApp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest extends KeyAdapter {
    private Player player;
    private BomberManGame openArenaGame;

    @BeforeEach
    void runBefore() {
        this.openArenaGame = new BomberManGame("open", "easy", new BomberManApp(false));
        this.player = this.openArenaGame.getPlayer();

        this.player.setPosX(2);
        this.player.setPosY(2);
    }

    @Test
    void testMove(){
        assertEquals(this.player.getPosX(), 2);
        assertEquals(this.player.getPosY(), 2);
        assertEquals(this.player.getBombs().size(), 0);

        this.player.move(KeyEvent.VK_W);
        assertEquals(this.player.getPosX(), 2);
        assertEquals(this.player.getPosY(), 1);
        assertEquals(this.player.getBombs().size(), 0);

        this.player.move(KeyEvent.VK_A);
        assertEquals(this.player.getPosX(), 1);
        assertEquals(this.player.getPosY(), 1);
        assertEquals(this.player.getBombs().size(), 0);

        this.player.move(KeyEvent.VK_S);
        assertEquals(this.player.getPosX(), 1);
        assertEquals(this.player.getPosY(), 2);
        assertEquals(this.player.getBombs().size(), 0);

        this.player.move(KeyEvent.VK_D);
        assertEquals(this.player.getPosX(), 2);
        assertEquals(this.player.getPosY(), 2);
        assertEquals(this.player.getBombs().size(), 0);

        this.player.move(KeyEvent.VK_SPACE);
        assertEquals(this.player.getPosX(), 2);
        assertEquals(this.player.getPosY(), 2);
        assertEquals(this.player.getBombs().size(), 1);
    }

    @Test
    void testKeyPressed() {
        this.player.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(this.player.getInAction());
        this.player.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(this.player.getInAction());
    }

    @Test
    void testKeyReleased() {
        this.player.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(this.player.getInAction());
        this.player.keyReleased();
        assertFalse(this.player.getInAction());
    }

    @Test
    void testGetInAction() {
        assertFalse(this.player.getInAction());
        this.player.keyPressed(KeyEvent.VK_SPACE);
        assertTrue(this.player.getInAction());
    }
}
