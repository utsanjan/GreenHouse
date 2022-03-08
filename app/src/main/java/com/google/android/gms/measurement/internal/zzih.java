package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzlm;
import com.google.android.gms.internal.measurement.zzlr;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzih extends zzkg {
    public zzih(zzkj zzkjVar) {
        super(zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkg
    protected final boolean zze() {
        return false;
    }

    public final byte[] zza(zzao zzaoVar, String str) {
        zzks zzksVar;
        Bundle bundle;
        zzcb.zzf.zza zzaVar;
        zzcb.zzg.zza zzaVar2;
        zzf zzfVar;
        byte[] bArr;
        Bundle bundle2;
        zzak zzakVar;
        long j;
        zzd();
        this.zzy.zzae();
        Preconditions.checkNotNull(zzaoVar);
        Preconditions.checkNotEmpty(str);
        if (!zzt().zze(str, zzaq.zzax)) {
            zzr().zzw().zza("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        } else if ("_iap".equals(zzaoVar.zza) || "_iapx".equals(zzaoVar.zza)) {
            zzcb.zzf.zza zzb = zzcb.zzf.zzb();
            zzi().zzf();
            try {
                zzf zzb2 = zzi().zzb(str);
                if (zzb2 == null) {
                    zzr().zzw().zza("Log and bundle not available. package_name", str);
                    return new byte[0];
                } else if (!zzb2.zzr()) {
                    zzr().zzw().zza("Log and bundle disabled. package_name", str);
                    return new byte[0];
                } else {
                    zzcb.zzg.zza zza = zzcb.zzg.zzbf().zza(1).zza(AbstractSpiCall.ANDROID_CLIENT_TYPE);
                    if (!TextUtils.isEmpty(zzb2.zzc())) {
                        zza.zzf(zzb2.zzc());
                    }
                    if (!TextUtils.isEmpty(zzb2.zzn())) {
                        zza.zze(zzb2.zzn());
                    }
                    if (!TextUtils.isEmpty(zzb2.zzl())) {
                        zza.zzg(zzb2.zzl());
                    }
                    if (zzb2.zzm() != -2147483648L) {
                        zza.zzh((int) zzb2.zzm());
                    }
                    zza.zzf(zzb2.zzo()).zzk(zzb2.zzq());
                    if (!zzlm.zzb() || !zzt().zze(zzb2.zzc(), zzaq.zzbn)) {
                        if (!TextUtils.isEmpty(zzb2.zze())) {
                            zza.zzk(zzb2.zze());
                        } else if (!TextUtils.isEmpty(zzb2.zzf())) {
                            zza.zzo(zzb2.zzf());
                        }
                    } else if (!TextUtils.isEmpty(zzb2.zze())) {
                        zza.zzk(zzb2.zze());
                    } else if (!TextUtils.isEmpty(zzb2.zzg())) {
                        zza.zzp(zzb2.zzg());
                    } else if (!TextUtils.isEmpty(zzb2.zzf())) {
                        zza.zzo(zzb2.zzf());
                    }
                    zza.zzh(zzb2.zzp());
                    if (this.zzy.zzab() && zzt().zzg(zza.zzj())) {
                        zza.zzj();
                        if (!TextUtils.isEmpty(null)) {
                            zza.zzn(null);
                        }
                    }
                    Pair<String, Boolean> zza2 = zzs().zza(zzb2.zzc());
                    if (zzb2.zzaf() && zza2 != null && !TextUtils.isEmpty((CharSequence) zza2.first)) {
                        zza.zzh(zza((String) zza2.first, Long.toString(zzaoVar.zzd)));
                        if (zza2.second != null) {
                            zza.zza(((Boolean) zza2.second).booleanValue());
                        }
                    }
                    zzl().zzaa();
                    zzcb.zzg.zza zzc = zza.zzc(Build.MODEL);
                    zzl().zzaa();
                    zzc.zzb(Build.VERSION.RELEASE).zzf((int) zzl().zzf()).zzd(zzl().zzg());
                    zza.zzi(zza(zzb2.zzd(), Long.toString(zzaoVar.zzd)));
                    if (!TextUtils.isEmpty(zzb2.zzi())) {
                        zza.zzl(zzb2.zzi());
                    }
                    String zzc2 = zzb2.zzc();
                    List<zzks> zza3 = zzi().zza(zzc2);
                    Iterator<zzks> it = zza3.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzksVar = null;
                            break;
                        }
                        zzksVar = it.next();
                        if ("_lte".equals(zzksVar.zzc)) {
                            break;
                        }
                    }
                    if (zzksVar == null || zzksVar.zze == null) {
                        zzks zzksVar2 = new zzks(zzc2, "auto", "_lte", zzm().currentTimeMillis(), 0L);
                        zza3.add(zzksVar2);
                        zzi().zza(zzksVar2);
                    }
                    zzkn zzg = zzg();
                    zzg.zzr().zzx().zza("Checking account type status for ad personalization signals");
                    if (zzg.zzl().zzj()) {
                        String zzc3 = zzb2.zzc();
                        if (zzb2.zzaf() && zzg.zzj().zze(zzc3)) {
                            zzg.zzr().zzw().zza("Turning off ad personalization due to account type");
                            Iterator<zzks> it2 = zza3.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                } else if ("_npa".equals(it2.next().zzc)) {
                                    it2.remove();
                                    break;
                                }
                            }
                            zza3.add(new zzks(zzc3, "auto", "_npa", zzg.zzm().currentTimeMillis(), 1L));
                        }
                    }
                    zzcb.zzk[] zzkVarArr = new zzcb.zzk[zza3.size()];
                    for (int i = 0; i < zza3.size(); i++) {
                        zzcb.zzk.zza zza4 = zzcb.zzk.zzj().zza(zza3.get(i).zzc).zza(zza3.get(i).zzd);
                        zzg().zza(zza4, zza3.get(i).zze);
                        zzkVarArr[i] = (zzcb.zzk) ((zzfo) zza4.zzv());
                    }
                    zza.zzb(Arrays.asList(zzkVarArr));
                    if (!zzlr.zzb() || !zzt().zza(zzaq.zzcn) || !zzt().zza(zzaq.zzco)) {
                        bundle = zzaoVar.zzb.zzb();
                    } else {
                        zzey zza5 = zzey.zza(zzaoVar);
                        zzp().zza(zza5.zzb, zzi().zzi(str));
                        zzp().zza(zza5, zzt().zza(str));
                        bundle = zza5.zzb;
                    }
                    bundle.putLong("_c", 1L);
                    zzr().zzw().zza("Marking in-app purchase as real-time");
                    bundle.putLong("_r", 1L);
                    bundle.putString("_o", zzaoVar.zzc);
                    if (zzp().zzf(zza.zzj())) {
                        zzp().zza(bundle, "_dbg", (Object) 1L);
                        zzp().zza(bundle, "_r", (Object) 1L);
                    }
                    zzak zza6 = zzi().zza(str, zzaoVar.zza);
                    if (zza6 == null) {
                        zzfVar = zzb2;
                        zzaVar2 = zza;
                        zzaVar = zzb;
                        bundle2 = bundle;
                        bArr = null;
                        zzakVar = new zzak(str, zzaoVar.zza, 0L, 0L, zzaoVar.zzd, 0L, null, null, null, null);
                        j = 0;
                    } else {
                        zzfVar = zzb2;
                        zzaVar2 = zza;
                        zzaVar = zzb;
                        bundle2 = bundle;
                        bArr = null;
                        j = zza6.zzf;
                        zzakVar = zza6.zza(zzaoVar.zzd);
                    }
                    zzi().zza(zzakVar);
                    zzal zzalVar = new zzal(this.zzy, zzaoVar.zzc, str, zzaoVar.zza, zzaoVar.zzd, j, bundle2);
                    zzcb.zzc.zza zzb3 = zzcb.zzc.zzj().zza(zzalVar.zzc).zza(zzalVar.zzb).zzb(zzalVar.zzd);
                    Iterator<String> it3 = zzalVar.zze.iterator();
                    while (it3.hasNext()) {
                        String next = it3.next();
                        zzcb.zze.zza zza7 = zzcb.zze.zzm().zza(next);
                        zzg().zza(zza7, zzalVar.zze.zza(next));
                        zzb3.zza(zza7);
                    }
                    zzaVar2.zza(zzb3).zza(zzcb.zzh.zza().zza(zzcb.zzd.zza().zza(zzakVar.zzc).zza(zzaoVar.zza)));
                    zzaVar2.zzc(e_().zza(zzfVar.zzc(), Collections.emptyList(), zzaVar2.zzd(), Long.valueOf(zzb3.zzf()), Long.valueOf(zzb3.zzf())));
                    if (zzb3.zze()) {
                        zzaVar2.zzb(zzb3.zzf()).zzc(zzb3.zzf());
                    }
                    long zzk = zzfVar.zzk();
                    if (zzk != 0) {
                        zzaVar2.zze(zzk);
                    }
                    long zzj = zzfVar.zzj();
                    if (zzj != 0) {
                        zzaVar2.zzd(zzj);
                    } else if (zzk != 0) {
                        zzaVar2.zzd(zzk);
                    }
                    zzfVar.zzv();
                    zzaVar2.zzg((int) zzfVar.zzs()).zzg(zzt().zzf()).zza(zzm().currentTimeMillis()).zzb(Boolean.TRUE.booleanValue());
                    zzaVar.zza(zzaVar2);
                    zzfVar.zza(zzaVar2.zzf());
                    zzfVar.zzb(zzaVar2.zzg());
                    zzi().zza(zzfVar);
                    zzi().b_();
                    try {
                        return zzg().zzc(((zzcb.zzf) ((zzfo) zzaVar.zzv())).zzbi());
                    } catch (IOException e) {
                        zzr().zzf().zza("Data loss. Failed to bundle and serialize. appId", zzeu.zza(str), e);
                        return bArr;
                    }
                }
            } catch (SecurityException e2) {
                zzr().zzw().zza("app instance id encryption failed", e2.getMessage());
                return new byte[0];
            } catch (SecurityException e3) {
                zzr().zzw().zza("Resettable device id encryption failed", e3.getMessage());
                return new byte[0];
            } finally {
                zzi().zzh();
            }
        } else {
            zzr().zzw().zza("Generating a payload for this event is not available. package_name, event_name", str, zzaoVar.zza);
            return null;
        }
    }

    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}
