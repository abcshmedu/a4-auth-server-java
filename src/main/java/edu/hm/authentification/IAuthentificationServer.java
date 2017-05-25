package edu.hm.authentification;

import edu.hm.authentification.model.Credentials;
import edu.hm.authentification.model.Token;

/**
 * Authentification Server.
 */
public interface IAuthentificationServer {

    /**
     * Logs in a user.
     *
     * @param credentials user-credentials
     * @return AuthentificationResult.SUCCESS if login was successful,
     * AuthentificationResult.USER_INVALID_CREDENTIALS if credentials (e.g. password) was wrong,
     * AuthentificationResult.USER_NOT_FOUND if user was not found.
     */
    AuthentificationResult login(Credentials credentials);

    /**
     * Logs out a user.
     *
     * @param token the token to invalidate/logout.
     * @return AuthentificationResult.SUCCESS if logout was successful,
     * AuthentificationResult.USER_INVALID_TOKEN if supplied token was invalid.
     */
    AuthentificationResult logout(Token token);

    /**
     * Used by a client (app) to verify/validate a token.
     *
     * @param clientId    the client/app requesting validation.
     * @param tokenString the token to validate.
     * @return AuthentificationResult.SUCCESS if token is valid,
     * AuthentificationResult.USER_INVALID_TOKEN if token was not found,
     * AuthentificationResult.USER_TOKEN_EXPIRED if token is expired,
     * AuthentificationResult.UNKNOWN_CLIENT if requesting app is not recognized.
     */
    AuthentificationResult validateToken(String clientId, String tokenString);

}
