package edu.hm.shareit.model;

/**
 * Concrete class representing a Book.
 */
public class Book extends Medium{

    /* Properties of a Book. */
    private final String author;
    private final String isbn;

    public Book() {
        this("", "", "");
    }

    public Book(String title, String author, String isbn) {
        super(title);
        this.author = author;
        this.isbn = isbn;
    }

    /** @return author of this Book. */
    public String getAuthor() {
        return author;
    }

    /** @return ISBN of this Book. */
    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Book)) {
            return false;
        }

        Book book = (Book) obj;

        return (author != null ? author.equals(book.author) : book.author == null) &&
                (isbn != null ? isbn.equals(book.isbn) : book.isbn == null);
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

    public boolean isValidIsbn() {
        if (isbn == null) {
            return false;
        }

        String sanitizedIsbn = isbn.trim().replaceAll("-", "").replaceAll(" +", "");

        boolean isValid = false;
        if(sanitizedIsbn.length() == 13) {
            isValid = isValidIsbnThirteen(sanitizedIsbn);
        } else if (sanitizedIsbn.length() == 10) {
            isValid = isValidIsbnTen(sanitizedIsbn);
        }

        return isValid;
    }

    private boolean isValidIsbnThirteen(String sanitizedIsbn) {
        char[] arr = sanitizedIsbn.toCharArray();

        //todo

        return true;
    }

    private boolean isValidIsbnTen(String sanitizedIsbn) {
        char[] arr = sanitizedIsbn.toCharArray();

        //todo

        return true;
    }
}
