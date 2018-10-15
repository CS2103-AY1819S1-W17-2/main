package seedu.address.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.XmlUtil;

/**
 * Stores data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given addressbook data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableAddressBook addressBook)
        throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, addressBook);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Saves the given modulelist data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableModuleList moduleList)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, moduleList);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Saves the given credential store data to the specified file.
     */
    public static void saveDataToFile(Path file,
                                      XmlSerializableCredentialStore credentialStore)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, credentialStore);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Saves the given credential store data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableConfigStore configStore) throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, configStore);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns address book in the file or an empty address book
     */
    public static XmlSerializableAddressBook loadDataFromSaveFile(Path file) throws DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableAddressBook.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Returns module list in the file
     */
    public static XmlSerializableModuleList loadModuleListFromSaveFile(Path file) throws
            DataConversionException,
            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableModuleList.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Returns CredentialStore in the file or an empty CredentialStore
     */
    public static XmlSerializableCredentialStore loadCredentialStoreDataFromSaveFile(Path file)
        throws DataConversionException,

        FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableCredentialStore.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

    /**
     * Returns CredentialStore in the file or an empty usercredentials
     */
    public static XmlSerializableConfigStore loadConfigStoreDataFromSaveFile(Path file) throws DataConversionException,

            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableConfigStore.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }


}
