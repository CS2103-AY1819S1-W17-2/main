package seedu.modsuni.logic.parser;

import static seedu.modsuni.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modsuni.logic.commands.CommandTestUtil.INVALID_PASSWORD_DESC;
import static seedu.modsuni.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.modsuni.logic.commands.CommandTestUtil.LOGIN_PASSWORD_DESC;
import static seedu.modsuni.logic.commands.CommandTestUtil.LOGIN_USERDATA_DESC;
import static seedu.modsuni.logic.commands.CommandTestUtil.LOGIN_USERNAME_DESC;
import static seedu.modsuni.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.modsuni.logic.commands.CommandTestUtil.VALID_USERDATA;
import static seedu.modsuni.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modsuni.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import seedu.modsuni.logic.commands.LoginCommand;
import seedu.modsuni.logic.parser.exceptions.ParseException;
import seedu.modsuni.model.credential.Credential;
import seedu.modsuni.model.credential.Password;
import seedu.modsuni.model.credential.Username;

public class LoginCommandParserTest {
    private LoginCommandParser parser = new LoginCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        String userInput = LOGIN_USERNAME_DESC + LOGIN_PASSWORD_DESC + LOGIN_USERDATA_DESC;

        Credential toVerify = new Credential(
            ParserUtil.parseUsername(VALID_USERNAME),
            ParserUtil.parsePassword(VALID_PASSWORD));

        Path toUserData = Paths.get(VALID_USERDATA);

        LoginCommand expectedCommand = new LoginCommand(toVerify, toUserData);

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
            INVALID_USERNAME_DESC + LOGIN_PASSWORD_DESC + LOGIN_USERDATA_DESC,
            Username.MESSAGE_USERNAME_CONSTRAINTS);

        // invalid password
        assertParseFailure(parser,
            LOGIN_USERNAME_DESC + INVALID_PASSWORD_DESC + LOGIN_USERDATA_DESC,
            Password.MESSAGE_PASSWORD_CONSTRAINTS);

    }
}
