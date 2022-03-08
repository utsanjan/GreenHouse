package com.google.firebase.installations;

import com.google.firebase.installations.local.PersistedInstallationEntry;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
interface StateListener {
    boolean onException(PersistedInstallationEntry persistedInstallationEntry, Exception exc);

    boolean onStateReached(PersistedInstallationEntry persistedInstallationEntry);
}
