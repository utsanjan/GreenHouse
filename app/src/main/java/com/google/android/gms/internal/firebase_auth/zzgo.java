package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzjn;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzgo<MessageType extends zzjn> implements zzjx<MessageType> {
    private static final zzhs zza = zzhs.zza();

    private final MessageType zzb(InputStream inputStream, zzhs zzhsVar) throws zzin {
        zzhh zzhhVar;
        if (inputStream == null) {
            byte[] bArr = zzii.zzb;
            zzhhVar = zzhh.zza(bArr, 0, bArr.length, false);
        } else {
            zzhhVar = new zzhi(inputStream);
        }
        MessageType messagetype = (MessageType) ((zzjn) zza(zzhhVar, zzhsVar));
        try {
            zzhhVar.zza(0);
            return messagetype;
        } catch (zzin e) {
            throw e.zza(messagetype);
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final /* synthetic */ Object zza(InputStream inputStream, zzhs zzhsVar) throws zzin {
        zzkx zzkxVar;
        MessageType zzb = zzb(inputStream, zzhsVar);
        if (zzb == null || zzb.b_()) {
            return zzb;
        }
        if (zzb instanceof zzgn) {
            zzkxVar = new zzkx((zzgn) zzb);
        } else if (zzb instanceof zzgp) {
            zzkxVar = new zzkx((zzgp) zzb);
        } else {
            zzkxVar = new zzkx(zzb);
        }
        throw new zzin(zzkxVar.getMessage()).zza(zzb);
    }
}
