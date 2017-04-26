package edu.hm.shareit.business;

import javax.ws.rs.core.Response;

/**
 * Contains possible results for user-queries.
 */
public enum MediaServiceResult {

    //todo: bei fehler "liefer sie in diesem fall ein json-objet mit den attributen code und detail redundant"
    BOOK_MISSING_AUHTOR(Response.Status.BAD_REQUEST, "Book has missing or invalid author."),

    BOOK_ISBN_NOT_FOUND(Response.Status.NOT_FOUND, "ISBN not found."),
    BOOK_ISBN_IMMUTABLE(Response.Status.BAD_REQUEST, "ISBN can't be modified."),
    BOOK_INVALID_ISBN(Response.Status.BAD_REQUEST, "Invalid ISBN."),
    BOOK_ISBN_DUPLICATE(Response.Status.BAD_REQUEST, "ISBN already in use."),

    DISC_INVALID_BARCODE(Response.Status.BAD_REQUEST, "Invalid barcode."),
    DISC_INVALID_DIRECTOR(Response.Status.BAD_REQUEST, "Invalid director."),
    DISC_INVALID_FSK(Response.Status.BAD_REQUEST, "Invalid fsk."),

    MEDIUM_MISSING_TITLE(Response.Status.BAD_REQUEST, "Medium has missing or invalid title."),
    MEDIUM_NOT_FOUND(Response.Status.NOT_FOUND, "Medium was not found."),

    ERROR(Response.Status.BAD_REQUEST, "Something bad happened."),
    SUCCESS(Response.Status.OK, "Success");

    private Response.Status status;
    private String text;

    MediaServiceResult(Response.Status status, String text) {
        this.status = status;
        this.text = text;
    }

    public Response.Status getStatus() {
        return this.status;
    }

    public int getStatusCode() {
        return this.status.getStatusCode();
    }

    public String getText() {
        return text;
    }
}
