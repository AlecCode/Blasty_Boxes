package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExplosionTest {
    private Explosion explosion;

    @BeforeEach
    void runBefore() {
        explosion = new Explosion(1, 1);
    }

    @Test
    void testTick() {
        assertEquals(explosion.getPosX(), 1);
        assertEquals(explosion.getPosY(), 1);
        assertEquals(explosion.getTimer(), 2);

        explosion.tick();

        assertEquals(explosion.getPosX(), 1);
        assertEquals(explosion.getPosY(), 1);
        assertEquals(explosion.getTimer(), 1);
    }
}
