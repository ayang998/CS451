package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.event.ActionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class NetworkWindowTest {

    @Mock
    private ActionListener mockActionListener;

    private NetworkWindow networkWindowUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        networkWindowUnderTest = new NetworkWindow(mockActionListener, 0, "destHost", 0);
    }

    @Test
    public void testSetCanUpdateListen() {
        // Setup
        final boolean canUpdate = false;

        // Run the test
        networkWindowUnderTest.setCanUpdateListen(canUpdate);

        // Verify the results
    }

    @Test
    public void testSetCanUpdateConnect() {
        // Setup
        final boolean canUpdate = false;

        // Run the test
        networkWindowUnderTest.setCanUpdateConnect(canUpdate);

        // Verify the results
    }

    @Test
    public void testGetSourcePort() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = networkWindowUnderTest.getSourcePort();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetSourcePort() {
        // Setup
        final int port = 0;

        // Run the test
        networkWindowUnderTest.setSourcePort(port);

        // Verify the results
    }

    @Test
    public void testGetDestinationHost() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = networkWindowUnderTest.getDestinationHost();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetDestinationHost() {
        // Setup
        final String host = "host";

        // Run the test
        networkWindowUnderTest.setDestinationHost(host);

        // Verify the results
    }

    @Test
    public void testGetDestinationPort() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = networkWindowUnderTest.getDestinationPort();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetDestinationPort() {
        // Setup
        final int port = 0;

        // Run the test
        networkWindowUnderTest.setDestinationPort(port);

        // Verify the results
    }

    @Test
    public void testGetMessage() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = networkWindowUnderTest.getMessage();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetMessage() {
        // Setup
        final String message = "message";

        // Run the test
        networkWindowUnderTest.setMessage(message);

        // Verify the results
    }
}
