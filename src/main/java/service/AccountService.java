package service;

import dao.AccountDao;
import entity.Account;
import security.PasswordEncryption;
import validator.EmailValidator;
import validator.PasswordValidator;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class AccountService {

    @EJB
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
}
