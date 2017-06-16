package edu.hm.shareit.data.hibernate;

import edu.hm.shareit.data.MediaAccess;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MediaAccessPersistentTest {

    private static MediaAccess mediaAccess = new MediaAccessPersistent();
    private Book b1 = new Book("Test1", "Test1", "0000000000001");
    private Book b2 = new Book("Test2", "Test2", "0000000000002");
    private Disc d1 = new Disc("Test1", "Test1", "Test1", 0);
    private Disc d2 = new Disc("Test2", "Test2", "Test2", 0);

    /**
     * Initialize database with some data.
     */
    @BeforeClass
    public static void initialize() {
        Book b1 = new Book("Test1", "Test1", "0000000000001");
        Book b2 = new Book("Test2", "Test2", "0000000000002");
        Disc d1 = new Disc("Test1", "Test1", "Test1", 0);
        Disc d2 = new Disc("Test2", "Test2", "Test2", 0);

        // Add books & discs to database
        assertTrue(mediaAccess.addMedium(b1));
        assertTrue(mediaAccess.addMedium(b2));
        assertTrue(mediaAccess.addMedium(d1));
        assertTrue(mediaAccess.addMedium(d2));
    }

    @AfterClass
    public static void shutdown() {
        mediaAccess.shutdown();
    }

    @Test
    public void addMedium() {
        Book b = new Book("AddTest", "AddTest", "0000000000003");
        Disc d = new Disc("AddTest", "AddTest", "AddTest", 18);

        mediaAccess.addMedium(b);
        mediaAccess.addMedium(d);

        Book vb = mediaAccess.getBook(b.getIsbn());
        assertEquals(b, vb);

        Disc vd = mediaAccess.getDisc(d.getBarcode());
        assertEquals(d, vd);
    }

    @Test
    public void removeMedium() {
        Book d = new Book("RemoveTest", "RemoveTest", "0000000000006");
        mediaAccess.addMedium(d);

        // Check if book exists
        Book d1 = mediaAccess.getBook(d.getIsbn());
        assertEquals(d, d1);

        // Remove book and check again, should be null
        mediaAccess.removeMedium(d);
        d1 = mediaAccess.getBook(d.getIsbn());
        assertNull(d1);
    }

    @Test
    public void updateMedium() {
        Book d = new Book("UpdateTest", "UpdateTest", "0000000000007");
        mediaAccess.addMedium(d);

        // Update to new title
        d.setTitle("Updated");
        mediaAccess.updateMedium(d);

        // Test if changed
        Book d1 = mediaAccess.getBook(d.getIsbn());
        assertEquals(d1.getTitle(), d.getTitle());
    }

    @Test
    public void getBook() {
        Book b = mediaAccess.getBook(b1.getIsbn());
        assertEquals(b1, b);
    }

    @Test
    public void getDisc() {
        Disc d = mediaAccess.getDisc(d1.getBarcode());
        assertEquals(d1, d);
    }

    @Test
    public void getBooks() {
        List<Book> books = mediaAccess.getBooks();
        assertTrue(books.contains(b1));
        assertTrue(books.contains(b2));
    }

    @Test
    public void getDiscs() {
        List<Disc> discs = mediaAccess.getDiscs();
        assertTrue(discs.contains(d1));
        assertTrue(discs.contains(d2));
    }
}