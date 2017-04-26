package edu.hm.shareit.business;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

/**
 * Defines operations available to the service.
 */
public interface MediaService {

    /**
     * Validates a book and adds it to persistent storage.
     *
     * @param book the book to be added.
     * @return result of the operation.
     */
    MediaServiceResult addBook(Book book);

    /**
     * Validates a disk and adds it to persistent storage.
     *
     * @param disc the disc to be added.
     * @return result of the operation.
     */
    MediaServiceResult addDisc(Disc disc);

    /**
     * Getter for all books.
     *
     * @return all books.
     */
    Medium[] getBooks();

    /**
     * Getter for all discs.
     *
     * @return all discs.
     */
    Medium[] getDiscs();

    /**
     * Getter for a specific book.
     *
     * @param isbn of the requested book.
     * @return the requested book or null.
     */
    Medium getBook(String isbn);

    /**
     * Getter for a specific disc.
     *
     * @param barcode of the requested disc.
     * @return the requested disc or null.
     */
    Medium getDisc(String barcode);

    /**
     * Updates a book already present in persistent storage.
     *
     * @param book updated version of the book.
     * @return result of the operation.
     */
    MediaServiceResult updateBook(Book book);

    /**
     * Updates a disc already present in persistent storage.
     *
     * @param disc updated version of the disc.
     * @return result of the operation.
     */
    MediaServiceResult updateDisc(Disc disc);
}
