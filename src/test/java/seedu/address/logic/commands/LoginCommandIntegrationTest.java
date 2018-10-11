package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCredentials.CREDENTIAL_STUDENT_SEB;
import static seedu.address.testutil.TypicalCredentials.getTypicalCredentialStore;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.ConfigStore;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.Credential;
import seedu.address.model.user.Name;
import seedu.address.model.user.PathToProfilePic;
import seedu.address.model.user.Role;
import seedu.address.model.user.User;
import seedu.address.model.user.student.EnrollmentDate;
import seedu.address.model.user.student.Student;
import seedu.address.testutil.CredentialBuilder;
import seedu.address.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code
 * RegisterCommand}.
 */
public class LoginCommandIntegrationTest {

    private static Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(
            new ModuleList(),
            new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore(),
            new ConfigStore());
        model.setCurrentUser(new StudentBuilder().build());
    }

    @Test
    public void execute_loginSuccess() {
        Credential toVerify = CREDENTIAL_STUDENT_SEB;

        Model expectedModel = new ModelManager(
            new ModuleList(),
            new AddressBook(),
            new UserPrefs(),
            getTypicalCredentialStore(),
            new ConfigStore());

        User loginUser = new Student(
            toVerify.getUsername(),
            new Name("dummy"),
            Role.STUDENT,
            new PathToProfilePic("dummy.img"),
            new EnrollmentDate("08/08/2018"),
            Arrays.asList("CS"),
            Arrays.asList("MA"));
        expectedModel.setCurrentUser(loginUser);

        assertCommandSuccess(new LoginCommand(toVerify), model,
            commandHistory,
            String.format(LoginCommand.MESSAGE_SUCCESS,
                toVerify.getUsername().toString()),
            expectedModel);
    }

    @Test
    public void execute_loginFailure() {
        Credential toVerify = new CredentialBuilder(CREDENTIAL_STUDENT_SEB)
            .withPassword("incorrectPassword")
            .build();
        assertCommandFailure(new LoginCommand(toVerify),
            model,
            commandHistory,
            LoginCommand.MESSAGE_LOGIN_FAILURE);
    }


}
