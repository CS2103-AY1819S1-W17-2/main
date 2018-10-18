package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.modsuni.logic.commands.AddModuleToStudentStagedCommand;

public class AddModuleToStudentStagedCommandParserTest {
    private AddModuleToStudentStagedCommandParser parser = new AddModuleToStudentStagedCommandParser();

    @Test
    public void parse_emptyCode_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddModuleToStudentStagedCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }

}
