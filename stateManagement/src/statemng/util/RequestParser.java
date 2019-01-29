package statemng.util;

import statemng.http.api.HttpCookie;
import statemng.http.api.HttpRequest;
import statemng.http.impl.HttpCookieImpl;
import statemng.http.impl.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static statemng.constants.HttpConstants.*;

public class RequestParser {
    private HttpRequest request;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public RequestParser() {
    }

    public HttpRequest parseRequest() throws IOException {

        try {
            request = getRequest(reader.readLine());

            String line;
            while ((line = reader.readLine()) != null && line.length() > 0) {
                getHeader(line);
            }

            if (request.getHeaders() != null && request.getHeaders().containsKey("Cookie")){
                setCookies(request);
            }

            while ((line = reader.readLine()) != null && line.length() > 0) {
                getRequestBody(line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return request;

    }

    private void setCookies(HttpRequest request) {
        String cookieTokens = request.getHeaders().get("Cookie");
        Arrays.stream(cookieTokens.split("; ")).forEach(
                cookieKvp ->{
                    String[] cookieParams = cookieKvp.split("=");
                    HttpCookie cookie =
                            new HttpCookieImpl(cookieParams[0], cookieParams[1]);
                    request.addCookie(cookie);
                }
        );

    }

    private void getRequestBody(String input) {
        String[] bodyTokens = input.split(SEPARATOR_BODY_PARAMS);
        for (String bodyToken : bodyTokens) {
            String[] kvp = bodyToken.split(SEPARATOR_BODY_KVP);
            request.addBodyParameter(kvp[0], kvp[1]);
        }
    }

    private void getHeader(String input) {
        String[] headerTokens = input.split(SEPARATOR_HEADER_KVP);
        request.addHeader(headerTokens[0], headerTokens[1]);
    }

    private HttpRequest getRequest(String input) {
        String[] reqTokens = input.split(SEPARATOR_EMPTY_SPACE);

        return new HttpRequestImpl(reqTokens[0], reqTokens[1]);
    }



}
