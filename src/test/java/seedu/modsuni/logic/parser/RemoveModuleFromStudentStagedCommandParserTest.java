package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.modsuni.logic.commands.RemoveModuleFromStudentStagedCommand;


public class RemoveModuleFromStudentStagedCommandParserTest {
    private RemoveModuleFromStudentStagedCommandParser parser = new RemoveModuleFromStudentStagedCommandParser();

    @Test
    public void parse_emptyCode_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveModuleFromStudentStagedCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }

}
