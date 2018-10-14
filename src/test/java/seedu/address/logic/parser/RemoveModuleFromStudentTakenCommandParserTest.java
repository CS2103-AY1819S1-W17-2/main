package seedu.address.logic.parser;

import org.junit.Test;
import seedu.address.logic.commands.AddModuleToStudentStagedCommand;
import seedu.address.logic.commands.RemoveModuleFromStudentStagedCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class RemoveModuleFromStudentStagedCommandParserTest {
    private RemoveModuleFromStudentStagedCommandParser parser = new RemoveModuleFromStudentStagedCommandParser();

    @Test
    public void parse_emptyCode_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveModuleFromStudentStagedCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expectedMessage);
    }

}
