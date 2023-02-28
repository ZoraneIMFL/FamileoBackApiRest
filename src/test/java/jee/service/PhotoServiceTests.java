package jee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Account;
import jee.model.Photo;
import jee.model.Profile;
import jee.model.Publication;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Properties;

public class PhotoServiceTests extends TestCase {
    @EJB
    private AccountService accountService;

    @EJB
    private ProfileService profileService;

    @EJB
    private PublicationService publicationService;

    @EJB
    private PhotoService photoServices;

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
    public void testPhotoCreation() {
        Account testA = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        testA = accountService.createAccount(testA);
        Profile testP = new Profile(testA, "Enfant1", "EnfantPswd1!", 0, null);
        testP = profileService.createProfile(testP);
        Publication pub = new Publication("Test", new Date(), 0.0, 0.0, testP, testA);
        pub = publicationService.createPublication(pub);
        Photo pho = new Photo("Test", new Date(), 0.0, 0.0);
        pho = photoServices.createPhoto(pho);
        Assert.assertNotNull("Photo creation failed", pho);
        Assert.assertNotNull("Photo creation failed", photoServices.findPhoto(pho.getId()));
        Assert.assertNotEquals("At least one photo should be present in the database", photoServices.getAllPhoto().size(), 0);
        /*Assert.assertEquals( pub.getPhotos().size(), 0);
        pub.addPhoto(pho);
        Assert.assertEquals("Add to publication failed", 1, pub.getPhotos().size());
        Assert.assertEquals("Add to publication failed", pho, pub.getPhotos().get(0));
        Assert.assertEquals("Add to publication failed", 1, pho.getPublications().size());
        pub.removePhoto(pho);
        Assert.assertEquals("Removed from publication failed", 0, pub.getPhotos().size());
        Assert.assertEquals("Removed from publication failed", 0, pho.getPublications().size());*/
    }

    @Test
    public void testPhotoUpdate() {
        Photo p = new Photo("Test", new Date(), 0.0, 0.0);
        p = photoServices.createPhoto(p);
        p.setName("Test2");
        p.setDate(new Date());
        p.setLatitude(1.1);
        p.setLongitude(1.1);
        Photo updated = photoServices.updatePhoto(p);
        Assert.assertEquals("Update photo failed", photoServices.findPhoto(p.getId()).getName(), updated.getName());
        Assert.assertEquals("Update photo failed", photoServices.findPhoto(p.getId()).getDate(), updated.getDate());
        Assert.assertEquals("Update photo failed", photoServices.findPhoto(p.getId()).getLatitude(), updated.getLatitude(), 0.1);
        Assert.assertEquals("Update photo failed", photoServices.findPhoto(p.getId()).getLongitude(), updated.getLongitude(), 0.1);

        Assert.assertNull("Can update a non existant Photo", photoServices.updatePhoto(new Photo()));
    }

    @Test
    public void testPhotoDelete() {
        Photo p = new Photo("Test", new Date(), 0.0, 0.0);
        p = photoServices.createPhoto(p);
        Assert.assertNotNull("Photo creation failed", photoServices.findPhoto(p.getId()));
        photoServices.deletePhoto(p.getId());
        Assert.assertNull("Photo deletion failed", photoServices.findPhoto(p.getId()));
    }
}
