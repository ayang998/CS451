package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoveTest {

    private Move moveUnderTest;

    @Before
    public void setUp() {
        moveUnderTest = new Move(0, 0);
    }

    @Test
    public void testGetStartIndex() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = moveUnderTest.getStartIndex();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetStartIndex() {
        // Setup
        final int startIndex = 0;

        // Run the test
        moveUnderTest.setStartIndex(startIndex);

        // Verify the results
    }

    @Test
    public void testGetEndIndex() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = moveUnderTest.getEndIndex();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetEndIndex() {
        // Setup
        final int endIndex = 0;

        // Run the test
        moveUnderTest.setEndIndex(endIndex);

        // Verify the results
    }

    @Test
    public void testGetStart() {
        // Setup
        final Point expectedResult = null;

        // Run the test
        final Point result = moveUnderTest.getStart();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetStart() {
        // Setup
        final Point start = null;

        // Run the test
        moveUnderTest.setStart(start);

        // Verify the results
    }

    @Test
    public void testGetEnd() {
        // Setup
        final Point expectedResult = null;

        // Run the test
        final Point result = moveUnderTest.getEnd();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetEnd() {
        // Setup
        final Point end = null;

        // Run the test
        moveUnderTest.setEnd(end);

        // Verify the results
    }

    @Test
    public void testChangeWeight() {
        // Setup
        final double delta = 0.0;

        // Run the test
        moveUnderTest.changeWeight(delta);

        // Verify the results
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = moveUnderTest.toString();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }
}
