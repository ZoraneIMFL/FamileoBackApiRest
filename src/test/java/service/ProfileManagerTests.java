package service;

import junit.framework.TestCase;
import org.junit.Assert;
import persistable.Profile;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.util.HexFormat;
import java.util.Properties;

public class ProfileManagerTests extends TestCase {
    @EJB(name="ProfileManager")
    private ProfileManager profileManager;
    protected  static EJBContainer ejbContainer;

    public void setUp() throws Exception {
        Properties p = new Properties();
        p.put("testDB", "new://Resource?type=DataSource");
        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);
    }

    public void tearDown() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }

    public void test(){
        System.out.println("Creation d'un profile avec password");
        byte[] pdp = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");
        Profile profile = profileManager.createProfile(1,"test","S*fdflip59",1,pdp);
        Assert.assertNotNull(profile);
        System.out.println("Creation d'un compte sans password");

    }
}