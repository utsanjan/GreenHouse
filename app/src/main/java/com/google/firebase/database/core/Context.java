package com.google.firebase.database.core;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.android.AndroidPlatform;
import com.google.firebase.database.connection.ConnectionAuthTokenProvider;
import com.google.firebase.database.connection.ConnectionContext;
import com.google.firebase.database.connection.HostInfo;
import com.google.firebase.database.connection.PersistentConnection;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.database.core.persistence.NoopPersistenceManager;
import com.google.firebase.database.core.persistence.PersistenceManager;
import com.google.firebase.database.core.utilities.DefaultRunLoop;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.logging.Logger;
import java.io.File;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class Context {
    private static final long DEFAULT_CACHE_SIZE = 10485760;
    protected AuthTokenProvider authTokenProvider;
    protected EventTarget eventTarget;
    protected FirebaseApp firebaseApp;
    private PersistenceManager forcedPersistenceManager;
    protected List<String> loggedComponents;
    protected Logger logger;
    protected boolean persistenceEnabled;
    protected String persistenceKey;
    private Platform platform;
    protected RunLoop runLoop;
    protected String userAgent;
    protected Logger.Level logLevel = Logger.Level.INFO;
    protected long cacheSize = DEFAULT_CACHE_SIZE;
    private boolean frozen = false;
    private boolean stopped = false;

    private Platform getPlatform() {
        if (this.platform == null) {
            initializeAndroidPlatform();
        }
        return this.platform;
    }

    private synchronized void initializeAndroidPlatform() {
        this.platform = new AndroidPlatform(this.firebaseApp);
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public synchronized void freeze() {
        if (!this.frozen) {
            this.frozen = true;
            initServices();
        }
    }

    public void requireStarted() {
        if (this.stopped) {
            restartServices();
            this.stopped = false;
        }
    }

    private void initServices() {
        ensureLogger();
        getPlatform();
        ensureUserAgent();
        ensureEventTarget();
        ensureRunLoop();
        ensureSessionIdentifier();
        ensureAuthTokenProvider();
    }

    private void restartServices() {
        this.eventTarget.restart();
        this.runLoop.restart();
    }

    public void stop() {
        this.stopped = true;
        this.eventTarget.shutdown();
        this.runLoop.shutdown();
    }

    public void assertUnfrozen() {
        if (isFrozen()) {
            throw new DatabaseException("Modifications to DatabaseConfig objects must occur before they are in use");
        }
    }

    public List<String> getOptDebugLogComponents() {
        return this.loggedComponents;
    }

    public Logger.Level getLogLevel() {
        return this.logLevel;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public LogWrapper getLogger(String component) {
        return new LogWrapper(this.logger, component);
    }

    public LogWrapper getLogger(String component, String prefix) {
        return new LogWrapper(this.logger, component, prefix);
    }

    public ConnectionContext getConnectionContext() {
        return new ConnectionContext(getLogger(), wrapAuthTokenProvider(getAuthTokenProvider(), getExecutorService()), getExecutorService(), isPersistenceEnabled(), FirebaseDatabase.getSdkVersion(), getUserAgent(), getSSLCacheDirectory().getAbsolutePath());
    }

    public PersistenceManager getPersistenceManager(String firebaseId) {
        PersistenceManager persistenceManager = this.forcedPersistenceManager;
        if (persistenceManager != null) {
            return persistenceManager;
        }
        if (!this.persistenceEnabled) {
            return new NoopPersistenceManager();
        }
        PersistenceManager cache = this.platform.createPersistenceManager(this, firebaseId);
        if (cache != null) {
            return cache;
        }
        throw new IllegalArgumentException("You have enabled persistence, but persistence is not supported on this platform.");
    }

    public boolean isPersistenceEnabled() {
        return this.persistenceEnabled;
    }

    public long getPersistenceCacheSizeBytes() {
        return this.cacheSize;
    }

    void forcePersistenceManager(PersistenceManager persistenceManager) {
        this.forcedPersistenceManager = persistenceManager;
    }

    public EventTarget getEventTarget() {
        return this.eventTarget;
    }

    public RunLoop getRunLoop() {
        return this.runLoop;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getPlatformVersion() {
        return getPlatform().getPlatformVersion();
    }

    public String getSessionPersistenceKey() {
        return this.persistenceKey;
    }

    public AuthTokenProvider getAuthTokenProvider() {
        return this.authTokenProvider;
    }

    public PersistentConnection newPersistentConnection(HostInfo info, PersistentConnection.Delegate delegate) {
        return getPlatform().newPersistentConnection(this, getConnectionContext(), info, delegate);
    }

    private ScheduledExecutorService getExecutorService() {
        RunLoop loop = getRunLoop();
        if (loop instanceof DefaultRunLoop) {
            return ((DefaultRunLoop) loop).getExecutorService();
        }
        throw new RuntimeException("Custom run loops are not supported!");
    }

    private void ensureLogger() {
        if (this.logger == null) {
            this.logger = getPlatform().newLogger(this, this.logLevel, this.loggedComponents);
        }
    }

    private void ensureRunLoop() {
        if (this.runLoop == null) {
            this.runLoop = this.platform.newRunLoop(this);
        }
    }

    private void ensureEventTarget() {
        if (this.eventTarget == null) {
            this.eventTarget = getPlatform().newEventTarget(this);
        }
    }

    private void ensureUserAgent() {
        if (this.userAgent == null) {
            this.userAgent = buildUserAgent(getPlatform().getUserAgent(this));
        }
    }

    private void ensureAuthTokenProvider() {
        Preconditions.checkNotNull(this.authTokenProvider, "You must register an authTokenProvider before initializing Context.");
    }

    private void ensureSessionIdentifier() {
        if (this.persistenceKey == null) {
            this.persistenceKey = "default";
        }
    }

    private String buildUserAgent(String platformAgent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Firebase/");
        sb.append("5");
        sb.append("/");
        sb.append(FirebaseDatabase.getSdkVersion());
        sb.append("/");
        StringBuilder sb2 = sb.append(platformAgent);
        return sb2.toString();
    }

    private static ConnectionAuthTokenProvider wrapAuthTokenProvider(AuthTokenProvider provider, ScheduledExecutorService executorService) {
        return Context$$Lambda$1.lambdaFactory$(provider, executorService);
    }

    public static /* synthetic */ void lambda$wrapAuthTokenProvider$0(AuthTokenProvider provider, ScheduledExecutorService executorService, boolean forceRefresh, ConnectionAuthTokenProvider.GetTokenCallback callback) {
        provider.getToken(forceRefresh, new AnonymousClass1(executorService, callback));
    }

    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.core.Context$1 */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements AuthTokenProvider.GetTokenCompletionListener {
        final /* synthetic */ ConnectionAuthTokenProvider.GetTokenCallback val$callback;
        final /* synthetic */ ScheduledExecutorService val$executorService;

        AnonymousClass1(ScheduledExecutorService scheduledExecutorService, ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback) {
            this.val$executorService = scheduledExecutorService;
            this.val$callback = getTokenCallback;
        }

        public static /* synthetic */ void lambda$onSuccess$0(ConnectionAuthTokenProvider.GetTokenCallback callback, String token) {
            callback.onSuccess(token);
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider.GetTokenCompletionListener
        public void onSuccess(String token) {
            this.val$executorService.execute(Context$1$$Lambda$1.lambdaFactory$(this.val$callback, token));
        }

        public static /* synthetic */ void lambda$onError$1(ConnectionAuthTokenProvider.GetTokenCallback callback, String error) {
            callback.onError(error);
        }

        @Override // com.google.firebase.database.core.AuthTokenProvider.GetTokenCompletionListener
        public void onError(String error) {
            this.val$executorService.execute(Context$1$$Lambda$4.lambdaFactory$(this.val$callback, error));
        }
    }

    public File getSSLCacheDirectory() {
        return getPlatform().getSSLCacheDirectory();
    }
}
