package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzhy implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzhc zza;

    private zzhy(zzhc zzhcVar) {
        this.zza = zzhcVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        String str;
        try {
            this.zza.zzr().zzx().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null && data.isHierarchical()) {
                    this.zza.zzp();
                    if (zzkr.zza(intent)) {
                        str = "gs";
                    } else {
                        str = "auto";
                    }
                    this.zza.zzq().zza(new zzib(this, bundle == null, data, str, data.getQueryParameter("referrer")));
                }
            }
        } catch (Exception e) {
            this.zza.zzr().zzf().zza("Throwable caught in onActivityCreated", e);
        } finally {
            this.zza.zzi().zza(activity, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a7 A[Catch: Exception -> 0x01df, TRY_ENTER, TryCatch #0 {Exception -> 0x01df, blocks: (B:3:0x000c, B:6:0x0026, B:8:0x0034, B:12:0x0045, B:15:0x0053, B:17:0x0059, B:19:0x005f, B:21:0x0065, B:23:0x006b, B:24:0x0078, B:26:0x0084, B:27:0x0089, B:28:0x008f, B:30:0x0099, B:33:0x00a7, B:35:0x00b5, B:37:0x00c8, B:40:0x00d0, B:42:0x00d6, B:43:0x00e9, B:45:0x00fc, B:47:0x0105, B:50:0x0115, B:53:0x0125, B:56:0x012d, B:58:0x0133, B:59:0x013e, B:62:0x0145, B:66:0x0166, B:68:0x0179, B:69:0x0181, B:70:0x0190, B:72:0x0197, B:74:0x019e, B:76:0x01a4, B:78:0x01aa, B:80:0x01b0, B:82:0x01b8, B:86:0x01c5, B:88:0x01d3, B:90:0x01d9), top: B:95:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0144 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0145 A[Catch: Exception -> 0x01df, TRY_LEAVE, TryCatch #0 {Exception -> 0x01df, blocks: (B:3:0x000c, B:6:0x0026, B:8:0x0034, B:12:0x0045, B:15:0x0053, B:17:0x0059, B:19:0x005f, B:21:0x0065, B:23:0x006b, B:24:0x0078, B:26:0x0084, B:27:0x0089, B:28:0x008f, B:30:0x0099, B:33:0x00a7, B:35:0x00b5, B:37:0x00c8, B:40:0x00d0, B:42:0x00d6, B:43:0x00e9, B:45:0x00fc, B:47:0x0105, B:50:0x0115, B:53:0x0125, B:56:0x012d, B:58:0x0133, B:59:0x013e, B:62:0x0145, B:66:0x0166, B:68:0x0179, B:69:0x0181, B:70:0x0190, B:72:0x0197, B:74:0x019e, B:76:0x01a4, B:78:0x01aa, B:80:0x01b0, B:82:0x01b8, B:86:0x01c5, B:88:0x01d3, B:90:0x01d9), top: B:95:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(boolean r18, android.net.Uri r19, java.lang.String r20, java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhy.zza(boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzi().zzc(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.zza.zzi().zzb(activity);
        zzjv zzk = this.zza.zzk();
        zzk.zzq().zza(new zzjx(zzk, zzk.zzm().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        zzjv zzk = this.zza.zzk();
        zzk.zzq().zza(new zzju(zzk, zzk.zzm().elapsedRealtime()));
        this.zza.zzi().zza(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzi().zzb(activity, bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhy(zzhc zzhcVar, zzhh zzhhVar) {
        this(zzhcVar);
    }
}
