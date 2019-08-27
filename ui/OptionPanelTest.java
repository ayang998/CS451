package ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class OptionPanelTest {

    @Mock
    private CheckersWindow mockWindow;

    private OptionPanel optionPanelUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        optionPanelUnderTest = new OptionPanel(mockWindow);
    }

    @Test
    public void testSetNetworkWindowMessage() {
        // Setup
        final boolean forPlayer1 = false;
        final String msg = "msg";

        // Run the test
        optionPanelUnderTest.setNetworkWindowMessage(forPlayer1, msg);

        // Verify the results
    }
}
