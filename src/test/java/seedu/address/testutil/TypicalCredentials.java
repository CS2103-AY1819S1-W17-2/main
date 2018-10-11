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
            .withUsername("max33")
            .withPassword("5ddd2cc3ae3a14b4d717e9a0eaa8b44f6d9d10d0d5edd8183d569622e29ce680")
            .withKey("5ddd2cc3ae3a14b4d717e9a0eaa8b44f6d9d10d0d5edd8183d569622e29ce680")
            .build();


    public static final Credential CREDENTIAL_STUDENT_SEB =
        new CredentialBuilder()
            .withUsername("vettel5")
            .withPassword("6284cd40cd25dc42e03dca6bd45c2cfe5463d82920ac231f29034f4424a3349c")
            .withKey("6284cd40cd25dc42e03dca6bd45c2cfe5463d82920ac231f29034f4424a3349c")
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
