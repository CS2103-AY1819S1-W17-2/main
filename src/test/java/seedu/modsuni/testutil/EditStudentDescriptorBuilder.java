package seedu.modsuni.testutil;

import java.util.List;

import seedu.modsuni.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.modsuni.model.user.Name;
import seedu.modsuni.model.user.PathToProfilePic;
import seedu.modsuni.model.user.student.EnrollmentDate;
import seedu.modsuni.model.user.student.Student;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder(){
        descriptor = new EditStudentDescriptor();
    }


    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code
     * student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPathToProfilePic(student.getPathToProfilePic());
        descriptor.setEnrollmentDate(student.getEnrollmentDate());
        descriptor.setMajors(student.getMajor());
        descriptor.setMinors(student.getMinor());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code PathToProfilePic} of the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withProfilePic(String path) {
        descriptor.setPathToProfilePic(new PathToProfilePic(path));
        return this;
    }

    /**
     * Sets the {@code EnrollmentDate} of the {@code EditStudentDescriptor} that
     * we are building.
     */
    public EditStudentDescriptorBuilder withEnrollmentDate(String enrollmentDate) {
        descriptor.setEnrollmentDate(new EnrollmentDate(enrollmentDate));
        return this;
    }

    /**
     * Sets the {@code Majors} of the {@code EditStudentDescriptor} that we are
     * building.
     */
    public EditStudentDescriptorBuilder withMajors(List<String> majors) {
        descriptor.setMajors(majors);
        return this;
    }

    /**
     * Parses the {@code Minors} of the {@code EditStudentDescriptor} that we are
     * building.
     */
    public EditStudentDescriptorBuilder withMinors(List<String> minors) {
        descriptor.setMinors(minors);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
