package jee.dao;

import jee.model.Account;

import java.util.List;

public class AccountDao extends DAO<Account> implements CRUD<Account>{

    public List<Account> findAllAccount() {
        List<Account> accounts = em.createQuery("from Account", Account.class).getResultList();
        System.out.println("Number of accounts retrieved: " + accounts.size());
        return accounts;
    }


}