package service;

import persistable.Account;
import validator.EmailValidator;
import validator.PasswordValidator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AccountManagerBean implements AccountManager{

    @PersistenceContext
    EntityManager em;

    private Account acc = null;


    @Override
    public Account createAccount(String name, String email, String password, int status) {
        if (PasswordValidator.isValid(password) && EmailValidator.isValid(email) ){

            acc = new Account(name, email, password, status);
            em.persist(acc);
            return acc;
        }
        else{
            System.out.println("INVALID FIELDS");
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
