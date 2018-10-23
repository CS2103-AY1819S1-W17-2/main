package seedu.modsuni.commons.events.ui;

import seedu.modsuni.commons.events.BaseEvent;

/**
 * An event requesting to view the Staged Tab
 */
public class ShowStagedTabRequestEvent extends BaseEvent {

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}