package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzl extends zzif<zzl, zza> implements zzjp {
    private static final zzl zzf;
    private static volatile zzjx<zzl> zzg;
    private int zzc;
    private String zzd = "";
    private String zze = "";

    private zzl() {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif.zza<zzl, zza> implements zzjp {
        private zza() {
            super(zzl.zzf);
        }

        /* synthetic */ zza(zzn zznVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.firebase_auth.zzif
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzn.zza[i - 1]) {
            case 1:
                return new zzl();
            case 2:
                return new zza(null);
            case 3:
                return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzc", "zzd", "zze"});
            case 4:
                return zzf;
            case 5:
                zzjx<zzl> zzjxVar = zzg;
                if (zzjxVar == null) {
                    synchronized (zzl.class) {
                        zzjxVar = zzg;
                        if (zzjxVar == null) {
                            zzjxVar = new zzif.zzc<>(zzf);
                            zzg = zzjxVar;
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
        zzl zzlVar = new zzl();
        zzf = zzlVar;
        zzif.zza(zzl.class, zzlVar);
    }
}
