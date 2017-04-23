package edu.hm.resource;

import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;

public interface MediaService {

    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc disc);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(Book book);
    MediaServiceResult updateDisc(Disc disc);
}
