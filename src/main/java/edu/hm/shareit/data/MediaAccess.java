package edu.hm.shareit.data;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import java.util.List;

public interface MediaAccess {

    boolean addMedium(Medium medium);

    List<Book> getBooks();

    List<Disc> getDiscs();
}
