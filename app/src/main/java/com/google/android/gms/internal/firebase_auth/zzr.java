package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzr extends zzif<zzr, zza> implements zzjp {
    private static final zzr zzk;
    private static volatile zzjx<zzr> zzl;
    private int zzc;
    private Object zze;
    private Object zzg;
    private zzkw zzj;
    private int zzd = 0;
    private int zzf = 0;
    private String zzh = "";
    private String zzi = "";

    private zzr() {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif.zza<zzr, zza> implements zzjp {
        private zza() {
            super(zzr.zzk);
        }

        /* synthetic */ zza(zzs zzsVar) {
            this();
        }
    }

    public final String zza() {
        if (this.zzd == 1) {
            return (String) this.zze;
        }
        return "";
    }

    public final String zzb() {
        return this.zzh;
    }

    public final String zzc() {
        return this.zzi;
    }

    public final zzkw zzd() {
        zzkw zzkwVar = this.zzj;
        return zzkwVar == null ? zzkw.zzb() : zzkwVar;
    }

    public final String zze() {
        if (this.zzf == 5) {
            return (String) this.zzg;
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.firebase_auth.zzif
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzs.zza[i - 1]) {
            case 1:
                return new zzr();
            case 2:
                return new zza(null);
            case 3:
                return zza(zzk, "\u0001\u0005\u0002\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ျ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003\u0005ျ\u0001", new Object[]{"zze", "zzd", "zzg", "zzf", "zzc", "zzh", "zzi", "zzj"});
            case 4:
                return zzk;
            case 5:
                zzjx<zzr> zzjxVar = zzl;
                if (zzjxVar == null) {
                    synchronized (zzr.class) {
                        zzjxVar = zzl;
                        if (zzjxVar == null) {
                            zzjxVar = new zzif.zzc<>(zzk);
                            zzl = zzjxVar;
                        }
                    }
                }
                return zzjxVar;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public static zzr zzf() {
        return zzk;
    }

    static {
        zzr zzrVar = new zzr();
        zzk = zzrVar;
        zzif.zza(zzr.class, zzrVar);
    }
}
