package com.google.firebase.iid;

import android.os.Bundle;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
final class zzak extends zzan<Void> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzak(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.zzan
    public final boolean zza() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.iid.zzan
    public final void zza(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            zza((zzak) null);
        } else {
            zza(new zzam(4, "Invalid response to one way request"));
        }
    }
}
