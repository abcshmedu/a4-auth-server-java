package edu.hm.shareit.data;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple Class representing a controller for persistent-data access.
 */
public class MediaAccessImpl implements MediaAccess {

    // The data
    private final List<Book> books = new LinkedList<>();
    private final List<Disc> discs = new LinkedList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addMedium(Medium medium) {
        boolean success = false;

        if (medium instanceof Book) {
            books.add((Book) medium);
            success = true;
        } else if (medium instanceof Disc) {
            discs.add((Disc) medium);
            success = true;
        }

        return success;
    }

    @Override
    public boolean updateMedium(Medium medium) {
        if (medium instanceof Book) {
            Book b = (Book) medium;
            // Do the actual updating here
        } else if (medium instanceof Disc) {
            Disc d = (Disc) medium;
            // Do the actual updating here
        }

        return true;
    }

    @Override
    public Book getBook(String ISBN) {
        for (Book b : books) {
            if (b.getIsbn().equals(ISBN)) {
                return b;
            }
        }

        return null;
    }

    @Override
    public Disc getDisc(String barcode) {
        for (Disc d : discs) {
            if (d.getBarcode().equals(barcode)) {
                return d;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Book> getBooks() {
        return books;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Disc> getDiscs() {
        return discs;
    }

    @Override
    public void shutdown() {
    }

    @Override
    public boolean removeMedium(Medium medium) {
        return false;
    }
}
