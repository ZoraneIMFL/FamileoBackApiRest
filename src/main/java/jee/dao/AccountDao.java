package jee.dao;

import jee.model.Account;

import java.util.List;

public class AccountDao extends DAO<Account> implements CRUD<Account>{

    public List<Account> findAllAccount() {
        return em.createQuery("from Account", Account.class).getResultList();
    }


}