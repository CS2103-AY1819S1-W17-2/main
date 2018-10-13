package seedu.address.model;

import seedu.address.model.user.Role;
import seedu.address.model.user.User;
import seedu.address.model.user.student.Student;

/**
 * Wraps all Configuration data
 */
public class ConfigStore {
    private byte[] configData;

    public void addConfigData (Config config) {
        configData = config.getConfigData();
    }

    public byte[] getConfigData() {
        return configData;
    }
}
