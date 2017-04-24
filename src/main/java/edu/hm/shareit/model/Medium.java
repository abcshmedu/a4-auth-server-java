package edu.hm.shareit.model;

/**
 * Abstract class representing a Medium.
 */
public abstract class Medium {

    /* Properties of a Medium. */
    private final String title;

    public Medium(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if(!(obj instanceof Medium)) {
            return false;
        }

        Medium medium = (Medium) obj;

        return title != null ? title.equals(medium.title) : medium.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Medium{" +
                "title='" + title + '\'' +
                '}';
    }
}
