package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeInfo;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zze extends ActionCodeInfo {
    public zze(String str) {
        this.email = Preconditions.checkNotEmpty(str);
    }
}
