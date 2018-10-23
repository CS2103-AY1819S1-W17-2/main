package guitests.guihandles;

import javafx.scene.control.TextArea;

/**
 * A handler for the {@code OutputDisplay} of the UI
 */
public class OutputDisplayHandle extends NodeHandle<TextArea> {

    public static final String RESULT_DISPLAY_ID = "#outputDisplay";

    public OutputDisplayHandle(TextArea resultDisplayNode) {
        super(resultDisplayNode);
    }

    /**
     * Returns the text in the result display.
     */
    public String getText() {
        return getRootNode().getText();
    }
}
