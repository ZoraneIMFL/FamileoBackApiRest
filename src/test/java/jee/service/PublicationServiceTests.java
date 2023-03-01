package jee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Account;
import jee.model.Profile;
import jee.model.Publication;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Properties;

public class PublicationServiceTests extends TestCase {
    @EJB
    private AccountService accountService;

    @EJB
    private ProfileService profileService;
    @EJB
    private PublicationService publicationService;

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
    public void testPublicationCreation() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = accountService.createAccount(testA);
        byte[] data = null;
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = profileService.createProfile(testP);
        Publication p = new Publication("Test", new Date(), 0.0, 0.0, testP, testA);
        p = publicationService.createPublication(p);
        Assert.assertNotNull("Publication creation failed", p);
        Assert.assertNotNull("Publication creation failed", publicationService.findPublication(p.getId()));
        Assert.assertNotEquals("At least one publication should be present in the database", 0, publicationService.getAllPublication().size());
        Assert.assertNotEquals("No publication is present in the profile", 0, publicationService.getAllPublicationOfProfile(testP).size());
    }

    @Test
    public void testPublicationUpdate() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = accountService.createAccount(testA);
        byte[] data = null;
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = profileService.createProfile(testP);
        Publication p = new Publication("Test", new Date(), 0.0, 0.0, testP, testA);
        p = publicationService.createPublication(p);
        p.setDescription("Test2");
        p.setPublishDate(new Date());
        p.setLatitude(1.1);
        p.setLongitude(1.1);
        p.setProfile(null);
        p.setAccount(null);
        Publication newP = publicationService.updatePublication(p);
        Assert.assertEquals("Update publication failed", newP.toString(), publicationService.findPublication(p.getId()).toString());

        Assert.assertNull("Can update a non existant Publication", publicationService.updatePublication(new Publication()));
    }

    @Test
    public void testPublicationDelete() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = accountService.createAccount(testA);
        byte[] data = null;
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, data);
        testP = profileService.createProfile(testP);
        Publication p = new Publication("Test", new Date(), 0.0, 0.0, testP, testA);
        p = publicationService.createPublication(p);
        Assert.assertNotNull("Publication creation failed", publicationService.findPublication(p.getId()));
        publicationService.deletePublication(p.getId());
        Assert.assertNull("Photo deletion failed", publicationService.findPublication(p.getId()));
    }
}
