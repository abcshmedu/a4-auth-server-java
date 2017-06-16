package edu.hm.shareit.business;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.data.MediaAccess;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MediaServiceTest {

    private static MediaService mediaService = new MediaServiceImpl();
    private static MediaAccess mediaAccessMock;
    /* Use Dependency-Injection to inject a mockup of MediaAcess which is
     * required by MediaService. */
    private static final Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            mediaAccessMock = mock(MediaAccess.class);
            bind(MediaAccess.class).toInstance(mediaAccessMock);
        }
    });
    private Book book = mock(Book.class);
    private Disc disc = mock(Disc.class);

    @BeforeClass
    public static void initialize() {
        injector.injectMembers(mediaService);
    }

    @Before
    public void setup() {
        // Mock the book
        when(book.isValidAuthor()).thenReturn(true);
        when(book.isValidIsbn()).thenReturn(true);
        when(book.isValidTitle()).thenReturn(true);
        when(book.isValidBook()).thenReturn(true);
        when(book.getAuthor()).thenReturn("TestAuthor");
        when(book.getIsbn()).thenReturn("0");
        when(book.getTitle()).thenReturn("TestTitle");

        // Mock the disc
        when(disc.isValidBarcode()).thenReturn(true);
        when(disc.isValidTitle()).thenReturn(true);
        when(disc.isValidFsk()).thenReturn(true);
        when(disc.isValidDirector()).thenReturn(true);
        when(disc.isValidDisc()).thenReturn(true);
        when(disc.getTitle()).thenReturn("TestTitle");
        when(disc.getBarcode()).thenReturn("0");
        when(disc.getDirector()).thenReturn("TestDirector");
        when(disc.getFsk()).thenReturn(0);
    }

    @Test
    public void addBook() throws Exception {
        // Add Book, should return MediaServiceResult.SUCCESS
        when(mediaAccessMock.addMedium(book)).thenReturn(true);
        MediaServiceResult msr = mediaService.addBook(book);
        assertEquals(MediaServiceResult.SUCCESS, msr);
    }

    @Test
    public void addBookNull() {
        // Test MediaServiceResult.ERROR
        MediaServiceResult msr = mediaService.addBook(null);
        assertEquals(MediaServiceResult.ERROR, msr);
    }

    @Test
    public void addBookMissingTitle() {
        // Test MediaServiceResult.MEDIUM_MISSING_TITLE
        when(book.isValidTitle()).thenReturn(false);
        MediaServiceResult msr = mediaService.addBook(book);
        assertEquals(MediaServiceResult.MEDIUM_MISSING_TITLE, msr);
    }

    @Test
    public void addBookMissingAuthor() {
        // Test MediaServiceResult.BOOK_MISSING_AUTHOR
        when(book.isValidAuthor()).thenReturn(false);
        MediaServiceResult msr = mediaService.addBook(book);
        assertEquals(MediaServiceResult.BOOK_MISSING_AUHTOR, msr);
    }

    @Test
    public void addBookInvalidIsbn() {
        // Test MediaServiceResult.BOOK_INVALID_ISBN
        when(book.isValidIsbn()).thenReturn(false);
        MediaServiceResult msr = mediaService.addBook(book);
        assertEquals(MediaServiceResult.BOOK_INVALID_ISBN, msr);
    }

    @Test
    public void addDisc() throws Exception {
        // Add Disc, should return MediaServiceResult.SUCCESS
        when(mediaAccessMock.addMedium(disc)).thenReturn(true);
        MediaServiceResult msr = mediaService.addDisc(disc);
        assertEquals(MediaServiceResult.SUCCESS, msr);
    }

    @Test
    public void addDiscNull() {
        // Test MediaServiceResult.ERROR
        MediaServiceResult msr = mediaService.addDisc(null);
        assertEquals(MediaServiceResult.ERROR, msr);
    }

    @Test
    public void addDiscMediumMissingTitle() {
        // Test MediaServiceResult.MEDIUM_MISSING_TITLE
        when(disc.isValidTitle()).thenReturn(false);
        MediaServiceResult msr = mediaService.addDisc(disc);
        assertEquals(MediaServiceResult.MEDIUM_MISSING_TITLE, msr);
    }

    @Test
    public void addDiscInvalidBarcode() {
        // Test MediaServiceResult.DISC_INVALID_BARCODE
        when(disc.isValidBarcode()).thenReturn(false);
        MediaServiceResult msr = mediaService.addDisc(disc);
        assertEquals(MediaServiceResult.DISC_INVALID_BARCODE, msr);
    }

    @Test
    public void addDiscInvalidDirector() {
        // Test MediaServiceResult.DISC_INVALID_DIRECTOR
        when(disc.isValidDirector()).thenReturn(false);
        MediaServiceResult msr = mediaService.addDisc(disc);
        assertEquals(MediaServiceResult.DISC_INVALID_DIRECTOR, msr);
    }

    @Test
    public void addDiscInvalidFsk() {
        // Test MediaServiceResult.DISC_INVALID_FSK
        when(disc.isValidFsk()).thenReturn(false);
        MediaServiceResult msr = mediaService.addDisc(disc);
        assertEquals(MediaServiceResult.DISC_INVALID_FSK, msr);
    }

    @Test
    public void getBooks() throws Exception {
        when(mediaAccessMock.getBooks()).thenReturn(new ArrayList<>());
        Medium[] books = mediaService.getBooks();
        assertEquals(0, books.length);
    }

    @Test
    public void getDiscs() throws Exception {
        when(mediaAccessMock.getDiscs()).thenReturn(new ArrayList<>());
        Medium[] discs = mediaService.getDiscs();
        assertEquals(0, discs.length);
    }

    @Test
    public void getBook() throws Exception {
        when(mediaAccessMock.getBook("")).thenReturn(book);
        Medium m = mediaService.getBook("");
        assertEquals(book, m);
    }

    @Test
    public void getBookNull() throws Exception {
        when(mediaAccessMock.getBook("")).thenReturn(null);
        Medium m = mediaService.getBook("");
        assertNull(m);
    }

    @Test
    public void getDisc() throws Exception {
        when(mediaAccessMock.getDisc("")).thenReturn(disc);
        Medium m = mediaService.getDisc("");
        assertEquals(disc, m);
    }

    @Test
    public void getDiscNull() throws Exception {
        when(mediaAccessMock.getDisc("")).thenReturn(null);
        Medium m = mediaService.getDisc("");
        assertNull(m);
    }

    @Test
    public void updateBook() throws Exception {
        // SUCCESS
        Book[] books = new Book[]{book};
        when(mediaAccessMock.getBooks()).thenReturn(Arrays.asList(books));
        MediaServiceResult msr = mediaService.updateBook(book, book.getIsbn());
        assertEquals(MediaServiceResult.SUCCESS, msr);
    }

    @Test
    public void updateBookNull() throws Exception {
        // ERROR
        MediaServiceResult msr = mediaService.updateBook(null, "");
        assertEquals(MediaServiceResult.ERROR, msr);
    }

    @Test
    public void updateBookImmutable() throws Exception {
        // MEDIUM_ID_IMMUTABLE
        MediaServiceResult msr = mediaService.updateBook(book, book.getIsbn() + "x");
        assertEquals(MediaServiceResult.MEDIUM_ID_IMMUTABLE, msr);
    }

    @Test
    public void updateBookNotFound() {
        // MEDIUM_NOT_FOUND
        when(mediaAccessMock.getBooks()).thenReturn(new ArrayList<>());
        MediaServiceResult msr = mediaService.updateBook(book, book.getIsbn());
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);
    }

    @Test
    public void updateBookInvalidUpdateInformation() throws Exception {
        // MEDIUM_INVALID_UPDATE_INFORMATION
        Mockito.doThrow(InvalidUpdateException.class).when(book).updateBook(book);
        Book[] books = new Book[]{book};
        when(mediaAccessMock.getBooks()).thenReturn(Arrays.asList(books));
        MediaServiceResult msr = mediaService.updateBook(book, book.getIsbn());
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);
    }

    @Test
    public void updateDisc() throws Exception {
        // SUCCESS
        Disc[] discs = new Disc[]{disc};
        when(mediaAccessMock.getDiscs()).thenReturn(Arrays.asList(discs));
        MediaServiceResult msr = mediaService.updateDisc(disc, disc.getBarcode());
        assertEquals(MediaServiceResult.SUCCESS, msr);
    }

    @Test
    public void updateDiscNull() throws Exception {
        // ERROR
        MediaServiceResult msr = mediaService.updateDisc(null, null);
        assertEquals(MediaServiceResult.ERROR, msr);
    }

    @Test
    public void updateDiscIdImmutable() throws Exception {
        // MEDIUM_ID_IMMUTABLE
        MediaServiceResult msr = mediaService.updateDisc(disc, disc.getBarcode() + "x");
        assertEquals(MediaServiceResult.MEDIUM_ID_IMMUTABLE, msr);
    }

    @Test
    public void updateDiscMediumNotFound() throws Exception {
        // MEDIUM_NOT_FOUND
        when(mediaAccessMock.getDiscs()).thenReturn(new ArrayList<>());
        MediaServiceResult msr = mediaService.updateDisc(disc, disc.getBarcode());
        assertEquals(MediaServiceResult.MEDIUM_NOT_FOUND, msr);
    }

    @Test
    public void updateDiscInvalidUpdateInformation() throws Exception {
        // MEDIUM_INVALID_UPDATE_INFORMATION
        Mockito.doThrow(InvalidUpdateException.class).when(disc).updateDisc(disc);
        Disc[] discs = new Disc[]{disc};
        when(mediaAccessMock.getDiscs()).thenReturn(Arrays.asList(discs));
        MediaServiceResult msr = mediaService.updateDisc(disc, disc.getBarcode());
        assertEquals(MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION, msr);
    }
}