package jee.service;

import jee.dao.AccountDao;
import jee.model.Account;
import jee.validator.EmailValidator;
import jee.validator.PasswordValidator;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class AccountService {

    @Inject
    private AccountDao dao;

    public List<Account> getAllAccount() {
        return dao.findAllAccount();
    }

    public Account createAccount(final Account newAccount) {
        System.out.println(newAccount);
        if (newAccount.isValidAccount()) {
            System.out.println("Is valid");
            return dao.create(newAccount);
        }
        return null;
    }

    public Account findAccount(final long id) {
        return dao.read(Account.class, id);
    }

    public Account updateAccount(final Account account) {
        final Account oldAccount = findAccount(account.getId());
        if (oldAccount == null) {
            return null;
        }
        account.setId(oldAccount.getId());
        //LOGGER.log(Level.INFO, "Account Updated");
        return dao.update(account);
    }

    public void deleteAccount(final Long id) {
        //LOGGER.log(Level.INFO, "Account Deleted");
        dao.delete(Account.class, id);
    }
}