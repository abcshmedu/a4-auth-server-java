package edu.hm.shareit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscTest {

    private static final String discName = "Testdisc";
    private static final String directorName = "Testdirector";
    private static final String barcode = "970306406157";
    private static final int fsk = 18;

    private Disc disc;

    @Before
    public void setUp() {
        disc = new Disc(discName, barcode, directorName, fsk);
    }

    @Test
    public void isValidDisc() throws Exception {
        assertTrue(disc.isValidDisc());

        disc.setDirector("");
        assertFalse(disc.isValidDisc());
    }

    @Test
    public void testEquals() throws Exception {
        Disc eqDisc = new Disc(discName, barcode, directorName, fsk);
        Disc eqDisc2 = new Disc(discName, barcode, directorName, fsk);

        // Reflexivity
        assertTrue(disc.equals(disc));

        // Symmetry
        assertTrue(disc.equals(eqDisc));
        assertTrue(eqDisc.equals(disc));

        // Transitive
        assertTrue(disc.equals(eqDisc));
        assertTrue(eqDisc.equals(eqDisc2));
        assertTrue(disc.equals(eqDisc2));

        // Null
        assertFalse(disc.equals(null));

        // Against empty disc
        assertFalse(disc.equals(new Disc()));
    }

    @Test
    public void testHashCode() throws Exception {
        int i = discName.hashCode();
        i = 31 * i + barcode.hashCode();
        i = 31 * i + directorName.hashCode();
        i = 31 * i + fsk;

        assertEquals(i, disc.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        String expected = String.format("Disc{title='%1$s', barcode='%2$s', director='%3$s, fsk='%4$d'}",
                discName, barcode, directorName, fsk);

        assertEquals(expected, disc.toString());
    }

}