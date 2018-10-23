package seedu.modsuni.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.modsuni.logic.commands.CommandTestUtil.DESC_MAX;
import static seedu.modsuni.logic.commands.CommandTestUtil.DESC_SEB;

import org.junit.Test;

import seedu.modsuni.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.modsuni.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        EditStudentDescriptor descriptorWithSameValue =
            new EditStudentDescriptor(DESC_MAX);

        // same values -> returns true
        assertTrue(DESC_MAX.equals(descriptorWithSameValue));

        // same object -> returns true
        assertTrue(DESC_MAX.equals(DESC_MAX));

        // null -> returns false
        assertFalse(DESC_MAX.equals(null));

        // different types -> returns false
        assertFalse(DESC_MAX.equals(1));

        // different values -> returns false
        assertFalse(DESC_MAX.equals(DESC_SEB));

        // different name -> returns false
        EditStudentDescriptor differentNameDescriptor =
            new EditStudentDescriptorBuilder(DESC_MAX)
                .withName("MAX")
                .build();
        assertFalse(DESC_MAX.equals(differentNameDescriptor));

        // different Path -> returns false
        EditStudentDescriptor differentProfilePicDescriptor =
            new EditStudentDescriptorBuilder(DESC_MAX)
                .withProfilePic("different.img")
                .build();
        assertFalse(DESC_MAX.equals(differentProfilePicDescriptor));

        // different Enrollment Date -> returns false
        EditStudentDescriptor differentEnrollmentDateDescriptor =
            new EditStudentDescriptorBuilder(DESC_MAX)
                .withEnrollmentDate("11/11/1111")
                .build();
        assertFalse(DESC_MAX.equals(differentEnrollmentDateDescriptor));

    }
}
