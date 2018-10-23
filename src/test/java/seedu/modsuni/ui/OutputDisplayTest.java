package seedu.modsuni.ui;

import static org.junit.Assert.assertEquals;
import static seedu.modsuni.testutil.EventsUtil.postNow;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.OutputDisplayHandle;
import seedu.modsuni.commons.events.ui.NewResultAvailableEvent;

public class OutputDisplayTest extends GuiUnitTest {

    private static final NewResultAvailableEvent NEW_RESULT_EVENT_STUB = new NewResultAvailableEvent("Stub");

    private OutputDisplayHandle outputDisplayHandle;

    @Before
    public void setUp() {
        OutputDisplay outputDisplay = new OutputDisplay();
        uiPartRule.setUiPart(outputDisplay);

        outputDisplayHandle =
            new OutputDisplayHandle(getChildNode(outputDisplay.getRoot(),
                OutputDisplayHandle.RESULT_DISPLAY_ID));
    }

    @Test
    public void display() {
        // default result text
        guiRobot.pauseForHuman();
        assertEquals("", outputDisplayHandle.getText());

        // new result received
        postNow(NEW_RESULT_EVENT_STUB);
        guiRobot.pauseForHuman();
        assertEquals(NEW_RESULT_EVENT_STUB.message, outputDisplayHandle.getText());
    }
}
