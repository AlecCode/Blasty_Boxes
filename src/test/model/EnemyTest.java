package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BomberManApp;

import java.lang.management.GarbageCollectorMXBean;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    private Player player;
    private Enemy enemy;
    private BomberManGame openArenaGame;

    @BeforeEach
    void runBefore() {
        this.openArenaGame = new BomberManGame("open", "easy", new BomberManApp(false));
        this.player = new Player(openArenaGame);

        this.player.setPosX(10);
        this.player.setPosY(10);

        this.enemy = new Enemy(openArenaGame, "easy");

        this.enemy.setPosX(9);
        this.enemy.setPosY(9);
    }

    @Test
    void testEasyMove() {
        this.enemy.setAct(0);
        this.enemy.move(player);
        assertEquals(this.enemy.getPosX(), 9);
        assertEquals(this.enemy.getPosY(), 8);
        assertEquals(this.enemy.getBombs().size(), 0);

        this.enemy.setAct(1);
        this.enemy.move(player);
        assertEquals(this.enemy.getPosX(), 10);
        assertEquals(this.enemy.getPosY(), 8);
        assertEquals(this.enemy.getBombs().size(), 0);

        this.enemy.setAct(2);
        this.enemy.move(player);
        assertEquals(this.enemy.getPosX(), 10);
        assertEquals(this.enemy.getPosY(), 9);
        assertEquals(this.enemy.getBombs().size(), 0);

        this.enemy.setAct(3);
        this.enemy.move(player);
        assertEquals(this.enemy.getPosX(), 9);
        assertEquals(this.enemy.getPosY(), 9);
        assertEquals(this.enemy.getBombs().size(), 0);

        this.enemy.setAct(4);
        this.enemy.move(player);
        assertEquals(this.enemy.getPosX(), 9);
        assertEquals(this.enemy.getPosY(), 9);
        assertEquals(this.enemy.getBombs().size(), 1);
    }

    @Test
    void testHardMove() {
        this.enemy = new Enemy(openArenaGame, "hard");

        this.enemy.setPosX(9);
        this.enemy.setPosY(9);

        this.enemy.setAct(0);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 9);

        this.enemy.setAct(0);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 9);

        this.enemy.setAct(1);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);

        this.enemy.setAct(1);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);

        this.enemy.setPosX(11);
        this.enemy.setPosY(11);

        this.enemy.setAct(0);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 11);

        this.enemy.setAct(0);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 11);

        this.enemy.setAct(1);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);

        this.enemy.setAct(1);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);

        this.enemy.setAct(2);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);
        assertEquals(enemy.getBombs().size(), 1);

        player.setPosX(1);
        player.setPosY(1);
        this.enemy.setAct(2);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);
        assertEquals(enemy.getBombs().size(), 1);

        player.setPosX(1);
        player.setPosY(10);
        this.enemy.setAct(2);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);
        assertEquals(enemy.getBombs().size(), 1);

        player.setPosX(10);
        player.setPosY(1);
        this.enemy.setAct(2);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);
        assertEquals(enemy.getBombs().size(), 1);

        player.setPosX(1);
        player.setPosY(1);
        this.enemy.setAct(10);
        this.enemy.move(player);
        assertEquals(enemy.getPosX(), 10);
        assertEquals(enemy.getPosY(), 10);
        assertEquals(enemy.getBombs().size(), 1);
    }
}
