package com.google.firebase.database.android;

import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.AuthTokenProvider;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
final /* synthetic */ class AndroidAuthTokenProvider$2$$Lambda$1 implements Runnable {
    private final AuthTokenProvider.TokenChangeListener arg$1;

    private AndroidAuthTokenProvider$2$$Lambda$1(AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        this.arg$1 = tokenChangeListener;
    }

    public static Runnable lambdaFactory$(AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        return new AndroidAuthTokenProvider$2$$Lambda$1(tokenChangeListener);
    }

    @Override // java.lang.Runnable
    public void run() {
        AndroidAuthTokenProvider.AnonymousClass2.lambda$addTokenChangeListener$0(this.arg$1);
    }
}
