package security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.SecureRandom;
public class PasswordEncryption  {

    public static String encryptPassword(String password, byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] hash = messageDigest.digest(password.getBytes());
            BigInteger bigInt = new BigInteger(1, hash);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
