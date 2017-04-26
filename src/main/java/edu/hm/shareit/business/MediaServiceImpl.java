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
    // Container for data
    MediaAccess mediaAccess = new MediaAccessImpl();

    @Override
    public Medium getBook(String isbn) {
        return null;
    }

    @Override
    public Medium getDisc(String barcode) {
        return null;
    }

    @Override
    public MediaServiceResult addBook(Book book) {
        if (book == null) {
            return MediaServiceResult.ERROR_INVALID_JSON;
        }

        if (!book.isValidIsbn()) {
            return MediaServiceResult.BOOK_ISBN_INVALID;
        }

        // todo: check if book was really added (boolean addMedium())
        mediaAccess.addMedium(book);

        return MediaServiceResult.SUCCESS;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        if (disc == null) {
            return MediaServiceResult.ERROR_INVALID_JSON;
        }

        // todo
        if (disc.getBarcode().length() == 0) {
            return MediaServiceResult.DISC_BARCODE_INVALID;
        }

        mediaAccess.addMedium(disc);

        return MediaServiceResult.SUCCESS;
    }

    @Override
    public Medium[] getBooks() {
        List<Book> books = mediaAccess.getBooks();
        return books.toArray(new Medium[books.size()]);
    }

    @Override
    public Medium[] getDiscs() {
        List<Disc> discs = mediaAccess.getDiscs();
        return discs.toArray(new Medium[discs.size()]);
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }
}
