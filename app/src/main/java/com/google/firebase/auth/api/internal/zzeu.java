package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzeu extends zzao implements Api.ApiOptions.HasOptions {
    private final String zzb;

    private zzeu(String str) {
        this.zzb = Preconditions.checkNotEmpty(str, "A valid API key must be provided");
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeu)) {
            return false;
        }
        return Objects.equal(this.zzb, ((zzeu) obj).zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb);
    }

    @Override // com.google.firebase.auth.api.internal.zzao
    public final /* synthetic */ zzao zza() {
        return (zzeu) clone();
    }

    @Override // com.google.firebase.auth.api.internal.zzao
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new zzex(this.zzb).zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeu(String str, zzev zzevVar) {
        this(str);
    }
}
