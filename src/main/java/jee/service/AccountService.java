package jee.service;

import jee.dao.AccountDao;
import jee.model.Account;
import security.PasswordEncryption;
import jee.validator.EmailValidator;
import jee.validator.PasswordValidator;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class AccountService {

    @Inject
    private AccountDao dao;

   public List<Account> getAllAccount() {
        return dao.findAllAccount();
    }
    //@Override
    public Account createAccount(final Account newAccount) {
        if (PasswordValidator.isValid(newAccount.getPassword()) && EmailValidator.isValid(newAccount.getEmail())){
            //LOGGER.log(Level.INFO, "Account Created");
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
        //LOGGER.log(Level.INFO, "Account Updated");
        return dao.update(account);
    }

    //@Override
    public void deleteAccount(final Long id) {
        //LOGGER.log(Level.INFO, "Account Deleted");
        dao.delete(Account.class, id);
    }
}