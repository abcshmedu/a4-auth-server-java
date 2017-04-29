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
     * @return MediaServiceResult.ERROR if book is null or couldn't be added to database,
     * MediaServiceResult.MEDIUM_MISSING_TITLE if title is invalid,
     * MediaServiceResult.BOOK_MISSING_AUTHOR if author is invalid,
     * MediaServiceResult.BOOK_INVALID_ISBN if isbn is invalid,
     * MediaServiceResult.SUCCESS if book was added successfully.
     */
    MediaServiceResult addBook(Book book);

    /**
     * Validates a disk and adds it to persistent storage.
     *
     * @param disc the disc to be added.
     * @return MediaServiceResult.ERROR if disc is null or couldn't be added to database,
     * MediaServiceResult.MEDIUM_MISSING_TITLE if title is invalid,
     * MediaServiceResult.DISC_INVALID_BARCODE if barcode is invalid,
     * MediaServiceResult.DISC_INVALID_DIRECTOR if director is invalid,
     * MediaServiceResult.DISC_INVALID_FSK if fsk is invalid,
     * MediaServiceResult.SUCCESS if disc was added successfully.
     */
    MediaServiceResult addDisc(Disc disc);

    /**
     * Getter for all books.
     *
     * @return all books, empty array if none.
     */
    Medium[] getBooks();

    /**
     * Getter for all discs.
     *
     * @return all discs, empty array if none.
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
     * @return MediaServiceResult.SUCCESS if book was updated successfully,
     * MediaServiceResult.ERROR if book is null,
     * MediaServiceResult.MEDIUM_NOT_FOUND if no matching book was found to modify,
     * MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION if the update information was invalid.
     */
    MediaServiceResult updateBook(Book book);

    /**
     * Updates a disc already present in persistent storage.
     *
     * @param disc updated version of the book.
     * @return MediaServiceResult.SUCCESS if disc was updated successfully,
     * MediaServiceResult.ERROR if disc is null,
     * MediaServiceResult.MEDIUM_NOT_FOUND if no matching book was found to modify,
     * MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION if the update information was invalid.
     */
    MediaServiceResult updateDisc(Disc disc);
}
