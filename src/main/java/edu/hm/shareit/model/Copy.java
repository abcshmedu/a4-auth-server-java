package edu.hm.shareit.model;

/**
 * Concrete class representing a copy of a Medium.
 */
public class Copy {

    // Medium and Owner of this copy
    private final Medium medium;
    private String owner;

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

        return (owner != null ? owner.equals(c.owner) : c.getOwner() == null) &&
                medium.equals(c.medium);
    }

    @Override
    public int hashCode() {
        int result = medium.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
