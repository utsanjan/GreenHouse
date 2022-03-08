package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzz extends zzif<zzz, zza> implements zzjp {
    private static final zzz zzac;
    private static volatile zzjx<zzz> zzad;
    private zzkw zzab;
    private int zzc;
    private int zzn;
    private boolean zzo;
    private long zzp;
    private long zzr;
    private boolean zzs;
    private long zzt;
    private long zzu;
    private boolean zzw;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private zzio<String> zzg = zzif.zzac();
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private zzgv zzl = zzgv.zza;
    private zzgv zzm = zzgv.zza;
    private zzio<zzu> zzq = zzac();
    private String zzv = "";
    private String zzx = "";
    private String zzy = "";
    private String zzz = "";
    private zzio<zzr> zzaa = zzac();

    private zzz() {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif.zza<zzz, zza> implements zzjp {
        private zza() {
            super(zzz.zzac);
        }

        /* synthetic */ zza(zzy zzyVar) {
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
        return this.zzi;
    }

    public final boolean zze() {
        return this.zzo;
    }

    public final List<zzu> zzf() {
        return this.zzq;
    }

    public final long zzg() {
        return this.zzt;
    }

    public final long zzh() {
        return this.zzu;
    }

    public final String zzi() {
        return this.zzx;
    }

    public final String zzj() {
        return this.zzy;
    }

    public final List<zzr> zzk() {
        return this.zzaa;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.firebase_auth.zzif
    public final Object zza(int i, Object obj, Object obj2) {
        switch (zzy.zza[i - 1]) {
            case 1:
                return new zzz();
            case 2:
                return new zza(null);
            case 3:
                return zza(zzac, "\u0001\u0019\u0000\u0001\u0001\u001c\u0019\u0000\u0003\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u001a\u0005ဈ\u0003\u0006ဈ\u0004\u0007ဈ\u0005\bဈ\u0006\tည\u0007\nည\b\u000bင\t\fဇ\n\rဂ\u000b\u000e\u001b\u000fဂ\f\u0010ဇ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဈ\u0013\u0019ဈ\u0014\u001a\u001b\u001cဉ\u0015", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzu.class, "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", zzr.class, "zzab"});
            case 4:
                return zzac;
            case 5:
                zzjx<zzz> zzjxVar = zzad;
                if (zzjxVar == null) {
                    synchronized (zzz.class) {
                        zzjxVar = zzad;
                        if (zzjxVar == null) {
                            zzjxVar = new zzif.zzc<>(zzac);
                            zzad = zzjxVar;
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
        zzz zzzVar = new zzz();
        zzac = zzzVar;
        zzif.zza(zzz.class, zzzVar);
    }
}
