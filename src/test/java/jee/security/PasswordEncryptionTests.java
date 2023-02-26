package jee.security;

import org.junit.Assert;
import org.junit.Test;
import security.PasswordEncryption;

public class PasswordEncryptionTests {

    @Test
    public void testEncryption() {
        byte[] salt = PasswordEncryption.generateSalt();
        String testPasswd = "TestPwd123";
        String encryptedPasswd = PasswordEncryption.encryptPassword(testPasswd, salt);
        Assert.assertNotEquals("Encryption failed", testPasswd, encryptedPasswd);
    }
}
