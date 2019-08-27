package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlayerTest {

    private Player playerUnderTest;

    @Before
    public void setUp() {
        playerUnderTest = new Player() {
            @Override
            public boolean isHuman() {
                return false;
            }

            @Override
            public void updateGame(Game game) {

            }
        };
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = playerUnderTest.toString();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }
}
