package jee.controller;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.dto.LitePhoto;
import jee.model.Account;
import jee.model.Photo;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class PhotoControllerTests extends TestCase {

    @EJB
    private PhotoController photoController;

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
    public void testGetPhoto() {
        Photo test = new Photo("Test", new Date(), 0.0, 0.0);
        Assert.assertEquals("Photo creation failed", 200, photoController.createPhoto(test).getStatus());
        Assert.assertEquals("Get all photos failed", 200, photoController.getAllPhoto().getStatus());
        List<LitePhoto> photos = (List<LitePhoto>) photoController.getAllPhoto().getEntity();
        Assert.assertEquals("Photo missing in the database / database didn't reset'", 1, photos.size());

        Assert.assertEquals("We can find a non existing photo", 404, photoController.findPhoto((long) 6448949).getStatus());
        Assert.assertEquals("We can find an photo with an invalid id", 400, photoController.findPhoto((long) -1).getStatus());
        Assert.assertEquals("We can't find the photo we added", photos.get(0).toString(), (new LitePhoto((Photo) photoController.findPhoto(photos.get(0).getId()).getEntity())).toString());
    }

    @Test
    public void testUpdatePhoto() {
        Photo test = new Photo("Test", new Date(), 0.0, 0.0);
        test = (Photo) photoController.createPhoto(test).getEntity();
        Photo updated = new Photo("TestUpdated", new Date(), 1.1, 1.1);
        Assert.assertEquals("We can update a photo with an invalid id", 400, photoController.updatePhoto((long) -1, updated).getStatus());
        Assert.assertEquals("We can update a non existing account", 404, photoController.updatePhoto((long) 6448949, updated).getStatus());
        Assert.assertEquals("We can't update an existing account", 200, photoController.updatePhoto(test.getId(), updated).getStatus());
        Assert.assertEquals("The update failed", updated.toString(), photoController.findPhoto(test.getId()).getEntity().toString());
    }

    @Test
    public void testDeleteAccount() {
        Photo test = new Photo("Test", new Date(), 0.0, 0.0);
        test = (Photo) photoController.createPhoto(test).getEntity();
        Assert.assertEquals("We can delete a photo with an invalid id", 400, photoController.deletePhoto((long) -1).getStatus());
        Assert.assertEquals("Delete system not working", 204, photoController.deletePhoto(test.getId()).getStatus());
        Assert.assertEquals("The deleted element is still in the db", 0, ((List<Account>) photoController.getAllPhoto().getEntity()).size());
    }
}
