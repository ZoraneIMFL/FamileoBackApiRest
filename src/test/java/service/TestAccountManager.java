package service;

import entity.Account;
import junit.framework.TestCase;
import org.junit.*;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.util.Properties;

public class TestAccountManager extends TestCase {
    @EJB(name = "AccountManager")
    private AccountManager accountManager;

    private Account validAcc = new Account("DupontFamily", "dupont@gmail.com", "S*fdflip59", 0);
    private Account invalidAcc =new Account("DupontFamily", "dupontgmail.com", "mdpTropSimple", 0);

    protected static EJBContainer ejbContainer;

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

    @Test
    public void testAccountCreation(){
        System.out.println("CREATION D'UN ACCOUNT VALIDE");
        Account acc = accountManager.createAccount(validAcc);
        Assert.assertNotNull(acc);
        System.out.println("CREATION D'UN ACCOUNT INVALIDE DONC PAS D'INSERT");
        Account acc1 =accountManager.createAccount(invalidAcc);
        Assert.assertNull(acc1);

        Assert.assertEquals(1, acc.getId());
        Assert.assertEquals("DupontFamily", acc.getName());
        System.out.println("RECHERCHE D'UN ACCOUNT");
        acc = accountManager.findAccount(1);
        System.out.println(acc);
        Assert.assertEquals("dupont@gmail.com", acc.getEmail());
        Assert.assertNull(accountManager.findAccount(2));
    }
}
