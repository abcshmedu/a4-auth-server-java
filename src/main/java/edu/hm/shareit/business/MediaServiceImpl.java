package edu.hm.shareit.business;

import edu.hm.shareit.data.MediaAccess;
import edu.hm.shareit.data.MediaAccessImpl;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.List;

/**
 * Implementation of the MediaService-Interface.
 */
public class MediaServiceImpl implements MediaService {

    // Controller for persistent data access
    private final MediaAccess mediaAccess = new MediaAccessImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Book getBook(String isbn) {
        Book book = null;
        for (Book b : mediaAccess.getBooks()) {
            if (b.getIsbn().equals(isbn)) {
                book = b;
            }
        }
        return book;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Disc getDisc(String barcode) {
        Disc disc = null;
        for (Disc d : mediaAccess.getDiscs()) {
            if (d.getBarcode().equals(barcode)) {
                disc = d;
            }
        }
        return disc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaServiceResult addBook(Book book) {
        if (book == null) {
            return MediaServiceResult.ERROR;
        }

        if (!book.isValidAuthor()) {
            return MediaServiceResult.BOOK_MISSING_AUHTOR;
        }

        if (!book.isValidIsbn()) {
            return MediaServiceResult.BOOK_INVALID_ISBN;
        }

        return addMedium(book);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaServiceResult addDisc(Disc disc) {
        if (disc == null) {
            return MediaServiceResult.ERROR;
        }

        if (!disc.isValidBarcode()) {
            return MediaServiceResult.DISC_INVALID_BARCODE;
        }

        if (!disc.isValidDirector()) {
            return MediaServiceResult.DISC_INVALID_DIRECTOR;
        }

        if (!disc.isValidFsk()) {
            return MediaServiceResult.DISC_INVALID_FSK;
        }

        return addMedium(disc);
    }

    /**
     * Adds a medium to the database.
     *
     * @param medium the medium to add.
     * @return MediaServiceResult.ERROR if medium is null or couldn't be added to database,
     * MediaServiceResult.MEDIUM_MISSING_TITLE if medium.title is invalid,
     * MediaServiceResult.SUCCESS if medium was added successfully.
     */
    private MediaServiceResult addMedium(Medium medium) {
        if (medium == null) {
            return MediaServiceResult.ERROR;
        }

        if (!medium.isValidTitle()) {
            return MediaServiceResult.MEDIUM_MISSING_TITLE;
        }

        MediaServiceResult msr;
        boolean success = mediaAccess.addMedium(medium);
        if (success) {
            msr = MediaServiceResult.SUCCESS;
        } else {
            msr = MediaServiceResult.ERROR;
        }

        return msr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Medium[] getBooks() {
        List<Book> books = mediaAccess.getBooks();
        return books.toArray(new Medium[books.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Medium[] getDiscs() {
        List<Disc> discs = mediaAccess.getDiscs();
        return discs.toArray(new Medium[discs.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaServiceResult updateBook(Book book) {
        if (book == null) {
            return MediaServiceResult.ERROR;
        }

        if (!book.isValidBook()) {
            return MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION;
        }

        List<Book> books = mediaAccess.getBooks();

        MediaServiceResult msr = MediaServiceResult.MEDIUM_NOT_FOUND;
        for (Book b : books) {
            if (b.equals(book)) {
                b.updateBook(book);
                msr = MediaServiceResult.SUCCESS;
            }
        }

        return msr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        if (disc == null) {
            return MediaServiceResult.ERROR;
        }

        if (!disc.isValidDisc()) {
            return MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION;
        }

        List<Disc> discs = mediaAccess.getDiscs();

        MediaServiceResult msr = MediaServiceResult.MEDIUM_NOT_FOUND;
        for (Disc d : discs) {
            if (d.equals(disc)) {
                d.updateDisc(disc);
                msr = MediaServiceResult.SUCCESS;
            }
        }

        return msr;
    }
}
