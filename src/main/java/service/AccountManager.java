package Service;

import Persistable.Account;

public interface AccountManager {

    Account createAccount(String name, String email, String password, int status);

    void updateStatus(int idAccount, int status);

    Account findByPrimaryKey(int idAccount);
}
