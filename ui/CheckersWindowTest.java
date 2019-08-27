package ui;

import model.Player;
import org.junit.Before;
import org.junit.Test;

public class CheckersWindowTest {

    private CheckersWindow checkersWindowUnderTest;

    @Before
    public void setUp() {
        checkersWindowUnderTest = new CheckersWindow(0, 0, "title");
    }

    @Test
    public void testSetPlayer1() {
        // Setup
        final Player player1 = null;

        // Run the test
        checkersWindowUnderTest.setPlayer1(player1);

        // Verify the results
    }

    @Test
    public void testSetPlayer2() {
        // Setup
        final Player player2 = null;

        // Run the test
        checkersWindowUnderTest.setPlayer2(player2);

        // Verify the results
    }

    @Test
    public void testRestart() {
        // Setup

        // Run the test
        checkersWindowUnderTest.restart();

        // Verify the results
    }

    @Test
    public void testSetGameState() {
        // Setup
        final String state = "state";

        // Run the test
        checkersWindowUnderTest.setGameState(state);

        // Verify the results
    }
}
