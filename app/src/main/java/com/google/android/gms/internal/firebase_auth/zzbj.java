package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbj<E> extends zzbc<E> {
    private final zzbg<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbj(zzbg<E> zzbgVar, int i) {
        super(zzbgVar.size(), i);
        this.zza = zzbgVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbc
    protected final E zza(int i) {
        return this.zza.get(i);
    }
}
