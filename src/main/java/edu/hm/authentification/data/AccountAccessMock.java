package edu.hm.authentification.data;

import edu.hm.authentification.model.Account;
import edu.hm.authentification.model.Administrator;
import edu.hm.authentification.model.Credentials;
import edu.hm.authentification.model.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;

/**
 * Lazy-Singleton mock-implementation of AccountAccess.
 */
public final class AccountAccessMock implements IAccountAccess {

    private static AccountAccessMock instance;
    private HashMap<String, Account> accounts = new HashMap<>();

    /**
     * Creates the instance of this mock and adds some test-users to the map.
     */
    private AccountAccessMock() {
        accounts.put("Jack", new User(new Credentials("Jack", "Jack")));
        accounts.put("Judy", new Administrator(new Credentials("Judy", "Judy")));
    }

    /**
     * Lazy-initialization of this class.
     *
     * @return the instance.
     */
    public static AccountAccessMock getInstance() {
        if (instance == null) {
            instance = new AccountAccessMock();
        }

        return instance;
    }

    @Override
    public void addAccount(Account account) throws AccountAlreadyExistsException {
        if (accounts.keySet().contains(account.getCredentials().getName())) {
            throw new AccountAlreadyExistsException();
        }

        accounts.put(account.getCredentials().getName(), account);
    }

    @Override
    public void removeAccount(Account account) throws AccountNotFoundException {
        if (accounts.remove(account.getCredentials().getName()) != null) {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public Account getAccountByName(String name) throws AccountNotFoundException {
        Account acc = accounts.get(name);

        if (acc == null) {
            throw new AccountNotFoundException();
        }

        return acc;
    }
}
