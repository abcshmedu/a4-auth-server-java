package edu.hm.shareit.model;

/**
 * Concrete class representing a copy of a Medium.
 */
public class Copy {

    /* Properties of a Copy. */
    private final Medium medium;
    private final String owner;

    public Copy(Medium medium, String owner) {
        this.medium = medium;
        this.owner = owner;
    }

    /**
     * @return Medium of this copy.
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * @return owner of this copy.
     */
    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Copy)) {
            return false;
        }

        Copy c = (Copy) obj;

        // todo: every copy of the same medium should be equal, no matter its owner

        return owner != null && owner.equals(c.owner) &&
                medium.equals(c.medium);
    }

    @Override
    public int hashCode() {
        int result = medium.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
