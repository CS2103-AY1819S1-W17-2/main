package seedu.address.commons.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

import com.google.common.base.Charsets;

import seedu.address.commons.exceptions.CorruptedFileException;
import seedu.address.commons.exceptions.InvalidPasswordException;

public class DataSecurityUtilTest {

    private static final String PASSWORD = "p@ssW0Rd";
    private static final String WRONG_PASSWORD = "1";

    private static final byte[] TEST_DATA_ENCRYPTION = "TEST DATA".getBytes();
    private static final String EXPECTED_ENCRYPTION = "D91B9E39621EACE5877DCD6158393F0F";

    private static final byte[] TEST_DATA_DECRYPTION = convertStringToByteArray("D91B9E39621EACE5877DCD6158393F0F");
    private static final String EXPECTED_DECRYPTION = "TEST DATA";

    private static final String TEST_FILE_PATH = "src/test/data/sandbox/";
    private static final String TEST_FILE_PREFIX = "test_encryption_file";
    private static final String TEST_FILE_EXTENSION = ".xml";


    // Encryption test case
    @Test
    public void testEncryptingBytes() {
        byte[] encryptedData = DataSecurityUtil.encrypt(TEST_DATA_ENCRYPTION, PASSWORD);
        String encryptedDataString = convertByteArrayToHexString(encryptedData);

        assertEquals(EXPECTED_ENCRYPTION, encryptedDataString);
    }

    @Test(expected = NullPointerException.class)
    public void testEncryptingNullBytesValidPassword() {
        DataSecurityUtil.encrypt(null, PASSWORD);
    }

    @Test(expected = NullPointerException.class)
    public void testEncryptingValidBytesAndNullPassword() {
        DataSecurityUtil.encrypt(TEST_DATA_ENCRYPTION, null);
    }

    @Test(expected = NullPointerException.class)
    public void testEncryptingNullBytesAndNullPassword() {
        DataSecurityUtil.encrypt(null, null);
    }

    // Decryption test case
    @Test
    public void testDecryptingBytes() throws CorruptedFileException, InvalidPasswordException {
        byte[] decryptedData = DataSecurityUtil.decrypt(TEST_DATA_DECRYPTION, PASSWORD);
        String decryptedDataString = new String(decryptedData, Charsets.UTF_8);

        assertEquals(EXPECTED_DECRYPTION, decryptedDataString);
    }

    @Test(expected = NullPointerException.class)
    public void testDecryptingNullBytesValidPassword() throws CorruptedFileException, InvalidPasswordException {
        DataSecurityUtil.decrypt(null, PASSWORD);
    }

    @Test(expected = NullPointerException.class)
    public void testDecryptingValidBytesAndNullPassword() {
        DataSecurityUtil.encrypt(TEST_DATA_DECRYPTION, null);
    }

