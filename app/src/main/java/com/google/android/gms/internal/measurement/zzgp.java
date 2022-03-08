package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzgp implements zzgx {
    private zzgx[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgp(zzgx... zzgxVarArr) {
        this.zza = zzgxVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean zza(Class<?> cls) {
        for (zzgx zzgxVar : this.zza) {
            if (zzgxVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final zzgu zzb(Class<?> cls) {
        zzgx[] zzgxVarArr;
        for (zzgx zzgxVar : this.zza) {
            if (zzgxVar.zza(cls)) {
                return zzgxVar.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
