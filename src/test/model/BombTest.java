package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    private Bomb bomb;
    private Arena openArena;

    @BeforeEach
    void runBefore() {
        bomb = new Bomb(2, 2);
        openArena = new Arena("open");
    }

    @Test
    void testTick() {
        bomb.tick();

        assertEquals(bomb.getTimer(), 9);
    }

    @Test
    void testExplode() {
        ArrayList<Explosion> actual = bomb.explode(openArena);
        ArrayList<Explosion> compare = new ArrayList<>();

        compare.add(new Explosion(2, 2));
        compare.add(new Explosion(3, 2));
        compare.add(new Explosion(4, 2));
        compare.add(new Explosion(5, 2));
        compare.add(new Explosion(2, 3));
        compare.add(new Explosion(2, 4));
        compare.add(new Explosion(2, 5));
        compare.add(new Explosion(1, 2));
        compare.add(new Explosion(2, 1));

        for (int i = 0; i < 9; i++) {
            assertEquals(actual.get(i).getPosX(), compare.get(i).getPosX());
            assertEquals(actual.get(i).getPosY(), compare.get(i).getPosY());
        }
    }

    @Test
    void testMakeExplosionsRight() {
        ArrayList<Explosion> actual = bomb.makeExplosionsRight(openArena);
        ArrayList<Explosion> compare = new ArrayList<>();

        compare.add(new Explosion(3, 2));
        compare.add(new Explosion(4, 2));
        compare.add(new Explosion(5, 2));

        for (int i = 0; i < 3; i++) {
            assertEquals(actual.get(i).getPosX(), compare.get(i).getPosX());
            assertEquals(actual.get(i).getPosY(), compare.get(i).getPosY());
        }

        bomb = new Bomb(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 2);
        actual = bomb.makeExplosionsRight(openArena);

        assertEquals(actual.size(), 0);
    }

    @Test
    void testMakeExplosionsDown() {
        ArrayList<Explosion> actual = bomb.makeExplosionsDown(openArena);
        ArrayList<Explosion> compare = new ArrayList<>();

        compare.add(new Explosion(2, 3));
        compare.add(new Explosion(2, 4));
        compare.add(new Explosion(2, 5));

        for (int i = 0; i < 3; i++) {
            assertEquals(actual.get(i).getPosX(), compare.get(i).getPosX());
            assertEquals(actual.get(i).getPosY(), compare.get(i).getPosY());
        }

        bomb = new Bomb(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 2);
        actual = bomb.makeExplosionsDown(openArena);

        assertEquals(actual.size(), 0);
    }

    @Test
    void testMakeExplosionsLeft() {
        bomb = new Bomb(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 2);
        ArrayList<Explosion> actual = bomb.makeExplosionsLeft(openArena);
        ArrayList<Explosion> compare = new ArrayList<>();

        compare.add(new Explosion(Constants.G_WIDTH - 3, Constants.G_HEIGHT - 2));
        compare.add(new Explosion(Constants.G_WIDTH - 4, Constants.G_HEIGHT - 2));
        compare.add(new Explosion(Constants.G_WIDTH - 5, Constants.G_HEIGHT - 2));

        for (int i = 0; i < 3; i++) {
            assertEquals(actual.get(i).getPosX(), compare.get(i).getPosX());
            assertEquals(actual.get(i).getPosY(), compare.get(i).getPosY());
        }

        bomb = new Bomb(1, 1);
        actual = bomb.makeExplosionsLeft(openArena);

        assertEquals(actual.size(), 0);
    }

    @Test
    void testMakeExplosionsUp() {
        bomb = new Bomb(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 2);
        ArrayList<Explosion> actual = bomb.makeExplosionsUp(openArena);
        ArrayList<Explosion> compare = new ArrayList<>();

        compare.add(new Explosion(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 3));
        compare.add(new Explosion(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 4));
        compare.add(new Explosion(Constants.G_WIDTH - 2, Constants.G_HEIGHT - 5));

        for (int i = 0; i < 3; i++) {
            assertEquals(actual.get(i).getPosX(), compare.get(i).getPosX());
            assertEquals(actual.get(i).getPosY(), compare.get(i).getPosY());
        }

        bomb = new Bomb(1, 1);
        actual = bomb.makeExplosionsUp(openArena);

        assertEquals(actual.size(), 0);
    }

    @Test
    void testGetPosX(){
        assertEquals(bomb.getPosX(), 2);
    }

    @Test
    void testGetPosY(){
        assertEquals(bomb.getPosY(), 2);
    }

    @Test
    void testGetTimer(){
        assertEquals(bomb.getTimer(), 10);
    }
}
