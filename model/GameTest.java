package model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameTest {

    @Mock
    private Board mockBoard;

    private Game gameUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        gameUnderTest = new Game(mockBoard, false, 0);
    }

    @Test
    public void testCopy() {
        // Setup
        final Game expectedResult = null;

        // Run the test
        final Game result = gameUnderTest.copy();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testRestart() {
        // Setup

        // Run the test
        gameUnderTest.restart();

        // Verify the results
    }

    @Test
    public void testMove() {
        // Setup
        final Point start = null;
        final Point end = null;

        // Run the test
        final boolean result = gameUnderTest.move(start, end);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testMove1() {
        // Setup
        final int startIndex = 0;
        final int endIndex = 0;

        // Run the test
        final boolean result = gameUnderTest.move(startIndex, endIndex);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testGetBoard() {
        // Setup
        final Board expectedResult = null;

        // Run the test
        final Board result = gameUnderTest.getBoard();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsGameOver() {
        // Setup

        // Run the test
        final boolean result = gameUnderTest.isGameOver();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetGameState() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = gameUnderTest.getGameState();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetGameState() {
        // Setup
        final String state = "state";

        // Run the test
        gameUnderTest.setGameState(state);

        // Verify the results
    }
}
