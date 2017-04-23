package edu.hm.shareit.resources;

import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc disc);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(Book book);
    MediaServiceResult updateDisc(Disc disc);
}
