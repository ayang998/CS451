package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class NetworkPlayerTest {

    private NetworkPlayer networkPlayerUnderTest;

    @Before
    public void setUp() {
        networkPlayerUnderTest = new NetworkPlayer();
    }

    @Test
    public void testIsHuman() {
        // Setup

        // Run the test
        final boolean result = networkPlayerUnderTest.isHuman();

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testUpdateGame() {
        // Setup
        final Game game = null;

        // Run the test
        networkPlayerUnderTest.updateGame(game);

        // Verify the results
    }
}
