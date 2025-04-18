package com.google.android.gms.auth.api.proxy;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
public interface ProxyApi {

    /* loaded from: classes.dex */
    public interface ProxyResult extends Result {
        ProxyResponse getResponse();
    }

    /* loaded from: classes.dex */
    public interface SpatulaHeaderResult extends Result {
        String getSpatulaHeader();
    }

    PendingResult<SpatulaHeaderResult> getSpatulaHeader(GoogleApiClient googleApiClient);

    PendingResult<ProxyResult> performProxyRequest(GoogleApiClient googleApiClient, ProxyRequest proxyRequest);
}
