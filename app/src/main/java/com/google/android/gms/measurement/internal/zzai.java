package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzai extends zzgv {
    private long zza;
    private String zzb;
    private Boolean zzc;
    private AccountManager zzd;
    private Boolean zze;
    private long zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzai(zzfy zzfyVar) {
        super(zzfyVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzgv
    protected final boolean zze() {
        Calendar instance = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert(instance.get(15) + instance.get(16), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase).length() + 1 + String.valueOf(lowerCase2).length());
        sb.append(lowerCase);
        sb.append("-");
        sb.append(lowerCase2);
        this.zzb = sb.toString();
        return false;
    }

    public final long zzf() {
        zzaa();
        return this.zza;
    }

    public final String zzg() {
        zzaa();
        return this.zzb;
    }

    public final boolean zza(Context context) {
        if (this.zzc == null) {
            zzu();
            this.zzc = false;
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    packageManager.getPackageInfo("com.google.android.gms", 128);
                    this.zzc = true;
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return this.zzc.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zzh() {
        zzd();
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi() {
        zzd();
        this.zze = null;
        this.zzf = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzj() {
        Account[] result;
        zzd();
        long currentTimeMillis = zzm().currentTimeMillis();
        if (currentTimeMillis - this.zzf > 86400000) {
            this.zze = null;
        }
        Boolean bool = this.zze;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ContextCompat.checkSelfPermission(zzn(), "android.permission.GET_ACCOUNTS") != 0) {
            zzr().zzj().zza("Permission error checking for dasher/unicorn accounts");
            this.zzf = currentTimeMillis;
            this.zze = false;
            return false;
        }
        if (this.zzd == null) {
            this.zzd = AccountManager.get(zzn());
        }
        try {
            result = this.zzd.getAccountsByTypeAndFeatures("com.google", new String[]{"service_HOSTED"}, null, null).getResult();
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            zzr().zzg().zza("Exception checking account types", e);
        }
        if (result == null || result.length <= 0) {
            Account[] result2 = this.zzd.getAccountsByTypeAndFeatures("com.google", new String[]{"service_uca"}, null, null).getResult();
            if (result2 != null && result2.length > 0) {
                this.zze = true;
                this.zzf = currentTimeMillis;
                return true;
            }
            this.zzf = currentTimeMillis;
            this.zze = false;
            return false;
        }
        this.zze = true;
        this.zzf = currentTimeMillis;
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzai zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzes zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzkr zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzfv zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzeu zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzfg zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ zzy zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzgs, com.google.android.gms.measurement.internal.zzgu
    public final /* bridge */ /* synthetic */ zzx zzu() {
        return super.zzu();
    }
}
