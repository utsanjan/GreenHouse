package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzkw extends zzif<zzkw, zza> implements zzjp {
    private static final zzkw zze;
    private static volatile zzjx<zzkw> zzf;
    private long zzc;
    private int zzd;

    private zzkw() {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif.zza<zzkw, zza> implements zzjp {
        private zza() {
            super(zzkw.zze);
        }

        /* synthetic */ zza(zzkv zzkvVar) {
            this();
        }
    }

    public final long zza() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.firebase_auth.zzif
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzkv.zza[i - 1]) {
            case 1:
                return new zzkw();
            case 2:
                return new zza(null);
            case 3:
                return zza(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzc", "zzd"});
            case 4:
                return zze;
            case 5:
                zzjx<zzkw> zzjxVar = zzf;
                if (zzjxVar == null) {
                    synchronized (zzkw.class) {
                        zzjxVar = zzf;
                        if (zzjxVar == null) {
                            zzjxVar = new zzif.zzc<>(zze);
                            zzf = zzjxVar;
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

    public static zzkw zzb() {
        return zze;
    }

    static {
        zzkw zzkwVar = new zzkw();
        zze = zzkwVar;
        zzif.zza(zzkw.class, zzkwVar);
    }
}
