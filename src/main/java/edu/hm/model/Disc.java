package edu.hm.model;

public class Disc extends Medium{

    private String barcode;
    private String director;
    private String fsk;

    public Disc(String title, String barcode, String director, String fsk) {
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

    public String getFsk() {
        return fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Disc disc = (Disc) o;

        if (barcode != null ? !barcode.equals(disc.barcode) : disc.barcode != null) return false;
        if (director != null ? !director.equals(disc.director) : disc.director != null) return false;
        return fsk != null ? fsk.equals(disc.fsk) : disc.fsk == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (fsk != null ? fsk.hashCode() : 0);
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
