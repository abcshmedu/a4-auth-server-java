package edu.hm.shareit.resources;

import javax.ws.rs.core.Response;

public enum MediaServiceResult {
    OK(200, Response.Status.OK),
    NO_CONTENT(204, Response.Status.NO_CONTENT)
    ;

    int code;
    Response.Status status;

    MediaServiceResult(int code, Response.Status status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public Response.Status getStatus() {
        return status;
    }
}