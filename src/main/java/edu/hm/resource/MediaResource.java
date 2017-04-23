package edu.hm.resource;

import edu.hm.model.Book;

import javax.ws.rs.core.Response;

public class MediaResource {

    public MediaResource() {
    }

    public Response createBook(Book book) {
        return Response.status(200).build();
    }

    public Response getBooks() {
        return Response.status(200).build();
    }

    public Response updateBook(Book book) {
        return Response.status(200).build();
    }
}
