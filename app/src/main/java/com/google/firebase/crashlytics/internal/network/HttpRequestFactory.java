package com.google.firebase.crashlytics.internal.network;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpRequestFactory {
    public HttpRequest buildHttpRequest(HttpMethod method, String url) {
        return buildHttpRequest(method, url, Collections.emptyMap());
    }

    public HttpRequest buildHttpRequest(HttpMethod method, String url, Map<String, String> queryParams) {
        return new HttpRequest(method, url, queryParams);
    }
}
