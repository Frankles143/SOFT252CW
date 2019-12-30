package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Users.AbstractPerson;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public abstract class Password {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final SecureRandom random = new SecureRandom();

    /**
     * A method to generate a salt for a hashing a password using Java's SecureRandom class
     * @param length length of salt
     * @return Returns empty if salt is too short or returns a salt String
     * @author Josh Franklin
     */
    public static Optional<String> GenerateSalt(final int length) {

        if (length < 1) {
            System.out.println("Salt length must be greater than 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        random.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }


    /**
     * A method to take a plaintext password and salt and convert and return a secure password
     * @param password plaintext password from user
     * @param salt salt for that user
     * @return returns nothing if an exception is thrown or the secure password if everything goes correctly
     * @author Josh Franklin
     */
    public static Optional<String> HashPassword(String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    /**
     * A way to verify if a password is correct by hashing a password with that users salt and comparing to their encrypted password
     * @param password the password being compared to the stored encrypted version
     * @param person person who is trying to log in
     * @return returns false if not present, otherwise returns either true or false depending on if the password is a match for what is stored
     * @author Josh Franklin
     */
    public static boolean VerifyPassword (String password, AbstractPerson person) {
        try {
            Optional<String> optEncrypted = HashPassword(password, person.getSalt());
            if (!optEncrypted.isPresent()) {
                return false;
            }

            return optEncrypted.get().equals(person.getEncryptedPassword());
        } catch (Exception e) {
            System.out.println("Unable to verify password: " + e);
            return false;
        }
    }

    /**
     * A method to change a users encrypted password and salt
     * @param newPassword the new password of their choice
     * @param person the person who's password will be changed
     * @author Josh Franklin
     */
    public static void ChangePassword(String newPassword, AbstractPerson person){
        try {
            person.setSalt(GenerateSalt(512).get());
            person.setEncryptedPassword(newPassword);
        } catch (Exception e) {
            System.out.println("Could not change password");
        }
    }
}
