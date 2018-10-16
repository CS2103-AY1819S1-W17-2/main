package seedu.address.model.semester;

import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

/**
 * Encapsulates data required for a semester.
 */
public class Semester {

    private UniqueModuleList toBeTaken;

    public Semester() {
        this.toBeTaken = new UniqueModuleList();
    }

    /**
     * Adds a module to the semester.
     */
    public void addModule(Module module) {
        toBeTaken.add(module);
    }

    /**
     * Get the total no of module credits in the semester.
     */
    public int totalCredits() {
        int total = 0;
        for (Module module : toBeTaken) {
            total += module.getCredit();
        }
        return total;
    }

    @Override
    public String toString() {
        return toBeTaken.getAllCode().toString();
    }
}
