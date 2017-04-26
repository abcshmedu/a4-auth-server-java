package edu.hm.shareit.model;

/**
 * Abstract class representing a Medium.
 */
public abstract class Medium {

    // Properties of a Medium.
    private final String title;

    public Medium(String title) {
        this.title = title;
    }

    /**
     * Getter for the title of this medium.
     * @return title of this medium.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the textual representation of a Medium.
     * @return textual representation of this Medium.
     */
    @Override
    public String toString() {
        return String.format("Medium{title='%1$s'}", title);
    }
}
