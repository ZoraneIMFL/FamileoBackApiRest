package service;

import entity.Account;

import java.util.List;


public interface AccountManager {
    Account createAccount(Account newAccount);

    Account findAccount(long id);

    Account updateAccount(final Account account);

    List<Account> getAllAccount();

    void deleteAccount(final Long id);
}
