package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.credential.Credential;
import seedu.address.model.credential.CredentialStore;

/**
 * A utility class containing a list of {@code Credential} objects to be used
 * in tests.
 */
public class TypicalCredentials {

    public static final Credential CREDENTIAL_STUDENT_MAX =
        new CredentialBuilder()
            .withUsername("max")
            .withPassword("@Verstappen33")
            .withKey("randomKeyMax33")
            .build();


    public static final Credential CREDENTIAL_STUDENT_SEB =
        new CredentialBuilder()
            .withUsername("seb")
            .withPassword("@Vettel5")
            .withKey("randomKeySebestian5")
            .build();

    private TypicalCredentials() {
    }

    /**
     * Returns a {@code CredentialStore} with all the typical credentials.
     */
    public static CredentialStore getTypicalCredentialStore() {
        CredentialStore cs = new CredentialStore();
        for (Credential credential : getTypicalCredentials()) {
            cs.addCredential(credential);
        }
        return cs;
    }

    private static List<Credential> getTypicalCredentials() {
        return new ArrayList<>(Arrays.asList(CREDENTIAL_STUDENT_MAX, CREDENTIAL_STUDENT_SEB));
    }
}
