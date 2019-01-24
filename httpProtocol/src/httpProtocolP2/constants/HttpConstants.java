package httpProtocolP2.constants;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpConstants {

    public static final String SERVER_HTTP_VERSION = "HTTP/1.1";

    public static final Charset SERVER_ENCODING = StandardCharsets.UTF_8;

    public static final String SEPARATOR_EMPTY_SPACE = "\\s+";
    public static final String SEPARATOR_HEADER_KVP = ": ";
    public static final String SEPARATOR_BODY_PARAMS = "&";
    public static final String SEPARATOR_BODY_KVP = "=";
    public static final String SEPARATOR_DOT = ".";
}
