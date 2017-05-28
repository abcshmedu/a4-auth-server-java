package edu.hm.shareit.authentification;

import edu.hm.authentification.AuthentificationResource;
import edu.hm.authentification.AuthentificationResult;
import edu.hm.authentification.model.Credentials;
import edu.hm.authentification.model.Token;
import edu.hm.shareit.business.MediaServiceResult;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.resources.MediaResource;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Calendar;

import static org.junit.Assert.*;

public class AuthentificationResourceTest {

    private static final String CLIENT_ID = "shareit-super-token";

    private AuthentificationResource ar = new AuthentificationResource();
    private Credentials jack = new Credentials("Jack", "Jack");
    private Credentials judy = new Credentials("Judy", "Judy");
    private Book book = new Book("TestTitle", "TestAuthor", "1234");

    @Test
    public void login() throws Exception {
        Response r;

        // Unknown user
        r = ar.login(new Credentials("Unknown", "Unknown"));
        assertEquals(AuthentificationResult.USER_NOT_FOUND.getStatusCode(), r.getStatus());

        // Wrong password
        r = ar.login(new Credentials("Jack", "Wrong"));
        assertEquals(AuthentificationResult.USER_INVALID_CREDENTIALS.getStatusCode(), r.getStatus());

        // Successful login
        r = ar.login(jack);
        assertEquals(AuthentificationResult.SUCCESS.getStatusCode(), r.getStatus());
        Token t = (Token)r.getEntity();

        // Assert conditions on valid token
        assertTrue(t.getTokenString().length() > 25);
        assertFalse(t.hasExpired());
    }

    @Test
    public void logout() throws Exception {
        // Logout invalid token
        Token t = Token.getToken();
        Response r = ar.logout(t);
        assertEquals(AuthentificationResult.USER_INVALID_TOKEN.getStatusCode(), r.getStatus());

        // Logout valid session
        r = ar.login(jack);
        t = (Token) r.getEntity();
        r = ar.logout(t);
        assertEquals(AuthentificationResult.SUCCESS.getStatusCode(), r.getStatus());
    }

    @Test
    public void validateToken() throws Exception {
        // Login to get valid token
        Response r = ar.login(jack);
        Token t = (Token) r.getEntity();
        r = ar.validateToken(CLIENT_ID, t.getTokenString());
        assertEquals(AuthentificationResult.SUCCESS.getStatusCode(), r.getStatus());

        // Verify with invalid client-id
        r = ar.validateToken("InvalidId", t.getTokenString());
        assertEquals(AuthentificationResult.UNKNOWN_CLIENT.getStatusCode(), r.getStatus());

        // Verify with invalid token
        t = Token.getToken();
        r = ar.validateToken(CLIENT_ID, t.getTokenString());
        assertEquals(AuthentificationResult.USER_INVALID_TOKEN.getStatusCode(), r.getStatus());
    }
}