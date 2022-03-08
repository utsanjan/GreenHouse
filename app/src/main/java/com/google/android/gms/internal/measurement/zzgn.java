package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzgn implements zzho {
    private static final zzgx zzb = new zzgm();
    private final zzgx zza;

    public zzgn() {
        this(new zzgp(zzfm.zza(), zza()));
    }

    private zzgn(zzgx zzgxVar) {
        this.zza = (zzgx) zzfr.zza(zzgxVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.measurement.zzho
    public final <T> zzhp<T> zza(Class<T> cls) {
        zzhr.zza((Class<?>) cls);
        zzgu zzb2 = this.zza.zzb(cls);
        if (zzb2.zzb()) {
            if (zzfo.class.isAssignableFrom(cls)) {
                return zzhc.zza(zzhr.zzc(), zzff.zza(), zzb2.zzc());
            }
            return zzhc.zza(zzhr.zza(), zzff.zzb(), zzb2.zzc());
        } else if (zzfo.class.isAssignableFrom(cls)) {
            if (zza(zzb2)) {
                return zzha.zza(cls, zzb2, zzhg.zzb(), zzgg.zzb(), zzhr.zzc(), zzff.zza(), zzgv.zzb());
            }
            return zzha.zza(cls, zzb2, zzhg.zzb(), zzgg.zzb(), zzhr.zzc(), (zzfd<?>) null, zzgv.zzb());
        } else if (zza(zzb2)) {
            return zzha.zza(cls, zzb2, zzhg.zza(), zzgg.zza(), zzhr.zza(), zzff.zzb(), zzgv.zza());
        } else {
            return zzha.zza(cls, zzb2, zzhg.zza(), zzgg.zza(), zzhr.zzb(), (zzfd<?>) null, zzgv.zza());
        }
    }

    private static boolean zza(zzgu zzguVar) {
        return zzguVar.zza() == zzfo.zzf.zzh;
    }

    private static zzgx zza() {
        try {
            return (zzgx) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zzb;
        }
    }
}
