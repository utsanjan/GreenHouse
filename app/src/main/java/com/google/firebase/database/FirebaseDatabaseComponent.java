package com.google.firebase.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.database.core.DatabaseConfig;
import com.google.firebase.database.core.RepoInfo;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class FirebaseDatabaseComponent {
    private final FirebaseApp app;
    private final AuthTokenProvider authProvider;
    private final Map<RepoInfo, FirebaseDatabase> instances = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseDatabaseComponent(FirebaseApp app, InternalAuthProvider authProvider) {
        this.app = app;
        if (authProvider != null) {
            this.authProvider = AndroidAuthTokenProvider.forAuthenticatedAccess(authProvider);
        } else {
            this.authProvider = AndroidAuthTokenProvider.forUnauthenticatedAccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized FirebaseDatabase get(RepoInfo repo) {
        FirebaseDatabase database;
        database = this.instances.get(repo);
        if (database == null) {
            DatabaseConfig config = new DatabaseConfig();
            if (!this.app.isDefaultApp()) {
                config.setSessionPersistenceKey(this.app.getName());
            }
            config.setFirebaseApp(this.app);
            config.setAuthTokenProvider(this.authProvider);
            database = new FirebaseDatabase(this.app, repo, config);
            this.instances.put(repo, database);
        }
        return database;
    }
}
