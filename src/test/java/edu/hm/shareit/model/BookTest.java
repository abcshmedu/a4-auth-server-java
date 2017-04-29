package edu.hm.shareit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    private static final String bookName = "Testbook";
    private static final String authorName = "Testauthor";
    private static final String isbn = "978-0-306-40615-7";

    private Book book;

    @Before
    public void setUp() {
        book = new Book(bookName, authorName, isbn);
    }

    @Test
    public void getAuthor() throws Exception {
        assertEquals(authorName, book.getAuthor());
    }

    @Test
    public void setAuthor() throws Exception {
        book.setAuthor("Testauthor2");
        assertEquals("Testauthor2", book.getAuthor());
    }

    @Test
    public void isValidBook() throws Exception {
        assertTrue(book.isValidBook());
        book.setAuthor("");
        assertFalse(book.isValidBook());
    }

    @Test
    public void getIsbn() throws Exception {
        assertEquals(isbn, book.getIsbn());
    }

    @Test
    public void equals() throws Exception {
        Book book2 = new Book("SomeTitle", "SomeAuthor", isbn);
        assertEquals(book, book2);

        // Even tho they share the same name, books should be differend
        // because ISBNs are not equal
        book2 = new Book(bookName, authorName, "123");
        assertNotEquals(book, book2);
    }

    @Test
    public void updateBook() throws Exception {
        Book book2 = new Book("SomeTitle", "SomeAuthor", isbn);
        book.updateBook(book2);
        assertEquals(book2.getTitle(), book.getTitle());
        assertEquals(book2.getAuthor(), book.getAuthor());
        assertEquals(book2.getIsbn(), book.getIsbn());
    }
}