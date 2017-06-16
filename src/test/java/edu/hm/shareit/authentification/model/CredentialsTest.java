package edu.hm.shareit.authentification.model;

import edu.hm.authentification.model.Credentials;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CredentialsTest {

    @Test
    public void equals() throws Exception {
        Credentials c = new Credentials("Name", "Password");
        Credentials v = new Credentials("Name", "Password");
        Credentials x = new Credentials("Name", "Password");

        // Reflexivity
        assertEquals(c, c);

        // Symmetry
        assertEquals(c, v);
        assertEquals(v, c);

        // Transitivity
        assertEquals(c, v);
        assertEquals(v, x);
        assertEquals(c, x);

        // Null
        assertNotEquals(c, null);

        // No matching password
        v = new Credentials("Name", "Password1");
        assertNotEquals(c, v);

        // No matching username
        v = new Credentials("Name1", "Password");
        assertNotEquals(c, v);
    }

    @Test
    public void testHashCode() throws Exception {
        Credentials c = new Credentials("Name", "Password");

        int i = "Name".hashCode();
        i = 31 * i + "Password".hashCode();

        assertEquals(i, c.hashCode());
    }

}