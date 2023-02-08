package service;

import persistable.Account;
import junit.framework.TestCase;
import org.junit.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.util.Properties;

public class AccountManagerTests extends TestCase {
    @EJB(name = "AccountManager")
    private AccountManager accountManager;

    protected static EJBContainer ejbContainer;

    public void setUp() throws Exception {
        Properties p = new Properties();
        p.put("projet", "new://Resource?type=DataSource");
        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);
    }

    public void tearDown() {
        if (ejbContainer != null) {
            ejbContainer.close();
        }
    }

    @Test
    public void test(){
        System.out.println("CREATION D'UN ACCOUNT VALIDE");
        Account acc = accountManager.createAccount("DupontFamily", "dupont@gmail.com", "S*fdflip59", 0);
        Assert.assertNotNull(acc);
        System.out.println("CREATION D'UN ACCOUNT INVALIDE DONC PAS D'INSERT");
        Account acc1 =accountManager.createAccount("DupontFamily", "dupontgmail.com", "Sfdflip59", 0);
        Assert.assertNull(acc1);

        Assert.assertEquals(1, acc.getId());
        Assert.assertEquals("DupontFamily", acc.getName());
        System.out.println("RECHERCHE D'UN ACCOUNT");
        acc = accountManager.findByPrimaryKey(1);
        System.out.println(acc);
        Assert.assertEquals("dupont@gmail.com", acc.getEmail());
    }
}
