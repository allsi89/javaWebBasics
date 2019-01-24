package httpProtocolP2.util;

import httpProtocolP2.http.api.HttpRequest;
import httpProtocolP2.http.api.HttpResponse;
import httpProtocolP2.http.impl.HttpResponseImpl;
import httpProtocolP2.server.Server;

import java.util.Base64;
import java.util.Map;

import static httpProtocolP2.constants.MessageConstants.*;

public class ResponseBuilder {

    private Server server;
    private HttpResponse response;

    public ResponseBuilder(Server server) {
        this.server = server;
        this.response = new HttpResponseImpl();
    }


    public HttpResponse build(HttpRequest request) {
        if (request == null){
            throw new IllegalArgumentException();
        }

        if (!server.isPathValid(request.getRequestUrl())) {
            response.setStatusCode(404);
            response.setContent(NOT_FOUND_CONTENT);
        }else if (!request.getHeaders().containsKey("Authorization")){
            response.setStatusCode(401);
            response.setContent(NOT_AUTHORIZED_CONTENT);
        }
        else if (request.getMethod().equals("POST") && request.getBodyParameters().size() == 0){
            response.setStatusCode(400);
            response.setContent(MALFORMED_REQUEST_CONTENT);
        }
        else{
            response.setStatusCode(200);
            int count = 0;
            String first = null;
            String second = null;
            String third = null;
            for (Map.Entry<String, String> bodyParameter : request.getBodyParameters().entrySet()) {
                count++;
                if (count == 1){
                    first = bodyParameter.getValue();
                }
                if (count == 2){
                    second = bodyParameter.getKey() + " - " + bodyParameter.getValue();
                }
                if (count == 3){
                    third = bodyParameter.getKey() + " - " + bodyParameter.getValue();
                }
            }

            String content = String.format(OK_CONTENT,
                    new String(Base64.getDecoder().decode
                            (request.getHeaders().get("Authorization").split("\\s+")[1])),
                   first, second, third);
            response.setContent(content.getBytes());
        }

        setResponseHeaders(request);
        return response;

    }


    private void setResponseHeaders(HttpRequest request){
        for (Map.Entry<String, String> kvp : request.getHeaders().entrySet()) {
            if (kvp.getKey().equals("Date") ||
                    kvp.getKey().equals("Host") ||
                    kvp.getKey().equals("Content-Type")) {
                response.addHeader(kvp.getKey(), kvp.getValue());
            }
        }
    }
}
