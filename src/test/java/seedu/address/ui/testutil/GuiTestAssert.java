package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.List;

import guitests.guihandles.ModuleCardHandle;
import guitests.guihandles.ModuleListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.module.Module;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    private static final String SEM1 = "Sem1";
    private static final String SEM2 = "Sem2";
    private static final String SPECIAL_TERM1 = "SpecialTerm1";
    private static final String SPECIAL_TERM2 = "SpecialTerm2";

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(ModuleCardHandle expectedCard, ModuleCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getCode(), actualCard.getCode());
        assertEquals(expectedCard.getDepartment(), actualCard.getDepartment());
        assertEquals(expectedCard.getTitle(), actualCard.getTitle());
        assertEquals(expectedCard.getDescription(), actualCard.getDescription());
        assertEquals(expectedCard.getCredit(), actualCard.getCredit());
        assertEquals(expectedCard.getAvailability(), actualCard.getAvailability());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedModule}.
     */
    public static void assertCardDisplaysModule(Module expectedModule, ModuleCardHandle actualCard) {
        assertEquals(expectedModule.getCode(), actualCard.getCode());
        assertEquals(expectedModule.getDepartment(), actualCard.getDepartment());
        assertEquals(expectedModule.getDescription(), actualCard.getDescription());
        assertEquals(expectedModule.getTitle(), actualCard.getTitle());
        assertEquals(String.valueOf(expectedModule.getCredit()), actualCard.getCredit());
        assertEquals(expectedModule.isAvailableInSem1(), actualCard.getAvailability().contains(SEM1));
        assertEquals(expectedModule.isAvailableInSem2(), actualCard.getAvailability().contains(SEM2));
        assertEquals(expectedModule.isAvailableInSpecialTerm1(), actualCard.getAvailability().contains(SPECIAL_TERM1));
        assertEquals(expectedModule.isAvailableInSpecialTerm2(), actualCard.getAvailability().contains(SPECIAL_TERM2));
    }

    /**
     * Asserts that the list in {@code moduleListPanelHandle} displays the details of {@code modules} correctly and
     * in the correct order.
     */
    public static void assertListMatching(ModuleListPanelHandle moduleListPanelHandle, Module... modules) {
        for (int i = 0; i < modules.length; i++) {
            moduleListPanelHandle.navigateToCard(i);
            assertCardDisplaysModule(modules[i], moduleListPanelHandle.getModuleCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code moduleListPanelHandle} displays the details of {@code modules} correctly and
     * in the correct order.
     */
    public static void assertListMatching(ModuleListPanelHandle moduleListPanelHandle, List<Module> modules) {
        assertListMatching(moduleListPanelHandle, modules.toArray(new Module[0]));
    }

    /**
     * Asserts the size of the list in {@code moduleListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(ModuleListPanelHandle moduleListPanelHandle, int size) {
        int numberOfPeople = moduleListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
