package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HighScoreTest {
    private HighScore highScore;
    private HighScore errorHighScore;

    @BeforeEach
    void runBefore() {
        this.highScore = new HighScore("TestHIGHSCORES");
        this.errorHighScore = new HighScore("Error");
        ArrayList errorNames = new ArrayList(Arrays.asList("?????","?????","?????","?????","?????"));
        ArrayList errorScores = new ArrayList(Arrays.asList("0","0","0","0","0"));

        assertEquals(errorNames, errorHighScore.getEasyHighScoreName());
        assertEquals(errorNames, errorHighScore.getHardHighScoreName());
        assertEquals(errorScores, errorHighScore.getEasyHighScore());
        assertEquals(errorScores, errorHighScore.getHardHighScore());
    }

    @Test
    void testHighScoreEligible() {
        assertTrue(highScore.highScoreEligible(1, "easy"));
        assertTrue(highScore.highScoreEligible(1, "hard"));

        for (int i = 0; i < 5; i++) {
            highScore.insertHighScore("Test", 2, "easy");
            highScore.insertHighScore("Test", 2, "hard");
        }

        assertTrue(highScore.highScoreEligible(1, "easy"));
        assertTrue(highScore.highScoreEligible(1, "hard"));
        assertFalse(highScore.highScoreEligible(5, "easy"));
        assertFalse(highScore.highScoreEligible(5, "hard"));
    }

    @Test
    void testWriteToFile() {
        ArrayList easyHighScore = highScore.getEasyHighScore();
        ArrayList hardHighScore = highScore.getHardHighScore();
        ArrayList errorEasyHighScore = errorHighScore.getEasyHighScore();
        ArrayList errorHardHighScore = errorHighScore.getHardHighScore();
        errorHighScore = new HighScore("MakeHighScoreThrowError");

        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());
        for (int i = 0; i < 5; i++) {
            highScore.insertHighScore("Test", 2, "easy");
            highScore.insertHighScore("Test", 2, "hard");
            errorHighScore.insertHighScore("Test", 2, "easy");
            errorHighScore.insertHighScore("Test", 2, "hard");
        }

        easyHighScore = new ArrayList(Arrays.asList("2", "2", "2", "2", "2"));
        hardHighScore = new ArrayList(Arrays.asList("2", "2", "2", "2", "2"));

        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());

        try {
            highScore.writeToFile();
        } catch (Exception e) {
            fail("Should not have thrown error");
        }
        try {
            errorHighScore.writeToFile();
            fail("Should have thrown error");
        } catch (Exception e) {
        }

        highScore = new HighScore("TestHIGHSCORES");
        errorHighScore = new HighScore("error");

        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());
        assertEquals(errorEasyHighScore, errorHighScore.getEasyHighScore());
        assertEquals(errorHardHighScore, errorHighScore.getHardHighScore());

        for (int i = 0; i < 5; i++) {
            highScore.insertHighScore("empty", 0, "easy");
            highScore.insertHighScore("empty", 0, "hard");
        }

        try {
            highScore.writeToFile();
        } catch (Exception e){
            fail("Should not have thrown error");
        }
    }

    @Test
    void testInsertHighScore() {
        ArrayList easyHighScore = highScore.getEasyHighScore();
        ArrayList hardHighScore = highScore.getHardHighScore();

        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());
        for (int i = 0; i < 5; i++) {
            highScore.insertHighScore("Test", 2, "easy");
            highScore.insertHighScore("Test", 2, "hard");
        }

        easyHighScore = new ArrayList(Arrays.asList("2", "2", "2", "2", "2"));
        hardHighScore = new ArrayList(Arrays.asList("2", "2", "2", "2", "2"));

        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());

        highScore.insertHighScore("Test", 5, "easy");
        highScore.insertHighScore("Test", 5, "hard");
        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());

        easyHighScore = new ArrayList(Arrays.asList("1", "2", "2", "2", "2"));
        hardHighScore = new ArrayList(Arrays.asList("1", "2", "2", "2", "2"));

        highScore.insertHighScore("Test", 1, "easy");
        highScore.insertHighScore("Test", 1, "hard");
        assertEquals(easyHighScore, highScore.getEasyHighScore());
        assertEquals(hardHighScore, highScore.getHardHighScore());
    }

    @Test
    void testInsert() {
        ArrayList list = this.highScore.insert(this.highScore.getEasyHighScoreName(), 0, "TEST");
        assertEquals(list.get(0), "TEST");
    }

    @Test
    void testGetEasyHighScore() {
        assertEquals(highScore.getEasyHighScore().get(0), "0");
    }

    @Test
    void testGetEasyHighScoreName() {
        assertEquals(highScore.getEasyHighScoreName().get(0), "empty");
    }

    @Test
    void testGetHardHighScore() {
        assertEquals(highScore.getHardHighScore().get(0), "0");
    }

    @Test
    void testGetHardHighScoreName() {
        assertEquals(highScore.getHardHighScoreName().get(0), "empty");
    }
}
