package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzu extends zzif<zzu, zza> implements zzjp {
    private static final zzu zzl;
    private static volatile zzjx<zzu> zzm;
    private int zzc;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";

    private zzu() {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif.zza<zzu, zza> implements zzjp {
        private zza() {
            super(zzu.zzl);
        }

        /* synthetic */ zza(zzt zztVar) {
            this();
        }
    }

    public final String zza() {
        return this.zzd;
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzh;
    }

    public final String zzf() {
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.firebase_auth.zzif
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzt.zza[i - 1]) {
            case 1:
                return new zzu();
            case 2:
                return new zza(null);
            case 3:
                return zza(zzl, "\u0001\b\u0000\u0001\u0001\t\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\tဈ\u0007", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 4:
                return zzl;
            case 5:
                zzjx<zzu> zzjxVar = zzm;
                if (zzjxVar == null) {
                    synchronized (zzu.class) {
                        zzjxVar = zzm;
                        if (zzjxVar == null) {
                            zzjxVar = new zzif.zzc<>(zzl);
                            zzm = zzjxVar;
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

    static {
        zzu zzuVar = new zzu();
        zzl = zzuVar;
        zzif.zza(zzu.class, zzuVar);
    }
}
