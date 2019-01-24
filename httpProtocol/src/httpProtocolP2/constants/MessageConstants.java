package httpProtocolP2.constants;

public class MessageConstants {
    public static final String INVALID_PATH_MESSAGE = "Server paths are not valid.";
    public static final String NOT_FOUND_404 = "HTTP/1.1 404 Not Found";

    public static final byte[] NOT_FOUND_CONTENT = "The requested functionality was not found.".getBytes();
    public static final byte[] NOT_AUTHORIZED_CONTENT =
            "You are not authorized to access the requested functionality.".getBytes();
    public static final byte[] MALFORMED_REQUEST_CONTENT =
            "There was an error with the requested functionality due to malformed request.".getBytes();
    public static final String OK_CONTENT = "Greetings %s! You have successfully created %s with %s, %s.";
}
