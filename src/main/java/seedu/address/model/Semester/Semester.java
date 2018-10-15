package seedu.address.model.Semester;

import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

public class Semester {

    private UniqueModuleList toBeTaken;

    public Semester() {
        this.toBeTaken = new UniqueModuleList();
    }

    public void addModule(Module module) {
        toBeTaken.add(module);
    }

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
