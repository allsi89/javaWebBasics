package statemng.http.api;

import java.util.HashMap;
import java.util.List;

public interface HttpRequest {

    HashMap<String, String> getHeaders();
    HashMap<String, String> getBodyParameters();
    String getMethod();
    void setMethod(String method);
    String getRequestUrl();
    void setRequestUrl(String requestUrl);
    void addHeader(String header, String value);
    void addBodyParameter(String parameter, String value);
    boolean isResource();
    List<HttpCookie> getCookies();
    void addCookie(HttpCookie cookie);

}
