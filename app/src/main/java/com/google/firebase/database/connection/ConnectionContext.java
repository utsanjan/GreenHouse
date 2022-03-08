package com.google.firebase.database.connection;

import com.google.firebase.database.logging.Logger;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class ConnectionContext {
    private final ConnectionAuthTokenProvider authTokenProvider;
    private final String clientSdkVersion;
    private final ScheduledExecutorService executorService;
    private final Logger logger;
    private final boolean persistenceEnabled;
    private final String sslCacheDirectory;
    private final String userAgent;

    public ConnectionContext(Logger logger, ConnectionAuthTokenProvider authTokenProvider, ScheduledExecutorService executorService, boolean persistenceEnabled, String clientSdkVersion, String userAgent, String sslCacheDirectory) {
        this.logger = logger;
        this.authTokenProvider = authTokenProvider;
        this.executorService = executorService;
        this.persistenceEnabled = persistenceEnabled;
        this.clientSdkVersion = clientSdkVersion;
        this.userAgent = userAgent;
        this.sslCacheDirectory = sslCacheDirectory;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public ConnectionAuthTokenProvider getAuthTokenProvider() {
        return this.authTokenProvider;
    }

    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    public boolean isPersistenceEnabled() {
        return this.persistenceEnabled;
    }

    public String getClientSdkVersion() {
        return this.clientSdkVersion;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getSslCacheDirectory() {
        return this.sslCacheDirectory;
    }
}
