package httpProtocolP2.http.impl;

import httpProtocolP2.http.api.HttpRequest;

import java.util.*;

import static httpProtocolP2.constants.HttpConstants.SEPARATOR_DOT;


public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private HashMap<String, String> headers;
    private HashMap<String, String> bodyParameters;

    public HttpRequestImpl(String method, String requestUrl) {
        this.setMethod(method);
        this.setRequestUrl(requestUrl);
        this.headers = new HashMap<>();
        this.bodyParameters = new HashMap<>();
    }

      @Override
    public HashMap<String, String> getHeaders() {
        return new HashMap<>(this.headers);
    }

    @Override
    public HashMap<String, String> getBodyParameters() {
        return new HashMap<>(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
       this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(SEPARATOR_DOT);
    }


}
