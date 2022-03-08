package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzje implements zzkg {
    private static final zzjo zzb = new zzjd();
    private final zzjo zza;

    public zzje() {
        this(new zzjg(zzid.zza(), zza()));
    }

    private zzje(zzjo zzjoVar) {
        this.zza = (zzjo) zzii.zza(zzjoVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkg
    public final <T> zzkd<T> zza(Class<T> cls) {
        zzkf.zza((Class<?>) cls);
        zzjl zzb2 = this.zza.zzb(cls);
        if (zzb2.zzb()) {
            if (zzif.class.isAssignableFrom(cls)) {
                return zzju.zza(zzkf.zzc(), zzhw.zza(), zzb2.zzc());
            }
            return zzju.zza(zzkf.zza(), zzhw.zzb(), zzb2.zzc());
        } else if (zzif.class.isAssignableFrom(cls)) {
            if (zza(zzb2)) {
                return zzjr.zza(cls, zzb2, zzjy.zzb(), zzix.zzb(), zzkf.zzc(), zzhw.zza(), zzjm.zzb());
            }
            return zzjr.zza(cls, zzb2, zzjy.zzb(), zzix.zzb(), zzkf.zzc(), null, zzjm.zzb());
        } else if (zza(zzb2)) {
            return zzjr.zza(cls, zzb2, zzjy.zza(), zzix.zza(), zzkf.zza(), zzhw.zzb(), zzjm.zza());
        } else {
            return zzjr.zza(cls, zzb2, zzjy.zza(), zzix.zza(), zzkf.zzb(), null, zzjm.zza());
        }
    }

    private static boolean zza(zzjl zzjlVar) {
        return zzjlVar.zza() == zzif.zzf.zzh;
    }

    private static zzjo zza() {
        try {
            return (zzjo) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zzb;
        }
    }
}
