package edu.hm.shareit.business;

import javax.ws.rs.core.Response;

/**
 * Contains possible results for user-queries.
 */
public enum MediaServiceResult {
    BOOK_ISBN_NOT_FOUND(Response.Status.NOT_FOUND, "ISBN not found."),
    BOOK_ISBN_IMMUTABLE(Response.Status.BAD_REQUEST, "ISBN can't be modified."),
    BOOK_ISBN_INVALID(Response.Status.BAD_REQUEST, "Invalid ISBN."),
    BOOK_ISBN_DUPLICATE(Response.Status.BAD_REQUEST, "ISBN already in use."),
    BOOK_MISSING_PARAMETER(Response.Status.BAD_REQUEST, "Missing author or title."),
    BOOK_NOT_FOUND(Response.Status.NOT_FOUND, "Book not found."),

    DISC_BARCODE_INVALID(Response.Status.BAD_REQUEST, "Invalid barcode."),

    ERROR_INVALID_JSON(Response.Status.BAD_REQUEST, "Invalid JSON-Object received."),
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

    public int getStatusCode() { return this.status.getStatusCode(); }

    public String getText() {
        return text;
    }
}
