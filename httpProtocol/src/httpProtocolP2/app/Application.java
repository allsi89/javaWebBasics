package httpProtocolP2.app;

import httpProtocolP2.http.api.HttpRequest;
import httpProtocolP2.http.api.HttpResponse;
import httpProtocolP2.server.Server;
import httpProtocolP2.util.RequestParser;
import httpProtocolP2.util.ResponseBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static httpProtocolP2.constants.HttpConstants.SERVER_ENCODING;

public class Application {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        RequestParser parser = new RequestParser(server);
        HttpRequest request = parser.parseRequest();
        ResponseBuilder builder = new ResponseBuilder(server);
        HttpResponse response = builder.build(request);
        System.out.println(new String(response.getBytes(), SERVER_ENCODING));


    }
}
