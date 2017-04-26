package edu.hm.shareit.resources;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the MediaService-Interface.
 */
public class MediaServiceImpl implements MediaService {
    // Container for data
    private final List<Medium> data = new ArrayList<>();

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

        // todo: add actual isbn-check
        if (book.getIsbn().length() == 0) {
            return MediaServiceResult.BOOK_ISBN_INVALID;
        }

        // todo: add actual persistency-backend
        data.add(book);

        return MediaServiceResult.SUCCESS;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        return null;
    }

    @Override
    public Medium[] getBooks() {
        return new Medium[0];
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
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
