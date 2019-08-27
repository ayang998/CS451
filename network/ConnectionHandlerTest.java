package network;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.net.Socket;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConnectionHandlerTest {

    @Mock
    private ConnectionListener mockListener;
    @Mock
    private Socket mockSocket;

    private ConnectionHandler connectionHandlerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        connectionHandlerUnderTest = new ConnectionHandler(mockListener, mockSocket);
    }

    @Test
    public void testRun() {
        // Setup
        when(mockListener.getConnectionHandler()).thenReturn(null);

        // Run the test
        connectionHandlerUnderTest.run();

        // Verify the results
    }
}
