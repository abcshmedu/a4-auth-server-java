package edu.hm.authentification.model;

/**
 * Represents an administrator in the user-hierarchy.
 */
public class Administrator extends Account {

    /**
     * Creates a new Administrator-account.
     *
     * @param credentials the credentials.
     */
    public Administrator(Credentials credentials) {
        super(credentials);
    }
}
