package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.logic.Generate;
import seedu.address.model.Semester.Semester;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.UniqueModuleList;

/**
 * Wraps all data relating to modules
 */
public class ModuleList implements ReadOnlyModuleList {

    private final UniqueModuleList modules;

    public ModuleList() {
        modules = new UniqueModuleList();
    }

    public ModuleList(ReadOnlyModuleList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the module list with {@code modules}.
     */
    public void setModules(List<Module> modules) {
        this.modules.setModules(modules);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyModuleList newData) {
        requireNonNull(newData);

        setModules(newData.getModuleList());
    }

    //// module-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to the module list.
     * The module must not already exist in the module list.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the module list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the
     * module list.
     */
    public void updateModule(Module target, Module editedModule) {
        requireNonNull(editedModule);

        modules.setModule(target, editedModule);
    }

    /**
     * Removes {@code key} from this {@code ModuleList}.
     * {@code key} must exist in the address book.
     */
    public void removeModule(Module key) {
        modules.remove(key);
    }

    //// util methods

    public Optional<Module> getModuleInformation(Module target) {
        return modules.search(target);
    }

    public List<Module> searchKeyword(Module keyword) {
        return modules.searchKeyword(keyword);
    }

    public List<Code> getAllCode() {
        List<Code> codes = new ArrayList<>();
        for (Module module : modules) {
            codes.add(module.getCode());
        }
        return codes;
    }

    public void generate(ModuleList modulesTaken) {
        UniqueModuleList modulesToTake = modules;
        List<Code> codesToTake = getAllCode();
        Generate generate = new Generate(codesToTake);
        for (Module moduleToTake : modules) {
            for (Code code : moduleToTake.getLockedModules()) {
                if (codesToTake.contains(code)) {
                    generate.addEdge(moduleToTake.getCode(), code);
                }
            }
        }
        generate.topologicalSort();
    }

    /**
     * Returns true if the student has added modules to take and false if otherwise.
     */
    public boolean hasModules() {
        if (modules.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return modules.asUnmodifiableObservableList().size() + " modules";
        // TODO: refine later
    }

    @Override
    public ObservableList<Module> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleList // instanceof handles nulls
                && modules.equals(((ModuleList) other).modules));
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }
}
