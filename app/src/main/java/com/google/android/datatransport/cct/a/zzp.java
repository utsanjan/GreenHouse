package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzg;

/* loaded from: classes.dex */
public abstract class zzp {

    /* loaded from: classes.dex */
    public static abstract class zza {
        public abstract zza zza(zza zzaVar);

        public abstract zza zza(zzb zzbVar);

        public abstract zzp zza();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* loaded from: classes.dex */
    public static final class zzb extends Enum<zzb> {
        public static final zzb zza = new zzb("UNKNOWN", 0, 0);
        public static final zzb zzb;

        static {
            zzb zzbVar = new zzb("ANDROID_FIREBASE", 1, 23);
            zzb = zzbVar;
            zzb[] zzbVarArr = {zza, zzbVar};
        }

        private zzb(String str, int i, int i2) {
        }
    }

    public static zza zza() {
        return new zzg.zza();
    }

    public abstract zza zzb();

    public abstract zzb zzc();
}
