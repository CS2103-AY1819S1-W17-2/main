package seedu.address.model.module;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

/**
 * A list of modules that enforces uniqueness between its elements and does not allow nulls.
 * A module is considered unique by comparing using {@code Module#isSameModule(Module)}. As such, adding and
 * updating of modules uses Module#isSameModule(Module) for equality so as to ensure that the module being
 * added or updated is unique in terms of identity in the UniqueModuleList. However, the removal of a module
 * uses Module#equals(Object) so as to ensure that the module with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Module#isSameModule(Module)
 * @see Module#isPrefixModule(Module)
 */
public class UniqueModuleList implements Iterable<Module> {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent module as the given argument.
     */
    public boolean contains(Module toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModule);
    }

    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    public int size() {
        return internalList.size();
    }

    public List<Code> getAllCode() {
        List<Code> codes = new ArrayList<>();
        for (Module module : internalList) {
            codes.add(module.getCode());
        }
        return codes;
    }

    /**
     * Returns true if the student has added modules to take and false if otherwise.
     */
    public boolean hasModules() {
        if (internalList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the Optional of the Module.
     */
    public Optional<Module> search(Module toSearch) {
        requireNonNull(toSearch);
        for (Module module : internalList) {
            if (module.getCode().equals(toSearch.getCode())) {
                return Optional.of(module);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the List of Modules start with the keyword
     */
    public List<Module> searchKeyword(Module keyword) {
        requireNonNull(keyword);
        Object[] objectsArray = internalList.stream().filter(keyword::isPrefixModule).toArray();
        Module[] modulesArray = Arrays.copyOf(objectsArray, objectsArray.length, Module[].class);
        return Arrays.asList(modulesArray);
    }

    /**
     * Adds a module to the list.
     * The module must not already exist in the list.
     */
    public void add(Module toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the module {@code target} in the list with {@code editedModule}.
     * {@code target} must exist in the list.
     * The module identity of {@code editedModule} must not be the same as another existing module in the
     * list.
     */
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleNotFoundException();
        }

        if (!target.isSameModule(editedModule) && contains(editedModule)) {
            throw new DuplicateModuleException();
        }
        internalList.set(index, editedModule);
    }

    /**
     * Removes the equivalent module from the list.
     * The module must exist in the list.
     */
    public void remove(Module toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleNotFoundException();
        }
    }

    public Module getModuleByCode(Code code) {
        requireNonNull(code);
        for (Module module : internalList) {
            if (module.getCode().equals(code)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    public void setModules(UniqueModuleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        if (!modulesAreUnique(modules)) {
            throw new DuplicateModuleException();
        }
        internalList.setAll(modules);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Module> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Module> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleList // instanceof handles nulls
                && internalList.equals(((UniqueModuleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code modules} contains only unique modules.
     */
    private boolean modulesAreUnique(List<Module> modules) {
        for (int i = 0; i < modules.size() - 1; i++) {
            for (int j = i + 1; j < modules.size(); j++) {
                if (modules.get(i).isSameModule(modules.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
