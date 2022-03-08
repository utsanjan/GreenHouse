package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
class GetAuthTokenListener implements StateListener {
    private final TaskCompletionSource<InstallationTokenResult> resultTaskCompletionSource;
    private final Utils utils;

    public GetAuthTokenListener(Utils utils, TaskCompletionSource<InstallationTokenResult> resultTaskCompletionSource) {
        this.utils = utils;
        this.resultTaskCompletionSource = resultTaskCompletionSource;
    }

    @Override // com.google.firebase.installations.StateListener
    public boolean onStateReached(PersistedInstallationEntry persistedInstallationEntry) {
        if (!persistedInstallationEntry.isRegistered() || this.utils.isAuthTokenExpired(persistedInstallationEntry)) {
            return false;
        }
        this.resultTaskCompletionSource.setResult(InstallationTokenResult.builder().setToken(persistedInstallationEntry.getAuthToken()).setTokenExpirationTimestamp(persistedInstallationEntry.getExpiresInSecs()).setTokenCreationTimestamp(persistedInstallationEntry.getTokenCreationEpochInSecs()).build());
        return true;
    }

    @Override // com.google.firebase.installations.StateListener
    public boolean onException(PersistedInstallationEntry persistedInstallationEntry, Exception exception) {
        if (!persistedInstallationEntry.isErrored() && !persistedInstallationEntry.isNotGenerated() && !persistedInstallationEntry.isUnregistered()) {
            return false;
        }
        this.resultTaskCompletionSource.trySetException(exception);
        return true;
    }
}
