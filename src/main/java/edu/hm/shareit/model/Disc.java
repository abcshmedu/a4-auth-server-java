package edu.hm.shareit.model;

/**
 * Concrete class representing a Disc.
 */
public class Disc extends Medium {

    /* Properties of a Disc. */
    private final String barcode;
    private final String director;
    private final int fsk;

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

    public int getFsk() {
        return fsk;
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

        return (barcode != null ? barcode.equals(disc.barcode) : disc.barcode != null) &&
                (director != null ? director.equals(disc.director) : disc.director != null) &&
                fsk == disc.fsk;
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
        return "Disc{" +
                "barcode='" + barcode + '\'' +
                ", director='" + director + '\'' +
                ", fsk='" + fsk + '\'' +
                '}';
    }
}
