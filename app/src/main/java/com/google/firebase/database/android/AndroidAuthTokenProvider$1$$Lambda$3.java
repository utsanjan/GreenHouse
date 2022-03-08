package com.google.firebase.database.android;

import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
final /* synthetic */ class AndroidAuthTokenProvider$1$$Lambda$3 implements IdTokenListener {
    private final ExecutorService arg$1;
    private final AuthTokenProvider.TokenChangeListener arg$2;

    private AndroidAuthTokenProvider$1$$Lambda$3(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        this.arg$1 = executorService;
        this.arg$2 = tokenChangeListener;
    }

    public static IdTokenListener lambdaFactory$(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        return new AndroidAuthTokenProvider$1$$Lambda$3(executorService, tokenChangeListener);
    }

    @Override // com.google.firebase.auth.internal.IdTokenListener
    public void onIdTokenChanged(InternalTokenResult internalTokenResult) {
        AndroidAuthTokenProvider.AnonymousClass1.lambda$addTokenChangeListener$3(this.arg$1, this.arg$2, internalTokenResult);
    }
}
