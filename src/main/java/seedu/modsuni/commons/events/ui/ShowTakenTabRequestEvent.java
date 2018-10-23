package seedu.modsuni.commons.events.ui;

import seedu.modsuni.commons.events.BaseEvent;

/**
 * An event requesting to show the Taken Tab
 */
public class ShowTakenTabRequestEvent extends BaseEvent {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
