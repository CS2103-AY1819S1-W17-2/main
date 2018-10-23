package seedu.modsuni.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.modsuni.commons.core.LogsCenter;
import seedu.modsuni.commons.events.ui.NewResultAvailableEvent;

/**
 * A ui for the output display that is displayed in the main display box.
 */
public class OutputDisplay extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(OutputDisplay.class);
    private static final String FXML = "OutputDisplay.fxml";

    private final StringProperty displayed = new SimpleStringProperty("");

    @FXML
    private TextArea outputDisplay;

    public OutputDisplay() {
        super(FXML);
        outputDisplay.textProperty().bind(displayed);
        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        Platform.runLater(() -> displayed.setValue(event.message));
    }

}
