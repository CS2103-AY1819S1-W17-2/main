package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.ACC1002;
import static seedu.address.testutil.TypicalModules.ACC1002X;
import static seedu.address.testutil.TypicalModules.CS1010;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.credential.CredentialStore;
import seedu.address.model.module.CodeStartsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SearchCommandTest {
    private Model model = new ModelManager(
            getTypicalModuleList(),
            getTypicalAddressBook(),
            new UserPrefs(),
            new CredentialStore());
    private Model expectedModel = new ModelManager(getTypicalModuleList(), getTypicalAddressBook(), new
            UserPrefs(), new CredentialStore());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        CodeStartsKeywordsPredicate firstPredicate =
                new CodeStartsKeywordsPredicate(Collections.singletonList("first"));
        CodeStartsKeywordsPredicate secondPredicate =
                new CodeStartsKeywordsPredicate(Collections.singletonList("second"));

        SearchCommand searchFirstCommand = new SearchCommand(firstPredicate);
        SearchCommand searchSecondCommand = new SearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchCommand searchFirstCommandCopy = new SearchCommand(firstPredicate);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noModuleFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED_OVERVIEW, 0);
        CodeStartsKeywordsPredicate predicate = preparePredicate(" ");
        SearchCommand command = new SearchCommand(predicate);

        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredModuleList());
    }

    @Test
    public void execute_multipleKeywords_multipleModulesFound() {
        String expectedMessage = String.format(MESSAGE_MODULE_LISTED_OVERVIEW, 3);
        CodeStartsKeywordsPredicate predicate = preparePredicate("ACC CS");
        SearchCommand command = new SearchCommand(predicate);
        model.addModuleToDatabase(CS1010);
        expectedModel.addModuleToDatabase(CS1010);
        expectedModel.updateFilteredModuleList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ACC1002, ACC1002X, CS1010), model.getFilteredModuleList());
    }

    /**
     * Parses {@code userInput} into a {@code CodeStartsKeywordsPredicate}.
     */
    private CodeStartsKeywordsPredicate preparePredicate(String userInput) {
        return new CodeStartsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
