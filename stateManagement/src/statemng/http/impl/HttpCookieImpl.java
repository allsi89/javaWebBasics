package statemng.http.impl;

import statemng.http.api.HttpCookie;

public class HttpCookieImpl implements HttpCookie {
    private String key;
    private String value;

    public HttpCookieImpl(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return String.format("%s <-> %s", this.key, this.value);
    }
}
