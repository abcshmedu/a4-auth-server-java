package edu.hm.shareit.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;

/**
 * Contains possible results for user-queries.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MediaServiceResult implements Response.StatusType {

    BOOK_MISSING_AUHTOR(Response.Status.BAD_REQUEST, "Invalid author."),

    BOOK_INVALID_ISBN(Response.Status.BAD_REQUEST, "Invalid ISBN."),

    DISC_INVALID_BARCODE(Response.Status.BAD_REQUEST, "Invalid barcode."),
    DISC_INVALID_DIRECTOR(Response.Status.BAD_REQUEST, "Invalid director."),
    DISC_INVALID_FSK(Response.Status.BAD_REQUEST, "Invalid fsk."),

    MEDIUM_ID_IMMUTABLE(Response.Status.BAD_REQUEST, "Media-ID can't be changed."),
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
    @JsonProperty("code")
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    @JsonIgnore
    public Response.Status.Family getFamily() {
        return family;
    }

    @Override
    @JsonProperty("detail")
    public String getReasonPhrase() {
        return reason;
    }
}
