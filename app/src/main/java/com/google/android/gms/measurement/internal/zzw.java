package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzz();
    public String zza;
    public String zzb;
    public zzkq zzc;
    public long zzd;
    public boolean zze;
    public String zzf;
    public zzao zzg;
    public long zzh;
    public zzao zzi;
    public long zzj;
    public zzao zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzw zzwVar) {
        Preconditions.checkNotNull(zzwVar);
        this.zza = zzwVar.zza;
        this.zzb = zzwVar.zzb;
        this.zzc = zzwVar.zzc;
        this.zzd = zzwVar.zzd;
        this.zze = zzwVar.zze;
        this.zzf = zzwVar.zzf;
        this.zzg = zzwVar.zzg;
        this.zzh = zzwVar.zzh;
        this.zzi = zzwVar.zzi;
        this.zzj = zzwVar.zzj;
        this.zzk = zzwVar.zzk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(String str, String str2, zzkq zzkqVar, long j, boolean z, String str3, zzao zzaoVar, long j2, zzao zzaoVar2, long j3, zzao zzaoVar3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzkqVar;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzaoVar;
        this.zzh = j2;
        this.zzi = zzaoVar2;
        this.zzj = j3;
        this.zzk = zzaoVar3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
