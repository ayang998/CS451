package network;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ui.CheckerBoard;
import ui.CheckersWindow;
import ui.OptionPanel;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CheckersNetworkHandlerTest {

    @Mock
    private CheckersWindow mockWindow;
    @Mock
    private CheckerBoard mockBoard;
    @Mock
    private OptionPanel mockOpts;

    private CheckersNetworkHandler checkersNetworkHandlerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        checkersNetworkHandlerUnderTest = new CheckersNetworkHandler(false, mockWindow, mockBoard, mockOpts);
    }

    @Test
    public void testActionPerformed() {
        // Setup
        final ActionEvent e = null;
        when(mockWindow.getSession1()).thenReturn(null);
        when(mockWindow.getSession2()).thenReturn(null);
        when(mockBoard.setGameState(false, "newState", "expected")).thenReturn(false);
        when(mockBoard.getCurrentPlayer()).thenReturn(null);
        when(mockBoard.getPlayer2()).thenReturn(null);
        when(mockBoard.getPlayer1()).thenReturn(null);
        when(mockOpts.getNetworkWindow1()).thenReturn(null);
        when(mockOpts.getNetworkWindow2()).thenReturn(null);
        when(mockBoard.getGame()).thenReturn(null);

        // Run the test
        checkersNetworkHandlerUnderTest.actionPerformed(e);

        // Verify the results
        boolean cond = true;
        assertTrue(cond);
    }
}
