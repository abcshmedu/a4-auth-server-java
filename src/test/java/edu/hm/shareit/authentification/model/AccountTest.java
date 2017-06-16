package edu.hm.shareit.authentification.model;

import edu.hm.authentification.model.Account;
import edu.hm.authentification.model.Administrator;
import edu.hm.authentification.model.Credentials;
import edu.hm.authentification.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountTest {

    private Account acc = new User(new Credentials("Name", "Password"));
    private Account acc2 = new Administrator(new Credentials("Name", "Password"));

    @Test
    public void equals() throws Exception {
        // Equal when credentials are equal
        assertEquals(acc, acc2);

        // Null
        assertNotEquals(acc, null);

        // Reflexivity
        assertEquals(acc, acc);
    }

    @Test
    public void testHashCode() throws Exception {
        int i = acc.hashCode();

        // hashCode of Account is hash of its credentials
        assertEquals(i, new Credentials("Name", "Password").hashCode());
    }

}