package com.google.firebase.database.core;

import com.google.firebase.database.connection.ConnectionAuthTokenProvider;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
final /* synthetic */ class Context$$Lambda$1 implements ConnectionAuthTokenProvider {
    private final AuthTokenProvider arg$1;
    private final ScheduledExecutorService arg$2;

    private Context$$Lambda$1(AuthTokenProvider authTokenProvider, ScheduledExecutorService scheduledExecutorService) {
        this.arg$1 = authTokenProvider;
        this.arg$2 = scheduledExecutorService;
    }

    public static ConnectionAuthTokenProvider lambdaFactory$(AuthTokenProvider authTokenProvider, ScheduledExecutorService scheduledExecutorService) {
        return new Context$$Lambda$1(authTokenProvider, scheduledExecutorService);
    }

    @Override // com.google.firebase.database.connection.ConnectionAuthTokenProvider
    public void getToken(boolean z, ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback) {
        Context.lambda$wrapAuthTokenProvider$0(this.arg$1, this.arg$2, z, getTokenCallback);
    }
}
