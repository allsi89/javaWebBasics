package statemng.app;

import statemng.http.api.HttpRequest;
import statemng.util.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        RequestParser parser = new RequestParser();
        HttpRequest request = parser.parseRequest();
        request.getCookies().forEach(System.out::println);


    }
}
