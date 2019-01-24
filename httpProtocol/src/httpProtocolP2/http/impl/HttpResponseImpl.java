package httpProtocolP2.http.impl;

import httpProtocolP2.http.api.HttpResponse;
import httpProtocolP2.http.enums.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static httpProtocolP2.constants.HttpConstants.SEPARATOR_HEADER_KVP;
import static httpProtocolP2.constants.HttpConstants.SERVER_ENCODING;

public class HttpResponseImpl implements HttpResponse {


    private HashMap<String, String> headers;
    private int statusCode;
    private byte[] content;
    private byte[] bytes;

    public HttpResponseImpl() {
        this.headers = new HashMap<>();
        this.content = new byte[0];
        this.bytes = new byte[0];
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return null;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getBytes() {
        //The getBytes() method should return the whole response (ResponseLine + Headers + Content) as byte array..

        byte[] responseLineBytes = getResponseLineBytes();
        byte[] headersBytes = this.getHeadersBytes();
        byte[] bodyBytes = this.getContent();

        byte[] bytes = new byte[responseLineBytes.length + headersBytes.length + bodyBytes.length];

        System.arraycopy(responseLineBytes, 0, bytes, 0, responseLineBytes.length);
        System.arraycopy(headersBytes, 0, bytes, responseLineBytes.length, headersBytes.length);
        System.arraycopy(bodyBytes, 0, bytes, responseLineBytes.length + headersBytes.length, bodyBytes.length);

        return bytes;
    }

    private byte[] getResponseLineBytes() {
        StringBuilder responseLine = new StringBuilder();
        for (HttpStatus value : HttpStatus.values()) {
            if (value.getStatusCode() == this.getStatusCode()) {
                responseLine.append(value.getAsResponse()).append(System.lineSeparator());
            }
        }
        return responseLine.toString().getBytes(SERVER_ENCODING);
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }


    private byte[] getHeadersBytes() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            result.append(header.getKey())
                    .append(SEPARATOR_HEADER_KVP)
                    .append(header.getValue())
                    .append(System.lineSeparator());
        }

        result.append(System.lineSeparator());
        return result.toString().getBytes(SERVER_ENCODING);
    }
}
