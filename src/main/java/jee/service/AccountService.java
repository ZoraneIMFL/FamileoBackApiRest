package jee.service;

import jee.dao.AccountDao;
import jee.model.Account;
import security.PasswordEncryption;
import jee.validator.EmailValidator;
import jee.validator.PasswordValidator;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class AccountService {

    @Inject
    private AccountDao dao;

   public List<Account> getAllAccount() {
        return dao.findAllAccount();
    }
    public Account createAccount(final Account newAccount) {
        if (PasswordValidator.isValid(newAccount.getPassword()) && EmailValidator.isValid(newAccount.getEmail())){
            newAccount.setSalt(PasswordEncryption.generateSalt());
            newAccount.setPassword(PasswordEncryption.encryptPassword(newAccount.getPassword(), newAccount.getSalt()));
            return dao.create(newAccount);
        }
        return null;
    }

    public Account findAccount(final long id) {
        return dao.read(Account.class, id);
    }

    public Account updateAccount(final Account account) {
        final var oldAccount = findAccount(account.getId());
        if (oldAccount == null) {
            return null;
        }
        account.setId(oldAccount.getId());
        return dao.update(account);
    }

    public void deleteAccount(final Long id) {
        dao.delete(Account.class, id);
    }
}