package edu.hm.shareit.data;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.List;

/**
 * Driver for storage.
 */
public interface MediaAccess {

    /**
     * Add a new Medium to the database.
     *
     * @param medium the medium to add.
     * @return true if success, false if failed.
     */
    boolean addMedium(Medium medium);

    /**
     * Removes the medium.
     *
     * @param medium the medium to remove.
     * @return true if success, false if not.
     */
    boolean removeMedium(Medium medium);

    /**
     * Updates the medium.
     *
     * @param medium the medium to update.
     * @return true if success, false if not.
     */
    boolean updateMedium(Medium medium);

    /**
     * Gets the book with specified ISBN.
     *
     * @param ISBN the ISBN.
     * @return the book or null if no book matches.
     */
    Book getBook(String ISBN);

    /**
     * Gets the disc with specified barcode.
     *
     * @param barcode the barcode.
     * @return the disc or null if no disc matches.
     */
    Disc getDisc(String barcode);

    /**
     * Gets list of all books from the database.
     *
     * @return all books.
     */
    List<Book> getBooks();

    /**
     * Gets list of all discs from the database.
     *
     * @return all discs.
     */
    List<Disc> getDiscs();

    /**
     * Gracefully shuts down underlying implementation (e.g. database-access provider)
     * if necessary.
     */
    void shutdown();
}
