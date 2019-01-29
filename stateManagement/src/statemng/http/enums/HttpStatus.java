package statemng.http.enums;

import static statemng.constants.HttpConstants.SERVER_HTTP_VERSION;

public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    UNAUTHORIZED(401, "Unauthorized"),
    BAD_REQUEST(400, "Bad Request");

    private String statusPhrase;
    private String statusResponse;
    private int statusCode;

    HttpStatus(int statusCode, final String statusText) {
        this.statusCode = statusCode;
        this.statusPhrase = String.format("%d %s", statusCode, statusText);
        this.statusResponse = String.format("%s %d %s", SERVER_HTTP_VERSION, statusCode, statusText);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusPhrase() {
        return this.statusPhrase;
    }

    public String getAsResponse() {
        return this.statusResponse;
    }
}
