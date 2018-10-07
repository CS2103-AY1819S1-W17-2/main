package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_ACC1002;
import static seedu.address.testutil.TypicalModules.ACC1002;
import static seedu.address.testutil.TypicalModules.CS1010;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isSameModule() {
        // same object -> returns true
        assertTrue(CS1010.isSameModule(CS1010));

        // null -> returns false
        assertFalse(CS1010.isSameModule(null));

        // different code -> returns false
        Module editedCs1010 = new ModuleBuilder(CS1010).withCode(VALID_CODE_ACC1002).build();
        assertFalse(CS1010.isSameModule(editedCs1010));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Module cs1010Copy = new ModuleBuilder(CS1010).build();
        assertTrue(CS1010.equals(cs1010Copy));

        // same object -> returns true
        assertTrue(CS1010.equals(CS1010));

        // null -> retturns false
        assertFalse(CS1010.equals(null));

        // different module -> returns false
        assertFalse(CS1010.equals(ACC1002));

        // different code -> returns false
        Module editedCs1010 = new ModuleBuilder(CS1010).withCode(VALID_CODE_ACC1002).build();
        assertFalse(CS1010.equals(editedCs1010));
    }
}
