package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzcq implements zzcl {
    private static zzcq zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcq zza(Context context) {
        zzcq zzcqVar;
        synchronized (zzcq.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzcq(context) : new zzcq();
            }
            zzcqVar = zza;
        }
        return zzcqVar;
    }

    private zzcq(Context context) {
        this.zzb = context;
        this.zzc = new zzcs(this, null);
        context.getContentResolver().registerContentObserver(zzcg.zza, true, this.zzc);
    }

    private zzcq() {
        this.zzb = null;
        this.zzc = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zza(final String str) {
        if (this.zzb == null) {
            return null;
        }
        try {
            return (String) zzco.zza(new zzcn(this, str) { // from class: com.google.android.gms.internal.measurement.zzcp
                private final zzcq zza;
                private final String zzb;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zza = this;
                    this.zzb = str;
                }

                @Override // com.google.android.gms.internal.measurement.zzcn
                public final Object zza() {
                    return this.zza.zzb(this.zzb);
                }
            });
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zza() {
        synchronized (zzcq.class) {
            if (!(zza == null || zza.zzb == null || zza.zzc == null)) {
                zza.zzb.getContentResolver().unregisterContentObserver(zza.zzc);
            }
            zza = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ String zzb(String str) {
        return zzcg.zza(this.zzb.getContentResolver(), str, (String) null);
    }
}
