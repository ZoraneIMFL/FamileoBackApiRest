package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class PasswordEncryption {

    private PasswordEncryption() {
    }

    public static String encryptPassword(String password, byte[] salt) {
        try {
            var messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hash = messageDigest.digest(password.getBytes());
            var bigInt = new BigInteger(1, hash);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] generateSalt() {
        var secureRandom = new SecureRandom();
        var salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
