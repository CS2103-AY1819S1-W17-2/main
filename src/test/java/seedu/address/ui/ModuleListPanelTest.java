package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysModule;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

import guitests.guihandles.ModuleCardHandle;
import guitests.guihandles.ModuleListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.module.Module;
import seedu.address.storage.XmlSerializableModuleList;


public class ModuleListPanelTest extends GuiUnitTest {
    private static final ObservableList<Module> TYPICAL_MODULES =
            FXCollections.observableList(getTypicalModules());

    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND_MODULE);

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "sandbox");

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private ModuleListPanelHandle moduleListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_MODULES);

        for (int i = 0; i < TYPICAL_MODULES.size(); i++) {
            moduleListPanelHandle.navigateToCard(TYPICAL_MODULES.get(i));
            Module expectedModule = TYPICAL_MODULES.get(i);
            ModuleCardHandle actualCard = moduleListPanelHandle.getModuleCardHandle(i);

            assertCardDisplaysModule(expectedModule, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void handleJumpToListRequestEvent() {
        initUi(TYPICAL_MODULES);
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        ModuleCardHandle expectedModule = moduleListPanelHandle.getModuleCardHandle(INDEX_SECOND_MODULE.getZeroBased());
        ModuleCardHandle selectedModule = moduleListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedModule, selectedModule);
    }

    /**
     * Verifies that creating and deleting large number of modules in {@code ModuleListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    @Ignore
    public void performanceTest() throws Exception {
        ObservableList<Module> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of module cards exceeded time limit");
    }

    /**
     * Returns a list of modules containing {@code moduleCount} modules that is used to populate the
     * {@code ModuleListPanel}.
     */
    private ObservableList<Module> createBackingList(int moduleCount) throws Exception {
        Path xmlFile = createXmlFileWithModules(moduleCount);
        XmlSerializableModuleList xmlSerializableModuleList =
                XmlUtil.getDataFromFile(xmlFile, XmlSerializableModuleList.class);
        return FXCollections.observableArrayList(xmlSerializableModuleList.toModelType().getModuleList());
    }

    /**
     * Returns a .xml file containing {@code moduleCount} modules. This file will be deleted when the JVM terminates.
     */
    private Path createXmlFileWithModules(int moduleCount) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        builder.append("<moduleList>\n");
        for (int i = 0; i < moduleCount; i++) {
            builder.append("<modules>\n");
            builder.append("<name>").append(i).append("a</name>\n");
            builder.append("<phone>000</phone>\n");
            builder.append("<email>a@aa</email>\n");
            builder.append("<address>a</address>\n");
            builder.append("<modules>\n");
        }
        builder.append("</moduleList>\n");

        Path manyModulesFile = Paths.get(TEST_DATA_FOLDER + "manyModules.xml");
        FileUtil.createFile(manyModulesFile);
        FileUtil.writeToFile(manyModulesFile, builder.toString());
        manyModulesFile.toFile().deleteOnExit();
        return manyModulesFile;
    }

    /**
     * Initializes {@code moduleListPanelHandle} with a {@code ModuleListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code ModuleListPanel}.
     */
    private void initUi(ObservableList<Module> backingList) {
        ModuleListPanel moduleListPanel = new ModuleListPanel(backingList);
        uiPartRule.setUiPart(moduleListPanel);

        moduleListPanelHandle = new ModuleListPanelHandle(getChildNode(moduleListPanel.getRoot(),
                ModuleListPanelHandle.MODULE_LIST_VIEW_ID));
    }
}
