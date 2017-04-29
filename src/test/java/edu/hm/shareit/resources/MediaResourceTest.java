package edu.hm.shareit.resources;

import edu.hm.shareit.business.MediaService;
import edu.hm.shareit.business.MediaServiceImpl;
import edu.hm.shareit.business.MediaServiceResult;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MediaResourceTest {

    private static MediaService mediaServiceTest;

    @Before
    public void setUp() {
        mediaServiceTest = new MediaServiceImpl();
    }

    @Test
    public void addBook() {
        Book b;
        MediaServiceResult msr;

        // Add 1000 Books, should always return success
        for (int i = 0; i < 1000; i++) {
            b = new Book("Test", "Test", "TestIsbn");
            msr = mediaServiceTest.addBook(b);
            assertEquals(MediaServiceResult.SUCCESS, msr);
        }
        assertEquals(1000, mediaServiceTest.getBooks().length);

        // Test MediaServiceResult.ERROR
        b = null;
        msr = mediaServiceTest.addBook(b);
        assertEquals(MediaServiceResult.ERROR, msr);

        // Test MediaServiceResult.MEDIUM_MISSING_TITLE
        b = new Book("", "Test", "TestIsbn");
        msr = mediaServiceTest.addBook(b);
        assertEquals(MediaServiceResult.MEDIUM_MISSING_TITLE, msr);

        // Test MediaServiceResult.BOOK_MISSING_AUTHOR
        b = new Book("Test", "", "TestIsbn");
        msr = mediaServiceTest.addBook(b);
        assertEquals(MediaServiceResult.BOOK_MISSING_AUHTOR, msr);

        // Test MediaServiceResult.BOOK_INVALID_ISBN
        b = new Book("Test", "Test", "0");
        msr = mediaServiceTest.addBook(b);
        assertEquals(MediaServiceResult.BOOK_INVALID_ISBN, msr);

        // No book was added
        assertEquals(1000, mediaServiceTest.getBooks().length);
    }

    @Test
    public void addDisc() {
        Disc d;
        MediaServiceResult msr;

        // Add 1000 discs, should always return success
        for (int i = 0; i < 1000; i++) {
            d = new Disc("Test", "Test", "Test", 0);
            msr = mediaServiceTest.addDisc(d);
            assertEquals(MediaServiceResult.SUCCESS, msr);
        }
        assertEquals(1000, mediaServiceTest.getDiscs().length);

        // Test MediaServiceResult.ERROR
        d = null;
        msr = mediaServiceTest.addDisc(d);
        assertEquals(MediaServiceResult.ERROR, msr);

        // Test MediaServiceResult.MEDIUM_MISSING_TITLE
        d = new Disc("", "Test", "Test", 0);
        msr = mediaServiceTest.addDisc(d);
        assertEquals(MediaServiceResult.MEDIUM_MISSING_TITLE, msr);

        // Test MediaServiceResult.DISC_INVALID_BARCODE
        d = new Disc("Test", "0", "Test", 0);
        msr = mediaServiceTest.addDisc(d);
        assertEquals(MediaServiceResult.DISC_INVALID_BARCODE, msr);

        // Test MediaServiceResult.DISC_INVALID_DIRECTOR
        d = new Disc("Test", "Test", "", 0);
        msr = mediaServiceTest.addDisc(d);
        assertEquals(MediaServiceResult.DISC_INVALID_DIRECTOR, msr);

        // Test MediaServiceResult.DISC_INVALID_FSK
        d = new Disc("Test", "Test", "Test", -1);
        msr = mediaServiceTest.addDisc(d);
        assertEquals(MediaServiceResult.DISC_INVALID_FSK, msr);

        // No disc was added
        assertEquals(1000, mediaServiceTest.getDiscs().length);
    }

    @Test
    public void getBooks() {
        assertEquals(0, mediaServiceTest.getBooks().length);

        Book b = new Book("Test", "Test", "Test");
        mediaServiceTest.addBook(b);

        assertEquals(1, mediaServiceTest.getBooks().length);
    }

    @Test
    public void getDiscs() {
        assertEquals(0, mediaServiceTest.getDiscs().length);

        Disc d = new Disc("Test", "Test", "Test", 0);
        mediaServiceTest.addDisc(d);

        assertEquals(1, mediaServiceTest.getDiscs().length);
    }

    @Test
    public void getBook() {
        Book b = new Book("Test", "Test", "Test");

        mediaServiceTest.addBook(b);
        Medium m = mediaServiceTest.getBook("Test");
        assertEquals(b, m);
        assertEquals(m, b); // transitivity

        m = mediaServiceTest.getBook("Test2");
        assertNull(m);
    }

    @Test
    public void getDisc() {
        Disc d = new Disc("Test", "Test", "Test", 0);

        mediaServiceTest.addDisc(d);
        Medium m = mediaServiceTest.getDisc("Test");
        assertEquals(d, m);
        assertEquals(m, d); // transitivity

        m = mediaServiceTest.getDisc("Test2");
        assertNull(m);
    }

    @Test
    public void updateBook() {
        Book b = new Book("Test", "Test", "Test");
        mediaServiceTest.addBook(b);

        MediaServiceResult msr;

        // Not Found
        Book u = new Book("Update", "Update", "Test2");
        msr = mediaServiceTest.updateBook(u);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);

        // Invalid Update-Information
        u = new Book("Update", "", "Test");
        msr = mediaServiceTest.updateBook(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);
        u = new Book("", "Update", "Test");
        msr = mediaServiceTest.updateBook(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        // Success
        u = new Book("Update", "Update", "Test");
        msr = mediaServiceTest.updateBook(u);
        assertEquals(MediaServiceResult.SUCCESS, msr);

        // Confirm update
        b = (Book) mediaServiceTest.getBook("Test");
        assertEquals("Update", b.getTitle());
        assertEquals("Update", b.getAuthor());
        assertEquals("Test", b.getIsbn());
    }

    @Test
    public void updateDisc() {
        Disc d = new Disc("Test", "Test", "Test", 0);
        mediaServiceTest.addDisc(d);

        MediaServiceResult msr;

        // Not Found
        Disc u = new Disc("Update", "Update", "Test2", 0);
        msr = mediaServiceTest.updateDisc(u);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);

        // Invalid Update-Information
        u = new Disc("Update", "", "Test", 0);
        msr = mediaServiceTest.updateDisc(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);
        u = new Disc("", "Update", "Test", 0);
        msr = mediaServiceTest.updateDisc(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        // Success
        u = new Disc("Update", "Test", "Update", 5);
        msr = mediaServiceTest.updateDisc(u);
        assertEquals(MediaServiceResult.SUCCESS, msr);

        // Confirm update
        d = (Disc) mediaServiceTest.getDisc("Test");
        assertEquals("Update", d.getTitle());
        assertEquals("Test", d.getBarcode());
        assertEquals("Update", d.getDirector());
        assertEquals(5, d.getFsk());
    }
}