package com.google.firebase.database.core;

import com.google.firebase.database.connection.ConnectionAuthTokenProvider;
import com.google.firebase.database.core.Context;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
final /* synthetic */ class Context$1$$Lambda$4 implements Runnable {
    private final ConnectionAuthTokenProvider.GetTokenCallback arg$1;
    private final String arg$2;

    private Context$1$$Lambda$4(ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback, String str) {
        this.arg$1 = getTokenCallback;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback, String str) {
        return new Context$1$$Lambda$4(getTokenCallback, str);
    }

    @Override // java.lang.Runnable
    public void run() {
        Context.AnonymousClass1.lambda$onError$1(this.arg$1, this.arg$2);
    }
}
