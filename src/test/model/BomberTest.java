package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BomberManApp;

import static org.junit.jupiter.api.Assertions.*;

public class BomberTest {
    private Bomber bomber;
    private BomberManGame openArenaGame;

    @BeforeEach
    void runBefore() {
        openArenaGame = new BomberManGame("open", "easy", new BomberManApp(false));
        bomber = new Bomber(openArenaGame);
        bomber.setPosX(10);
        bomber.setPosY(10);
    }

    @Test
    void testMove() {
        this.openArenaGame.getEnemies().get(2).dropBomb();
        bomber.setPosX(Constants.G_WIDTH - 2);
        bomber.setPosY(Constants.G_HEIGHT - 3);
        bomber.move("down");
        assertEquals(bomber.getPosX(), Constants.G_WIDTH - 2);
        assertEquals(bomber.getPosY(), Constants.G_HEIGHT - 3);

        bomber.setPosX(10);
        bomber.setPosY(10);
        assertEquals(bomber.getPosX(), 10);
        assertEquals(bomber.getPosY(), 10);

        bomber.move("up");
        assertEquals(bomber.getPosX(), 10);
        assertEquals(bomber.getPosY(), 9);

        bomber.move("left");
        assertEquals(bomber.getPosX(), 9);
        assertEquals(bomber.getPosY(), 9);

        bomber.move("right");
        assertEquals(bomber.getPosX(), 10);
        assertEquals(bomber.getPosY(), 9);

        bomber.move("down");
        assertEquals(bomber.getPosX(), 10);
        assertEquals(bomber.getPosY(), 10);

        bomber.move("no");
        assertEquals(bomber.getPosX(), 10);
        assertEquals(bomber.getPosY(), 10);

        bomber.setPosX(1);
        bomber.setPosY(1);
        bomber.move("up");
        assertEquals(bomber.getPosX(), 1);
        assertEquals(bomber.getPosY(), 1);

        bomber.move("left");
        assertEquals(bomber.getPosX(), 1);
        assertEquals(bomber.getPosY(), 1);

        bomber.setPosX(Constants.G_WIDTH - 2);
        bomber.setPosY(Constants.G_HEIGHT - 2);
        bomber.move("right");
        assertEquals(bomber.getPosX(), Constants.G_WIDTH - 2);
        assertEquals(bomber.getPosY(), Constants.G_HEIGHT - 2);

        bomber.move("down");
        assertEquals(bomber.getPosX(), Constants.G_WIDTH - 2);
        assertEquals(bomber.getPosY(), Constants.G_HEIGHT - 2);

        this.openArenaGame.getPlayer().move("down");
        this.openArenaGame.getPlayer().dropBomb();
        this.openArenaGame.getPlayer().move("right");
        this.openArenaGame.getPlayer().dropBomb();
        this.openArenaGame.getPlayer().setPosX(1);
        this.openArenaGame.getPlayer().setPosY(1);
        this.openArenaGame.getPlayer().dropBomb();
        bomber.setPosX(2);
        bomber.setPosY(1);
        bomber.move("left");
        assertEquals(bomber.getPosX(), 2);
        assertEquals(bomber.getPosY(), 1);
    }

    @Test
    void testDropBomb() {
        bomber.dropBomb();

        assertEquals(bomber.getBombs().size(), 1);
        assertEquals(bomber.getBombs().get(0).getPosX(), bomber.getPosX());
        assertEquals(bomber.getBombs().get(0).getPosY(), bomber.getPosY());
    }

    @Test
    void testRemoveBomb() {
        bomber.dropBomb();
        assertEquals(bomber.getBombs().size(), 1);

        Bomb bomb = bomber.getBombs().get(0);
        bomber.removeBomb(bomb);
        assertEquals(bomber.getBombs().size(), 0);
    }

    @Test
    void testSetPosX(){
        bomber.setPosX(11);

        assertEquals(bomber.getPosX(), 11);
    }

    @Test
    void testSetPosY(){
        bomber.setPosY(11);

        assertEquals(bomber.getPosY(), 11);
    }
}
