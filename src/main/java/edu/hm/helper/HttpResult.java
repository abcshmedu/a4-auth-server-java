package edu.hm.helper;

/**
 * Simple container for a reponse to a HTTP-request.
 */
public class HttpResult {

    private int statusCode;
    private String statusMessage;
    private String content;

    public HttpResult(int statusCode, String statusMessage, String content) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "HttpResult{"
                + "statusCode=" + statusCode
                + ", statusMessage='" + statusMessage + '\''
                + ", content='" + content + '\''
                + '}';
    }
}