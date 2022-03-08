package com.google.firebase.database.core;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Logger;
import com.google.firebase.database.logging.Logger;
import java.util.List;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class DatabaseConfig extends Context {
    public synchronized void setLogger(Logger logger) {
        assertUnfrozen();
        this.logger = logger;
    }

    public synchronized void setEventTarget(EventTarget eventTarget) {
        assertUnfrozen();
        this.eventTarget = eventTarget;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.core.DatabaseConfig$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$Logger$Level;

        static {
            int[] iArr = new int[Logger.Level.values().length];
            $SwitchMap$com$google$firebase$database$Logger$Level = iArr;
            try {
                iArr[Logger.Level.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.WARN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$firebase$database$Logger$Level[Logger.Level.NONE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public synchronized void setLogLevel(Logger.Level logLevel) {
        assertUnfrozen();
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$Logger$Level[logLevel.ordinal()];
        if (i == 1) {
            this.logLevel = Logger.Level.DEBUG;
        } else if (i == 2) {
            this.logLevel = Logger.Level.INFO;
        } else if (i == 3) {
            this.logLevel = Logger.Level.WARN;
        } else if (i == 4) {
            this.logLevel = Logger.Level.ERROR;
        } else if (i == 5) {
            this.logLevel = Logger.Level.NONE;
        } else {
            throw new IllegalArgumentException("Unknown log level: " + logLevel);
        }
    }

    public synchronized void setDebugLogComponents(List<String> debugComponents) {
        assertUnfrozen();
        setLogLevel(Logger.Level.DEBUG);
        this.loggedComponents = debugComponents;
    }

    public void setRunLoop(RunLoop runLoop) {
        this.runLoop = runLoop;
    }

    public void setAuthTokenProvider(AuthTokenProvider provider) {
        this.authTokenProvider = provider;
    }

    public synchronized void setSessionPersistenceKey(String sessionKey) {
        assertUnfrozen();
        if (sessionKey == null || sessionKey.isEmpty()) {
            throw new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
        }
        this.persistenceKey = sessionKey;
    }

    public synchronized void setPersistenceEnabled(boolean isEnabled) {
        assertUnfrozen();
        this.persistenceEnabled = isEnabled;
    }

    public synchronized void setPersistenceCacheSizeBytes(long cacheSizeInBytes) {
        assertUnfrozen();
        if (cacheSizeInBytes < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            throw new DatabaseException("The minimum cache size must be at least 1MB");
        } else if (cacheSizeInBytes <= 104857600) {
            this.cacheSize = cacheSizeInBytes;
        } else {
            throw new DatabaseException("Firebase Database currently doesn't support a cache size larger than 100MB");
        }
    }

    public synchronized void setFirebaseApp(FirebaseApp app) {
        this.firebaseApp = app;
    }
}
