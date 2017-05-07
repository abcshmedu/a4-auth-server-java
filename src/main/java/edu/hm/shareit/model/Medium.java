package edu.hm.shareit.model;

/**
 * Abstract class representing a Medium.
 */
public abstract class Medium {

    // Requirements of properties for a Medium
    private static final int MINIMUM_TITLE_LENGTH = 4;

    // Properties of a Medium.
    private String title;

    public Medium(String title) {
        this.title = title;
    }

    /**
     * Getter for the title of this medium.
     *
     * @return title of this medium.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter to set/chance the title.
     *
     * @param title the title of this medium.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if this medium meets the requirements.
     *
     * @return true if yes, false if not.
     */
    public boolean isValidMedium() {
        return isValidTitle();
    }

    /**
     * Checks if title meets the requirements.
     *
     * @return true if title is valid, false if not.
     */
    public boolean isValidTitle() {
        return title.length() >= MINIMUM_TITLE_LENGTH;
    }

    /**
     * Updates this mediums information.
     *
     * @param medium with updated information.
     */
    public void updateMedium(Medium medium) {
        this.setTitle(medium.getTitle());
    }

    /**
     * Hashcode for a medium.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
