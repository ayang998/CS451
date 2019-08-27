package logic;

import model.Board;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MoveGeneratorTest {

    @Test
    public void testGetMoves() {
        // Setup
        final Board board = null;
        final Point start = null;
        final List<Point> expectedResult = Arrays.asList();

        // Run the test
        final List<Point> result = MoveGenerator.getMoves(board, start);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetMoves1() {
        // Setup
        final Board board = null;
        final int startIndex = 0;
        final List<Point> expectedResult = Arrays.asList();

        // Run the test
        final List<Point> result = MoveGenerator.getMoves(board, startIndex);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSkips() {
        // Setup
        final Board board = null;
        final Point start = null;
        final List<Point> expectedResult = Arrays.asList();

        // Run the test
        final List<Point> result = MoveGenerator.getSkips(board, start);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSkips1() {
        // Setup
        final Board board = null;
        final int startIndex = 0;
        final List<Point> expectedResult = Arrays.asList();

        // Run the test
        final List<Point> result = MoveGenerator.getSkips(board, startIndex);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsValidSkip() {
        // Setup
        final Board board = null;
        final int startIndex = 0;
        final int endIndex = 0;

        // Run the test
        final boolean result = MoveGenerator.isValidSkip(board, startIndex, endIndex);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testAddPoints() {
        // Setup
        final List<Point> points = Arrays.asList();
        final Point p = null;
        final int id = 0;
        final int delta = 0;

        // Run the test
        MoveGenerator.addPoints(points, p, id, delta);

        // Verify the results
    }
}
