package seedu.address.model.module;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalModules.ACC1002;
import static seedu.address.testutil.TypicalModules.ACC1002X;
import static seedu.address.testutil.TypicalModules.CS1010;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class UniqueModuleListTest {
    @Test
    public void search() {
        UniqueModuleList list = new UniqueModuleList();
        Module toSearch = new Module("CS1010");
        Module toSearchNotExist = new Module("CS2103T");

        list.add(CS1010);
        Optional<Module> optional = list.search(toSearch);
        assertTrue(optional.isPresent());
        assertEquals(optional.get(), CS1010);

        Optional<Module> optionalNotExist = list.search(toSearchNotExist);
        assertFalse(optionalNotExist.isPresent());
    }

    @Test
    public void searchKeyword() {
        UniqueModuleList list = new UniqueModuleList();
        Module keyword = new Module("ACC");
        Module keywordNotExist = new Module("GEH");

        list.add(ACC1002);
        list.add(CS1010);
        list.add(ACC1002X);
        List<Module> modules = list.searchKeyword(keyword);
        assertEquals(modules.size(), 2);
        assertTrue(modules.contains(ACC1002));

        List<Module> emptyModules = list.searchKeyword(keywordNotExist);
        assertEquals(emptyModules.size(), 0);
    }
}
