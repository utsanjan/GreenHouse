package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzbi extends zzbg<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzbg zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbi(zzbg zzbgVar, int i, int i2) {
        this.zzc = zzbgVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final Object[] zzd() {
        return this.zzc.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final int zze() {
        return this.zzc.zze() + this.zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    final int zzf() {
        return this.zzc.zze() + this.zza + this.zzb;
    }

    @Override // java.util.List
    public final E get(int i) {
        zzav.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg
    public final zzbg<E> zza(int i, int i2) {
        zzav.zza(i, i2, this.zzb);
        zzbg zzbgVar = this.zzc;
        int i3 = this.zza;
        return (zzbg) zzbgVar.subList(i + i3, i2 + i3);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbg, java.util.List
    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }
}
