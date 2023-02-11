package service;

import entity.Account;
import dao.AccountDao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class AccountManagerBean implements AccountManager{

    @Inject
    private AccountDao dao;

    @Override
    public List<Account> getAllAccount() {
        return dao.findAllAccount();
    }
    @Override
    public Account createAccount(final Account newAccount) {
        return dao.create(newAccount);
    }

    @Override
    public Account findAccount(final long id) {
        return dao.read(Account.class, id);
    }

    @Override
    public Account updateAccount(final Account account) {
        final Account oldAccount = findAccount(account.getId());
        if (oldAccount == null) {
            return null;
        }
        account.setId(oldAccount.getId());
        return dao.update(account);
    }

    @Override
    public void deleteAccount(final Long id) {
        dao.delete(Account.class, id);
    }










}
