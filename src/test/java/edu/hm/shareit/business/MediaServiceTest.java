package edu.hm.shareit.business;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MediaServiceTest {

    private static MediaService mediaServiceTest;

    @Before
    public void setUp() {
        mediaServiceTest = new MediaServiceImpl();
    }

    @Test
    public void addBook() throws Exception {
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
    public void addDisc() throws Exception {
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
    public void getBooks() throws Exception {
        assertEquals(0, mediaServiceTest.getBooks().length);

        Book b = new Book("Test", "Test", "Test");
        mediaServiceTest.addBook(b);

        assertEquals(1, mediaServiceTest.getBooks().length);
    }

    @Test
    public void getDiscs() throws Exception {
        assertEquals(0, mediaServiceTest.getDiscs().length);

        Disc d = new Disc("Test", "Test", "Test", 0);
        mediaServiceTest.addDisc(d);

        assertEquals(1, mediaServiceTest.getDiscs().length);
    }

    @Test
    public void getBook() throws Exception {
        Book b = new Book("Test", "Test", "Test");

        mediaServiceTest.addBook(b);
        Medium m = mediaServiceTest.getBook("Test");
        assertEquals(b, m);
        assertEquals(m, b); // transitivity

        m = mediaServiceTest.getBook("Test2");
        assertNull(m);
    }

    @Test
    public void getDisc() throws Exception {
        Disc d = new Disc("Test", "Test", "Test", 0);

        mediaServiceTest.addDisc(d);
        Medium m = mediaServiceTest.getDisc("Test");
        assertEquals(d, m);
        assertEquals(m, d); // transitivity

        m = mediaServiceTest.getDisc("Test2");
        assertNull(m);
    }

    @Test
    public void updateBook() throws Exception {
        Book b = new Book("Test", "Test", "Test");
        mediaServiceTest.addBook(b);

        MediaServiceResult msr;

        // ERROR
        msr = MediaServiceResult.ERROR;
        assertEquals(msr, mediaServiceTest.updateBook(null, "Test"));

        // MEDIUM_ID_IMMUTABLE
        msr = MediaServiceResult.MEDIUM_ID_IMMUTABLE;
        Book c = new Book("Test", "Test", "Test123");
        assertEquals(msr, mediaServiceTest.updateBook(c, "Test"));

        // MEDIUM_NOT_FOUND
        Book u = new Book("Update", "", "");
        msr = mediaServiceTest.updateBook(u, "unknownIsbn");
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);

        // MEDIUM_INVALID_UPDATE_INFORMATION
        u = new Book("", "", "");
        msr = mediaServiceTest.updateBook(u, "Test");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        u = new Book("asd", "asd", "");
        msr = mediaServiceTest.updateBook(u, "Test");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        // SUCCESS
        u = new Book("Update", "Update", "");
        msr = mediaServiceTest.updateBook(u, "Test");
        assertEquals(MediaServiceResult.SUCCESS, msr);

        // Confirm update
        b = (Book) mediaServiceTest.getBook("Test");
        assertEquals("Update", b.getTitle());
        assertEquals("Update", b.getAuthor());
        assertEquals("Test", b.getIsbn());
    }

    @Test
    public void updateDisc() throws Exception {
        Disc d = new Disc("Test", "Test", "Test", 0);
        mediaServiceTest.addDisc(d);

        MediaServiceResult msr;

        // ERROR
        msr = MediaServiceResult.ERROR;
        assertEquals(msr, mediaServiceTest.updateDisc(null, "Test"));

        // MEDIUM_ID_IMMUTABLE
        msr = MediaServiceResult.MEDIUM_ID_IMMUTABLE;
        Disc c = new Disc("Test", "Test123", "Test", 0);
        assertEquals(msr, mediaServiceTest.updateDisc(c, "Test"));

        // MEDIUM_NOT_FOUND
        Disc u = new Disc("Update", "", "", 0);
        msr = mediaServiceTest.updateDisc(u, "unknownBarcode");
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);

        // MEDIUM_INVALID_UPDATE_INFORMATION
        u = new Disc("", "", "", 0);
        msr = mediaServiceTest.updateDisc(u, "Test");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        u = new Disc("asd", "", "asd", -1);
        msr = mediaServiceTest.updateDisc(u, "Test");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        // SUCCESS
        u = new Disc("Update", "", "Update", 12);
        msr = mediaServiceTest.updateDisc(u, "Test");
        assertEquals(MediaServiceResult.SUCCESS, msr);

        // Confirm update
        d = (Disc) mediaServiceTest.getDisc("Test");
        assertEquals("Update", d.getTitle());
        assertEquals("Update", d.getDirector());
        assertEquals(12, d.getFsk());
    }
}