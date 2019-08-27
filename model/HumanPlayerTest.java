package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HumanPlayerTest {

    private HumanPlayer humanPlayerUnderTest;

    @Before
    public void setUp() {
        humanPlayerUnderTest = new HumanPlayer();
    }

    @Test
    public void testIsHuman() {
        // Setup

        // Run the test
        final boolean result = humanPlayerUnderTest.isHuman();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testUpdateGame() {
        // Setup
        final Game game = null;

        // Run the test
        humanPlayerUnderTest.updateGame(game);

        // Verify the results
    }
}
