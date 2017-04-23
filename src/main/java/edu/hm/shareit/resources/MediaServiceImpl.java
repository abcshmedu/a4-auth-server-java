package edu.hm.resource;

import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;

import java.util.ArrayList;
import java.util.List;


public class MediaServiceImpl implements MediaService{

    private List<Medium> data = new ArrayList<>();

    public MediaServiceImpl() {
    }

    @Override
    public MediaServiceResult addBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        return null;
    }

    @Override
    public Medium[] getBooks() {
        return new Medium[0];
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }

    @Override
    public MediaServiceResult updateBook(Book book) {
        return null;
    }

    @Override
    public MediaServiceResult updateDisc(Disc disc) {
        return null;
    }




    public List<Medium> getData() {
        return data;
    }

    public void setData(List<Medium> data) {
        this.data = data;
    }
}
