package network;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

public class CheckersConnectionTest {

    private CheckersConnection checkersConnectionUnderTest;

    @Before
    public void setUp() {
        checkersConnectionUnderTest = new CheckersConnection();
    }

    @Test
    public void testActionPerformed() {
        // Setup
        final ActionEvent e = null;

        // Run the test
        checkersConnectionUnderTest.actionPerformed(e);

        // Verify the results
    }
}
