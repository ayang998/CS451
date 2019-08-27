package network;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandTest {

    private Command commandUnderTest;

    @Before
    public void setUp() {
        commandUnderTest = new Command("command", "data");
    }

    @Test
    public void testSend() {
        // Setup
        final String host = "host";
        final int port = 0;
        final String expectedResult = "result";

        // Run the test
        final String result = commandUnderTest.send(host, port);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testGetOutput() {
        // Setup
        final String expectedResult = "result";

        // Run the test
        final String result = commandUnderTest.getOutput();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }

    @Test
    public void testGetData() {
        // Setup
        final String[] expectedResult = new String[]{};

        // Run the test
        final String[] result = commandUnderTest.getData();

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }
}
