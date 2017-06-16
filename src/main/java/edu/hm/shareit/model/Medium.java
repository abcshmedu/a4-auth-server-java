package edu.hm.shareit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Abstract class representing a Medium.
 */
@MappedSuperclass
public abstract class Medium implements Serializable {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private Integer id;

    // Requirements of properties for a Medium
    private static final int MINIMUM_TITLE_LENGTH = 4;

    // Properties of a Medium.
    @Column(name = "TITLE")
    private String title;

    public Medium() {
        this.title = "";
    }

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
    @JsonIgnore
    public boolean isValidMedium() {
        return isValidTitle();
    }

    /**
     * Checks if title meets the requirements.
     *
     * @return true if title is valid, false if not.
     */
    @JsonIgnore
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
