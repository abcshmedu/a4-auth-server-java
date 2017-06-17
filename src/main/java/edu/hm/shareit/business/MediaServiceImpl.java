package edu.hm.shareit.business;

import com.google.inject.Inject;
import edu.hm.shareit.data.MediaAccess;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.List;

/**
 * Implementation of the MediaService-Interface.
 */
public class MediaServiceImpl implements MediaService {

    // Controller for data-access
    @Inject
    private MediaAccess mediaAccess;

    /**
     * {@inheritDoc}
     */
    @Override
    public Book getBook(String isbn) {
        return mediaAccess.getBook(isbn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Disc getDisc(String barcode) {
        return mediaAccess.getDisc(barcode);
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
    public MediaServiceResult updateBook(Book book, String isbn) {
        // TODO: this is a last minute hack, fix it
        if (book == null) {
            return MediaServiceResult.ERROR;
        }

        if (book.getIsbn() != null && book.getIsbn().length() > 0
                && !book.getIsbn().equals(isbn)) {
            return MediaServiceResult.MEDIUM_ID_IMMUTABLE;
        }

        List<Book> books = mediaAccess.getBooks();

        MediaServiceResult msr = MediaServiceResult.MEDIUM_NOT_FOUND;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                try {
                    b.updateBook(book);
                    mediaAccess.updateMedium(b);
                    msr = MediaServiceResult.SUCCESS;
                } catch (InvalidUpdateException ex) {
                    msr = MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION;
                }
            }
        }

        return msr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaServiceResult updateDisc(Disc disc, String barcode) {
        // TODO: this is a last minute hack, fix it
        if (disc == null) {
            return MediaServiceResult.ERROR;
        }

        if (disc.getBarcode() != null && disc.getBarcode().length() > 0
                && !disc.getBarcode().equals(barcode)) {
            return MediaServiceResult.MEDIUM_ID_IMMUTABLE;
        }

        List<Disc> discs = mediaAccess.getDiscs();

        MediaServiceResult msr = MediaServiceResult.MEDIUM_NOT_FOUND;
        for (Disc d : discs) {
            if (d.getBarcode().equals(barcode)) {
                try {
                    d.updateDisc(disc);
                    msr = MediaServiceResult.SUCCESS;
                } catch (InvalidUpdateException ex) {
                    msr = MediaServiceResult.MEDIUM_INVALID_UPDATE_INFORMATION;
                }
            }
        }

        return msr;
    }
}
