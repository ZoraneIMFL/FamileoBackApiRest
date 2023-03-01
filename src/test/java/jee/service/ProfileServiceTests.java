package jee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Account;
import jee.model.Profile;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import junit.framework.TestCase;

public class ProfileServiceTests extends TestCase {
    @EJB
    private ProfileService ps;

    @EJB
    private AccountService as;

    protected static EJBContainer ejbContainer;

    public void setUp() throws Exception {
        Properties p = new Properties();
        p.put("test", "new://Resource?type=DataSource");
        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);
    }

    public void tearDown() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }

    @Test
    public void testProfileCreation() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = as.createAccount(testA);

        byte[] data = null;
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = ps.createProfile(testP);
        Assert.assertNotNull("Profile creation failed", testP);
        Assert.assertNotNull("Profile creation failed", ps.findProfile(testP.getId()));
        Assert.assertNotNull("Account creation failed", testP.getAcc());
        Profile fetch = ps.findProfile(testP.getId());
        Assert.assertEquals("Enfant1", fetch.getName());
        Assert.assertEquals(0, fetch.getType());

        Assert.assertNotEquals("At least one profile should be present in the database", ps.getAllProfile().size(), 0);
        Assert.assertNotEquals("At least one profile should exist using the account testA", ps.getAllProfileOfAccount(testA.getId()).size(), 0);
    }

    @Test
    public void testUpdateProfile() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = as.createAccount(testA);
        byte[] data = null;
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = ps.createProfile(testP);
        Assert.assertNotNull("Profile creation failed", testP);
        testP.setName("Enfant2");
        testP.setType(1);
        testP.setProfileImage(null);
        String oldPass = testP.getPassword();
        testP.setPassword("NewPasswd1");
        testP = ps.updateProfile(testP);

        Profile res = ps.findProfile(testP.getId());
        Assert.assertEquals("Update failed", res.toString(), testP.toString());
        Assert.assertNotEquals("Password update failed", testP.getPassword(), oldPass);
        Assert.assertEquals("Update profile picture", null, testP.getProfileImage());

        Assert.assertNull("We can update a non existing account", ps.updateProfile(new Profile()));
    }

    @Test
    public void testDeleteProfile() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = as.createAccount(testA);
        byte[] data = null;
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = ps.createProfile(testP);
        Assert.assertNotNull("Profile creation failed", testP);
        ps.deleteProfile(testP.getId());

        Assert.assertNotEquals("Delete failed", ps.findProfile(testP.getId()), testP);
    }
}
