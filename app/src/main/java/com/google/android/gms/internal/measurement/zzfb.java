package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public class zzfb {
    private static volatile zzfb zzc;
    private static volatile zzfb zzd;
    private final Map<zza, zzfo.zzd<?, ?>> zzf;
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static final zzfb zze = new zzfb(true);

    public static zzfb zza() {
        zzfb zzfbVar = zzc;
        if (zzfbVar == null) {
            synchronized (zzfb.class) {
                zzfbVar = zzc;
                if (zzfbVar == null) {
                    zzfbVar = zze;
                    zzc = zzfbVar;
                }
            }
        }
        return zzfbVar;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    static final class zza {
        private final Object zza;
        private final int zzb;

        zza(Object obj, int i) {
            this.zza = obj;
            this.zzb = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.zza == zzaVar.zza && this.zzb == zzaVar.zzb;
        }
    }

    public static zzfb zzb() {
        zzfb zzfbVar = zzd;
        if (zzfbVar != null) {
            return zzfbVar;
        }
        synchronized (zzfb.class) {
            zzfb zzfbVar2 = zzd;
            if (zzfbVar2 != null) {
                return zzfbVar2;
            }
            zzfb zza2 = zzfn.zza(zzfb.class);
            zzd = zza2;
            return zza2;
        }
    }

    public final <ContainingType extends zzgw> zzfo.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzfo.zzd<ContainingType, ?>) this.zzf.get(new zza(containingtype, i));
    }

    zzfb() {
        this.zzf = new HashMap();
    }

    private zzfb(boolean z) {
        this.zzf = Collections.emptyMap();
    }
}
