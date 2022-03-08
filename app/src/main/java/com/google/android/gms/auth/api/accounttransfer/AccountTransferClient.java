package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.auth.zzab;
import com.google.android.gms.internal.auth.zzad;
import com.google.android.gms.internal.auth.zzaf;
import com.google.android.gms.internal.auth.zzah;
import com.google.android.gms.internal.auth.zzs;
import com.google.android.gms.internal.auth.zzu;
import com.google.android.gms.internal.auth.zzv;
import com.google.android.gms.internal.auth.zzy;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public class AccountTransferClient extends GoogleApi<zzn> {
    private static final Api.ClientKey<zzu> zzaj = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzu, zzn> zzak = new zzc();
    private static final Api<zzn> zzal = new Api<>("AccountTransfer.ACCOUNT_TRANSFER_API", zzak, zzaj);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class zzb<T> extends TaskApiCall<zzu, T> {
        private TaskCompletionSource<T> zzaw;

        private zzb() {
        }

        protected abstract void zza(zzz zzzVar) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: protected */
        public final void setResult(T t) {
            this.zzaw.setResult(t);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void zza(Status status) {
            AccountTransferClient.zza(this.zzaw, status);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.internal.TaskApiCall
        public /* synthetic */ void doExecute(zzu zzuVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
            this.zzaw = taskCompletionSource;
            zza((zzz) zzuVar.getService());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ zzb(zzc zzcVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountTransferClient(Context context) {
        super(context, zzal, (Api.ApiOptions) null, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static abstract class zzc extends zzb<Void> {
        zzy zzax;

        private zzc() {
            super(null);
            this.zzax = new zzk(this);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ zzc(zzc zzcVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class zza<T> extends zzs {
        private zzb<T> zzav;

        public zza(zzb<T> zzbVar) {
            this.zzav = zzbVar;
        }

        @Override // com.google.android.gms.internal.auth.zzs, com.google.android.gms.internal.auth.zzx
        public final void onFailure(Status status) {
            this.zzav.zza(status);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountTransferClient(Activity activity) {
        super(activity, (Api<Api.ApiOptions>) zzal, (Api.ApiOptions) null, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    public Task<Void> sendData(String str, byte[] bArr) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(bArr);
        return doWrite(new zzd(this, new zzaf(str, bArr)));
    }

    public Task<byte[]> retrieveData(String str) {
        Preconditions.checkNotNull(str);
        return doRead(new zze(this, new zzad(str)));
    }

    public Task<DeviceMetaData> getDeviceMetaData(String str) {
        Preconditions.checkNotNull(str);
        return doRead(new zzg(this, new zzv(str)));
    }

    public Task<Void> showUserChallenge(String str, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(pendingIntent);
        return doWrite(new zzi(this, new zzah(str, pendingIntent)));
    }

    public Task<Void> notifyCompletion(String str, int i) {
        Preconditions.checkNotNull(str);
        return doWrite(new zzj(this, new zzab(str, i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(TaskCompletionSource taskCompletionSource, Status status) {
        taskCompletionSource.setException(new AccountTransferException(status));
    }
}
