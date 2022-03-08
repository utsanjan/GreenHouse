package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzhk implements zzke {
    private final zzhh zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    public static zzhk zza(zzhh zzhhVar) {
        if (zzhhVar.zzd != null) {
            return zzhhVar.zzd;
        }
        return new zzhk(zzhhVar);
    }

    private zzhk(zzhh zzhhVar) {
        zzhh zzhhVar2 = (zzhh) zzii.zza(zzhhVar, "input");
        this.zza = zzhhVar2;
        zzhhVar2.zzd = this;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zza() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zza();
        }
        int i2 = this.zzb;
        if (i2 == 0 || i2 == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final boolean zzc() throws IOException {
        int i;
        if (this.zza.zzt() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzb(i);
    }

    private final void zza(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzin.zzf();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final double zzd() throws IOException {
        zza(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final float zze() throws IOException {
        zza(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final long zzf() throws IOException {
        zza(0);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final long zzg() throws IOException {
        zza(0);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzh() throws IOException {
        zza(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final long zzi() throws IOException {
        zza(1);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzj() throws IOException {
        zza(5);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final boolean zzk() throws IOException {
        zza(0);
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final String zzl() throws IOException {
        zza(2);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final String zzm() throws IOException {
        zza(2);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final <T> T zza(zzkd<T> zzkdVar, zzhs zzhsVar) throws IOException {
        zza(2);
        return (T) zzc(zzkdVar, zzhsVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final <T> T zzb(zzkd<T> zzkdVar, zzhs zzhsVar) throws IOException {
        zza(3);
        return (T) zzd(zzkdVar, zzhsVar);
    }

    private final <T> T zzc(zzkd<T> zzkdVar, zzhs zzhsVar) throws IOException {
        int zzm = this.zza.zzm();
        if (this.zza.zza < this.zza.zzb) {
            int zzc = this.zza.zzc(zzm);
            T zza = zzkdVar.zza();
            this.zza.zza++;
            zzkdVar.zza(zza, this, zzhsVar);
            zzkdVar.zzc(zza);
            this.zza.zza(0);
            zzhh zzhhVar = this.zza;
            zzhhVar.zza--;
            this.zza.zzd(zzc);
            return zza;
        }
        throw new zzin("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzkd<T> zzkdVar, zzhs zzhsVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T zza = zzkdVar.zza();
            zzkdVar.zza(zza, this, zzhsVar);
            zzkdVar.zzc(zza);
            if (this.zzb == this.zzc) {
                return zza;
            }
            throw zzin.zzh();
        } finally {
            this.zzc = i;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final zzgv zzn() throws IOException {
        zza(2);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzo() throws IOException {
        zza(0);
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzp() throws IOException {
        zza(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzq() throws IOException {
        zza(5);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final long zzr() throws IOException {
        zza(1);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final int zzs() throws IOException {
        zza(0);
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final long zzt() throws IOException {
        zza(0);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zza(List<Double> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzhr) {
            zzhr zzhrVar = (zzhr) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzhrVar.zza(this.zza.zzb());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzm = this.zza.zzm();
                zzb(zzm);
                int zzu = this.zza.zzu() + zzm;
                do {
                    zzhrVar.zza(this.zza.zzb());
                } while (this.zza.zzu() < zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzm2 = this.zza.zzm();
                zzb(zzm2);
                int zzu2 = this.zza.zzu() + zzm2;
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                } while (this.zza.zzu() < zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzb(List<Float> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzib) {
            zzib zzibVar = (zzib) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zzm = this.zza.zzm();
                zzc(zzm);
                int zzu = this.zza.zzu() + zzm;
                do {
                    zzibVar.zza(this.zza.zzc());
                } while (this.zza.zzu() < zzu);
            } else if (i == 5) {
                do {
                    zzibVar.zza(this.zza.zzc());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzm2 = this.zza.zzm();
                zzc(zzm2);
                int zzu2 = this.zza.zzu() + zzm2;
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzu() < zzu2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzc(List<Long> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzjb) {
            zzjb zzjbVar = (zzjb) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzjbVar.zza(this.zza.zzd());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzjbVar.zza(this.zza.zzd());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzd()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Long.valueOf(this.zza.zzd()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzd(List<Long> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzjb) {
            zzjb zzjbVar = (zzjb) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzjbVar.zza(this.zza.zze());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzjbVar.zza(this.zza.zze());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zze()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Long.valueOf(this.zza.zze()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zze(List<Integer> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzig) {
            zzig zzigVar = (zzig) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzigVar.zzd(this.zza.zzf());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzigVar.zzd(this.zza.zzf());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzf(List<Long> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzjb) {
            zzjb zzjbVar = (zzjb) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzjbVar.zza(this.zza.zzg());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzm = this.zza.zzm();
                zzb(zzm);
                int zzu = this.zza.zzu() + zzm;
                do {
                    zzjbVar.zza(this.zza.zzg());
                } while (this.zza.zzu() < zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzg()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzm2 = this.zza.zzm();
                zzb(zzm2);
                int zzu2 = this.zza.zzu() + zzm2;
                do {
                    list.add(Long.valueOf(this.zza.zzg()));
                } while (this.zza.zzu() < zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzg(List<Integer> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzig) {
            zzig zzigVar = (zzig) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zzm = this.zza.zzm();
                zzc(zzm);
                int zzu = this.zza.zzu() + zzm;
                do {
                    zzigVar.zzd(this.zza.zzh());
                } while (this.zza.zzu() < zzu);
            } else if (i == 5) {
                do {
                    zzigVar.zzd(this.zza.zzh());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzm2 = this.zza.zzm();
                zzc(zzm2);
                int zzu2 = this.zza.zzu() + zzm2;
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzu() < zzu2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzh(List<Boolean> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzgt) {
            zzgt zzgtVar = (zzgt) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzgtVar.zza(this.zza.zzi());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzgtVar.zza(this.zza.zzi());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzi()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Boolean.valueOf(this.zza.zzi()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzi(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzj(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zza;
        int zza2;
        if ((this.zzb & 7) != 2) {
            throw zzin.zzf();
        } else if (!(list instanceof zziy) || z) {
            do {
                list.add(z ? zzm() : zzl());
                if (!this.zza.zzt()) {
                    zza = this.zza.zza();
                } else {
                    return;
                }
            } while (zza == this.zzb);
            this.zzd = zza;
        } else {
            zziy zziyVar = (zziy) list;
            do {
                zziyVar.zza(zzn());
                if (!this.zza.zzt()) {
                    zza2 = this.zza.zza();
                } else {
                    return;
                }
            } while (zza2 == this.zzb);
            this.zzd = zza2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final <T> void zza(List<T> list, zzkd<T> zzkdVar, zzhs zzhsVar) throws IOException {
        int zza;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzkdVar, zzhsVar));
                if (!this.zza.zzt() && this.zzd == 0) {
                    zza = this.zza.zza();
                } else {
                    return;
                }
            } while (zza == i);
            this.zzd = zza;
            return;
        }
        throw zzin.zzf();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final <T> void zzb(List<T> list, zzkd<T> zzkdVar, zzhs zzhsVar) throws IOException {
        int zza;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzkdVar, zzhsVar));
                if (!this.zza.zzt() && this.zzd == 0) {
                    zza = this.zza.zza();
                } else {
                    return;
                }
            } while (zza == i);
            this.zzd = zza;
            return;
        }
        throw zzin.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzk(List<zzgv> list) throws IOException {
        int zza;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzn());
                if (!this.zza.zzt()) {
                    zza = this.zza.zza();
                } else {
                    return;
                }
            } while (zza == this.zzb);
            this.zzd = zza;
            return;
        }
        throw zzin.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzl(List<Integer> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzig) {
            zzig zzigVar = (zzig) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzigVar.zzd(this.zza.zzm());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzigVar.zzd(this.zza.zzm());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzm()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzm()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzm(List<Integer> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzig) {
            zzig zzigVar = (zzig) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzigVar.zzd(this.zza.zzn());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzigVar.zzd(this.zza.zzn());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzn(List<Integer> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzig) {
            zzig zzigVar = (zzig) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zzm = this.zza.zzm();
                zzc(zzm);
                int zzu = this.zza.zzu() + zzm;
                do {
                    zzigVar.zzd(this.zza.zzo());
                } while (this.zza.zzu() < zzu);
            } else if (i == 5) {
                do {
                    zzigVar.zzd(this.zza.zzo());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzm2 = this.zza.zzm();
                zzc(zzm2);
                int zzu2 = this.zza.zzu() + zzm2;
                do {
                    list.add(Integer.valueOf(this.zza.zzo()));
                } while (this.zza.zzu() < zzu2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzo()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzo(List<Long> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzjb) {
            zzjb zzjbVar = (zzjb) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzjbVar.zza(this.zza.zzp());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzm = this.zza.zzm();
                zzb(zzm);
                int zzu = this.zza.zzu() + zzm;
                do {
                    zzjbVar.zza(this.zza.zzp());
                } while (this.zza.zzu() < zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzm2 = this.zza.zzm();
                zzb(zzm2);
                int zzu2 = this.zza.zzu() + zzm2;
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzu() < zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzp(List<Integer> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzig) {
            zzig zzigVar = (zzig) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzigVar.zzd(this.zza.zzq());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzigVar.zzd(this.zza.zzq());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzq()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzq()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzke
    public final void zzq(List<Long> list) throws IOException {
        int zza;
        int zza2;
        if (list instanceof zzjb) {
            zzjb zzjbVar = (zzjb) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzjbVar.zza(this.zza.zzr());
                    if (!this.zza.zzt()) {
                        zza2 = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza2 == this.zzb);
                this.zzd = zza2;
            } else if (i == 2) {
                int zzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzjbVar.zza(this.zza.zzr());
                } while (this.zza.zzu() < zzu);
                zzd(zzu);
            } else {
                throw zzin.zzf();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzr()));
                    if (!this.zza.zzt()) {
                        zza = this.zza.zza();
                    } else {
                        return;
                    }
                } while (zza == this.zzb);
                this.zzd = zza;
            } else if (i2 == 2) {
                int zzu2 = this.zza.zzu() + this.zza.zzm();
                do {
                    list.add(Long.valueOf(this.zza.zzr()));
                } while (this.zza.zzu() < zzu2);
                zzd(zzu2);
            } else {
                throw zzin.zzf();
            }
        }
    }

    private static void zzb(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzin.zzh();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
        r8.put(r2, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0064, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzke
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.firebase_auth.zzji<K, V> r9, com.google.android.gms.internal.firebase_auth.zzhs r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zza(r0)
            com.google.android.gms.internal.firebase_auth.zzhh r1 = r7.zza
            int r1 = r1.zzm()
            com.google.android.gms.internal.firebase_auth.zzhh r2 = r7.zza
            int r1 = r2.zzc(r1)
            K r2 = r9.zzb
            V r3 = r9.zzd
        L_0x0014:
            int r4 = r7.zza()     // Catch: all -> 0x0065
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x005c
            com.google.android.gms.internal.firebase_auth.zzhh r5 = r7.zza     // Catch: all -> 0x0065
            boolean r5 = r5.zzt()     // Catch: all -> 0x0065
            if (r5 != 0) goto L_0x005c
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0046
            if (r4 == r0) goto L_0x0039
            boolean r4 = r7.zzc()     // Catch: zziq -> 0x004e, all -> 0x0065
            if (r4 == 0) goto L_0x0033
            goto L_0x0014
        L_0x0033:
            com.google.android.gms.internal.firebase_auth.zzin r4 = new com.google.android.gms.internal.firebase_auth.zzin     // Catch: zziq -> 0x004e, all -> 0x0065
            r4.<init>(r6)     // Catch: zziq -> 0x004e, all -> 0x0065
            throw r4     // Catch: zziq -> 0x004e, all -> 0x0065
        L_0x0039:
            com.google.android.gms.internal.firebase_auth.zzlm r4 = r9.zzc     // Catch: zziq -> 0x004e, all -> 0x0065
            V r5 = r9.zzd     // Catch: zziq -> 0x004e, all -> 0x0065
            java.lang.Class r5 = r5.getClass()     // Catch: zziq -> 0x004e, all -> 0x0065
            java.lang.Object r3 = r7.zza(r4, r5, r10)     // Catch: zziq -> 0x004e, all -> 0x0065
            goto L_0x0014
        L_0x0046:
            com.google.android.gms.internal.firebase_auth.zzlm r4 = r9.zza     // Catch: zziq -> 0x004e, all -> 0x0065
            r5 = 0
            java.lang.Object r2 = r7.zza(r4, r5, r5)     // Catch: zziq -> 0x004e, all -> 0x0065
            goto L_0x0014
        L_0x004e:
            r4 = move-exception
            boolean r4 = r7.zzc()     // Catch: all -> 0x0065
            if (r4 == 0) goto L_0x0056
            goto L_0x0014
        L_0x0056:
            com.google.android.gms.internal.firebase_auth.zzin r8 = new com.google.android.gms.internal.firebase_auth.zzin     // Catch: all -> 0x0065
            r8.<init>(r6)     // Catch: all -> 0x0065
            throw r8     // Catch: all -> 0x0065
        L_0x005c:
            r8.put(r2, r3)     // Catch: all -> 0x0065
            com.google.android.gms.internal.firebase_auth.zzhh r8 = r7.zza
            r8.zzd(r1)
            return
        L_0x0065:
            r8 = move-exception
            com.google.android.gms.internal.firebase_auth.zzhh r9 = r7.zza
            r9.zzd(r1)
            goto L_0x006d
        L_0x006c:
            throw r8
        L_0x006d:
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzhk.zza(java.util.Map, com.google.android.gms.internal.firebase_auth.zzji, com.google.android.gms.internal.firebase_auth.zzhs):void");
    }

    private final Object zza(zzlm zzlmVar, Class<?> cls, zzhs zzhsVar) throws IOException {
        switch (zzhn.zza[zzlmVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzk());
            case 2:
                return zzn();
            case 3:
                return Double.valueOf(zzd());
            case 4:
                return Integer.valueOf(zzp());
            case 5:
                return Integer.valueOf(zzj());
            case 6:
                return Long.valueOf(zzi());
            case 7:
                return Float.valueOf(zze());
            case 8:
                return Integer.valueOf(zzh());
            case 9:
                return Long.valueOf(zzg());
            case 10:
                zza(2);
                return zzc(zzjz.zza().zza((Class) cls), zzhsVar);
            case 11:
                return Integer.valueOf(zzq());
            case 12:
                return Long.valueOf(zzr());
            case 13:
                return Integer.valueOf(zzs());
            case 14:
                return Long.valueOf(zzt());
            case 15:
                return zzm();
            case 16:
                return Integer.valueOf(zzo());
            case 17:
                return Long.valueOf(zzf());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzin.zzh();
        }
    }

    private final void zzd(int i) throws IOException {
        if (this.zza.zzu() != i) {
            throw zzin.zza();
        }
    }
}
