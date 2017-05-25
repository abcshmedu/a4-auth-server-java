package edu.hm.authentification.data;

import edu.hm.authentification.model.Account;

import javax.security.auth.login.AccountNotFoundException;

/**
 * Data-backend for user-accounts (e.g. some persistent storage
 * keeping Accounts).
 */
public interface IAccountAccess {

    /**
     * Adds a new account to the database.
     *
     * @param account the account to add.
     * @throws AccountAlreadyExistsException if the account already exists.
     */
    void addAccount(Account account) throws AccountAlreadyExistsException;

    /**
     * Removes an account from the database.
     *
     * @param account the account to be removed.
     * @throws AccountNotFoundException if the account does not exist.
     */
    void removeAccount(Account account) throws AccountNotFoundException;

    /**
     * Returns an account with matching name.
     *
     * @param name the name to match.
     * @return the account.
     * @throws AccountNotFoundException if the account does not exist.
     */
    Account getAccountByName(String name) throws AccountNotFoundException;
}
