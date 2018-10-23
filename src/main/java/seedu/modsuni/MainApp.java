package seedu.modsuni;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import seedu.modsuni.commons.core.Config;
import seedu.modsuni.commons.core.EventsCenter;
import seedu.modsuni.commons.core.LogsCenter;
import seedu.modsuni.commons.core.Version;
import seedu.modsuni.commons.events.ui.ExitAppRequestEvent;
import seedu.modsuni.commons.exceptions.DataConversionException;
import seedu.modsuni.commons.util.ConfigUtil;
import seedu.modsuni.commons.util.StringUtil;
import seedu.modsuni.logic.Logic;
import seedu.modsuni.logic.LogicManager;
import seedu.modsuni.model.AddressBook;
import seedu.modsuni.model.Model;
import seedu.modsuni.model.ModelManager;
import seedu.modsuni.model.ModuleList;
import seedu.modsuni.model.ReadOnlyAddressBook;
import seedu.modsuni.model.ReadOnlyModuleList;
import seedu.modsuni.model.UserPrefs;
import seedu.modsuni.model.credential.CredentialStore;
import seedu.modsuni.model.credential.ReadOnlyCredentialStore;
import seedu.modsuni.model.util.SampleDataUtil;
import seedu.modsuni.storage.AddressBookStorage;
import seedu.modsuni.storage.CredentialStoreStorage;
import seedu.modsuni.storage.JsonUserPrefsStorage;
import seedu.modsuni.storage.ModuleListStorage;
import seedu.modsuni.storage.Storage;
import seedu.modsuni.storage.StorageManager;
import seedu.modsuni.storage.UserPrefsStorage;
import seedu.modsuni.storage.UserStorage;
import seedu.modsuni.storage.XmlAddressBookStorage;
import seedu.modsuni.storage.XmlCredentialStoreStorage;
import seedu.modsuni.storage.XmlModuleListStorage;
import seedu.modsuni.storage.XmlUserStorage;
import seedu.modsuni.ui.Ui;
import seedu.modsuni.ui.UiManager;

/**
 * The main entry point to the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;
    protected UserPrefs userPrefs;


    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing modsUni "
            + "]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        userPrefs = initPrefs(userPrefsStorage);
        CredentialStoreStorage credentialStoreStorage =
            new XmlCredentialStoreStorage(userPrefs.getCredentialStoreFilePath());
        AddressBookStorage addressBookStorage = new XmlAddressBookStorage(userPrefs.getAddressBookFilePath());
        ModuleListStorage moduleListStorage = new XmlModuleListStorage(userPrefs.getModuleFilePath());
        UserStorage userStorage = new XmlUserStorage(userPrefs.getUserStorageFilePath());
        storage = new StorageManager(moduleListStorage, addressBookStorage,
            userPrefsStorage, credentialStoreStorage, userStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model);

        ui = new UiManager(logic, config, userPrefs);

        initEventsCenter();
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}' and {@code userPrefs}.
     *
     */
    private Model initModelManager(Storage storage, UserPrefs userPrefs) {

        Optional<ReadOnlyModuleList> moduleListOptional;
        Optional<ReadOnlyCredentialStore> credentialStoreOptional;
        Optional<ReadOnlyAddressBook> addressBookOptional;

        ReadOnlyAddressBook initialData;
        ReadOnlyModuleList initialModuleListData;
        ReadOnlyCredentialStore initialCredentialStore;

        try {
            // initCredentialStore
            credentialStoreOptional = storage.readCredentialStore();
            if (!credentialStoreOptional.isPresent()) {
                logger.info("Credential Store file not found. Will be starting with a sample Credential Store");
            }
            initialCredentialStore = credentialStoreOptional.orElse(new CredentialStore());
            // initModuleList
            moduleListOptional = storage.readModuleList();
            if (!moduleListOptional.isPresent()) {
                logger.info("Module List data file not found. Will be starting with a sample module list "
                        + "data");
            }
            initialModuleListData = moduleListOptional.orElseGet(SampleDataUtil::getSampleModuleList);
            // initAddressBook
            addressBookOptional = storage.readAddressBook();
            if (!addressBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample AddressBook");
            }
            initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
            initialCredentialStore = new CredentialStore();
            initialModuleListData = new ModuleList();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initialData = new AddressBook();
            initialCredentialStore = new CredentialStore();
            initialModuleListData = new ModuleList();
        }

        return new ModelManager(initialModuleListData, initialData, userPrefs,
            initialCredentialStore);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    private void initEventsCenter() {
        EventsCenter.getInstance().registerHandler(this);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting modsUni " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping modsUni ] "
            + "=============================");
        ui.stop();
        try {
            storage.saveUserPrefs(userPrefs);
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
        Platform.exit();
        System.exit(0);
    }

    @Subscribe
    public void handleExitAppRequestEvent(ExitAppRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
