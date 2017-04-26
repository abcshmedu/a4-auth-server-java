package edu.hm.shareit.data;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.List;

/**
 * Driver for persistent-storage.
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
}
