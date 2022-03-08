package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbm<E> extends zzbg<E> {
    static final zzbg<Object> zza = new zzbm(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbm(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final Object[] zzd() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final int zze() {
        return 0;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    final int zzf() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg, com.google.android.gms.internal.firebase_auth.zzbh
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzav.zza(i, this.zzc);
        return (E) this.zzb[i];
    }
}
