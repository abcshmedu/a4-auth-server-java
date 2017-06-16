package edu.hm.shareit.authentification.model;

import edu.hm.authentification.model.Token;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TokenTest {

    /* Two token are equal when their tokenStrings match */
    @Test
    public void equals() throws Exception {
        // Reflexivity
        Token t = Token.getToken();
        assertEquals(t, t);

        // Symmetry
        Token v = Token.getToken();
        setTokenString(v, t.getTokenString());
        assertEquals(v, t);
        assertEquals(t, v);

        // Transivity
        Token x = Token.getToken();
        setTokenString(x, v.getTokenString());
        assertEquals(t, v);
        assertEquals(v, x);
        assertEquals(t, x);

        // Null
        assertNotEquals(t, null);

        // Not equal, no matching tokenStrings
        setTokenString(v, t.getTokenString() + "test");
        assertNotEquals(v, t);
    }

    private void setTokenString(Token t, String s) throws IllegalAccessException {
        Class c = t.getClass();

        for (Field f : c.getDeclaredFields()) {
            f.setAccessible(true);

            if (f.getName().equals("tokenString")) {
                f.set(t, s);
            }
        }
    }
}