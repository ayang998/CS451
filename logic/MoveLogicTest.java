package logic;

import model.Board;
import model.Game;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveLogicTest {

    @Test
    public void testIsValidMove() {
        // Setup
        final Game game = null;
        final int startIndex = 0;
        final int endIndex = 0;

        // Run the test
        final boolean result = MoveLogic.isValidMove(game, startIndex, endIndex);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsValidMove1() {
        // Setup
        final Board board = null;
        final boolean isP1Turn = false;
        final int startIndex = 0;
        final int endIndex = 0;
        final int skipIndex = 0;

        // Run the test
        final boolean result = MoveLogic.isValidMove(board, isP1Turn, startIndex, endIndex, skipIndex);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsSafe() {
        // Setup
        final Board board = null;
        final Point checker = null;

        // Run the test
        final boolean result = MoveLogic.isSafe(board, checker);

        // Verify the results
        assertTrue(result);
    }
}
