package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzhc<T> implements zzhp<T> {
    private final zzgw zza;
    private final zzih<?, ?> zzb;
    private final boolean zzc;
    private final zzfd<?> zzd;

    private zzhc(zzih<?, ?> zzihVar, zzfd<?> zzfdVar, zzgw zzgwVar) {
        this.zzb = zzihVar;
        this.zzc = zzfdVar.zza(zzgwVar);
        this.zzd = zzfdVar;
        this.zza = zzgwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzhc<T> zza(zzih<?, ?> zzihVar, zzfd<?> zzfdVar, zzgw zzgwVar) {
        return new zzhc<>(zzihVar, zzfdVar, zzgwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final T zza() {
        return (T) this.zza.zzbr().zzu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final boolean zza(T t, T t2) {
        if (!this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final int zza(T t) {
        int hashCode = this.zzb.zzb(t).hashCode();
        if (this.zzc) {
            return (hashCode * 53) + this.zzd.zza(t).hashCode();
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zzb(T t, T t2) {
        zzhr.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzhr.zza(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zza(T t, zzja zzjaVar) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd = this.zzd.zza(t).zzd();
        while (zzd.hasNext()) {
            Map.Entry<?, Object> next = zzd.next();
            zzfg zzfgVar = (zzfg) next.getKey();
            if (zzfgVar.zzc() != zzjb.MESSAGE || zzfgVar.zzd() || zzfgVar.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzgd) {
                zzjaVar.zza(zzfgVar.zza(), (Object) ((zzgd) next).zza().zzc());
            } else {
                zzjaVar.zza(zzfgVar.zza(), next.getValue());
            }
        }
        zzih<?, ?> zzihVar = this.zzb;
        zzihVar.zzb((zzih<?, ?>) zzihVar.zzb(t), zzjaVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f A[EDGE_INSN: B:59:0x009f->B:33:0x009f ?: BREAK  , SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzhp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.measurement.zzeb r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.measurement.zzfo r0 = (com.google.android.gms.internal.measurement.zzfo) r0
            com.google.android.gms.internal.measurement.zzig r1 = r0.zzb
            com.google.android.gms.internal.measurement.zzig r2 = com.google.android.gms.internal.measurement.zzig.zza()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.measurement.zzig r1 = com.google.android.gms.internal.measurement.zzig.zzb()
            r0.zzb = r1
        L_0x0011:
            com.google.android.gms.internal.measurement.zzfo$zzb r10 = (com.google.android.gms.internal.measurement.zzfo.zzb) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L_0x0018:
            if (r12 >= r13) goto L_0x00ac
            int r4 = com.google.android.gms.internal.measurement.zzec.zza(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L_0x0053
            r12 = r2 & 7
            if (r12 != r3) goto L_0x004e
            com.google.android.gms.internal.measurement.zzfd<?> r12 = r9.zzd
            com.google.android.gms.internal.measurement.zzfb r0 = r14.zzd
            com.google.android.gms.internal.measurement.zzgw r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.measurement.zzfo$zzd r0 = (com.google.android.gms.internal.measurement.zzfo.zzd) r0
            if (r0 != 0) goto L_0x0045
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.measurement.zzec.zza(r2, r3, r4, r5, r6, r7)
            goto L_0x0018
        L_0x0045:
            com.google.android.gms.internal.measurement.zzhl.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x004e:
            int r12 = com.google.android.gms.internal.measurement.zzec.zza(r2, r11, r4, r13, r14)
            goto L_0x0018
        L_0x0053:
            r12 = 0
            r2 = r10
        L_0x0055:
            if (r4 >= r13) goto L_0x009f
            int r4 = com.google.android.gms.internal.measurement.zzec.zza(r11, r4, r14)
            int r5 = r14.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L_0x0081
            r8 = 3
            if (r6 == r8) goto L_0x006b
            goto L_0x0096
        L_0x006b:
            if (r0 != 0) goto L_0x0078
            if (r7 != r3) goto L_0x0096
            int r4 = com.google.android.gms.internal.measurement.zzec.zze(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.gms.internal.measurement.zzeg r2 = (com.google.android.gms.internal.measurement.zzeg) r2
            goto L_0x0055
        L_0x0078:
            com.google.android.gms.internal.measurement.zzhl.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L_0x0081:
            if (r7 != 0) goto L_0x0096
            int r4 = com.google.android.gms.internal.measurement.zzec.zza(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.gms.internal.measurement.zzfd<?> r0 = r9.zzd
            com.google.android.gms.internal.measurement.zzfb r5 = r14.zzd
            com.google.android.gms.internal.measurement.zzgw r6 = r9.zza
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.measurement.zzfo$zzd r0 = (com.google.android.gms.internal.measurement.zzfo.zzd) r0
            goto L_0x0055
        L_0x0096:
            r6 = 12
            if (r5 == r6) goto L_0x009f
            int r4 = com.google.android.gms.internal.measurement.zzec.zza(r5, r11, r4, r13, r14)
            goto L_0x0055
        L_0x009f:
            if (r2 == 0) goto L_0x00a9
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zza(r12, r2)
        L_0x00a9:
            r12 = r4
            goto L_0x0018
        L_0x00ac:
            if (r12 != r13) goto L_0x00af
            return
        L_0x00af:
            com.google.android.gms.internal.measurement.zzfw r10 = com.google.android.gms.internal.measurement.zzfw.zzg()
            goto L_0x00b5
        L_0x00b4:
            throw r10
        L_0x00b5:
            goto L_0x00b4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhc.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzeb):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zza(T t, zzhm zzhmVar, zzfb zzfbVar) throws IOException {
        boolean z;
        zzih<?, ?> zzihVar = this.zzb;
        zzfd<?> zzfdVar = this.zzd;
        Object zzc = zzihVar.zzc(t);
        zzfe<?> zzb = zzfdVar.zzb(t);
        do {
            try {
                if (zzhmVar.zza() != Integer.MAX_VALUE) {
                    int zzb2 = zzhmVar.zzb();
                    if (zzb2 == 11) {
                        int i = 0;
                        Object obj = null;
                        zzeg zzegVar = null;
                        while (zzhmVar.zza() != Integer.MAX_VALUE) {
                            int zzb3 = zzhmVar.zzb();
                            if (zzb3 == 16) {
                                i = zzhmVar.zzo();
                                obj = zzfdVar.zza(zzfbVar, this.zza, i);
                            } else if (zzb3 == 26) {
                                if (obj != null) {
                                    zzfdVar.zza(zzhmVar, obj, zzfbVar, zzb);
                                } else {
                                    zzegVar = zzhmVar.zzn();
                                }
                            } else if (!zzhmVar.zzc()) {
                                break;
                            }
                        }
                        if (zzhmVar.zzb() != 12) {
                            throw zzfw.zze();
                        } else if (zzegVar != null) {
                            if (obj != null) {
                                zzfdVar.zza(zzegVar, obj, zzfbVar, zzb);
                            } else {
                                zzihVar.zza((zzih<?, ?>) zzc, i, zzegVar);
                            }
                        }
                    } else if ((zzb2 & 7) == 2) {
                        Object zza = zzfdVar.zza(zzfbVar, this.zza, zzb2 >>> 3);
                        if (zza != null) {
                            zzfdVar.zza(zzhmVar, zza, zzfbVar, zzb);
                        } else {
                            z = zzihVar.zza((zzih<?, ?>) zzc, zzhmVar);
                            continue;
                        }
                    } else {
                        z = zzhmVar.zzc();
                        continue;
                    }
                    z = true;
                    continue;
                } else {
                    return;
                }
            } finally {
                zzihVar.zzb((Object) t, (T) zzc);
            }
        } while (z);
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhp
    public final int zzb(T t) {
        zzih<?, ?> zzihVar = this.zzb;
        int zze = zzihVar.zze(zzihVar.zzb(t)) + 0;
        if (this.zzc) {
            return zze + this.zzd.zza(t).zzg();
        }
        return zze;
    }
}
