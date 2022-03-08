package com.google.firebase.crashlytics.internal.network;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Response;

/* loaded from: classes.dex */
public class HttpResponse {
    private String body;
    private int code;
    private Headers headers;

    HttpResponse(int code, String body, Headers headers) {
        this.code = code;
        this.body = body;
        this.headers = headers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HttpResponse create(Response response) throws IOException {
        String body = response.body() == null ? null : response.body().string();
        return new HttpResponse(response.code(), body, response.headers());
    }

    public int code() {
        return this.code;
    }

    public String body() {
        return this.body;
    }

    public String header(String name) {
        return this.headers.get(name);
    }
}
