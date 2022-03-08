package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzf {
    private final zzfy zza;
    private long zzaa;
    private long zzab;
    private long zzac;
    private String zzad;
    private boolean zzae;
    private long zzaf;
    private long zzag;
    private final String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    private String zzj;
    private long zzk;
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private long zzp;
    private boolean zzq;
    private boolean zzr;
    private String zzs;
    private Boolean zzt;
    private long zzu;
    private List<String> zzv;
    private String zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzfy zzfyVar, String str) {
        Preconditions.checkNotNull(zzfyVar);
        Preconditions.checkNotEmpty(str);
        this.zza = zzfyVar;
        this.zzb = str;
        zzfyVar.zzq().zzd();
    }

    public final boolean zza() {
        this.zza.zzq().zzd();
        return this.zzae;
    }

    public final void zzb() {
        this.zza.zzq().zzd();
        this.zzae = false;
    }

    public final String zzc() {
        this.zza.zzq().zzd();
        return this.zzb;
    }

    public final String zzd() {
        this.zza.zzq().zzd();
        return this.zzc;
    }

    public final void zza(String str) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zzc(this.zzc, str);
        this.zzc = str;
    }

    public final String zze() {
        this.zza.zzq().zzd();
        return this.zzd;
    }

    public final void zzb(String str) {
        this.zza.zzq().zzd();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzae |= !zzkr.zzc(this.zzd, str);
        this.zzd = str;
    }

    public final String zzf() {
        this.zza.zzq().zzd();
        return this.zzs;
    }

    public final void zzc(String str) {
        this.zza.zzq().zzd();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzae |= !zzkr.zzc(this.zzs, str);
        this.zzs = str;
    }

    public final String zzg() {
        this.zza.zzq().zzd();
        return this.zzw;
    }

    public final void zzd(String str) {
        this.zza.zzq().zzd();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzae |= !zzkr.zzc(this.zzw, str);
        this.zzw = str;
    }

    public final String zzh() {
        this.zza.zzq().zzd();
        return this.zze;
    }

    public final void zze(String str) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zzc(this.zze, str);
        this.zze = str;
    }

    public final String zzi() {
        this.zza.zzq().zzd();
        return this.zzf;
    }

    public final void zzf(String str) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zzc(this.zzf, str);
        this.zzf = str;
    }

    public final long zzj() {
        this.zza.zzq().zzd();
        return this.zzh;
    }

    public final void zza(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzh != j;
        this.zzh = j;
    }

    public final long zzk() {
        this.zza.zzq().zzd();
        return this.zzi;
    }

    public final void zzb(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzi != j;
        this.zzi = j;
    }

    public final String zzl() {
        this.zza.zzq().zzd();
        return this.zzj;
    }

    public final void zzg(String str) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zzc(this.zzj, str);
        this.zzj = str;
    }

    public final long zzm() {
        this.zza.zzq().zzd();
        return this.zzk;
    }

    public final void zzc(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzk != j;
        this.zzk = j;
    }

    public final String zzn() {
        this.zza.zzq().zzd();
        return this.zzl;
    }

    public final void zzh(String str) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zzc(this.zzl, str);
        this.zzl = str;
    }

    public final long zzo() {
        this.zza.zzq().zzd();
        return this.zzm;
    }

    public final void zzd(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzm != j;
        this.zzm = j;
    }

    public final long zzp() {
        this.zza.zzq().zzd();
        return this.zzn;
    }

    public final void zze(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzn != j;
        this.zzn = j;
    }

    public final long zzq() {
        this.zza.zzq().zzd();
        return this.zzu;
    }

    public final void zzf(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzu != j;
        this.zzu = j;
    }

    public final boolean zzr() {
        this.zza.zzq().zzd();
        return this.zzo;
    }

    public final void zza(boolean z) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzo != z;
        this.zzo = z;
    }

    public final void zzg(long j) {
        boolean z = true;
        Preconditions.checkArgument(j >= 0);
        this.zza.zzq().zzd();
        boolean z2 = this.zzae;
        if (this.zzg == j) {
            z = false;
        }
        this.zzae = z | z2;
        this.zzg = j;
    }

    public final long zzs() {
        this.zza.zzq().zzd();
        return this.zzg;
    }

    public final long zzt() {
        this.zza.zzq().zzd();
        return this.zzaf;
    }

    public final void zzh(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzaf != j;
        this.zzaf = j;
    }

    public final long zzu() {
        this.zza.zzq().zzd();
        return this.zzag;
    }

    public final void zzi(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzag != j;
        this.zzag = j;
    }

    public final void zzv() {
        this.zza.zzq().zzd();
        long j = this.zzg + 1;
        if (j > 2147483647L) {
            this.zza.zzr().zzi().zza("Bundle index overflow. appId", zzeu.zza(this.zzb));
            j = 0;
        }
        this.zzae = true;
        this.zzg = j;
    }

    public final long zzw() {
        this.zza.zzq().zzd();
        return this.zzx;
    }

    public final void zzj(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzx != j;
        this.zzx = j;
    }

    public final long zzx() {
        this.zza.zzq().zzd();
        return this.zzy;
    }

    public final void zzk(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzy != j;
        this.zzy = j;
    }

    public final long zzy() {
        this.zza.zzq().zzd();
        return this.zzz;
    }

    public final void zzl(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzz != j;
        this.zzz = j;
    }

    public final long zzz() {
        this.zza.zzq().zzd();
        return this.zzaa;
    }

    public final void zzm(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzaa != j;
        this.zzaa = j;
    }

    public final long zzaa() {
        this.zza.zzq().zzd();
        return this.zzac;
    }

    public final void zzn(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzac != j;
        this.zzac = j;
    }

    public final long zzab() {
        this.zza.zzq().zzd();
        return this.zzab;
    }

    public final void zzo(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzab != j;
        this.zzab = j;
    }

    public final String zzac() {
        this.zza.zzq().zzd();
        return this.zzad;
    }

    public final String zzad() {
        this.zza.zzq().zzd();
        String str = this.zzad;
        zzi((String) null);
        return str;
    }

    public final void zzi(String str) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zzc(this.zzad, str);
        this.zzad = str;
    }

    public final long zzae() {
        this.zza.zzq().zzd();
        return this.zzp;
    }

    public final void zzp(long j) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzp != j;
        this.zzp = j;
    }

    public final boolean zzaf() {
        this.zza.zzq().zzd();
        return this.zzq;
    }

    public final void zzb(boolean z) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzq != z;
        this.zzq = z;
    }

    public final boolean zzag() {
        this.zza.zzq().zzd();
        return this.zzr;
    }

    public final void zzc(boolean z) {
        this.zza.zzq().zzd();
        this.zzae |= this.zzr != z;
        this.zzr = z;
    }

    public final Boolean zzah() {
        this.zza.zzq().zzd();
        return this.zzt;
    }

    public final void zza(Boolean bool) {
        this.zza.zzq().zzd();
        this.zzae |= !zzkr.zza(this.zzt, bool);
        this.zzt = bool;
    }

    public final List<String> zzai() {
        this.zza.zzq().zzd();
        return this.zzv;
    }

    public final void zza(List<String> list) {
        this.zza.zzq().zzd();
        if (!zzkr.zza(this.zzv, list)) {
            this.zzae = true;
            this.zzv = list != null ? new ArrayList(list) : null;
        }
    }
}
