package edu.hm.resource;

import javax.ws.rs.core.Response;

public enum MediaServiceResult {
    OK(200, Response.Status.OK),
    CREATED(201, Response.Status.CREATED)
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
