package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzkb implements zzjl {
    private final zzjn zza;
    private final String zzb;
    private final Object[] zzc;
    private final int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkb(zzjn zzjnVar, String str, Object[] objArr) {
        this.zza = zzjnVar;
        this.zzb = str;
        this.zzc = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.zzd = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 >= 55296) {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            } else {
                this.zzd = i | (charAt2 << i2);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzd() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] zze() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjl
    public final zzjn zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjl
    public final int zza() {
        return (this.zzd & 1) == 1 ? zzif.zzf.zzh : zzif.zzf.zzi;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjl
    public final boolean zzb() {
        return (this.zzd & 2) == 2;
    }
}
