import entity.Account;
import entity.Profile;
import service.AccountManager;
import service.ProfileManager;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.util.Properties;

public class TestProfileManager extends TestCase {

    @EJB(name="ProfileManager")
    private ProfileManager profileManager;

    @EJB(name = "AccountManager")
    private AccountManager accountManager;

    protected static EJBContainer ejbContainer;

    private Account acc = new Account("DupontFamily", "dupont@gmail.com", "S*fdflip59", 0);

    private Profile profile;

    private byte[] profileImage =  {0,1,0,0};

    public void setUp() throws Exception {
        Properties p = new Properties();
        p.put("testDB", "new://Resource?type=DataSource");
        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);
        acc = accountManager.createAccount(acc);
        this.profile = new Profile(acc,"FrançoisFather", "NotVerySecuredPassword", 0,profileImage );


    }
    public void tearDown() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }

    @Test
    public void testProfileCreation(){

        System.out.println("CREATION PROFILE");
        Profile p = profileManager.createProfile(profile);
        Assert.assertNotNull(p);
        Assert.assertEquals( "FrançoisFather", p.getName());
        Assert.assertEquals("NotVerySecuredPassword", p.getPassword());
        Assert.assertEquals(0 , p.getType());
        p = profileManager.findProfile(p.getId());
        Assert.assertEquals( "FrançoisFather", p.getName());
        Assert.assertEquals(0 , p.getType());
        profile.setType(1);
        profile.setName("JeanSon");
        p = profileManager.updateProfile(profile);
        Assert.assertEquals( "JeanSon", p.getName());
        Assert.assertEquals(1 , p.getType());

    }

}
