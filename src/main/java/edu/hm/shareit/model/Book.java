package edu.hm.shareit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.shareit.business.InvalidUpdateException;

/**
 * Concrete class representing a Book.
 */
public class Book extends Medium {

    // Requirements of a valid disc.
    private static final int MINIMUM_AUTHOR_LENGTH = 4;
    private static final int MINIMUM_ISBN_LENGTH = 4;

    // Properties of a Book.
    private final String isbn;
    private String author;

    /**
     * Default-constructor.
     */
    public Book() {
        this("", "", "");
    }

    /**
     * Initializes a new book.
     *
     * @param title  of the book.
     * @param author of the book.
     * @param isbn   of the book.
     */
    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Checks if this book is valid.
     *
     * @return true if it is valid, false if not.
     */
    @JsonIgnore
    public boolean isValidBook() {
        return isValidMedium() && isValidIsbn() && isValidAuthor();
    }

    /**
     * Checks if author is valid.
     *
     * @return true if it is valid, false if not.
     */
    @JsonIgnore
    public boolean isValidAuthor() {
        return author.length() >= MINIMUM_AUTHOR_LENGTH;
    }

    public String getIsbn() {
        return isbn;
    }

    /**
     * Checks if ISBN meets conditions.
     *
     * @return true if yes, false if not.
     */
    @JsonIgnore
    public boolean isValidIsbn() {
        return isbn.length() >= MINIMUM_ISBN_LENGTH;
    }

    /**
     * Compares this Book to an Object.
     *
     * @param obj object to compare to.
     * @return true if the object is a book with identical isbn,
     * false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Book)) {
            return false;
        }

        Book book = (Book) obj;

        return (isbn != null ? isbn.equals(book.isbn) : book.isbn == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Book{title='%1$s', author='%2$s', isbn='%3$s'}",
                super.getTitle(), author, isbn);
    }

    /**
     * Updates this books information.
     *
     * @param book with updated information.
     * @throws InvalidUpdateException when book has invalid information.
     */
    public void updateBook(Book book) throws InvalidUpdateException {
        // TODO: this is a last minute hack, fix it
        // ..still not fixed, this is legacy code now ;)
        if (book.getTitle().length() == 0 && book.getAuthor().length() == 0) {
            throw new InvalidUpdateException();
        }

        // We only receive updated info!
        if (book.getTitle().length() == 0) {
            book.setTitle(getTitle());
        } else if (!book.isValidTitle()) {
            throw new InvalidUpdateException();
        }

        if (book.getAuthor().length() == 0) {
            book.setAuthor(getAuthor());
        } else if (!book.isValidAuthor()) {
            throw new InvalidUpdateException();
        }

        super.updateMedium(book);
        this.setAuthor(book.getAuthor());
    }
}
