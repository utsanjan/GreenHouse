package com.google.android.gms.internal.measurement;

import android.content.Context;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzcv<T> {
    private static volatile Context zzb;
    private static volatile zzdj<zzdi<zzcr>> zze;
    private final zzdb zzf;
    private final String zzg;
    private final T zzh;
    private volatile int zzj;
    private volatile T zzk;
    private final boolean zzl;
    private static final Object zza = new Object();
    private static volatile boolean zzc = false;
    private static final AtomicReference<Collection<zzcv<?>>> zzd = new AtomicReference<>();
    private static final AtomicInteger zzi = new AtomicInteger();

    @Deprecated
    public static void zza(Context context) {
        synchronized (zza) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zzb != context) {
                zzch.zzc();
                zzde.zza();
                zzcq.zza();
                zze = zzdm.zza(zzcy.zza);
                zzb = context;
                zzi.incrementAndGet();
            }
        }
    }

    abstract T zza(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza() {
        zzi.incrementAndGet();
    }

    private zzcv(zzdb zzdbVar, String str, T t, boolean z) {
        this.zzj = -1;
        if (zzdbVar.zzb != null) {
            this.zzf = zzdbVar;
            this.zzg = str;
            this.zzh = t;
            this.zzl = z;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    private final String zza(String str) {
        if (str != null && str.isEmpty()) {
            return this.zzg;
        }
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(this.zzg);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzb() {
        return zza(this.zzf.zzd);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a5 A[Catch: all -> 0x0105, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x0010, B:9:0x0017, B:11:0x0028, B:17:0x003c, B:19:0x0042, B:21:0x004e, B:23:0x005f, B:25:0x0067, B:27:0x0071, B:29:0x0077, B:31:0x0080, B:33:0x0092, B:34:0x0097, B:35:0x009d, B:39:0x00a5, B:41:0x00bb, B:45:0x00c5, B:46:0x00c7, B:48:0x00d7, B:50:0x00ed, B:51:0x00f0, B:52:0x00f6, B:53:0x00fb, B:54:0x0102, B:55:0x0103), top: B:62:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d7 A[Catch: all -> 0x0105, TryCatch #0 {, blocks: (B:5:0x000b, B:7:0x0010, B:9:0x0017, B:11:0x0028, B:17:0x003c, B:19:0x0042, B:21:0x004e, B:23:0x005f, B:25:0x0067, B:27:0x0071, B:29:0x0077, B:31:0x0080, B:33:0x0092, B:34:0x0097, B:35:0x009d, B:39:0x00a5, B:41:0x00bb, B:45:0x00c5, B:46:0x00c7, B:48:0x00d7, B:50:0x00ed, B:51:0x00f0, B:52:0x00f6, B:53:0x00fb, B:54:0x0102, B:55:0x0103), top: B:62:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T zzc() {
        /*
            Method dump skipped, instructions count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcv.zzc():java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcv<Long> zzb(zzdb zzdbVar, String str, long j, boolean z) {
        return new zzcx(zzdbVar, str, Long.valueOf(j), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcv<Boolean> zzb(zzdb zzdbVar, String str, boolean z, boolean z2) {
        return new zzda(zzdbVar, str, Boolean.valueOf(z), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcv<Double> zzb(zzdb zzdbVar, String str, double d, boolean z) {
        return new zzcz(zzdbVar, str, Double.valueOf(d), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzcv<String> zzb(zzdb zzdbVar, String str, String str2, boolean z) {
        return new zzdc(zzdbVar, str, str2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ zzdi zzd() {
        new zzcu();
        return zzcu.zza(zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcv(zzdb zzdbVar, String str, Object obj, boolean z, zzcx zzcxVar) {
        this(zzdbVar, str, obj, z);
    }
}
