package edu.hm.shareit.model;

/**
 * Concrete class representing a Book.
 */
public class Book extends Medium {

    // Requirements for properties of a disc.
    private static final int MINIMUM_AUTHOR_LENGTH = 4;
    private static final int MINIMUM_ISBN_LENGTH = 4;

    // Properties of a Book.
    private final String isbn;
    private String author;

    public Book() {
        this("", "", "");
    }

    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * @return author of this Book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter to set/chance the author.
     *
     * @param author name of the author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Checks if this book is valid.
     *
     * @return true if it is valid, false if not.
     */
    public boolean isValidBook() {
        return isValidIsbn() && isValidAuthor();
    }

    /**
     * Checks if author is valid.
     *
     * @return true if it is valid, false if not.
     */
    public boolean isValidAuthor() {
        return author.length() >= MINIMUM_AUTHOR_LENGTH;
    }

    /**
     * @return ISBN of this Book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Checks if ISBN meets conditions.
     *
     * @return true if yes, false if not.
     */
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
     */
    public void updateBook(Book book) {
        //todo: should validate update-information
        super.updateMedium(book);
        this.setAuthor(book.getAuthor());
    }
}
