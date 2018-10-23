package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.RemoveModuleFromStudentStagedCommand;
import seedu.address.model.module.Code;
import seedu.address.model.module.Module;
import seedu.address.model.module.Prereq;


public class RemoveModuleFromStudentStagedCommandParserTest {
    private RemoveModuleFromStudentStagedCommandParser parser = new RemoveModuleFromStudentStagedCommandParser();

    @Test
    public void parse_emptyCode_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveModuleFromStudentStagedCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_multiCode_success() {
        Module cs1010 = new Module(new Code("CS1010"), "", "", "",
                0, false, false, false, false, new ArrayList<Code>(), new Prereq());
        Module cs2103T = new Module(new Code("CS2103T"), "", "", "",
                0, false, false, false, false, new ArrayList<Code>(), new Prereq());
        ArrayList<Module> expectedModuleList = new ArrayList<>(Arrays.asList(cs1010, cs2103T));
        assertParseSuccess(parser, "CS1010 CS2103T", new RemoveModuleFromStudentStagedCommand(expectedModuleList));
    }

    @Test
    public void parse_caseInsensitive_success() {
        Module cs1010 = new Module(new Code("CS1010"), "", "", "",
                0, false, false, false, false, new ArrayList<Code>(), new Prereq());
        ArrayList<Module> expectedModuleList = new ArrayList<>(Arrays.asList(cs1010));
        assertParseSuccess(parser, "cs1010", new RemoveModuleFromStudentStagedCommand(expectedModuleList));
    }
}
