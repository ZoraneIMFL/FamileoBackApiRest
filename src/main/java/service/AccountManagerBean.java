package service;

import persistable.Account;
import validator.EmailValidator;
import validator.PasswordValidator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This Bean manage the accounts system of the application
 */
@Stateless
public class AccountManagerBean implements AccountManager{

    private static final Logger LOGGER = Logger.getLogger(AccountManagerBean.class.getName());

    @PersistenceContext
    EntityManager em;

    @Override
    public Account createAccount(String name, String email, String password, int status) {
        if (PasswordValidator.isValid(password) && EmailValidator.isValid(email) ){
            var acc = new Account(name, email, password, status);
            em.persist(acc);
            return acc;
        }
        else{
            LOGGER.log(Level.WARNING, "INVALID FIELDS");
        }

        return null;
    }

    @Override
    public void updateStatus(int idAccount, int status) {
        Account managedAcc = em.find(Account.class, idAccount);
        managedAcc.setStatus(status);
    }

    @Override
    public Account findByPrimaryKey(int idAccount) {
        return em.find(Account.class, idAccount);
    }
}
