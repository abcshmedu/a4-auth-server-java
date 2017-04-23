package edu.hm.resource;

import edu.hm.model.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/media")
public class MediaResource {

    public MediaResource() {
    }

    @POST
    @Path("/books/")
    @Produces("application/jason")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        System.out.println("test");

        return Response.status(200).build();
    }

    public Response getBooks() {
        return Response.status(200).build();
    }

    public Response updateBook(Book book) {
        return Response.status(200).build();
    }
}
