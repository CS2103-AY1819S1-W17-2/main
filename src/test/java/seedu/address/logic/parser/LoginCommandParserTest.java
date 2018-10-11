package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOGIN_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOGIN_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.credential.Credential;
import seedu.address.model.credential.Password;
import seedu.address.model.credential.Username;

public class LoginCommandParserTest {
    private LoginCommandParser parser = new LoginCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        String userInput = LOGIN_USERNAME_DESC + LOGIN_PASSWORD_DESC;

        Credential toVerify = new Credential(
            ParserUtil.parseUsername(VALID_USERNAME),
            ParserUtil.parsePassword(VALID_PASSWORD));

        LoginCommand expectedCommand = new LoginCommand(toVerify);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LoginCommand.MESSAGE_USAGE);

        // missing username prefix
        assertParseFailure(parser,
            VALID_USERNAME + LOGIN_PASSWORD_DESC,
                expectedMessage);

        // missing password prefix
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC + VALID_PASSWORD,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
            VALID_USERNAME + VALID_PASSWORD,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid username
        assertParseFailure(parser,
            INVALID_USERNAME_DESC + LOGIN_PASSWORD_DESC,
            Username.MESSAGE_USERNAME_CONSTRAINTS);

        // invalid password
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC + INVALID_PASSWORD_DESC,
            Password.MESSAGE_PASSWORD_CONSTRAINTS);

    }
}