    @Test(expected = NullPointerException.class)
    public void testDecryptingNullBytesAndNullPassword() {
        DataSecurityUtil.encrypt(null, null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void testDecryptingBytesWrongPassword() throws CorruptedFileException, InvalidPasswordException {
        DataSecurityUtil.decrypt(TEST_DATA_DECRYPTION, WRONG_PASSWORD);
    }

    // File encryption test
    @Test
    public void testFileEncrypting() throws IOException {

        // Create and write TEST_DATA_ENCRYPTION to file
        File file = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_EXTENSION, new File(TEST_FILE_PATH));
        Files.write(file.toPath(), TEST_DATA_ENCRYPTION);

        // Encrypt file
        DataSecurityUtil.encryptFile(file, PASSWORD);

        // Get content from file
        byte[] fileContent = Files.readAllBytes(file.toPath());

        assertNotEquals(Arrays.toString(TEST_DATA_ENCRYPTION), Arrays.toString(fileContent));

        file.deleteOnExit();
    }

    @Test(expected = NullPointerException.class)
    public void testFileEncryptingNullFileValidPassword() throws IOException {
        DataSecurityUtil.encryptFile(null, PASSWORD);
    }

    @Test(expected = NullPointerException.class)
    public void testFileEncryptingValidFileNullPassword() throws IOException {

        File file = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_EXTENSION, new File(TEST_FILE_PATH));
        Files.write(file.toPath(), TEST_DATA_ENCRYPTION);

        try {
            DataSecurityUtil.encryptFile(file, null);
        } finally {
            file.deleteOnExit();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testFileEncryptingNullFileNullPassword() throws IOException {
        DataSecurityUtil.encryptFile(null, null);
    }

    // File decryption test
    @Test
    public void testFileDecrypting() throws IOException, CorruptedFileException, InvalidPasswordException {

        // Create and write TEST_DATA_ENCRYPTION to file
        File file = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_EXTENSION, new File(TEST_FILE_PATH));
        Files.write(file.toPath(), TEST_DATA_ENCRYPTION);

        // Encrypt file
        DataSecurityUtil.encryptFile(file, PASSWORD);

        // Decrypt file
        DataSecurityUtil.decryptFile(file, PASSWORD);

        // Get content from file
        byte[] fileContent = Files.readAllBytes(file.toPath());

        assertEquals(Arrays.toString(TEST_DATA_ENCRYPTION), Arrays.toString(fileContent));

        file.deleteOnExit();
    }

    @Test(expected = NullPointerException.class)
    public void testFileDecryptingNullFileValidPassword() throws IOException,
            CorruptedFileException, InvalidPasswordException {
        DataSecurityUtil.decryptFile(null, PASSWORD);
    }

    @Test(expected = NullPointerException.class)
    public void testFileDecryptingValidFileNullPassword() throws IOException,
            CorruptedFileException, InvalidPasswordException {
        File file = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_EXTENSION, new File(TEST_FILE_PATH));
        Files.write(file.toPath(), TEST_DATA_ENCRYPTION);

        try {
            DataSecurityUtil.decryptFile(file, null);
        } finally {
            file.deleteOnExit();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testFileDecryptingNullFileNullPassword() throws IOException,
            CorruptedFileException, InvalidPasswordException {
        DataSecurityUtil.decryptFile(null, null);
    }

    @Test(expected = InvalidPasswordException.class)
    public void testFileDecryptingWrongPassword() throws IOException,
            CorruptedFileException, InvalidPasswordException {

        // Create and write TEST_DATA_ENCRYPTION to file
        File file = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_EXTENSION, new File(TEST_FILE_PATH));
        Files.write(file.toPath(), TEST_DATA_ENCRYPTION);

        try {
            // Encrypt file
            DataSecurityUtil.encryptFile(file, PASSWORD);

            // Decrypt file
            DataSecurityUtil.decryptFile(file, WRONG_PASSWORD);
        } finally {
            // Clean up
            file.deleteOnExit();
        }
    }

    @Test(expected = CorruptedFileException.class)
    public void testFileDecryptingCorruptedFile() throws IOException,
            CorruptedFileException, InvalidPasswordException {

        // Create and write TEST_DATA_ENCRYPTION to file
        File file = File.createTempFile(TEST_FILE_PREFIX, TEST_FILE_EXTENSION, new File(TEST_FILE_PATH));
        Files.write(file.toPath(), TEST_DATA_ENCRYPTION);

        try {
            // Encrypt file
            DataSecurityUtil.encryptFile(file, PASSWORD);

            // Corrupt data
            byte[] cleanFileContent = Files.readAllBytes(file.toPath());
            byte[] corruptedFileContent = Arrays.copyOfRange(cleanFileContent, 0,
                    cleanFileContent.length - 5);

            // Write corrupted data to file
            Files.write(file.toPath(), corruptedFileContent);

            // Decrypt file
            DataSecurityUtil.decryptFile(file, PASSWORD);
        } finally {
            // Clean up
            file.deleteOnExit();
        }
    }


    private static String convertByteArrayToHexString(byte[] data) {
        return DatatypeConverter.printHexBinary(data);
    }

    private static byte[] convertStringToByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

}
