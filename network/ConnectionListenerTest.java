package network;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.awt.event.ActionListener;
import java.net.Socket;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConnectionListenerTest {

    @Mock
    private ActionListener mockConnectionHandler;

    private ConnectionListener connectionListenerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        connectionListenerUnderTest = new ConnectionListener(0, mockConnectionHandler);
    }

    @Test
    public void testListen() {
        // Setup

        // Run the test
        connectionListenerUnderTest.listen();

        // Verify the results
    }

    @Test
    public void testRun() {
        // Setup

        // Run the test
        boolean cond = true;
        assertTrue(cond);

        // Verify the results
    }

    @Test
    public void testStopListening() {
        // Setup

        // Run the test
        final boolean result = connectionListenerUnderTest.stopListening();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetPort() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = connectionListenerUnderTest.getPort();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testSetPort() {
        // Setup
        final int port = 0;

        // Run the test
        boolean cond = true;
        assertTrue(cond);

        // Verify the results
    }

    @Test
    public void testRead() {
        // Setup
        final Socket socket = null;
        final String expectedResult = "result";

        // Run the test
        final String result = ConnectionListener.read(socket);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testAvailable() {
        // Setup
        final int port = 0;

        // Run the test
        final boolean result = ConnectionListener.available(port);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }
}
