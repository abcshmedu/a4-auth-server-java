package edu.hm.shareit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.hm.shareit.business.InvalidUpdateException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Concrete class representing a Disc.
 */
@Entity
@Table(name = "DISCS")
public class Disc extends Medium {

    // Requirements for properties of a disc.
    private static final int MINIMUM_BARCODE_LENGTH = 4;
    private static final int MINIMUM_DIRECTOR_LENGTH = 4;
    private static final int MINIMUM_FSK_AGE = 0;
    private static final int MAXIMUM_FSK_AGE = 100;

    // Properties of a Disc.
    @Id
    @Column(name = "BARCODE")
    private final String barcode;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "FSK")
    private int fsk;

    public Disc() {
        this("", "", "", 0);
    }

    public Disc(String title, String barcode, String director, int fsk) {
        super(title);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    /**
     * Checks if this disc is valid.
     *
     * @return true if it is valid, false if not.
     */
    @JsonIgnore
    public boolean isValidDisc() {
        return isValidMedium() && isValidBarcode() && isValidDirector() && isValidFsk();
    }

    /**
     * Checks if barcode meets the requirements.
     *
     * @return true if barcode is valid, false if not.
     */
    @JsonIgnore
    public boolean isValidBarcode() {
        return barcode.length() >= MINIMUM_BARCODE_LENGTH;
    }

    /**
     * Checks if director meets the requirements.
     *
     * @return true if director is valid, false if not.
     */
    @JsonIgnore
    public boolean isValidDirector() {
        return director.length() >= MINIMUM_DIRECTOR_LENGTH;
    }

    /**
     * Checks if fsk meets the requirements.
     *
     * @return true if fsk is valid, false if not.
     */
    @JsonIgnore
    public boolean isValidFsk() {
        return fsk >= MINIMUM_FSK_AGE && fsk <= MAXIMUM_FSK_AGE;
    }

    /**
     * Updates this discs information.
     *
     * @param disc with updated information.
     * @throws InvalidUpdateException when disc has invalid information.
     */
    public void updateDisc(Disc disc) throws InvalidUpdateException {
        // TODO: this is a last minute hack, fix it
        if (disc.getTitle().length() == 0 && disc.getDirector().length() == 0
                && disc.getFsk() == 0) {
            throw new InvalidUpdateException();
        }

        // We only receive updated info!
        if (disc.getTitle().length() == 0) {
            disc.setTitle(getTitle());
        } else if (!disc.isValidTitle()) {
            throw new InvalidUpdateException();
        }

        if (disc.getDirector().length() == 0) {
            disc.setDirector(getDirector());
        } else if (!disc.isValidDirector()) {
            throw new InvalidUpdateException();
        }

        if (!disc.isValidFsk()) {
            throw new InvalidUpdateException();
        }

        super.updateMedium(disc);
        setDirector(disc.getDirector());
        setFsk(disc.getFsk());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Disc)) {
            return false;
        }

        Disc disc = (Disc) obj;

        return (barcode != null ? barcode.equals(disc.barcode) : disc.barcode != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + fsk;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Disc{title='%1$s', barcode='%2$s', director='%3$s, fsk='%4$d'}",
                super.getTitle(), barcode, director, fsk);
    }
}
