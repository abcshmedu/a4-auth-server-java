package edu.hm.shareit.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CopyTest {

    private final Book book = new Book("Test", "Test", "0000");
    private final Copy copy = new Copy(book, "Tester");

    @Test
    public void getMedium() throws Exception {
        assertEquals(book, copy.getMedium());
    }

    @Test
    public void getOwner() throws Exception {
        assertEquals("Tester", copy.getOwner());
    }

    @Test
    public void equals() throws Exception {
        // Same object
        assertTrue(copy.equals(copy));

        // False because book does not equal book2
        Book book2 = new Book("InvTest", "Test", "0001");
        Copy copy2 = new Copy(book2, "Tester");
        assertFalse(copy.equals(copy2));

        // False because owners are not equal
        copy2 = new Copy(book, "InvalidTester");
        assertFalse(copy.equals(copy2));

        // True
        book2 = new Book("Test", "Test", "0000");
        copy2 = new Copy(book2, "Tester");
        assertEquals(copy2, copy);

        // Null
        assertFalse(copy.equals(null));

        // Not instance of Copy
        assertFalse(copy.equals(new Book()));
    }

    @Test
    public void testHashcode() throws Exception {
        int i = copy.getMedium().hashCode();
        i = 31 * i + "Tester".hashCode();

        assertEquals(i, copy.hashCode());
    }
}