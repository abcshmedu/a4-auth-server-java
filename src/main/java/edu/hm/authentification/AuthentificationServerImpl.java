package edu.hm.authentification;

import edu.hm.authentification.data.IAccountAccess;
import edu.hm.authentification.model.Account;
import edu.hm.authentification.model.Credentials;
import edu.hm.authentification.model.Token;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation for the authentification Server.
 */
public class AuthentificationServerImpl implements IAuthentificationServer {

    private static final String CLIENT_ID = "shareit-super-token";

    private HashMap<Token, Account> userTokens = new HashMap<>();

    private IAccountAccess accountAccess;

    /**
     * Creates a new AuthentificationServer with the supplied data-backend.
     *
     * @param accountAccess the data-backend.
     */
    public AuthentificationServerImpl(IAccountAccess accountAccess) {
        this.accountAccess = accountAccess;
    }

    /**
     * {@inheritDoc}
     */
    public AuthentificationResult login(Credentials credentials) {
        try {
            Account acc = accountAccess.getAccountByName(credentials.getName());

            if (acc.getCredentials().getPassword().equals(credentials.getPassword())) {
                userTokens.put(Token.getToken(), acc);
                return AuthentificationResult.SUCCESS;
            } else {
                return AuthentificationResult.USER_INVALID_CREDENTIALS;
            }
        } catch (AccountNotFoundException ex) {
            return AuthentificationResult.USER_NOT_FOUND;
        }
    }

    /**
     * {@inheritDoc}
     */
    public AuthentificationResult logout(Token token) {
        if (userTokens.remove(token) != null) {
            return AuthentificationResult.SUCCESS;
        }

        return AuthentificationResult.USER_INVALID_TOKEN;
    }

    /**
     * {@inheritDoc}
     */
    public AuthentificationResult validateToken(String clientId, String tokenString) {
        if (CLIENT_ID.equals(clientId)) {
            Token dbt = userTokens.keySet()
                    .stream()
                    .filter(t -> t.getTokenString().equals(tokenString))
                    .findFirst()
                    .orElse(null);

            if (dbt == null) {
                return AuthentificationResult.USER_INVALID_TOKEN;
            } else if (!dbt.hasExpired()) {
                dbt.resetTimer();
                return AuthentificationResult.SUCCESS;
            } else {
                userTokens.remove(dbt);
                return AuthentificationResult.USER_TOKEN_EXPIRED;
            }
        } else {
            return AuthentificationResult.UNKNOWN_CLIENT;
        }
    }

    /**
     * Returns the first token for these credentials.
     * <p>
     * This is potentially broken because there could be multiple sessions
     * with the same credentials.
     *
     * @param credentials for the token.
     * @return the token.
     */
    public Token getTokenFromCredentials(Credentials credentials) {
        return userTokens
                .entrySet()
                .stream()
                .filter(t -> t.getValue().getCredentials().equals(credentials))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
