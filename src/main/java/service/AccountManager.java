package service;

import entity.Account;

import java.util.List;

public interface AccountManager  {

    Account createAccount(final Account newAccount);

    Account updateAccount(final Account account);

    Account findAccount(final long id);

    List<Account> getAllAccount();

    void deleteAccount(final Long id);
}
