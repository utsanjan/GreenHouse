package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzki;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzq {
    private String zza;
    private boolean zzb;
    private zzcb.zzi zzc;
    private BitSet zzd;
    private BitSet zze;
    private Map<Integer, Long> zzf;
    private Map<Integer, List<Long>> zzg;
    private final /* synthetic */ zzo zzh;

    private zzq(zzo zzoVar, String str) {
        this.zzh = zzoVar;
        this.zza = str;
        this.zzb = true;
        this.zzd = new BitSet();
        this.zze = new BitSet();
        this.zzf = new ArrayMap();
        this.zzg = new ArrayMap();
    }

    private zzq(zzo zzoVar, String str, zzcb.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.zzh = zzoVar;
        this.zza = str;
        this.zzd = bitSet;
        this.zze = bitSet2;
        this.zzf = map;
        this.zzg = new ArrayMap();
        if (map2 != null) {
            for (Integer num : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(num));
                this.zzg.put(num, arrayList);
            }
        }
        this.zzb = false;
        this.zzc = zziVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzv zzvVar) {
        int zza = zzvVar.zza();
        if (zzvVar.zzc != null) {
            this.zze.set(zza, zzvVar.zzc.booleanValue());
        }
        if (zzvVar.zzd != null) {
            this.zzd.set(zza, zzvVar.zzd.booleanValue());
        }
        if (zzvVar.zze != null) {
            Long l = this.zzf.get(Integer.valueOf(zza));
            long longValue = zzvVar.zze.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.zzf.put(Integer.valueOf(zza), Long.valueOf(longValue));
            }
        }
        if (zzvVar.zzf != null) {
            List<Long> list = this.zzg.get(Integer.valueOf(zza));
            if (list == null) {
                list = new ArrayList<>();
                this.zzg.put(Integer.valueOf(zza), list);
            }
            if (zzvVar.zzb()) {
                list.clear();
            }
            if (zzki.zzb() && this.zzh.zzt().zzd(this.zza, zzaq.zzbf) && zzvVar.zzc()) {
                list.clear();
            }
            if (!zzki.zzb() || !this.zzh.zzt().zzd(this.zza, zzaq.zzbf)) {
                list.add(Long.valueOf(zzvVar.zzf.longValue() / 1000));
                return;
            }
            long longValue2 = zzvVar.zzf.longValue() / 1000;
            if (!list.contains(Long.valueOf(longValue2))) {
                list.add(Long.valueOf(longValue2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.List] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.measurement.zzcb.zza zza(int r8) {
        /*
            r7 = this;
            com.google.android.gms.internal.measurement.zzcb$zza$zza r0 = com.google.android.gms.internal.measurement.zzcb.zza.zzh()
            r0.zza(r8)
            boolean r8 = r7.zzb
            r0.zza(r8)
            com.google.android.gms.internal.measurement.zzcb$zzi r8 = r7.zzc
            if (r8 == 0) goto L_0x0013
            r0.zza(r8)
        L_0x0013:
            com.google.android.gms.internal.measurement.zzcb$zzi$zza r8 = com.google.android.gms.internal.measurement.zzcb.zzi.zzi()
            java.util.BitSet r1 = r7.zzd
            java.util.List r1 = com.google.android.gms.measurement.internal.zzkn.zza(r1)
            com.google.android.gms.internal.measurement.zzcb$zzi$zza r8 = r8.zzb(r1)
            java.util.BitSet r1 = r7.zze
            java.util.List r1 = com.google.android.gms.measurement.internal.zzkn.zza(r1)
            com.google.android.gms.internal.measurement.zzcb$zzi$zza r8 = r8.zza(r1)
            java.util.Map<java.lang.Integer, java.lang.Long> r1 = r7.zzf
            if (r1 != 0) goto L_0x0032
            r1 = 0
            goto L_0x0081
        L_0x0032:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Map<java.lang.Integer, java.lang.Long> r2 = r7.zzf
            int r2 = r2.size()
            r1.<init>(r2)
            java.util.Map<java.lang.Integer, java.lang.Long> r2 = r7.zzf
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0047:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0080
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            com.google.android.gms.internal.measurement.zzcb$zzb$zza r4 = com.google.android.gms.internal.measurement.zzcb.zzb.zze()
            com.google.android.gms.internal.measurement.zzcb$zzb$zza r4 = r4.zza(r3)
            java.util.Map<java.lang.Integer, java.lang.Long> r5 = r7.zzf
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r3 = r5.get(r3)
            java.lang.Long r3 = (java.lang.Long) r3
            long r5 = r3.longValue()
            com.google.android.gms.internal.measurement.zzcb$zzb$zza r3 = r4.zza(r5)
            com.google.android.gms.internal.measurement.zzgw r3 = r3.zzv()
            com.google.android.gms.internal.measurement.zzfo r3 = (com.google.android.gms.internal.measurement.zzfo) r3
            com.google.android.gms.internal.measurement.zzcb$zzb r3 = (com.google.android.gms.internal.measurement.zzcb.zzb) r3
            r1.add(r3)
            goto L_0x0047
        L_0x0080:
        L_0x0081:
            r8.zzc(r1)
            java.util.Map<java.lang.Integer, java.util.List<java.lang.Long>> r1 = r7.zzg
            if (r1 != 0) goto L_0x008f
            java.util.List r1 = java.util.Collections.emptyList()
            goto L_0x00d9
        L_0x008f:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Map<java.lang.Integer, java.util.List<java.lang.Long>> r2 = r7.zzg
            int r2 = r2.size()
            r1.<init>(r2)
            java.util.Map<java.lang.Integer, java.util.List<java.lang.Long>> r2 = r7.zzg
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x00a4:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00d8
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            com.google.android.gms.internal.measurement.zzcb$zzj$zza r4 = com.google.android.gms.internal.measurement.zzcb.zzj.zze()
            int r5 = r3.intValue()
            com.google.android.gms.internal.measurement.zzcb$zzj$zza r4 = r4.zza(r5)
            java.util.Map<java.lang.Integer, java.util.List<java.lang.Long>> r5 = r7.zzg
            java.lang.Object r3 = r5.get(r3)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L_0x00cc
            java.util.Collections.sort(r3)
            r4.zza(r3)
        L_0x00cc:
            com.google.android.gms.internal.measurement.zzgw r3 = r4.zzv()
            com.google.android.gms.internal.measurement.zzfo r3 = (com.google.android.gms.internal.measurement.zzfo) r3
            com.google.android.gms.internal.measurement.zzcb$zzj r3 = (com.google.android.gms.internal.measurement.zzcb.zzj) r3
            r1.add(r3)
            goto L_0x00a4
        L_0x00d8:
        L_0x00d9:
            r8.zzd(r1)
            r0.zza(r8)
            com.google.android.gms.internal.measurement.zzgw r8 = r0.zzv()
            com.google.android.gms.internal.measurement.zzfo r8 = (com.google.android.gms.internal.measurement.zzfo) r8
            com.google.android.gms.internal.measurement.zzcb$zza r8 = (com.google.android.gms.internal.measurement.zzcb.zza) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq.zza(int):com.google.android.gms.internal.measurement.zzcb$zza");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzq(zzo zzoVar, String str, zzcb.zzi zziVar, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzr zzrVar) {
        this(zzoVar, str, zziVar, bitSet, bitSet2, map, map2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzq(zzo zzoVar, String str, zzr zzrVar) {
        this(zzoVar, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BitSet zza(zzq zzqVar) {
        return zzqVar.zzd;
    }
}
