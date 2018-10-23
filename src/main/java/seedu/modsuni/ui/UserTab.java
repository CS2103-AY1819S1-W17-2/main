package seedu.modsuni.ui;

import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.modsuni.commons.core.LogsCenter;

/**
 * The User tab of the Application.
 */
public class UserTab extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(UserTab.class);
    private static final String FXML = "UserTab.fxml";

    private final StringProperty displayed = new SimpleStringProperty("");

    @FXML
    private TextArea test;

    public UserTab() {
        super(FXML);
        test.textProperty().bind(displayed);
    }
}
