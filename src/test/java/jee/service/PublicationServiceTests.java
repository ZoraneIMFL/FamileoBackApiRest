package jee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Photo;
import jee.model.Publication;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Properties;

public class PublicationServiceTests extends TestCase {
    @EJB
    private PublicationService ps;

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
        Publication p = new Publication("Test", new Date(), 0.0, 0.0, null, null);
        p = ps.createPublication(p);
        Assert.assertNotNull("Publication creation failed", p);
        Assert.assertNotNull("Publication creation failed", ps.findPublication(p.getId()));
        Assert.assertNotEquals("At least one publication should be present in the database", ps.getAllPublication().size(), 0);
    }

    @Test
    public void testPublicationUpdate() {
        Publication p = new Publication("Test", new Date(), 0.0, 0.0, null, null);
        p = ps.createPublication(p);
        p.setDescription("Test2");
        p.setPublishDate(new Date());
        p.setLatitude(1.1);
        p.setLongitude(1.1);
        p.setProfile(null);
        p.setAccount(null);
        Publication newP = ps.updatePublication(p);
        Assert.assertEquals("Update publication failed", newP.getDescription(), ps.findPublication(p.getId()).getDescription());
        Assert.assertEquals("Update publication failed", newP.getPublishDate(), ps.findPublication(p.getId()).getPublishDate());
        Assert.assertEquals("Update publication failed", newP.getLatitude(), ps.findPublication(p.getId()).getLatitude(), 0.1);
        Assert.assertEquals("Update publication failed", newP.getLongitude(), ps.findPublication(p.getId()).getLongitude(), 0.1);
        Assert.assertEquals("Update publication failed", newP.getProfile(), ps.findPublication(p.getId()).getProfile());
        Assert.assertEquals("Update publication failed", newP.getAccount(), ps.findPublication(p.getId()).getAccount());

        Assert.assertNull("Can update a non existant Publication", ps.updatePublication(new Publication()));
    }

    @Test
    public void testPublicationDelete() {
        Publication p = new Publication("Test", new Date(), 0.0, 0.0, null, null);
        p = ps.createPublication(p);
        Assert.assertNotNull("Publication creation failed", ps.findPublication(p.getId()));
        ps.deletePublication(p.getId());
        Assert.assertNull("Photo deletion failed", ps.findPublication(p.getId()));
    }
}
