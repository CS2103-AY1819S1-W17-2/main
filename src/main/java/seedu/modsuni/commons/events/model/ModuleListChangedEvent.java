package seedu.modsuni.commons.events.model;

import seedu.modsuni.commons.events.BaseEvent;
import seedu.modsuni.model.ReadOnlyModuleList;

/** Indicates the ModuleList in the model has changed*/
public class ModuleListChangedEvent extends BaseEvent {

    public final ReadOnlyModuleList data;

    public ModuleListChangedEvent(ReadOnlyModuleList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of modules " + data.getModuleList().size();
    }
}
