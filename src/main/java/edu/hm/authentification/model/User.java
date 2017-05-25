package edu.hm.authentification.model;

/**
 * Represents an (simple) user in the user-hierarchy.
 */
public class User extends Account {

    public User(Credentials credentials) {
        super(credentials);
    }
}
