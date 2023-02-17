package service;

import dao.AccountDao;
import entity.Account;
import security.PasswordEncryption;
import validator.EmailValidator;
import validator.PasswordValidator;

import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;
import jakarta.ejb.embeddable.EJBContainer;import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Bean manage the accounts system of the application
 */
@Stateless
public class AccountManagerBean /*implements AccountManager*/{
    private static final Logger LOGGER = Logger.getLogger(AccountManagerBean.class.getName());

    @EJB
    private AccountDao dao;

    //@Override
    public List<Account> getAllAccount() {
        return dao.findAllAccount();
    }
    //@Override
    public Account createAccount(final Account newAccount) {
        if (PasswordValidator.isValid(newAccount.getPassword()) && EmailValidator.isValid(newAccount.getEmail())){
            LOGGER.log(Level.INFO, "Account Created");
            newAccount.setSalt(PasswordEncryption.generateSalt());
            newAccount.setPassword(PasswordEncryption.encryptPassword(newAccount.getPassword(), newAccount.getSalt()));
            return dao.create(newAccount);
        }
        return null;
    }

    //@Override
    public Account findAccount(final long id) {
        return dao.read(Account.class, id);
    }

    //@Override
    public Account updateAccount(final Account account) {
        final Account oldAccount = findAccount(account.getId());
        if (oldAccount == null) {
            return null;
        }
        account.setId(oldAccount.getId());
        LOGGER.log(Level.INFO, "Account Updated");
        return dao.update(account);
    }

    //@Override
    public void deleteAccount(final Long id) {
        LOGGER.log(Level.INFO, "Account Deleted");
        dao.delete(Account.class, id);
    }
}
