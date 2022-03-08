package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public class zzeq extends zzen {
    protected final byte[] zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeq(byte[] bArr) {
        if (bArr != null) {
            this.zzb = bArr;
            return;
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    public byte zza(int i) {
        return this.zzb[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzeg
    public byte zzb(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    public final zzeg zza(int i, int i2) {
        int zzb = zzb(0, i2, zza());
        if (zzb == 0) {
            return zzeg.zza;
        }
        return new zzej(this.zzb, zze(), zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzeg
    public final void zza(zzed zzedVar) throws IOException {
        zzedVar.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    protected final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    public final boolean zzc() {
        int zze = zze();
        return zzip.zza(this.zzb, zze, zza() + zze);
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeg) || zza() != ((zzeg) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (!(obj instanceof zzeq)) {
            return obj.equals(this);
        }
        zzeq zzeqVar = (zzeq) obj;
        int zzd = zzd();
        int zzd2 = zzeqVar.zzd();
        if (zzd == 0 || zzd2 == 0 || zzd == zzd2) {
            return zza(zzeqVar, 0, zza());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzen
    final boolean zza(zzeg zzegVar, int i, int i2) {
        if (i2 > zzegVar.zza()) {
            int zza = zza();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(zza);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzegVar.zza()) {
            int zza2 = zzegVar.zza();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(zza2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzegVar instanceof zzeq)) {
            return zzegVar.zza(0, i2).equals(zza(0, i2));
        } else {
            zzeq zzeqVar = (zzeq) zzegVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzeqVar.zzb;
            int zze = zze() + i2;
            int zze2 = zze();
            int zze3 = zzeqVar.zze();
            while (zze2 < zze) {
                if (bArr[zze2] != bArr2[zze3]) {
                    return false;
                }
                zze2++;
                zze3++;
            }
            return true;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzeg
    protected final int zza(int i, int i2, int i3) {
        return zzfr.zza(i, this.zzb, zze(), i3);
    }

    protected int zze() {
        return 0;
    }
}
