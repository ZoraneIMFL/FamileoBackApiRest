package jee.controller;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Account;
import jee.model.Profile;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

public class ProfileControllerTests extends TestCase {
    @EJB
    private ProfileController profileController;
    @EJB
    private AccountController accountController;

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
    public void testGetAccount() {
        Account testA = (Account) accountController.createAccount(new Account("Alice", "alice@gmail.com", "TestPswd45!", 0)).getEntity();
        Profile test = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, null);
        Assert.assertEquals("Profile creation failed", 200, profileController.createProfile(test).getStatus());
        Assert.assertEquals("Get all accounts failed", 200, profileController.getAllProfile().getStatus());
        List<Profile> profiles = (List<Profile>) profileController.getAllProfile().getEntity();
        Assert.assertEquals("Profile missing in the database / database didn't reset'", 1, profiles.size());

        Assert.assertEquals("We can find a non existing profile", 404, profileController.findProfile((long) 6448949).getStatus());
        Assert.assertEquals("We can find a profile with an invalid id", 400, profileController.findProfile((long) -1).getStatus());
        Assert.assertEquals("We can't find the account we added", profiles.get(0).toString(), profileController.findProfile(profiles.get(0).getId()).getEntity().toString());
    }

    @Test
    public void testUpdateAccount() {
        Account testA = (Account) accountController.createAccount(new Account("Alice", "alice@gmail.com", "TestPswd45!", 0)).getEntity();
        Profile test = (Profile) profileController.createProfile(new Profile(testA, "Enfant1", "EnfantPswd1!", 0, null)).getEntity();
        Profile updatedProfile = new Profile(testA, "Enfant1Updated", "EnfantPswd1!Updated", 1, null);
        Assert.assertEquals("We can update a profile with an invalid id", 400, profileController.updateProfile((long) -1, updatedProfile).getStatus());
        Assert.assertEquals("We can update a non existing profile", 404, profileController.updateProfile((long) 6448949, updatedProfile).getStatus());
        Assert.assertEquals("We can't update an existing profile", 200, profileController.updateProfile(test.getId(), updatedProfile).getStatus());
        Assert.assertEquals("The update failed", updatedProfile.toString(), profileController.findProfile(test.getId()).getEntity().toString());
    }

    @Test
    public void testDeleteAccount() {
        Account testA = (Account) accountController.createAccount(new Account("Alice", "alice@gmail.com", "TestPswd45!", 0)).getEntity();
        Profile test = (Profile) profileController.createProfile(new Profile(testA, "Enfant1", "EnfantPswd1!", 0, null)).getEntity();
        Assert.assertEquals("We can delete a profile with an invalid id", 400, profileController.deleteProfile((long) -1).getStatus());
        Assert.assertEquals("Delete system not working", 204, profileController.deleteProfile(test.getId()).getStatus());
        Assert.assertEquals("The deleted element is still in the db", 0, ((List<Account>) profileController.getAllProfile().getEntity()).size());
    }
}
