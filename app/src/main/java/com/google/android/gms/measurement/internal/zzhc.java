package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzjw;
import com.google.android.gms.internal.measurement.zzkh;
import com.google.android.gms.internal.measurement.zzko;
import com.google.android.gms.internal.measurement.zzla;
import com.google.android.gms.internal.measurement.zzlr;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzhc extends zzg {
    protected zzhy zza;
    final zzp zzb;
    private zzhb zzd;
    private boolean zzf;
    private final Set<zzha> zze = new CopyOnWriteArraySet();
    protected boolean zzc = true;
    private final AtomicReference<String> zzg = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: protected */
    public zzhc(zzfy zzfyVar) {
        super(zzfyVar);
        this.zzb = new zzp(zzfyVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zzz() {
        return false;
    }

    public final void zzab() {
        if (zzn().getApplicationContext() instanceof Application) {
            ((Application) zzn().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzq().zza(atomicReference, 15000L, "boolean test flag value", new zzhh(this, atomicReference));
    }

    public final String zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzq().zza(atomicReference, 15000L, "String test flag value", new zzho(this, atomicReference));
    }

    public final Long zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzq().zza(atomicReference, 15000L, "long test flag value", new zzht(this, atomicReference));
    }

    public final Integer zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzq().zza(atomicReference, 15000L, "int test flag value", new zzhs(this, atomicReference));
    }

    public final Double zzag() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzq().zza(atomicReference, 15000L, "double test flag value", new zzhv(this, atomicReference));
    }

    public final void zza(boolean z) {
        zzw();
        zzb();
        zzq().zza(new zzhu(this, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(boolean z) {
        zzd();
        zzb();
        zzw();
        zzr().zzw().zza("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzs().zzb(z);
        zzam();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzam() {
        zzd();
        String zza = zzs().zzn.zza();
        if (zza != null) {
            if ("unset".equals(zza)) {
                zza("app", "_npa", (Object) null, zzm().currentTimeMillis());
            } else {
                zza("app", "_npa", Long.valueOf("true".equals(zza) ? 1L : 0L), zzm().currentTimeMillis());
            }
        }
        if (!this.zzy.zzab() || !this.zzc) {
            zzr().zzw().zza("Updating Scion state (FE)");
            zzh().zzac();
            return;
        }
        zzr().zzw().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzai();
        if (zzla.zzb() && zzt().zza(zzaq.zzbv)) {
            zzk().zza.zza();
        }
        if (zzko.zzb() && zzt().zza(zzaq.zzca)) {
            if (!(this.zzy.zzf().zza.zzc().zzi.zza() > 0)) {
                zzfp zzf = this.zzy.zzf();
                zzf.zza.zzad();
                zzf.zza(zzf.zza.zzn().getPackageName());
            }
        }
        if (zzt().zza(zzaq.zzcq)) {
            zzq().zza(new zzhw(this));
        }
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzm().currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(String str, String str2, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, zzm().currentTimeMillis(), bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzb();
        zzd();
        zza(str, str2, j, bundle, true, this.zzd == null || zzkr.zze(str2), false, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzij zzijVar;
        zzij zzijVar2;
        String str4;
        long j2;
        long j3;
        String str5;
        String str6;
        String str7;
        ArrayList arrayList;
        String str8;
        Bundle bundle2;
        String str9;
        boolean z4;
        int i;
        Class<?> cls;
        List<String> zzah;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzd();
        zzw();
        if (!this.zzy.zzab()) {
            zzr().zzw().zza("Event not sent since app measurement is disabled");
        } else if (!zzt().zza(zzaq.zzbb) || (zzah = zzg().zzah()) == null || zzah.contains(str2)) {
            int i2 = 0;
            if (!this.zzf) {
                this.zzf = true;
                try {
                    if (!this.zzy.zzt()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zzn().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", Context.class).invoke(null, zzn());
                    } catch (Exception e) {
                        zzr().zzi().zza("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException e2) {
                    zzr().zzv().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if (zzt().zza(zzaq.zzbh) && "_cmp".equals(str2) && bundle.containsKey("gclid")) {
                zza("auto", "_lgclid", bundle.getString("gclid"), zzm().currentTimeMillis());
            }
            if (zzlr.zzb() && zzt().zza(zzaq.zzcm)) {
                zzu();
                if (z && zzkr.zzg(str2)) {
                    zzp().zza(bundle, zzs().zzx.zza());
                }
            }
            if (z3) {
                zzu();
                if (!"_iap".equals(str2)) {
                    zzkr zzi = this.zzy.zzi();
                    if (!zzi.zza(NotificationCompat.CATEGORY_EVENT, str2)) {
                        i = 2;
                    } else if (!zzi.zza(NotificationCompat.CATEGORY_EVENT, zzgw.zza, str2)) {
                        i = 13;
                    } else if (!zzi.zza(NotificationCompat.CATEGORY_EVENT, 40, str2)) {
                        i = 2;
                    } else {
                        i = 0;
                    }
                    if (i != 0) {
                        zzr().zzh().zza("Invalid public event name. Event will not be logged (FE)", zzo().zza(str2));
                        this.zzy.zzi();
                        String zza = zzkr.zza(str2, 40, true);
                        if (str2 != null) {
                            i2 = str2.length();
                        }
                        this.zzy.zzi().zza(i, "_ev", zza, i2);
                        return;
                    }
                }
            }
            zzu();
            zzij zza2 = zzi().zza(false);
            if (zza2 != null && !bundle.containsKey("_sc")) {
                zza2.zzd = true;
            }
            zzii.zza(zza2, bundle, z && z3);
            boolean equals = "am".equals(str);
            boolean zze = zzkr.zze(str2);
            if (z && this.zzd != null && !zze && !equals) {
                zzr().zzw().zza("Passing event to registered event handler (FE)", zzo().zza(str2), zzo().zza(bundle));
                this.zzd.interceptEvent(str, str2, bundle, j);
            } else if (this.zzy.zzag()) {
                int zzb = zzp().zzb(str2);
                if (zzb != 0) {
                    zzr().zzh().zza("Invalid event name. Event will not be logged (FE)", zzo().zza(str2));
                    zzp();
                    String zza3 = zzkr.zza(str2, 40, true);
                    if (str2 != null) {
                        i2 = str2.length();
                    }
                    this.zzy.zzi().zza(str3, zzb, "_ev", zza3, i2);
                    return;
                }
                List<String> listOf = CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"});
                Bundle zza4 = zzp().zza(str3, str2, bundle, listOf, z3, true);
                if (zza4 == null || !zza4.containsKey("_sc") || !zza4.containsKey("_si")) {
                    zzijVar = null;
                } else {
                    zzijVar = new zzij(zza4.getString("_sn"), zza4.getString("_sc"), Long.valueOf(zza4.getLong("_si")).longValue());
                }
                if (zzijVar == null) {
                    zzijVar2 = zza2;
                } else {
                    zzijVar2 = zzijVar;
                }
                String str10 = "_ae";
                if (zzt().zza(zzaq.zzat)) {
                    zzu();
                    if (zzi().zza(false) != null) {
                        str4 = str2;
                        if (str10.equals(str4)) {
                            long zzb2 = zzk().zzb.zzb();
                            if (zzb2 > 0) {
                                zzp().zza(zza4, zzb2);
                            }
                        }
                    } else {
                        str4 = str2;
                    }
                } else {
                    str4 = str2;
                }
                if (zzkh.zzb() && zzt().zza(zzaq.zzbu)) {
                    if (!"auto".equals(str) && "_ssr".equals(str4)) {
                        zzkr zzp = zzp();
                        String string = zza4.getString("_ffr");
                        if (Strings.isEmptyOrWhitespace(string)) {
                            str9 = null;
                        } else {
                            str9 = string.trim();
                        }
                        if (zzkr.zzc(str9, zzp.zzs().zzu.zza())) {
                            zzp.zzr().zzw().zza("Not logging duplicate session_start_with_rollout event");
                            z4 = false;
                        } else {
                            zzp.zzs().zzu.zza(str9);
                            z4 = true;
                        }
                        if (!z4) {
                            return;
                        }
                    } else if (str10.equals(str4)) {
                        String zza5 = zzp().zzs().zzu.zza();
                        if (!TextUtils.isEmpty(zza5)) {
                            zza4.putString("_ffr", zza5);
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(zza4);
                long nextLong = zzp().zzh().nextLong();
                if (zzs().zzp.zza() > 0) {
                    j3 = j;
                    if (!zzs().zza(j3)) {
                        j2 = nextLong;
                        str5 = "_o";
                    } else if (zzs().zzr.zza()) {
                        zzr().zzx().zza("Current session is expired, remove the session number, ID, and engagement time");
                        j2 = nextLong;
                        str5 = "_o";
                        zza("auto", "_sid", (Object) null, zzm().currentTimeMillis());
                        zza("auto", "_sno", (Object) null, zzm().currentTimeMillis());
                        zza("auto", "_se", (Object) null, zzm().currentTimeMillis());
                    } else {
                        j2 = nextLong;
                        str5 = "_o";
                    }
                } else {
                    j3 = j;
                    j2 = nextLong;
                    str5 = "_o";
                }
                if (zza4.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, 0L) == 1) {
                    zzr().zzx().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.zzy.zze().zza.zza(j3, true);
                }
                String[] strArr = (String[]) zza4.keySet().toArray(new String[zza4.size()]);
                Arrays.sort(strArr);
                if (!zzjw.zzb() || !zzt().zza(zzaq.zzcg) || !zzt().zza(zzaq.zzcf)) {
                    int length = strArr.length;
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < length) {
                        String str11 = strArr[i3];
                        Object obj = zza4.get(str11);
                        zzp();
                        Bundle[] zzb3 = zzkr.zzb(obj);
                        if (zzb3 != null) {
                            length = length;
                            zza4.putInt(str11, zzb3.length);
                            int i5 = 0;
                            while (i5 < zzb3.length) {
                                Bundle bundle3 = zzb3[i5];
                                zzii.zza(zzijVar2, bundle3, true);
                                Bundle zza6 = zzp().zza(str3, "_ep", bundle3, listOf, z3, false);
                                zza6.putString("_en", str2);
                                zza6.putLong("_eid", j2);
                                zza6.putString("_gn", str11);
                                zza6.putInt("_ll", zzb3.length);
                                zza6.putInt("_i", i5);
                                arrayList2.add(zza6);
                                i5++;
                                zza4 = zza4;
                                zzijVar2 = zzijVar2;
                                listOf = listOf;
                                str10 = str10;
                                str4 = str2;
                                str5 = str5;
                                arrayList2 = arrayList2;
                            }
                            listOf = listOf;
                            arrayList2 = arrayList2;
                            str10 = str10;
                            zzijVar2 = zzijVar2;
                            zza4 = zza4;
                            j2 = j2;
                            i4 += zzb3.length;
                        } else {
                            listOf = listOf;
                            length = length;
                            str5 = str5;
                            arrayList2 = arrayList2;
                            str4 = str4;
                            str10 = str10;
                            zzijVar2 = zzijVar2;
                            zza4 = zza4;
                            j2 = j2;
                        }
                        i3++;
                        strArr = strArr;
                    }
                    str6 = str5;
                    arrayList = arrayList2;
                    str7 = str4;
                    str8 = str10;
                    if (i4 != 0) {
                        zza4.putLong("_eid", j2);
                        zza4.putInt("_epc", i4);
                    }
                } else {
                    for (String str12 : strArr) {
                        zzp();
                        Bundle[] zzb4 = zzkr.zzb(zza4.get(str12));
                        if (zzb4 != null) {
                            zza4.putParcelableArray(str12, zzb4);
                        }
                    }
                    str6 = str5;
                    arrayList = arrayList2;
                    str7 = str4;
                    str8 = str10;
                }
                int i6 = 0;
                while (i6 < arrayList.size()) {
                    Bundle bundle4 = (Bundle) arrayList.get(i6);
                    String str13 = i6 != 0 ? "_ep" : str7;
                    bundle4.putString(str6, str);
                    if (z2) {
                        bundle2 = zzp().zza(bundle4);
                    } else {
                        bundle2 = bundle4;
                    }
                    zzh().zza(new zzao(str13, new zzan(bundle2), str, j), str3);
                    if (!equals) {
                        for (zzha zzhaVar : this.zze) {
                            zzhaVar.onEvent(str, str2, new Bundle(bundle2), j);
                        }
                    }
                    i6++;
                    str6 = str6;
                }
                zzu();
                if (zzi().zza(false) != null && str8.equals(str7)) {
                    zzk().zza(true, true, zzm().elapsedRealtime());
                }
            }
        } else {
            zzr().zzw().zza("Dropping non-safelisted event. event name, origin", str2, str);
        }
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        String str3;
        Bundle bundle2;
        boolean z3;
        zzb();
        if (str == null) {
            str3 = "app";
        } else {
            str3 = str;
        }
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = bundle;
        }
        if (zzt().zza(zzaq.zzcc) && zzkr.zzc(str2, "screen_view")) {
            zzi().zza(bundle2);
            return;
        }
        if (z2 && this.zzd != null && !zzkr.zze(str2)) {
            z3 = false;
            zzb(str3, str2, j, bundle2, z2, z3, !z, null);
        }
        z3 = true;
        zzb(str3, str2, j, bundle2, z2, z3, !z, null);
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzq().zza(new zzhj(this, str, str2, j, zzkr.zzb(bundle), z, z2, z3, str3));
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, true, zzm().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        String str3;
        if (str == null) {
            str3 = "app";
        } else {
            str3 = str;
        }
        int i = 6;
        int i2 = 0;
        if (z) {
            i = zzp().zzc(str2);
        } else {
            zzkr zzp = zzp();
            if (zzp.zza("user property", str2)) {
                if (!zzp.zza("user property", zzgy.zza, str2)) {
                    i = 15;
                } else if (zzp.zza("user property", 24, str2)) {
                    i = 0;
                }
            }
        }
        if (i != 0) {
            zzp();
            String zza = zzkr.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.zzy.zzi().zza(i, "_ev", zza, i2);
        } else if (obj != null) {
            int zzb = zzp().zzb(str2, obj);
            if (zzb != 0) {
                zzp();
                String zza2 = zzkr.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.zzy.zzi().zza(zzb, "_ev", zza2, i2);
                return;
            }
            Object zzc = zzp().zzc(str2, obj);
            if (zzc != null) {
                zza(str3, str2, j, zzc);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzq().zza(new zzhi(this, str, str2, obj, j));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(java.lang.String r9, java.lang.String r10, java.lang.Object r11, long r12) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzd()
            r8.zzb()
            r8.zzw()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L_0x0069
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L_0x0058
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x0058
            java.util.Locale r10 = java.util.Locale.ENGLISH
            java.lang.String r10 = r0.toLowerCase(r10)
            java.lang.String r11 = "false"
            boolean r10 = r11.equals(r10)
            r2 = 1
            if (r10 == 0) goto L_0x0038
            r4 = r2
            goto L_0x003a
        L_0x0038:
            r4 = 0
        L_0x003a:
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            com.google.android.gms.measurement.internal.zzfg r0 = r8.zzs()
            com.google.android.gms.measurement.internal.zzfm r0 = r0.zzn
            r4 = r10
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x0052
            java.lang.String r11 = "true"
        L_0x0052:
            r0.zza(r11)
            r6 = r10
            r3 = r1
            goto L_0x006b
        L_0x0058:
            if (r11 != 0) goto L_0x0069
            com.google.android.gms.measurement.internal.zzfg r10 = r8.zzs()
            com.google.android.gms.measurement.internal.zzfm r10 = r10.zzn
            java.lang.String r0 = "unset"
            r10.zza(r0)
            r6 = r11
            r3 = r1
            goto L_0x006b
        L_0x0069:
            r3 = r10
            r6 = r11
        L_0x006b:
            com.google.android.gms.measurement.internal.zzfy r10 = r8.zzy
            boolean r10 = r10.zzab()
            if (r10 != 0) goto L_0x0081
            com.google.android.gms.measurement.internal.zzeu r9 = r8.zzr()
            com.google.android.gms.measurement.internal.zzew r9 = r9.zzx()
            java.lang.String r10 = "User property not set since app measurement is disabled"
            r9.zza(r10)
            return
        L_0x0081:
            com.google.android.gms.measurement.internal.zzfy r10 = r8.zzy
            boolean r10 = r10.zzag()
            if (r10 != 0) goto L_0x008a
            return
        L_0x008a:
            com.google.android.gms.measurement.internal.zzkq r10 = new com.google.android.gms.measurement.internal.zzkq
            r2 = r10
            r4 = r12
            r7 = r9
            r2.<init>(r3, r4, r6, r7)
            com.google.android.gms.measurement.internal.zzir r9 = r8.zzh()
            r9.zza(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhc.zza(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    public final List<zzkq> zzb(boolean z) {
        zzb();
        zzw();
        zzr().zzx().zza("Getting user properties (FE)");
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzy.zzq().zza(atomicReference, 5000L, "get user properties", new zzhl(this, atomicReference, z));
            List<zzkq> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzr().zzf().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
    }

    public final String zzah() {
        zzb();
        return this.zzg.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(String str) {
        this.zzg.set(str);
    }

    public final void zzai() {
        zzd();
        zzb();
        zzw();
        if (this.zzy.zzag()) {
            if (zzt().zza(zzaq.zzbg)) {
                zzy zzt = zzt();
                zzt.zzu();
                Boolean zze = zzt.zze("google_analytics_deferred_deep_link_enabled");
                if (zze != null && zze.booleanValue()) {
                    zzr().zzw().zza("Deferred Deep Link feature enabled.");
                    zzq().zza(new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzhe
                        private final zzhc zza;

                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            this.zza = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            zzhc zzhcVar = this.zza;
                            zzhcVar.zzd();
                            if (zzhcVar.zzs().zzs.zza()) {
                                zzhcVar.zzr().zzw().zza("Deferred Deep Link already retrieved. Not fetching again.");
                                return;
                            }
                            long zza = zzhcVar.zzs().zzt.zza();
                            zzhcVar.zzs().zzt.zza(1 + zza);
                            if (zza >= 5) {
                                zzhcVar.zzr().zzi().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                                zzhcVar.zzs().zzs.zza(true);
                                return;
                            }
                            zzhcVar.zzy.zzah();
                        }
                    });
                }
            }
            zzh().zzae();
            this.zzc = false;
            String zzw = zzs().zzw();
            if (!TextUtils.isEmpty(zzw)) {
                zzl().zzaa();
                if (!zzw.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzw);
                    zza("auto", "_ou", bundle);
                }
            }
        }
    }

    public final void zza(zzhb zzhbVar) {
        zzhb zzhbVar2;
        zzd();
        zzb();
        zzw();
        if (!(zzhbVar == null || zzhbVar == (zzhbVar2 = this.zzd))) {
            Preconditions.checkState(zzhbVar2 == null, "EventInterceptor already set.");
        }
        this.zzd = zzhbVar;
    }

    public final void zza(zzha zzhaVar) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzhaVar);
        if (!this.zze.add(zzhaVar)) {
            zzr().zzi().zza("OnEventListener already registered");
        }
    }

    public final void zzb(zzha zzhaVar) {
        zzb();
        zzw();
        Preconditions.checkNotNull(zzhaVar);
        if (!this.zze.remove(zzhaVar)) {
            zzr().zzi().zza("OnEventListener had not been registered");
        }
    }

    public final void zza(Bundle bundle) {
        zza(bundle, zzm().currentTimeMillis());
    }

    public final void zza(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzb();
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzr().zzi().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        zzb(bundle2, j);
    }

    public final void zzb(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("app_id"));
        zza();
        zzb(new Bundle(bundle), zzm().currentTimeMillis());
    }

    private final void zzb(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        zzgx.zza(bundle, "app_id", String.class, null);
        zzgx.zza(bundle, "origin", String.class, null);
        zzgx.zza(bundle, "name", String.class, null);
        zzgx.zza(bundle, "value", Object.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgx.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle.getString("name");
        Object obj = bundle.get("value");
        if (zzp().zzc(string) != 0) {
            zzr().zzf().zza("Invalid conditional user property name", zzo().zzc(string));
        } else if (zzp().zzb(string, obj) != 0) {
            zzr().zzf().zza("Invalid conditional user property value", zzo().zzc(string), obj);
        } else {
            Object zzc = zzp().zzc(string, obj);
            if (zzc == null) {
                zzr().zzf().zza("Unable to normalize conditional user property value", zzo().zzc(string), obj);
                return;
            }
            zzgx.zza(bundle, zzc);
            long j2 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j3 > 15552000000L || j3 < 1) {
                    zzr().zzf().zza("Invalid conditional user property time to live", zzo().zzc(string), Long.valueOf(j3));
                } else {
                    zzq().zza(new zzhm(this, bundle));
                }
            } else {
                zzr().zzf().zza("Invalid conditional user property timeout", zzo().zzc(string), Long.valueOf(j2));
            }
        }
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        zzb();
        zzb((String) null, str, str2, bundle);
    }

    public final void zza(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zza();
        zzb(str, str2, str3, bundle);
    }

    private final void zzb(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzm().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        Bundle bundle2 = new Bundle();
        if (str != null) {
            bundle2.putString("app_id", str);
        }
        bundle2.putString("name", str2);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str3 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str3);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzq().zza(new zzhp(this, bundle2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(Bundle bundle) {
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        Preconditions.checkNotEmpty(bundle.getString("origin"));
        Preconditions.checkNotNull(bundle.get("value"));
        if (!this.zzy.zzab()) {
            zzr().zzx().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzkq zzkqVar = new zzkq(bundle.getString("name"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle.get("value"), bundle.getString("origin"));
        try {
            zzao zza = zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false);
            zzh().zza(new zzw(bundle.getString("app_id"), bundle.getString("origin"), zzkqVar, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zza, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), 0L, true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(Bundle bundle) {
        zzd();
        zzw();
        Preconditions.checkNotNull(bundle);
        Preconditions.checkNotEmpty(bundle.getString("name"));
        if (!this.zzy.zzab()) {
            zzr().zzx().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        try {
            zzh().zza(new zzw(bundle.getString("app_id"), bundle.getString("origin"), new zzkq(bundle.getString("name"), 0L, null, null), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), bundle.getBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), null, bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zzp().zza(bundle.getString("app_id"), bundle.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), bundle.getString("origin"), bundle.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, false)));
        } catch (IllegalArgumentException e) {
        }
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        zzb();
        return zzb((String) null, str, str2);
    }

    public final ArrayList<Bundle> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3);
    }

    private final ArrayList<Bundle> zzb(String str, String str2, String str3) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzy.zzq().zza(atomicReference, 5000L, "get conditional user properties", new zzhr(this, atomicReference, str, str2, str3));
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzkr.zzb((List<zzw>) list);
            }
            zzr().zzf().zza("Timed out waiting for get conditional user properties", str);
            return new ArrayList<>();
        }
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzb();
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> zza(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zza();
        return zzb(str, str2, str3, z);
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzq().zzg()) {
            zzr().zzf().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzx.zza()) {
            zzr().zzf().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzy.zzq().zza(atomicReference, 5000L, "get user properties", new zzhq(this, atomicReference, str, str2, str3, z));
            List<zzkq> list = (List) atomicReference.get();
            if (list == null) {
                zzr().zzf().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzkq zzkqVar : list) {
                arrayMap.put(zzkqVar.zza, zzkqVar.zza());
            }
            return arrayMap;
        }
    }

    public final String zzaj() {
        zzij zzab = this.zzy.zzv().zzab();
        if (zzab != null) {
            return zzab.zza;
        }
        return null;
    }

    public final String zzak() {
        zzij zzab = this.zzy.zzv().zzab();
        if (zzab != null) {
            return zzab.zzb;
        }
        return null;
    }

    public final String zzal() {
        if (this.zzy.zzo() != null) {
            return this.zzy.zzo();
        }
        try {
            return new StringResourceValueReader(zzn()).getString("google_app_id");
        } catch (IllegalStateException e) {
            this.zzy.zzr().zzf().zza("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgs
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhc zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzer zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzir zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzii zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeq zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzjv zzk() {
        return super.zzk();
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
