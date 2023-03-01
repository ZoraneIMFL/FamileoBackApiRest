package jee.service;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jee.model.Account;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

public class AccountServiceTests extends TestCase {
    @EJB
    private AccountService as;

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
    public void testAccountCreation() {
        Account test = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        Assert.assertTrue("Incorrect email or password", test.isValidAccount());
        test = as.createAccount(test);
        Assert.assertNotNull("Account creation failed", test);
        Assert.assertNotNull("Account creation failed", as.findAccount(test.getId()));
        Account fetch = as.findAccount(test.getId());
        Assert.assertEquals(fetch.getName(), "Alice");
        Assert.assertEquals(fetch.getStatus(), 0);

        test = new Account("Alice", "alice@gmail.com", "TestPswd", 0);
        test = as.createAccount(test);
        Assert.assertNull("An Account can be created with an invalid password", test);

        test = new Account("Alice", "", "TestPswd45!", 0);
        test = as.createAccount(test);
        Assert.assertNull("An Account can be created with an invalid email", test);

        Assert.assertNotEquals("At least one account should be present in the database", 0, as.getAllAccount().size());
    }

    @Test
    public void testUpdateAccount() {
        Account test = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        test = as.createAccount(test);
        test.setName("Tom");
        test.setEmail("tom@gmail.com");
        test.setStatus(1);
        String oldPass = test.getPassword();
        test.setPassword("NewPasswd41!");
        test = as.updateAccount(test);

        Account res = as.findAccount(test.getId());
        Assert.assertEquals("Update failed", res.toString(), test.toString());
        Assert.assertNotEquals("Password not updated", oldPass, test.getPassword());
        //An account that doesn't exist shouldn't be updated
        Assert.assertNull(as.updateAccount(new Account()));
    }

    @Test
    public void testDeleteAccount() {
        Account test = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        test = as.createAccount(test);
        as.deleteAccount(test.getId());

        Assert.assertNotEquals("Delete failed", as.findAccount(test.getId()), test);
    }

}
