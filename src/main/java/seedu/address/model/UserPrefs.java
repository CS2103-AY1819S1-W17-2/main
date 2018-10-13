package seedu.address.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs {

    private GuiSettings guiSettings;
    private Path addressBookFilePath = Paths.get("data" , "addressbook.xml");
    private Path moduleFilePath = Paths.get("data", "moduleList.xml.xml");
    private Path credentialStoreFilePath = Paths.get("data" , "credentialstore.xml");
    private Path userConfigFilePath = Paths.get("data", "user123");

    public UserPrefs() {
        setGuiSettings(500, 500, 0, 0);
    }

    public GuiSettings getGuiSettings() {
        return guiSettings == null ? new GuiSettings() : guiSettings;
    }

    public void updateLastUsedGuiSetting(GuiSettings guiSettings) {
        this.guiSettings = guiSettings;
    }

    public void setGuiSettings(double width, double height, int x, int y) {
        guiSettings = new GuiSettings(width, height, x, y);
    }

    public Path getCredentialStoreFilePath() {
        return credentialStoreFilePath;
    }

    public void setCredentialStoreFilePath(Path credentialStoreFilePath) {
        this.credentialStoreFilePath = credentialStoreFilePath;
    }

    public Path getUserConfigFilePath() {
        return userConfigFilePath;
    }
    public void setUserConfigFilePath(Path userConfigFilePath) {
        this.userConfigFilePath = userConfigFilePath;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getModuleFilePath() {
        return moduleFilePath;
    }

    public void setModuleFilePath(Path moduleFilePath) {
        this.moduleFilePath = moduleFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return Objects.equals(guiSettings, o.guiSettings)
                && Objects.equals(addressBookFilePath, o.addressBookFilePath)
                && Objects.equals(moduleFilePath, o.moduleFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings.toString());
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nLocal module list data file location : " + moduleFilePath);
        sb.append("\nLocal Credential Store File Location : " + credentialStoreFilePath);
        return sb.toString();
    }

}
