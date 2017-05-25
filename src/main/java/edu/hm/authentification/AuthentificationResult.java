package edu.hm.authentification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;

/**
 * Possible outcomes of the authentification-process.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthentificationResult implements Response.StatusType {

    USER_NOT_FOUND(Response.Status.BAD_REQUEST, "User not found"),
    USER_NOT_QUALIFIED(Response.Status.BAD_REQUEST, "Insufficient permissions"),
    USER_INVALID_CREDENTIALS(Response.Status.BAD_REQUEST, "Invalid password"),
    USER_TOKEN_EXPIRED(Response.Status.BAD_REQUEST, "Token expired"),
    USER_INVALID_TOKEN(Response.Status.BAD_REQUEST, "Invalid token"),

    UNKNOWN_CLIENT(Response.Status.BAD_REQUEST, "Unknown client-entity"),

    ERROR(Response.Status.BAD_REQUEST, "Something bad happened."),
    SUCCESS(Response.Status.OK, "Success");

    private final int statusCode;
    private final String reason;
    private final Response.Status.Family family;

    /**
     * Initializes the enum.
     *
     * @param status of this property.
     * @param reason of this property.
     */
    AuthentificationResult(Response.Status status, String reason) {
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
