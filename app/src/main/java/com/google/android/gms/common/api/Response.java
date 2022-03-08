package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public class Response<T extends Result> {
    private T zzbb;

    public Response() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Response(T t) {
        this.zzbb = t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T getResult() {
        return this.zzbb;
    }

    public void setResult(T t) {
        this.zzbb = t;
    }
}
