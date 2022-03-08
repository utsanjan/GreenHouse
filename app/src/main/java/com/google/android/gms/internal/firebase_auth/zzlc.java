package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzlc implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzla zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlc(zzla zzlaVar) {
        zziy zziyVar;
        this.zzb = zzlaVar;
        zziyVar = this.zzb.zza;
        this.zza = zziyVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }
}
