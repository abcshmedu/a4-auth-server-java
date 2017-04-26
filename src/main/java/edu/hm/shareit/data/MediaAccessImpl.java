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
            success = false;
        }

        return success;
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
    public List<Disc> getDiscs() {
        return discs;
    }
}
