package httpProtocolP2.util;

import httpProtocolP2.http.api.HttpRequest;
import httpProtocolP2.http.impl.HttpRequestImpl;
import httpProtocolP2.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static httpProtocolP2.constants.HttpConstants.*;

public class RequestParser {
    private Server server;
    private HttpRequest request;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public RequestParser(Server server) {
        this.server = server;
    }

    public HttpRequest parseRequest() throws IOException {

        try {
            List<String> paths = Arrays.asList(reader.readLine().split(SEPARATOR_EMPTY_SPACE));
            server.setPaths(paths);

            request = getRequest(reader.readLine());

            String line;
            while ((line = reader.readLine()) != null && line.length() > 0) {
                getHeader(line);
            }

            while ((line = reader.readLine()) != null && line.length() > 0) {

                getRequestBody(line);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return request;

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
