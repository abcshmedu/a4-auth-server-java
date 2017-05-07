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
 * API-Layer (REST).
 */
@Path("/media")
public class MediaResource {

    private static final MediaService MEDIASERVICE = new MediaServiceImpl();

    // Books -----------------------------------------------------------------------------------------------------------

    /**
     * Adds a new book to persistent storage.
     *
     * @param book the book to add to persistent storage.
     * @return response according to result.
     * @see MediaService#addBook(Book)
     */
    @POST
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        MediaServiceResult msr = MEDIASERVICE.addBook(book);
        return Response.status(msr).entity(Json.serializeObject(msr)).build();
    }

    /**
     * Returns a book with specified isbn.
     *
     * @param isbn of the book to be returned.
     * @return the book and MediaServiceResult.SUCCESS,
     * MediaServiceResult.MEDIUM_NOT_FOUND if the requested book was not found.
     */
    @GET
    @Path("/books/{isbn}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        Book book = (Book) MEDIASERVICE.getBook(isbn);

        Response response;
        if (book == null) {
            response = Response.status(MediaServiceResult.MEDIUM_NOT_FOUND)
                    .entity(Json.serializeObject(MediaServiceResult.MEDIUM_NOT_FOUND))
                    .build();
        } else {
            response = Response.status(MediaServiceResult.SUCCESS)
                    .entity(book)
                    .build();
        }
        return response;
    }

    /**
     * Returns all books available in persistent storage.
     *
     * @return all books, empty array if none.
     */
    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        Medium[] media = MEDIASERVICE.getBooks();
        return Response.ok(media).build();
    }

    /**
     * Updates a book.
     *
     * @param book updated book.
     * @param isbn isbn to be updated.
     * @return response according to result.
     * @see MediaService#updateBook(Book)
     */
    @PUT
    @Path("/books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {
        MediaServiceResult msr = MEDIASERVICE.updateBook(book, isbn);
        return Response.status(msr)
                .entity(Json.serializeObject(msr))
                .build();
    }

    // Discs -----------------------------------------------------------------------------------------------------------

    /**
     * Adds a new disc to persistent storage.
     *
     * @param disc the disc to add to persistent storage.
     * @return response according to result.
     * @see MediaService#addDisc(Disc)
     */
    @POST
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {
        MediaServiceResult msr = MEDIASERVICE.addDisc(disc);
        return Response.status(msr)
                .entity(Json.serializeObject(msr))
                .build();
    }

    /**
     * Returns a disc with specified barcode.
     *
     * @param barcode of the book to be returned.
     * @return the disc and MediaServiceResult.SUCCESS,
     * MediaServiceResult.MEDIUM_NOT_FOUND if the requested disc was not found.
     */
    @GET
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {
        Disc disc = (Disc) MEDIASERVICE.getDisc(barcode);

        Response response;
        if (disc == null) {
            response = Response.status(MediaServiceResult.MEDIUM_NOT_FOUND)
                    .entity(Json.serializeObject(MediaServiceResult.MEDIUM_NOT_FOUND))
                    .build();
        } else {
            response = Response.status(MediaServiceResult.SUCCESS)
                    .entity(disc)
                    .build();
        }
        return response;
    }

    /**
     * Returns all discs available in persistent storage.
     *
     * @return all discs, empty array if none.
     */
    @GET
    @Path("/discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        Medium[] media = MEDIASERVICE.getDiscs();
        return Response.ok(media).build();
    }

    /**
     * Updates a disc.
     *
     * @param disc updated disc.
     * @param barcode of disc to be updated.
     * @return response according to result.
     * @see MediaService#updateDisc(Disc)
     */
    @PUT
    @Path("/discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode) {
        MediaServiceResult msr = MEDIASERVICE.updateDisc(disc, barcode);
        return Response.status(msr)
                .entity(Json.serializeObject(msr))
                .build();
    }

}
