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
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatus());

        BOOK = new Book("TestTitle", "", "");
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatus());

        BOOK = new Book("", "TestAuthor", "");
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatus());

        // MediaServiceResult.ERROR
        assertEquals(MediaServiceResult.ERROR.getStatusCode(),
                MEDIARESOURCE.updateBook(null, "0000").getStatus());

        // MediaServiceResult.MEDIUM_ID_IMMUTABLE
        BOOK = new Book("", "", "0001");
        assertEquals(MediaServiceResult.MEDIUM_ID_IMMUTABLE.getStatusCode(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatus());

        // MediaServiceResult.MEDIUM_NOT_FOUND
        BOOK = new Book("", "", "0001");
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK, "0001").getStatusInfo().getReasonPhrase());

        // MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION
        BOOK = new Book("", "", "");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatusInfo().getReasonPhrase());

        BOOK = new Book("asd", "", "");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatusInfo().getReasonPhrase());

        BOOK = new Book("", "asd", "");
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateBook(BOOK, "0000").getStatusInfo().getReasonPhrase());
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
        assertEquals(MediaServiceResult.SUCCESS.getStatusCode(),
                MEDIARESOURCE.updateDisc(DISC, "0000").getStatus());

        // MediaServiceResult.ERROR
        assertEquals(MediaServiceResult.ERROR.getStatusCode(),
                MEDIARESOURCE.updateDisc(null, "0000").getStatus());

        // MediaServiceResult.MEDIUM_ID_IMMUTABLE
        DISC = new Disc("TestTitle", "0001", "TestDirector", 19);
        assertEquals(MediaServiceResult.MEDIUM_ID_IMMUTABLE.getStatusCode(),
                MEDIARESOURCE.updateDisc(DISC, "0000").getStatus());

        // MediaServiceResult.MEDIUM_NOT_FOUND
        DISC = new Disc("TestTitle", "0001", "TestDirector", 19);
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC, "0001").getStatusInfo().getReasonPhrase());

        // MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION
        DISC = new Disc();
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC, "0000").getStatusInfo().getReasonPhrase());

        DISC = new Disc("asd", "", "", 0);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC, "0000").getStatusInfo().getReasonPhrase());

        DISC = new Disc("", "", "asd", 0);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC, "0000").getStatusInfo().getReasonPhrase());

        DISC = new Disc("", "", "", 199);
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION.getReasonPhrase(),
                MEDIARESOURCE.updateDisc(DISC, "0000").getStatusInfo().getReasonPhrase());
    }
}