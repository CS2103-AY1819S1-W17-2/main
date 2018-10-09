package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalModules.ACC1002;
import static seedu.address.testutil.TypicalModules.CS1010;

import org.junit.Test;

public class ModuleTest {
    @Test
    public void isPrefixModule() {
        Module cs1010Prefix = new Module("CS");

        // same object -> returns true
        assertTrue(CS1010.isPrefixModule((CS1010)));

        // null -> returns false
        assertFalse(CS1010.isPrefixModule((null)));

        // this is the prefix of other module -> returns true
        assertTrue(cs1010Prefix.isPrefixModule(CS1010));

        // other module is the prefix of this -> returns false
        assertFalse(CS1010.isPrefixModule(cs1010Prefix));

        // is not the prefix -> returns false
        assertFalse(cs1010Prefix.isPrefixModule(ACC1002));
    }
}
