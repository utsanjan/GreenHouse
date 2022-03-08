package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class zzhs {
    private static volatile zzhs zzc;
    private static volatile zzhs zzd;
    private final Map<zza, zzif.zzd<?, ?>> zzf;
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static final zzhs zze = new zzhs(true);

    public static zzhs zza() {
        zzhs zzhsVar = zzc;
        if (zzhsVar == null) {
            synchronized (zzhs.class) {
                zzhsVar = zzc;
                if (zzhsVar == null) {
                    zzhsVar = zze;
                    zzc = zzhsVar;
                }
            }
        }
        return zzhsVar;
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
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

    public static zzhs zzb() {
        zzhs zzhsVar = zzd;
        if (zzhsVar != null) {
            return zzhsVar;
        }
        synchronized (zzhs.class) {
            zzhs zzhsVar2 = zzd;
            if (zzhsVar2 != null) {
                return zzhsVar2;
            }
            zzhs zza2 = zzie.zza(zzhs.class);
            zzd = zza2;
            return zza2;
        }
    }

    public final <ContainingType extends zzjn> zzif.zzd<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzif.zzd<ContainingType, ?>) this.zzf.get(new zza(containingtype, i));
    }

    zzhs() {
        this.zzf = new HashMap();
    }

    private zzhs(boolean z) {
        this.zzf = Collections.emptyMap();
    }
}
