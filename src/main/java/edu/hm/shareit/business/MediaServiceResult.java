package edu.hm.shareit.business;

import javax.ws.rs.core.Response;

/**
 * Contains possible results for user-queries.
 */
public enum MediaServiceResult implements Response.StatusType {

    //todo: bei fehler "liefer sie in diesem fall ein json-objet mit den attributen statusCode und detail redundant"
    BOOK_MISSING_AUHTOR(Response.Status.BAD_REQUEST, "Invalid author."),

    BOOK_ISBN_NOT_FOUND(Response.Status.NOT_FOUND, "ISBN not found."),
    BOOK_ISBN_IMMUTABLE(Response.Status.BAD_REQUEST, "ISBN can't be modified."),
    BOOK_INVALID_ISBN(Response.Status.BAD_REQUEST, "Invalid ISBN."),
    BOOK_ISBN_DUPLICATE(Response.Status.BAD_REQUEST, "ISBN already in use."),

    DISC_INVALID_BARCODE(Response.Status.BAD_REQUEST, "Invalid barcode."),
    DISC_INVALID_DIRECTOR(Response.Status.BAD_REQUEST, "Invalid director."),
    DISC_INVALID_FSK(Response.Status.BAD_REQUEST, "Invalid fsk."),

    MEDIUM_INVALID_UPDATE_INFORMATION(Response.Status.BAD_REQUEST, "Invalid update-information."),
    MEDIUM_MISSING_TITLE(Response.Status.BAD_REQUEST, "Missing or invalid title."),
    MEDIUM_NOT_FOUND(Response.Status.NOT_FOUND, "Medium was not found."),

    ERROR(Response.Status.BAD_REQUEST, "Something bad happened."),
    SUCCESS(Response.Status.OK, "Success");

    private final int statusCode;
    private final String reason;
    private final Response.Status.Family family;

    MediaServiceResult(Response.Status status, String reason) {
        this.statusCode = status.getStatusCode();
        this.reason = reason;
        this.family = Response.Status.Family.familyOf(statusCode);
    }


    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public Response.Status.Family getFamily() {
        return family;
    }

    @Override
    public String getReasonPhrase() {
        return reason;
    }
}
