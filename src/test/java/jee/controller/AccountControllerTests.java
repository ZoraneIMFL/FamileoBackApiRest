package jee.controller;

import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;
import jakarta.ws.rs.core.Response;
import jee.model.Account;
import jee.model.Profile;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Properties;

public class AccountControllerTests extends TestCase {

    @EJB
    private AccountController accountController;

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
    public void testGetAccount() {
        Account test = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        Assert.assertEquals("Account creation failed", 200, accountController.createAccount(test).getStatus());
        Assert.assertEquals("Get all accounts failed", 200, accountController.getAllAccount().getStatus());
        List<Account> accs = (List<Account>) accountController.getAllAccount().getEntity();
        Assert.assertEquals("Account missing in the database / database didn't reset'", 1, accs.size());

        Assert.assertEquals("We can find a non existing account", 404, accountController.findAccount((long) 6448949).getStatus());
        Assert.assertEquals("We can find an account with an invalid id", 400, accountController.findAccount((long) -1).getStatus());
        Assert.assertEquals("We can't find the account we added", accs.get(0).toString(), accountController.findAccount(accs.get(0).getId()).getEntity().toString());

        Assert.assertEquals("We can find profiles of a non existing account", 404, accountController.findProfilesByAccountId((long) 6448949).getStatus());
        Assert.assertEquals("We can find profiles of an account with an invalid id", 400, accountController.findProfilesByAccountId((long) -1).getStatus());
        Assert.assertEquals("Profiles of account is null", 0, ((List<Profile>) accountController.findProfilesByAccountId(accs.get(0).getId()).getEntity()).size());
    }

    @Test
    public void testUpdateAccount() {
        Account test = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        test = (Account) accountController.createAccount(test).getEntity();
        Account updatedAccount = new Account("Updated", "updated@gmail.com", "TestPswd45!", 0);
        Assert.assertEquals("We can update an account with an invalid id", 400, accountController.updateAccount((long) -1, updatedAccount).getStatus());
        Assert.assertEquals("We can update a non existing account", 404, accountController.updateAccount((long) 6448949, updatedAccount).getStatus());
        Assert.assertEquals("We can't update an existing account", 200, accountController.updateAccount(test.getId(), updatedAccount).getStatus());
        Assert.assertEquals("The update failed", updatedAccount.toString(), accountController.findAccount(test.getId()).getEntity().toString());
    }

    @Test
    public void testDeleteAccount() {
        Account test = new Account("Alice", "alice@gmail.com", "TestPswd45!", 0);
        test = (Account) accountController.createAccount(test).getEntity();
        Assert.assertEquals("We can delete an account with an invalid id", 400, accountController.deleteAccount((long) -1).getStatus());
        Assert.assertEquals("Delete system not working", 204, accountController.deleteAccount(test.getId()).getStatus());
        Assert.assertEquals("The deleted element is still in the db", 0, ((List<Account>) accountController.getAllAccount().getEntity()).size());
    }


}
