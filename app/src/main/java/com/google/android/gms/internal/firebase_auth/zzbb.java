package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
abstract class zzbb extends zzae<String> {
    final CharSequence zza;
    private final zzaf zzb;
    private int zze;
    private int zzd = 0;
    private final boolean zzc = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzbb(zzau zzauVar, CharSequence charSequence) {
        zzaf zzafVar;
        int i;
        zzafVar = zzauVar.zza;
        this.zzb = zzafVar;
        i = zzauVar.zzd;
        this.zze = i;
        this.zza = charSequence;
    }

    abstract int zza(int i);

    abstract int zzb(int i);

    @Override // com.google.android.gms.internal.firebase_auth.zzae
    protected final /* synthetic */ String zza() {
        int zza;
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 != -1) {
                zza = zza(i2);
                if (zza == -1) {
                    zza = this.zza.length();
                    this.zzd = -1;
                } else {
                    this.zzd = zzb(zza);
                }
                int i3 = this.zzd;
                if (i3 == i) {
                    int i4 = i3 + 1;
                    this.zzd = i4;
                    if (i4 > this.zza.length()) {
                        this.zzd = -1;
                    }
                } else {
                    while (i < zza && this.zzb.zza(this.zza.charAt(i))) {
                        i++;
                    }
                    while (zza > i && this.zzb.zza(this.zza.charAt(zza - 1))) {
                        zza--;
                    }
                    if (!this.zzc || i != zza) {
                        break;
                    }
                    i = this.zzd;
                }
            } else {
                zzb();
                return null;
            }
        }
        int i5 = this.zze;
        if (i5 == 1) {
            zza = this.zza.length();
            this.zzd = -1;
            while (zza > i && this.zzb.zza(this.zza.charAt(zza - 1))) {
                zza--;
            }
        } else {
            this.zze = i5 - 1;
        }
        return this.zza.subSequence(i, zza).toString();
    }
}
