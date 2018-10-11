package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AVAILABLE_DESC_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.CODE_DESC_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.CREDIT_DESC_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.DEPARTMENT_DESC_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AVAILABLE_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CODE_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CREDIT_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2109;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CS2109;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.AddModuleToDatabaseCommand;

public class AddModuleToDatabaseCommandParserTest {
    private AddModuleToDatabaseCommandParser parser = new AddModuleToDatabaseCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddModuleToDatabaseCommand.MESSAGE_USAGE);

        // missing code prefix
        assertParseFailure(parser, VALID_CODE_CS2109 + CREDIT_DESC_CS2109 + TITLE_DESC_CS2109
                + DESCRIPTION_DESC_CS2109 + DEPARTMENT_DESC_CS2109 + AVAILABLE_DESC_CS2109, expectedMessage);

        // missing credit prefix
        assertParseFailure(parser, CODE_DESC_CS2109 + VALID_CREDIT_CS2109 + TITLE_DESC_CS2109
                + DESCRIPTION_DESC_CS2109 + DEPARTMENT_DESC_CS2109 + AVAILABLE_DESC_CS2109, expectedMessage);

        // missing title prefix
        assertParseFailure(parser, CODE_DESC_CS2109 + CREDIT_DESC_CS2109 + VALID_TITLE_CS2109
                + DESCRIPTION_DESC_CS2109 + DEPARTMENT_DESC_CS2109 + AVAILABLE_DESC_CS2109, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, CODE_DESC_CS2109 + CREDIT_DESC_CS2109 + TITLE_DESC_CS2109
                + VALID_DESCRIPTION_CS2109 + DEPARTMENT_DESC_CS2109 + AVAILABLE_DESC_CS2109, expectedMessage);

        // missing department prefix
        assertParseFailure(parser, CODE_DESC_CS2109 + CREDIT_DESC_CS2109 + TITLE_DESC_CS2109
                + DESCRIPTION_DESC_CS2109 + VALID_DEPARTMENT_CS2109 + AVAILABLE_DESC_CS2109, expectedMessage);

        // missing available prefix
        assertParseFailure(parser, CODE_DESC_CS2109 + CREDIT_DESC_CS2109 + TITLE_DESC_CS2109
                + DESCRIPTION_DESC_CS2109 + DEPARTMENT_DESC_CS2109 + VALID_AVAILABLE_CS2109, expectedMessage);
    }
}
