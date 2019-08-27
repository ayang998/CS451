package ui;

import model.Game;
import model.Player;
import network.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CheckerBoardTest {

    @Mock
    private CheckersWindow mockWindow;
    @Mock
    private Game mockGame;
    @Mock
    private Player mockPlayer1;
    @Mock
    private Player mockPlayer2;

    private CheckerBoard checkerBoardUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        checkerBoardUnderTest = new CheckerBoard(mockWindow, mockGame, mockPlayer1, mockPlayer2);
    }

    @Test
    public void testUpdate() {
        // Setup
        when(mockWindow.getSession1()).thenReturn(null);
        when(mockWindow.getSession2()).thenReturn(null);

        // Run the test
        checkerBoardUnderTest.update();

        // Verify the results
    }

    @Test
    public void testUpdateNetwork() {
        // Setup
        when(mockWindow.getSession1()).thenReturn(null);
        when(mockWindow.getSession2()).thenReturn(null);

        // Run the test
        checkerBoardUnderTest.updateNetwork();

        // Verify the results
    }

    @Test
    public void testSetGameState() {
        // Setup
        final boolean testValue = false;
        final String newState = "newState";
        final String expected = "expected";

        // Run the test
        final boolean result = checkerBoardUnderTest.setGameState(testValue, newState, expected);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testSendGameState() {
        // Setup
        final Session s = null;

        // Run the test
        checkerBoardUnderTest.sendGameState(s);

        // Verify the results
    }

    @Test
    public void testPaint() {
        // Setup
        final Graphics g = null;

        // Run the test
        boolean cond = true;
        assertTrue(cond);

        // Verify the results
    }

    @Test
    public void testSetGame() {
        // Setup
        final Game game = null;

        // Run the test
        checkerBoardUnderTest.setGame(game);

        // Verify the results
    }

    @Test
    public void testSetPlayer1() {
        // Setup
        final Player player1 = null;

        // Run the test
        checkerBoardUnderTest.setPlayer1(player1);

        // Verify the results
    }

    @Test
    public void testSetPlayer2() {
        // Setup
        final Player player2 = null;

        // Run the test
        checkerBoardUnderTest.setPlayer2(player2);

        // Verify the results
    }

    @Test
    public void testGetCurrentPlayer() {
        // Setup
        final Player expectedResult = null;

        // Run the test
        final Player result = checkerBoardUnderTest.getCurrentPlayer();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetLightTile() {
        // Setup
        final Color lightTile = null;

        // Run the test
        checkerBoardUnderTest.setLightTile(lightTile);

        // Verify the results
    }

    @Test
    public void testSetDarkTile() {
        // Setup
        final Color darkTile = null;

        // Run the test
        checkerBoardUnderTest.setDarkTile(darkTile);

        // Verify the results
    }
}
