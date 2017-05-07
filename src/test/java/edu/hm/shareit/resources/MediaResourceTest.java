package edu.hm.shareit.resources;

import edu.hm.shareit.business.MediaServiceResult;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class MediaResourceTest {

    private static MediaResource MEDIARESOURCE;
    private static Book BOOK;
    private static Disc DISC;

    @Before
    public void setUp() {
        MEDIARESOURCE = new MediaResource();
        BOOK = new Book("Test", "Test", "0000");
        DISC = new Disc("Test", "0000", "Test", 18);
    }

    @Test
    public void createBook() throws Exception {
        // Adding valid Book
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), MEDIARESOURCE.createBook(BOOK).getStatus());

        // Adding invalid Book
        assertEquals(MediaServiceResult.ERROR.getStatusCode(), MEDIARESOURCE.createBook(null).getStatus());
    }

    @Test
    public void getBook() throws Exception {
        MEDIARESOURCE.createBook(BOOK);

        // Request valid Book
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), MEDIARESOURCE.getBook("0000").getStatus());

        // Request invalid Book
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                MEDIARESOURCE.getBook("0001").getStatusInfo().getReasonPhrase());
    }

    @Test
    public void getBooks() throws Exception {
        MEDIARESOURCE.createBook(BOOK);
        MEDIARESOURCE.createBook(BOOK);

        assertEquals(Response.ok().build().getStatus(), MEDIARESOURCE.getBooks().getStatus());
    }

    @Test
    public void updateBook() throws Exception {
        MEDIARESOURCE.createBook(BOOK);
        BOOK = new Book("TestTitle", "TestAuthor", "0000");

        // MediaServiceResult.SUCCESS
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), MEDIARESOURCE.updateBook(BOOK).getStatus());

        // MediaServiceResult.ERROR
        assertEquals(MediaServiceResult.ERROR.getStatusCode(), MEDIARESOURCE.updateBook(null).getStatus());

        // MediaServiceResult.MEDIUM_NOT_FOUND
        BOOK = new Book("TestTitle", "TestAuthor", "0001");
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK).getStatusInfo().getReasonPhrase());

        // MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION
        BOOK = new Book("TestTitle", "", "0000");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK).getStatusInfo().getReasonPhrase());
        BOOK = new Book("", "TestAuthor", "0000");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK).getStatusInfo().getReasonPhrase());
    }

    @Test
    public void createDisc() throws Exception {
        // Adding valid Disc
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), MEDIARESOURCE.createDisc(DISC).getStatus());

        // Adding invalid Disc
        assertEquals(MediaServiceResult.ERROR.getStatusCode(), MEDIARESOURCE.createDisc(null).getStatus());
    }

    @Test
    public void getDisc() throws Exception {
        MEDIARESOURCE.createDisc(DISC);

        // Request valid Disc
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), MEDIARESOURCE.getDisc("0000").getStatus());

        // Request invalid Book
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                MEDIARESOURCE.getDisc("0001").getStatusInfo().getReasonPhrase());
    }

    @Test
    public void getDiscs() throws Exception {
        MEDIARESOURCE.createDisc(DISC);
        MEDIARESOURCE.createDisc(DISC);

        assertEquals(Response.ok().build().getStatus(), MEDIARESOURCE.getDiscs().getStatus());
    }

    @Test
    public void updateDisc() throws Exception {
        MEDIARESOURCE.createDisc(DISC);
        DISC = new Disc("TestTitle", "0000", "TestDirector", 19);

        // MediaServiceResult.SUCCESS
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(), MEDIARESOURCE.updateDisc(DISC).getStatus());

        // MediaServiceResult.ERROR
        assertEquals(MediaServiceResult.ERROR.getStatusCode(), MEDIARESOURCE.updateDisc(null).getStatus());

        // MediaServiceResult.MEDIUM_NOT_FOUND
        DISC = new Disc("TestTitle", "0001", "TestDirector", 19);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC).getStatusInfo().getReasonPhrase());

        // MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION
        DISC = new Disc("TestTitle", "0000", "", 87);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC).getStatusInfo().getReasonPhrase());
        DISC = new Disc("", "0000", "TestDirector", 87);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC).getStatusInfo().getReasonPhrase());
        DISC = new Disc("TestTitle", "0000", "", 187);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC).getStatusInfo().getReasonPhrase());
    }

    /*@Test
    public void addBook() {
        Book b;
        MediaServiceResult msr;

        // Add 1000 Books, should always return success
        for (int i = 0; i < 1000; i++) {
            b = new Book("Test", "Test", "TestIsbn");
            msr = MEDIASERVICETEST.addBook(b);
            assertEquals(MediaServiceResult.SUCCESS, msr);
        }
        assertEquals(1000, MEDIASERVICETEST.getBooks().length);

        // Test MediaServiceResult.ERROR
        b = null;
        msr = MEDIASERVICETEST.addBook(b);
        assertEquals(MediaServiceResult.ERROR, msr);

        // Test MediaServiceResult.MEDIUM_MISSING_TITLE
        b = new Book("", "Test", "TestIsbn");
        msr = MEDIASERVICETEST.addBook(b);
        assertEquals(MediaServiceResult.MEDIUM_MISSING_TITLE, msr);

        // Test MediaServiceResult.BOOK_MISSING_AUTHOR
        b = new Book("Test", "", "TestIsbn");
        msr = MEDIASERVICETEST.addBook(b);
        assertEquals(MediaServiceResult.BOOK_MISSING_AUHTOR, msr);

        // Test MediaServiceResult.BOOK_INVALID_ISBN
        b = new Book("Test", "Test", "0");
        msr = MEDIASERVICETEST.addBook(b);
        assertEquals(MediaServiceResult.BOOK_INVALID_ISBN, msr);

        // No book was added
        assertEquals(1000, MEDIASERVICETEST.getBooks().length);
    }

    @Test
    public void addDisc() {
        Disc d;
        MediaServiceResult msr;

        // Add 1000 discs, should always return success
        for (int i = 0; i < 1000; i++) {
            d = new Disc("Test", "Test", "Test", 0);
            msr = MEDIASERVICETEST.addDisc(d);
            assertEquals(MediaServiceResult.SUCCESS, msr);
        }
        assertEquals(1000, MEDIASERVICETEST.getDiscs().length);

        // Test MediaServiceResult.ERROR
        d = null;
        msr = MEDIASERVICETEST.addDisc(d);
        assertEquals(MediaServiceResult.ERROR, msr);

        // Test MediaServiceResult.MEDIUM_MISSING_TITLE
        d = new Disc("", "Test", "Test", 0);
        msr = MEDIASERVICETEST.addDisc(d);
        assertEquals(MediaServiceResult.MEDIUM_MISSING_TITLE, msr);

        // Test MediaServiceResult.DISC_INVALID_BARCODE
        d = new Disc("Test", "0", "Test", 0);
        msr = MEDIASERVICETEST.addDisc(d);
        assertEquals(MediaServiceResult.DISC_INVALID_BARCODE, msr);

        // Test MediaServiceResult.DISC_INVALID_DIRECTOR
        d = new Disc("Test", "Test", "", 0);
        msr = MEDIASERVICETEST.addDisc(d);
        assertEquals(MediaServiceResult.DISC_INVALID_DIRECTOR, msr);

        // Test MediaServiceResult.DISC_INVALID_FSK
        d = new Disc("Test", "Test", "Test", -1);
        msr = MEDIASERVICETEST.addDisc(d);
        assertEquals(MediaServiceResult.DISC_INVALID_FSK, msr);

        // No disc was added
        assertEquals(1000, MEDIASERVICETEST.getDiscs().length);
    }

    @Test
    public void getBooks() {
        assertEquals(0, MEDIASERVICETEST.getBooks().length);

        Book b = new Book("Test", "Test", "Test");
        MEDIASERVICETEST.addBook(b);

        assertEquals(1, MEDIASERVICETEST.getBooks().length);
    }

    @Test
    public void getDiscs() {
        assertEquals(0, MEDIASERVICETEST.getDiscs().length);

        Disc d = new Disc("Test", "Test", "Test", 0);
        MEDIASERVICETEST.addDisc(d);

        assertEquals(1, MEDIASERVICETEST.getDiscs().length);
    }

    @Test
    public void getBook() {
        Book b = new Book("Test", "Test", "Test");

        MEDIASERVICETEST.addBook(b);
        Medium m = MEDIASERVICETEST.getBook("Test");
        assertEquals(b, m);
        assertEquals(m, b); // transitivity

        m = MEDIASERVICETEST.getBook("Test2");
        assertNull(m);
    }

    @Test
    public void getDisc() {
        Disc d = new Disc("Test", "Test", "Test", 0);

        MEDIASERVICETEST.addDisc(d);
        Medium m = MEDIASERVICETEST.getDisc("Test");
        assertEquals(d, m);
        assertEquals(m, d); // transitivity

        m = MEDIASERVICETEST.getDisc("Test2");
        assertNull(m);
    }

    @Test
    public void updateBook() {
        Book b = new Book("Test", "Test", "Test");
        MEDIASERVICETEST.addBook(b);

        MediaServiceResult msr;

        // Not Found
        Book u = new Book("Update", "Update", "Test2");
        msr = MEDIASERVICETEST.updateBook(u);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);

        // Invalid Update-Information
        u = new Book("Update", "", "Test");
        msr = MEDIASERVICETEST.updateBook(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);
        u = new Book("", "Update", "Test");
        msr = MEDIASERVICETEST.updateBook(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        // Success
        u = new Book("Update", "Update", "Test");
        msr = MEDIASERVICETEST.updateBook(u);
        assertEquals(MediaServiceResult.SUCCESS, msr);

        // Confirm update
        b = (Book) MEDIASERVICETEST.getBook("Test");
        assertEquals("Update", b.getTitle());
        assertEquals("Update", b.getAuthor());
        assertEquals("Test", b.getIsbn());
    }

    @Test
    public void updateDisc() {
        Disc d = new Disc("Test", "Test", "Test", 0);
        MEDIASERVICETEST.addDisc(d);

        MediaServiceResult msr;

        // Not Found
        Disc u = new Disc("Update", "Update", "Test2", 0);
        msr = MEDIASERVICETEST.updateDisc(u);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);

        // Invalid Update-Information
        u = new Disc("Update", "", "Test", 0);
        msr = MEDIASERVICETEST.updateDisc(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);
        u = new Disc("", "Update", "Test", 0);
        msr = MEDIASERVICETEST.updateDisc(u);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);

        // Success
        u = new Disc("Update", "Test", "Update", 5);
        msr = MEDIASERVICETEST.updateDisc(u);
        assertEquals(MediaServiceResult.SUCCESS, msr);

        // Confirm update
        d = (Disc) MEDIASERVICETEST.getDisc("Test");
        assertEquals("Update", d.getTitle());
        assertEquals("Test", d.getBarcode());
        assertEquals("Update", d.getDirector());
        assertEquals(5, d.getFsk());
    }*/
}