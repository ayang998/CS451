package network;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SessionTest {

    @Mock
    private ConnectionListener mockListener;

    private Session sessionUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        sessionUnderTest = new Session(mockListener, "sid", "destinationHost", 0);
    }

    @Test
    public void testGetSourcePort() {
        // Setup
        final int expectedResult = 0;
        when(mockListener.getPort()).thenReturn(0);

        // Run the test
        final int result = sessionUnderTest.getSourcePort();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetSourcePort() {
        // Setup
        final int sourcePort = 0;

        // Run the test
        sessionUnderTest.setSourcePort(sourcePort);

        // Verify the results
        verify(mockListener).setPort(0);
    }
}
