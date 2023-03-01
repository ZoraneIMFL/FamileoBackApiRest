package jee.controller;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Publication;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Properties;

public class PublicationControllerTests extends TestCase {
    @EJB
    private PublicationController publicationController;

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
    public void testGetPublication() {
        Publication test = new Publication("Test", new Date(), 0.0, 0.0, null, null);
        Assert.assertEquals("Publication creation failed", 200, publicationController.createPublication(test).getStatus());
        Assert.assertEquals("Get all Publication failed", 200, publicationController.getAllPublication().getStatus());
        List<Publication> pubs = (List<Publication>) publicationController.getAllPublication().getEntity();
        Assert.assertEquals("Publication missing in the database / database didn't reset'", 1, pubs.size());

        Assert.assertEquals("We can find a non existing publication", 404, publicationController.findPublication((long) 6448949).getStatus());
        Assert.assertEquals("We can find a publication with an invalid id", 400, publicationController.findPublication((long) -1).getStatus());
        Assert.assertEquals("We can't find the publication we added", pubs.get(0).toString(), publicationController.findPublication(pubs.get(0).getId()).getEntity().toString());
    }

    @Test
    public void testUpdatePublication() {
        Publication test = new Publication("Test", new Date(), 0.0, 0.0, null, null);
        test = (Publication) publicationController.createPublication(test).getEntity();
        Publication updatedPub = new Publication("TestUpdated", new Date(), 1.1, 1.1, null, null);
        Assert.assertEquals("We can update a publication with an invalid id", 400, publicationController.updatePublication((long) -1, updatedPub).getStatus());
        Assert.assertEquals("We can update a non existing publication", 404, publicationController.updatePublication((long) 6448949, updatedPub).getStatus());
        Assert.assertEquals("We can't update an existing publication", 200, publicationController.updatePublication(test.getId(), updatedPub).getStatus());
        Assert.assertEquals("The update failed", updatedPub.toString(), publicationController.findPublication(test.getId()).getEntity().toString());
    }

    @Test
    public void testDeletePublication() {
        Publication test = new Publication("Test", new Date(), 0.0, 0.0, null, null);
        test = (Publication) publicationController.createPublication(test).getEntity();
        Assert.assertEquals("We can delete a publication with an invalid id", 400, publicationController.deletePublication((long) -1).getStatus());
        Assert.assertEquals("Delete system not working", 204, publicationController.deletePublication(test.getId()).getStatus());
        Assert.assertEquals("The deleted element is still in the db", 0, ((List<Publication>) publicationController.getAllPublication().getEntity()).size());
    }
}
