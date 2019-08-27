package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ComputerPlayerTest {

    private ComputerPlayer computerPlayerUnderTest;

    @Before
    public void setUp() {
        computerPlayerUnderTest = new ComputerPlayer();
    }

    @Test
    public void testIsHuman() {
        // Setup

        // Run the test
        final boolean result = computerPlayerUnderTest.isHuman();

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testUpdateGame() {
        // Setup
        final Game game = null;

        // Run the test
        computerPlayerUnderTest.updateGame(game);

        // Verify the results
    }
}
