package edu.hm.shareit.resources;

import edu.hm.shareit.business.MediaService;
import edu.hm.shareit.business.MediaServiceImpl;
import edu.hm.shareit.business.MediaServiceResult;
import edu.hm.shareit.model.Book;
import edu.hm.shareit.model.Disc;
import edu.hm.shareit.model.Medium;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * API-Layer (REST)
 */
@Path("/media")
public class MediaResource {

    private static final MediaService mediaService = new MediaServiceImpl();

    // Books -----------------------------------------------------------------------------------------------------------

    /**
     * Adds a new book to persistent storage.
     *
     * @param book the book to add to persistent storage.
     * @return response according to result.
     */
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        MediaServiceResult msr = mediaService.addBook(book);
        return Response.status(msr.getStatusCode()).build();
    }

    /**
     * Returns a book with specified isbn.
     *
     * @param string isbn of the book to be returned.
     * @return the book or MediaServiceResult.BOOK_NOT_FOUND.
     */
    @GET
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String string) {
        // todo
        return Response.status(200).build();
    }

    /**
     * Returns all books available in persistent storage.
     *
     * @return all books.
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        Medium[] media = mediaService.getBooks();
        return Response.ok(media).build();
    }

    /**
     * Updates a book.
     *
     * @param book updated book.
     * @return response according to result. //todo add the right responses
     */
    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book) {
        return Response.status(200).build();
    }

    // Discs -----------------------------------------------------------------------------------------------------------

    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {
        MediaServiceResult msr = mediaService.addDisc(disc);
        return Response.status(msr.getStatusCode()).build();
    }

    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {
        return Response.status(200).build();
    }

    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        Medium[] media = mediaService.getDiscs();
        return Response.ok(media).build();
    }

    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc) {
        return Response.status(200).build();
    }

}
