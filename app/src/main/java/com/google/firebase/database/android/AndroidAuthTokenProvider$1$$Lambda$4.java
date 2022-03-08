package com.google.firebase.database.android;

import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.internal.InternalTokenResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public final /* synthetic */ class AndroidAuthTokenProvider$1$$Lambda$4 implements Runnable {
    private final AuthTokenProvider.TokenChangeListener arg$1;
    private final InternalTokenResult arg$2;

    private AndroidAuthTokenProvider$1$$Lambda$4(AuthTokenProvider.TokenChangeListener tokenChangeListener, InternalTokenResult internalTokenResult) {
        this.arg$1 = tokenChangeListener;
        this.arg$2 = internalTokenResult;
    }

    public static Runnable lambdaFactory$(AuthTokenProvider.TokenChangeListener tokenChangeListener, InternalTokenResult internalTokenResult) {
        return new AndroidAuthTokenProvider$1$$Lambda$4(tokenChangeListener, internalTokenResult);
    }

    @Override // java.lang.Runnable
    public void run() {
        AndroidAuthTokenProvider.AnonymousClass1.lambda$addTokenChangeListener$2(this.arg$1, this.arg$2);
    }
}
