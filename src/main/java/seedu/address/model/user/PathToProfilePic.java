package seedu.address.model.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a User's path to profile picture.
 * Guarantees: immutable; is valid as declared in {@link #isValidPath(String)}
 */
public class PathToProfilePic {

    public static final String MESSAGE_PATH_CONSTRAINTS =
            "Path names should end with a .img";

    public static final String PATH_VALIDATION_REGEX = "^(\\w)+(\\.img)";

    public final String path;

    /**
     * Constructs a {@code pathToProfilePic}.
     * @param path A valid path name.
     */
    public PathToProfilePic(String path) {
        requireNonNull(path);
        checkArgument(isValidPath(path), MESSAGE_PATH_CONSTRAINTS);
        this.path = path;
    }
    /**
     * Returns true if a given string is a valid path name.
     */
    public static boolean isValidPath(String test) {
        return test.matches(PATH_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return path;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PathToProfilePic // instanceof handles nulls
                && path.equals(((PathToProfilePic) other).path)); // state check
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
