package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzeu extends zzgv {
    private String zzc;
    private char zza = 0;
    private long zzb = -1;
    private final zzew zzd = new zzew(this, 6, false, false);
    private final zzew zze = new zzew(this, 6, true, false);
    private final zzew zzf = new zzew(this, 6, false, true);
    private final zzew zzg = new zzew(this, 5, false, false);
    private final zzew zzh = new zzew(this, 5, true, false);
    private final zzew zzi = new zzew(this, 5, false, true);
    private final zzew zzj = new zzew(this, 4, false, false);
    private final zzew zzk = new zzew(this, 3, false, false);
    private final zzew zzl = new zzew(this, 2, false, false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeu(zzfy zzfyVar) {
        super(zzfyVar);
    }

    public final zzew zzf() {
        return this.zzd;
    }

    public final zzew zzg() {
        return this.zze;
    }

    public final zzew zzh() {
        return this.zzf;
    }

    public final zzew zzi() {
        return this.zzg;
    }

    public final zzew zzj() {
        return this.zzh;
    }

    public final zzew zzk() {
        return this.zzi;
    }

    public final zzew zzv() {
        return this.zzj;
    }

    public final zzew zzw() {
        return this.zzk;
    }

    public final zzew zzx() {
        return this.zzl;
    }

    @Override // com.google.android.gms.measurement.internal.zzgv
    protected final boolean zze() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zza(String str) {
        if (str == null) {
            return null;
        }
        return new zzez(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zza(i)) {
            zza(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            Preconditions.checkNotNull(str);
            zzfv zzg = this.zzy.zzg();
            if (zzg == null) {
                zza(6, "Scheduler not set. Not logging error/warn");
            } else if (!zzg.zzz()) {
                zza(6, "Scheduler not initialized. Not logging error/warn");
            } else {
                if (i < 0) {
                    i = 0;
                }
                zzg.zza(new zzex(this, i >= 9 ? 8 : i, str, obj, obj2, obj3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zza(int i) {
        return Log.isLoggable(zzad(), i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(int i, String str) {
        Log.println(i, zzad(), str);
    }

    private final String zzad() {
        String str;
        String str2;
        synchronized (this) {
            if (this.zzc == null) {
                if (this.zzy.zzs() != null) {
                    str2 = this.zzy.zzs();
                } else {
                    zzt().zzu();
                    str2 = "FA";
                }
                this.zzc = str2;
            }
            str = this.zzc;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        String zza = zza(z, obj);
        String zza2 = zza(z, obj2);
        String zza3 = zza(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(zza)) {
            sb.append(str2);
            sb.append(zza);
            str2 = str3;
        }
        if (!TextUtils.isEmpty(zza2)) {
            sb.append(str2);
            sb.append(zza2);
        } else {
            str3 = str2;
        }
        if (!TextUtils.isEmpty(zza3)) {
            sb.append(str3);
            sb.append(zza3);
        }
        return sb.toString();
    }

    private static String zza(boolean z, Object obj) {
        String str;
        String className;
        String str2 = "";
        if (obj == null) {
            return str2;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i = 0;
        if (obj instanceof Long) {
            if (!z) {
                return String.valueOf(obj);
            }
            Long l = (Long) obj;
            if (Math.abs(l.longValue()) < 100) {
                return String.valueOf(obj);
            }
            if (String.valueOf(obj).charAt(0) == '-') {
                str2 = "-";
            }
            String valueOf = String.valueOf(Math.abs(l.longValue()));
            long round = Math.round(Math.pow(10.0d, valueOf.length() - 1));
            long round2 = Math.round(Math.pow(10.0d, valueOf.length()) - 1.0d);
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 43 + String.valueOf(str2).length());
            sb.append(str2);
            sb.append(round);
            sb.append("...");
            sb.append(str2);
            sb.append(round2);
            return sb.toString();
        } else if (obj instanceof Boolean) {
            return String.valueOf(obj);
        } else {
            if (obj instanceof Throwable) {
                Throwable th = (Throwable) obj;
                StringBuilder sb2 = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzb = zzb(zzfy.class.getCanonicalName());
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTrace[i];
                    if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzb(className).equals(zzb)) {
                        sb2.append(": ");
                        sb2.append(stackTraceElement);
                        break;
                    }
                    i++;
                }
                return sb2.toString();
            } else if (!(obj instanceof zzez)) {
                return z ? "-" : String.valueOf(obj);
            } else {
                str = ((zzez) obj).zza;
                return str;
            }
        }
    }

    private static String zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    public final String zzy() {
        Pair<String, Long> zza = zzs().zzb.zza();
        if (zza == null || zza == zzfg.zza) {
            return null;
        }
        String valueOf = String.valueOf(zza.second);
        String str = (String) zza.first;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(":");
        sb.append(str);
        return sb.toString();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzes zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzkr zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfv zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzeu zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzfg zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
