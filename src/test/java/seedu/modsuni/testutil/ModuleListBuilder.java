package seedu.modsuni.testutil;

import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.module.Module;

/**
 * A utility class to help with building ModuleList objects.
 * Example usage: <br>
 *     {@code ModuleList moduleList = new ModuleListBuilder().withCode("CS1010").build();}
 */
public class ModuleListBuilder {

    private ModuleList moduleList;

    public ModuleListBuilder() {
        moduleList = new ModuleList();
    }

    public ModuleListBuilder(ModuleList moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * Adds a new {@code Module} to the {@code ModuleList} that we are building.
     */
    public ModuleListBuilder withModule(Module module) {
        moduleList.addModule(module);
        return this;
    }

    public ModuleList build() {
        return moduleList;
    }
}
