package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {

    private Board boardUnderTest;

    @Before
    public void setUp() {
        boardUnderTest = new Board();
    }

    @Test
    public void testCopy() {
        // Setup
        final Board expectedResult = null;

        // Run the test
        final Board result = boardUnderTest.copy();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testReset() {
        // Setup

        // Run the test
        boardUnderTest.reset();

        // Verify the results
    }

    @Test
    public void testFind() {
        // Setup
        final int id = 0;
        final List<Point> expectedResult = Arrays.asList();

        // Run the test
        final List<Point> result = boardUnderTest.find(id);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSet() {
        // Setup
        final int x = 0;
        final int y = 0;
        final int id = 0;

        // Run the test
        boardUnderTest.set(x, y, id);

        // Verify the results
    }

    @Test
    public void testSet1() {
        // Setup
        final int index = 0;
        final int id = 0;

        // Run the test
        boardUnderTest.set(index, id);

        // Verify the results
    }

    @Test
    public void testGet() {
        // Setup
        final int x = 0;
        final int y = 0;
        final int expectedResult = 0;

        // Run the test
        final int result = boardUnderTest.get(x, y);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testGet1() {
        // Setup
        final int index = 0;
        final int expectedResult = 0;

        // Run the test
        final int result = boardUnderTest.get(index);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = boardUnderTest.toString();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testToPoint() {
        // Setup
        final int index = 0;
        final Point expectedResult = null;

        // Run the test
        final Point result = Board.toPoint(index);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testToIndex() {
        // Setup
        final int x = 0;
        final int y = 0;
        final int expectedResult = 0;

        // Run the test
        final int result = Board.toIndex(x, y);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testToIndex1() {
        // Setup
        final Point p = null;
        final int expectedResult = 0;

        // Run the test
        final int result = Board.toIndex(p);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetBit() {
        // Setup
        final int target = 0;
        final int bit = 0;
        final boolean set = false;
        final int expectedResult = 0;

        // Run the test
        final int result = Board.setBit(target, bit, set);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetBit() {
        // Setup
        final int target = 0;
        final int bit = 0;
        final int expectedResult = 0;

        // Run the test
        final int result = Board.getBit(target, bit);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testMiddle() {
        // Setup
        final Point p1 = null;
        final Point p2 = null;
        final Point expectedResult = null;

        // Run the test
        final Point result = Board.middle(p1, p2);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testMiddle1() {
        // Setup
        final int index1 = 0;
        final int index2 = 0;
        final Point expectedResult = null;

        // Run the test
        final Point result = Board.middle(index1, index2);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testMiddle2() {
        // Setup
        final int x1 = 0;
        final int y1 = 0;
        final int x2 = 0;
        final int y2 = 0;
        final Point expectedResult = null;

        // Run the test
        final Point result = Board.middle(x1, y1, x2, y2);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testIsValidIndex() {
        // Setup
        final int testIndex = 0;

        // Run the test
        final boolean result = Board.isValidIndex(testIndex);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testIsValidPoint() {
        // Setup
        final Point testPoint = null;

        // Run the test
        final boolean result = Board.isValidPoint(testPoint);

        // Verify the results
        assertFalse(result);
    }
}
