package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.RemoveModuleFromDatabaseCommand;

public class RemoveModuleFromDatabaseCommandParserTest {

    private RemoveModuleFromDatabaseCommandParser parser = new RemoveModuleFromDatabaseCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveModuleFromDatabaseCommand() {
        assertParseSuccess(parser, "CS1010", new RemoveModuleFromDatabaseCommand("CS1010"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //TODO implement this test after parseModuleCode in testUtil is complete.
    }
}
