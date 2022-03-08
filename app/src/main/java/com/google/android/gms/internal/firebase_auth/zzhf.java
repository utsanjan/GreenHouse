package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class zzhf extends zzhc {
    protected final byte[] zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhf(byte[] bArr) {
        if (bArr != null) {
            this.zzb = bArr;
            return;
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public byte zza(int i) {
        return this.zzb[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public byte zzb(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public final zzgv zza(int i, int i2) {
        int zzb = zzb(0, i2, zza());
        if (zzb == 0) {
            return zzgv.zza;
        }
        return new zzgy(this.zzb, zze(), zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public final void zza(zzgs zzgsVar) throws IOException {
        zzgsVar.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    protected final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public final boolean zzc() {
        int zze = zze();
        return zzlh.zza(this.zzb, zze, zza() + zze);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgv) || zza() != ((zzgv) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (!(obj instanceof zzhf)) {
            return obj.equals(this);
        }
        zzhf zzhfVar = (zzhf) obj;
        int zzd = zzd();
        int zzd2 = zzhfVar.zzd();
        if (zzd == 0 || zzd2 == 0 || zzd == zzd2) {
            return zza(zzhfVar, 0, zza());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhc
    final boolean zza(zzgv zzgvVar, int i, int i2) {
        if (i2 > zzgvVar.zza()) {
            int zza = zza();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(zza);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzgvVar.zza()) {
            int zza2 = zzgvVar.zza();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(zza2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzgvVar instanceof zzhf)) {
            return zzgvVar.zza(0, i2).equals(zza(0, i2));
        } else {
            zzhf zzhfVar = (zzhf) zzgvVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzhfVar.zzb;
            int zze = zze() + i2;
            int zze2 = zze();
            int zze3 = zzhfVar.zze();
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

    @Override // com.google.android.gms.internal.firebase_auth.zzgv
    protected final int zza(int i, int i2, int i3) {
        return zzii.zza(i, this.zzb, zze(), i3);
    }

    protected int zze() {
        return 0;
    }
}
