package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.modsuni.logic.commands.SearchCommand;
import seedu.modsuni.model.module.CodeStartsKeywordsPredicate;

public class SearchCommandParserTest {

    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SearchCommand expectedSearchCommand =
                new SearchCommand(new CodeStartsKeywordsPredicate(Arrays.asList("CS", "ACC")));
        assertParseSuccess(parser, "CS ACC", expectedSearchCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \nCS \n \tACC  \t", expectedSearchCommand);
    }

}
