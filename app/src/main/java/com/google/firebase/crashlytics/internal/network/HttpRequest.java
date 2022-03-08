package com.google.firebase.crashlytics.internal.network;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes.dex */
public class HttpRequest {
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().callTimeout(10000, TimeUnit.MILLISECONDS).build();
    private static final int DEFAULT_TIMEOUT_MS = 10000;
    private MultipartBody.Builder bodyBuilder = null;
    private final Map<String, String> headers = new HashMap();
    private final HttpMethod method;
    private final Map<String, String> queryParams;
    private final String url;

    public HttpRequest(HttpMethod method, String url, Map<String, String> queryParams) {
        this.method = method;
        this.url = url;
        this.queryParams = queryParams;
    }

    public HttpRequest header(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public HttpRequest header(Map.Entry<String, String> entry) {
        return header(entry.getKey(), entry.getValue());
    }

    private MultipartBody.Builder getOrCreateBodyBuilder() {
        if (this.bodyBuilder == null) {
            this.bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        }
        return this.bodyBuilder;
    }

    public HttpRequest part(String name, String value) {
        this.bodyBuilder = getOrCreateBodyBuilder().addFormDataPart(name, value);
        return this;
    }

    public HttpRequest part(String name, String filename, String contentType, File part) {
        MediaType type = MediaType.parse(contentType);
        RequestBody body = RequestBody.create(type, part);
        this.bodyBuilder = getOrCreateBodyBuilder().addFormDataPart(name, filename, body);
        return this;
    }

    public String method() {
        return this.method.name();
    }

    private Request build() {
        Request.Builder builder = new Request.Builder().cacheControl(new CacheControl.Builder().noCache().build());
        HttpUrl.Builder urlBuilder = HttpUrl.parse(this.url).newBuilder();
        for (Map.Entry<String, String> entry : this.queryParams.entrySet()) {
            urlBuilder = urlBuilder.addEncodedQueryParameter(entry.getKey(), entry.getValue());
        }
        Request.Builder builder2 = builder.url(urlBuilder.build());
        for (Map.Entry<String, String> entry2 : this.headers.entrySet()) {
            builder2 = builder2.header(entry2.getKey(), entry2.getValue());
        }
        MultipartBody.Builder builder3 = this.bodyBuilder;
        RequestBody body = builder3 == null ? null : builder3.build();
        return builder2.method(this.method.name(), body).build();
    }

    public HttpResponse execute() throws IOException {
        Request request = build();
        Call call = CLIENT.newCall(request);
        return HttpResponse.create(call.execute());
    }
}
