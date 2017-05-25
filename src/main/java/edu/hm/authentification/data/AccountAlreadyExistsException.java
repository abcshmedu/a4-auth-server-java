package edu.hm.authentification.data;

/**
 * Exception to throw when a conflict occurs due to a user already existing.
 */
class AccountAlreadyExistsException extends Exception {

    /**
     * When conflict occurs.
     */
    AccountAlreadyExistsException() {
    }
}
