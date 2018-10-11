package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.google.common.hash.Hashing;

import seedu.address.commons.exceptions.CorruptedFileException;
import seedu.address.commons.exceptions.InvalidPasswordException;

/**
 * Encrypts and decrypts data
 */
public class DataSecurityUtil {

    private static final String ALGORITHM = "AES";
    private static final String CIPHER_INSTANCE = "AES/ECB/PKCS5Padding";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid Password";
    private static final String CORRUPTED_FILE_MESSAGE = "The encrypted file may be corrupted. Decryption failed.";
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * Encrypts the given file using a password and overwrites the original plaintext file
     *
     * @param file The file to be encrypted
     * @param password Used to encrypt file
     */
    public static void encryptFile(File file, String password) throws IOException {
        requireNonNull(file);
        requireNonNull(password);
        byte[] fileContent = convertFileToByteArray(file);
        byte[] encryptedFileContent = encrypt(fileContent, password);

        writeByteArrayToFile(file, encryptedFileContent);
    }

    /**
     * Decrypts the given file using a password and overwrites the original encrypted file
     *
     * @param file The file to be decrypted
     * @param password Used to decrypt file
     */
    public static void decryptFile(File file, String password) throws IOException,
            InvalidPasswordException, CorruptedFileException {
        requireNonNull(file);
        requireNonNull(password);
        byte[] fileContent = convertFileToByteArray(file);
        byte[] decryptedFileContent = decrypt(fileContent, password);

        writeByteArrayToFile(file, decryptedFileContent);
    }


    /**
     * Encrypts the given data using a password
     *
     * @param data The data to be encrypted
     * @param password Used to encrypt data
     * @return byte[] of the encrypted data
     */
    public static byte[] encrypt(byte[] data, String password) {
        requireNonNull(data);
        requireNonNull(password);
        try {
            Key secretKey = generateSecretKey(password);
            Cipher aesCipher = Cipher.getInstance(CIPHER_INSTANCE);
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return aesCipher.doFinal(data);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException
                | InvalidKeyException | BadPaddingException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    /**
     * Decrypts the data using a given password
     *
     * @param data The data to be decrypted
     * @param password Used to decrypt data
     * @return byte[] of the decrypted data
     * @throws InvalidPasswordException if an invalid password is supplied
     */
    public static byte[] decrypt(byte[] data, String password) throws InvalidPasswordException, CorruptedFileException {
        requireNonNull(data);
        requireNonNull(password);
        try {
            Key secretKey = generateSecretKey(password);
            Cipher aesCipher = Cipher.getInstance(CIPHER_INSTANCE);
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
            return aesCipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            handleBadPaddingException();
        } catch (IllegalBlockSizeException e) {
            handleIllegalBlockSizeException();
        }

        return new byte[0];
    }

    /**
     * Writes byte array to file
     *
     * @param file The file to be written
     * @param data The data to be written into file
     * @throws IOException if file do not exist
     */
    private static void writeByteArrayToFile(File file, byte[] data) throws IOException {
        requireNonNull(file);
        requireNonNull(data);
        Files.write(file.toPath(), data);
    }

    /**
     * Converts a file to byte array
     *
     * @param file The file to be converted
     * @return file in byte array
     * @throws IOException if file do not exist
     */
    private static byte[] convertFileToByteArray(File file) throws IOException {
        requireNonNull(file);
        return Files.readAllBytes(file.toPath());
    }

    /**
     * Generates a key
     *
     * @param password The password to generate the key
     * @return A secret key
     */
    private static Key generateSecretKey(String password) {
        requireNonNull(password);
        return new SecretKeySpec(getFirst16Bytes(hash(password).getBytes()), ALGORITHM);
    }

    /**
     * Handles BadPaddingException
     *
     * @throws InvalidPasswordException if an invalid password is supplied
     */
    private static void handleBadPaddingException() throws InvalidPasswordException {
        throw new InvalidPasswordException(INVALID_PASSWORD_MESSAGE);
    }

    /**
     * Handles IllegalBlockSizeException
     *
     * @throws CorruptedFileException if an encrypted file is corrupted
     */
    private static void handleIllegalBlockSizeException() throws CorruptedFileException {
        throw new CorruptedFileException(CORRUPTED_FILE_MESSAGE);
    }

    /**
     * Generates a SHA-1 hash using a string
     *
     * @param password The string to be hashed
     * @return
     */
    private static String hash(String password) {
        requireNonNull(password);
        return Hashing.sha1().hashString(password, CHARSET).toString();
    }

    /**
     * Gets the first 16 bytes of a byte array
     *
     * @param password The string to be hashed
     * @return first 16 bytes of a byte array
     */
    private static byte[] getFirst16Bytes(byte[] password) {
        requireNonNull(password);
        return Arrays.copyOf(password, 16);
    }
}
