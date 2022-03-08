package com.google.firebase.iid;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-iid@@20.1.5 */
/* loaded from: classes.dex */
public final class zzt {
    private final FirebaseApp zza;
    private final zzao zzb;
    private final zzau zzc;
    private final Executor zzd;
    private final UserAgentPublisher zze;
    private final HeartBeatInfo zzf;
    private final FirebaseInstallationsApi zzg;

    public zzt(FirebaseApp firebaseApp, zzao zzaoVar, Executor executor, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi) {
        this(firebaseApp, zzaoVar, executor, new zzau(firebaseApp.getApplicationContext(), zzaoVar), userAgentPublisher, heartBeatInfo, firebaseInstallationsApi);
    }

    private zzt(FirebaseApp firebaseApp, zzao zzaoVar, Executor executor, zzau zzauVar, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi) {
        this.zza = firebaseApp;
        this.zzb = zzaoVar;
        this.zzc = zzauVar;
        this.zzd = executor;
        this.zze = userAgentPublisher;
        this.zzf = heartBeatInfo;
        this.zzg = firebaseInstallationsApi;
    }

    public final Task<String> zza(String str, String str2, String str3) {
        return zzb(zza(str, str2, str3, new Bundle()));
    }

    public final Task<Void> zzb(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("delete", "1");
        return zza(zzb(zza(str, str2, str3, bundle)));
    }

    public final Task<Void> zzc(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return zza(zzb(zza(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    public final Task<Void> zzd(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        String valueOf = String.valueOf("/topics/");
        String valueOf2 = String.valueOf(str3);
        bundle.putString("gcm.topic", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        bundle.putString("delete", "1");
        String valueOf3 = String.valueOf("/topics/");
        String valueOf4 = String.valueOf(str3);
        return zza(zzb(zza(str, str2, valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3), bundle)));
    }

    private final Task<Bundle> zza(final String str, final String str2, final String str3, final Bundle bundle) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzd.execute(new Runnable(this, str, str2, str3, bundle, taskCompletionSource) { // from class: com.google.firebase.iid.zzs
            private final zzt zza;
            private final String zzb;
            private final String zzc;
            private final String zzd;
            private final Bundle zze;
            private final TaskCompletionSource zzf;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = str;
                this.zzc = str2;
                this.zzd = str3;
                this.zze = bundle;
                this.zzf = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
            }
        });
        return taskCompletionSource.getTask();
    }

    private final String zza() {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("SHA-1").digest(this.zza.getName().getBytes()), 11);
        } catch (NoSuchAlgorithmException e) {
            return "[HASH-ERROR]";
        }
    }

    private final Bundle zzb(String str, String str2, String str3, Bundle bundle) {
        bundle.putString("scope", str3);
        bundle.putString("sender", str2);
        bundle.putString("subtype", str2);
        bundle.putString("appid", str);
        bundle.putString("gmp_app_id", this.zza.getOptions().getApplicationId());
        bundle.putString("gmsv", Integer.toString(this.zzb.zze()));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", this.zzb.zzc());
        bundle.putString("app_ver_name", this.zzb.zzd());
        bundle.putString("firebase-app-name-hash", zza());
        try {
            String token = ((InstallationTokenResult) Tasks.await(this.zzg.getToken(false))).getToken();
            if (!TextUtils.isEmpty(token)) {
                bundle.putString("Goog-Firebase-Installations-Auth", token);
            } else {
                Log.w("FirebaseInstanceId", "FIS auth token is empty");
            }
        } catch (InterruptedException | ExecutionException e) {
            Log.e("FirebaseInstanceId", "Failed to get FIS auth token", e);
        }
        String version = LibraryVersion.getInstance().getVersion("firebase-iid");
        if ("UNKNOWN".equals(version)) {
            int i = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
            StringBuilder sb = new StringBuilder(19);
            sb.append("unknown_");
            sb.append(i);
            version = sb.toString();
        }
        String valueOf = String.valueOf(version);
        bundle.putString("cliv", valueOf.length() != 0 ? "fiid-".concat(valueOf) : new String("fiid-"));
        HeartBeatInfo.HeartBeat heartBeatCode = this.zzf.getHeartBeatCode("fire-iid");
        if (heartBeatCode != HeartBeatInfo.HeartBeat.NONE) {
            bundle.putString("Firebase-Client-Log-Type", Integer.toString(heartBeatCode.getCode()));
            bundle.putString("Firebase-Client", this.zze.getUserAgent());
        }
        return bundle;
    }

    private static <T> Task<Void> zza(Task<T> task) {
        return task.continueWith(zzh.zza(), zzv.zza);
    }

    private final Task<String> zzb(Task<Bundle> task) {
        return task.continueWith(this.zzd, new Continuation(this) { // from class: com.google.firebase.iid.zzu
            private final zzt zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                Bundle bundle = (Bundle) task2.getResult(IOException.class);
                if (bundle != null) {
                    String string = bundle.getString("registration_id");
                    if (string != null) {
                        return string;
                    }
                    String string2 = bundle.getString("unregistered");
                    if (string2 != null) {
                        return string2;
                    }
                    String string3 = bundle.getString("error");
                    if ("RST".equals(string3)) {
                        throw new IOException("INSTANCE_ID_RESET");
                    } else if (string3 != null) {
                        throw new IOException(string3);
                    } else {
                        String valueOf = String.valueOf(bundle);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 21);
                        sb.append("Unexpected response: ");
                        sb.append(valueOf);
                        Log.w("FirebaseInstanceId", sb.toString(), new Throwable());
                        throw new IOException("SERVICE_NOT_AVAILABLE");
                    }
                } else {
                    throw new IOException("SERVICE_NOT_AVAILABLE");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str, String str2, String str3, Bundle bundle, TaskCompletionSource taskCompletionSource) {
        try {
            zzb(str, str2, str3, bundle);
            taskCompletionSource.setResult(this.zzc.zza(bundle));
        } catch (IOException e) {
            taskCompletionSource.setException(e);
        }
    }
}
