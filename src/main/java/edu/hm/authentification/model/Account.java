package edu.hm.authentification.model;

/**
 * Base-class for an user-account.
 */
public abstract class Account {

    private final Credentials credentials;

    /**
     * Creates a new account with supploed credentials.
     *
     * @param credentials of the account.
     */
    Account(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Getter for the account-credentials.
     *
     * @return the credentials.
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Two accounts are equal when they have matching credentials.
     *
     * @param o other object.
     * @return true if they are equal, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;

        return credentials != null ? credentials.equals(account.credentials) : account.credentials == null;
    }

    @Override
    public int hashCode() {
        return credentials != null ? credentials.hashCode() : 0;
    }
}
