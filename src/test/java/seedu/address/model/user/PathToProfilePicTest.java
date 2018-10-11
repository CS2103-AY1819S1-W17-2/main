package seedu.address.model.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class PathToProfilePicTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new PathToProfilePic(null));
    }

    @Test
    public void constructor_invalidPathToProfilePic_throwsIllegalArgumentException() {
        String invalidPathToProfilePic = "invalid.pdf";
        Assert.assertThrows(IllegalArgumentException.class, () -> new PathToProfilePic(invalidPathToProfilePic));
    }

    @Test
    public void isValidPathToProfilePic() {
        // null path
        Assert.assertThrows(NullPointerException.class, () -> PathToProfilePic.isValidPath(null));

        // invalid path
        assertFalse(PathToProfilePic.isValidPath("")); // empty string
        assertFalse(PathToProfilePic.isValidPath(" ")); // spaces only
        assertFalse(PathToProfilePic.isValidPath("abcABC")); // no extension
        assertFalse(PathToProfilePic.isValidPath("invalid.pdf")); // invalid extension

        // valid salary
        assertTrue(PathToProfilePic.isValidPath("valid.img")); // digits only
    }
}
