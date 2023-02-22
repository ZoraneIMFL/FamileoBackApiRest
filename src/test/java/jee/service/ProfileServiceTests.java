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
        /*try {
            BufferedImage bImage = ImageIO.read(new File("Enfant1.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            data = bos.toByteArray();
        } catch (java.io.IOException e) {
            data = null;
        }*/
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = ps.createProfile(testP);
        Assert.assertNotNull("Profile creation failed", testP);
        Assert.assertNotNull("Profile creation failed", ps.findProfile(testP.getId()));
        Assert.assertNotNull("Account creation failed", testP.getAcc());
        Profile fetch = ps.findProfile(testP.getId());
        Assert.assertEquals(fetch.getName(), "Enfant1");
        Assert.assertEquals(fetch.getType(), 0);

        Assert.assertNotEquals("At least one profile should be present in the database", ps.getAllProfile().size(), 0);
        //TODO REPAIR NAMED QUERIES Assert.assertNotEquals("At least one profile should exist using the account testA", ps.getAllProfileOfAccount(testA).size(), 0);
    }

    @Test
    public void testUpdateProfile() {
        byte[] data = null;
        /*try {
            BufferedImage bImage = ImageIO.read(new File("Enfant1.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            data = bos.toByteArray();
        } catch (java.io.IOException e) {
            data = null;
        }*/
        Profile testP = new Profile(null, "Enfant1", "EnfantPswd1!", 0, data);
        testP = ps.createProfile(testP);
        Assert.assertNotNull("Profile creation failed", testP);
        testP.setName("Enfant2");
        testP.setType(1);
        testP = ps.updateProfile(testP);

        Profile res = ps.findProfile(testP.getId());
        Assert.assertEquals("Update failed", res.toString(), testP.toString());

        //An account that doesn't exist shouldn't be updated
        Assert.assertNull(ps.updateProfile(new Profile()));
    }

    @Test
    public void testDeleteProfile() {
        byte[] data = null;
        /*try {
            BufferedImage bImage = ImageIO.read(new File("Enfant1.jpg"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );
            data = bos.toByteArray();
        } catch (java.io.IOException e) {
            data = null;
        }*/
        Profile testP = new Profile(null, "Enfant1", "EnfantPswd1!", 0, data);
        testP = ps.createProfile(testP);
        Assert.assertNotNull("Profile creation failed", testP);
        ps.deleteProfile(testP.getId());

        Assert.assertNotEquals("Delete failed", ps.findProfile(testP.getId()));
    }
}
