package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
class Utils {
    public static final long AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS = TimeUnit.HOURS.toSeconds(1);

    public boolean isAuthTokenExpired(PersistedInstallationEntry entry) {
        return TextUtils.isEmpty(entry.getAuthToken()) || entry.getTokenCreationEpochInSecs() + entry.getExpiresInSecs() < currentTimeInSecs() + AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS;
    }

    public long currentTimeInSecs() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }
}
