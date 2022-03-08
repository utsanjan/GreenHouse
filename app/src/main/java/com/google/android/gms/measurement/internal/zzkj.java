package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzby;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzkz;
import com.google.android.gms.internal.measurement.zzlm;
import com.google.android.gms.internal.measurement.zzlr;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public class zzkj implements zzgu {
    private static volatile zzkj zza;
    private zzfs zzb;
    private zzfb zzc;
    private zzad zzd;
    private zzfe zze;
    private zzkf zzf;
    private zzo zzg;
    private final zzkn zzh;
    private zzih zzi;
    private final zzfy zzj;
    private boolean zzk;
    private boolean zzl;
    private long zzm;
    private List<Runnable> zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private FileLock zzt;
    private FileChannel zzu;
    private List<Long> zzv;
    private List<Long> zzw;
    private long zzx;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public class zza implements zzaf {
        zzcb.zzg zza;
        List<Long> zzb;
        List<zzcb.zzc> zzc;
        private long zzd;

        private zza() {
        }

        @Override // com.google.android.gms.measurement.internal.zzaf
        public final void zza(zzcb.zzg zzgVar) {
            Preconditions.checkNotNull(zzgVar);
            this.zza = zzgVar;
        }

        @Override // com.google.android.gms.measurement.internal.zzaf
        public final boolean zza(long j, zzcb.zzc zzcVar) {
            Preconditions.checkNotNull(zzcVar);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (this.zzc.size() > 0 && zza(this.zzc.get(0)) != zza(zzcVar)) {
                return false;
            }
            long zzbm = this.zzd + zzcVar.zzbm();
            if (zzbm >= Math.max(0, zzaq.zzh.zza(null).intValue())) {
                return false;
            }
            this.zzd = zzbm;
            this.zzc.add(zzcVar);
            this.zzb.add(Long.valueOf(j));
            return this.zzc.size() < Math.max(1, zzaq.zzi.zza(null).intValue());
        }

        private static long zza(zzcb.zzc zzcVar) {
            return ((zzcVar.zze() / 1000) / 60) / 60;
        }

        /* synthetic */ zza(zzkj zzkjVar, zzki zzkiVar) {
            this();
        }
    }

    public static zzkj zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzkj.class) {
                if (zza == null) {
                    zza = new zzkj(new zzko(context));
                }
            }
        }
        return zza;
    }

    private zzkj(zzko zzkoVar) {
        this(zzkoVar, null);
    }

    private zzkj(zzko zzkoVar, zzfy zzfyVar) {
        this.zzk = false;
        Preconditions.checkNotNull(zzkoVar);
        this.zzj = zzfy.zza(zzkoVar.zza, null, null);
        this.zzx = -1L;
        zzkn zzknVar = new zzkn(this);
        zzknVar.zzal();
        this.zzh = zzknVar;
        zzfb zzfbVar = new zzfb(this);
        zzfbVar.zzal();
        this.zzc = zzfbVar;
        zzfs zzfsVar = new zzfs(this);
        zzfsVar.zzal();
        this.zzb = zzfsVar;
        this.zzj.zzq().zza(new zzki(this, zzkoVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzko zzkoVar) {
        this.zzj.zzq().zzd();
        zzad zzadVar = new zzad(this);
        zzadVar.zzal();
        this.zzd = zzadVar;
        this.zzj.zzb().zza(this.zzb);
        zzo zzoVar = new zzo(this);
        zzoVar.zzal();
        this.zzg = zzoVar;
        zzih zzihVar = new zzih(this);
        zzihVar.zzal();
        this.zzi = zzihVar;
        zzkf zzkfVar = new zzkf(this);
        zzkfVar.zzal();
        this.zzf = zzkfVar;
        this.zze = new zzfe(this);
        if (this.zzo != this.zzp) {
            this.zzj.zzr().zzf().zza("Not all upload components initialized", Integer.valueOf(this.zzo), Integer.valueOf(this.zzp));
        }
        this.zzk = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zza() {
        this.zzj.zzq().zzd();
        zze().zzv();
        if (this.zzj.zzc().zzc.zza() == 0) {
            this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
        }
        zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final zzx zzu() {
        return this.zzj.zzu();
    }

    public final zzy zzb() {
        return this.zzj.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final zzeu zzr() {
        return this.zzj.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final zzfv zzq() {
        return this.zzj.zzq();
    }

    public final zzfs zzc() {
        zzb(this.zzb);
        return this.zzb;
    }

    public final zzfb zzd() {
        zzb(this.zzc);
        return this.zzc;
    }

    public final zzad zze() {
        zzb(this.zzd);
        return this.zzd;
    }

    private final zzfe zzt() {
        zzfe zzfeVar = this.zze;
        if (zzfeVar != null) {
            return zzfeVar;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzkf zzv() {
        zzb(this.zzf);
        return this.zzf;
    }

    public final zzo zzf() {
        zzb(this.zzg);
        return this.zzg;
    }

    public final zzih zzg() {
        zzb(this.zzi);
        return this.zzi;
    }

    public final zzkn zzh() {
        zzb(this.zzh);
        return this.zzh;
    }

    public final zzes zzi() {
        return this.zzj.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final Context zzn() {
        return this.zzj.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public final Clock zzm() {
        return this.zzj.zzm();
    }

    public final zzkr zzj() {
        return this.zzj.zzi();
    }

    private final void zzw() {
        this.zzj.zzq().zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk() {
        if (!this.zzk) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    private static void zzb(zzkg zzkgVar) {
        if (zzkgVar == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (!zzkgVar.zzaj()) {
            String valueOf = String.valueOf(zzkgVar.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    private final long zzx() {
        long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
        zzfg zzc = this.zzj.zzc();
        zzc.zzaa();
        zzc.zzd();
        long zza2 = zzc.zzg.zza();
        if (zza2 == 0) {
            zza2 = 1 + zzc.zzp().zzh().nextInt(86400000);
            zzc.zzg.zza(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzao zzaoVar, String str) {
        String str2;
        zzf zzb = zze().zzb(str);
        if (zzb == null || TextUtils.isEmpty(zzb.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping event", str);
            return;
        }
        Boolean zzb2 = zzb(zzb);
        if (zzb2 == null) {
            if (!"_ui".equals(zzaoVar.zza)) {
                this.zzj.zzr().zzi().zza("Could not find package. appId", zzeu.zza(str));
            }
        } else if (!zzb2.booleanValue()) {
            this.zzj.zzr().zzf().zza("App version does not match; dropping event. appId", zzeu.zza(str));
            return;
        }
        String zze = zzb.zze();
        String zzl = zzb.zzl();
        long zzm = zzb.zzm();
        String zzn = zzb.zzn();
        long zzo = zzb.zzo();
        long zzp = zzb.zzp();
        boolean zzr = zzb.zzr();
        String zzi = zzb.zzi();
        long zzae = zzb.zzae();
        boolean zzaf = zzb.zzaf();
        boolean zzag = zzb.zzag();
        String zzf = zzb.zzf();
        Boolean zzah = zzb.zzah();
        long zzq = zzb.zzq();
        List<String> zzai = zzb.zzai();
        if (!zzlm.zzb() || !this.zzj.zzb().zze(zzb.zzc(), zzaq.zzbn)) {
            str2 = null;
        } else {
            str2 = zzb.zzg();
        }
        zzb(zzaoVar, new zzn(str, zze, zzl, zzm, zzn, zzo, zzp, (String) null, zzr, false, zzi, zzae, 0L, 0, zzaf, zzag, false, zzf, zzah, zzq, zzai, str2));
    }

    private final void zzb(zzao zzaoVar, zzn zznVar) {
        if (zzlr.zzb() && this.zzj.zzb().zza(zzaq.zzcn)) {
            zzey zza2 = zzey.zza(zzaoVar);
            this.zzj.zzi().zza(zza2.zzb, zze().zzi(zznVar.zza));
            this.zzj.zzi().zza(zza2, this.zzj.zzb().zza(zznVar.zza));
            zzaoVar = zza2.zza();
        }
        zza(zzaoVar, zznVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzao zzaoVar, zzn zznVar) {
        List<zzw> list;
        List<zzw> list2;
        List<zzw> list3;
        zzao zzaoVar2 = zzaoVar;
        Preconditions.checkNotNull(zznVar);
        Preconditions.checkNotEmpty(zznVar.zza);
        zzw();
        zzk();
        String str = zznVar.zza;
        long j = zzaoVar2.zzd;
        zzh();
        if (zzkn.zza(zzaoVar, zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            if (this.zzj.zzb().zze(str, zzaq.zzbb) && zznVar.zzu != null) {
                if (zznVar.zzu.contains(zzaoVar2.zza)) {
                    Bundle zzb = zzaoVar2.zzb.zzb();
                    zzb.putLong("ga_safelisted", 1L);
                    zzaoVar2 = new zzao(zzaoVar2.zza, new zzan(zzb), zzaoVar2.zzc, zzaoVar2.zzd);
                } else {
                    this.zzj.zzr().zzw().zza("Dropping non-safelisted event. appId, event name, origin", str, zzaoVar2.zza, zzaoVar2.zzc);
                    return;
                }
            }
            zze().zzf();
            try {
                zzad zze = zze();
                Preconditions.checkNotEmpty(str);
                zze.zzd();
                zze.zzak();
                if (j < 0) {
                    zze.zzr().zzi().zza("Invalid time querying timed out conditional properties", zzeu.zza(str), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zze.zza("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str, String.valueOf(j)});
                }
                for (zzw zzwVar : list) {
                    if (zzwVar != null) {
                        this.zzj.zzr().zzx().zza("User property timed out", zzwVar.zza, this.zzj.zzj().zzc(zzwVar.zzc.zza), zzwVar.zzc.zza());
                        if (zzwVar.zzg != null) {
                            zzc(new zzao(zzwVar.zzg, j), zznVar);
                        }
                        zze().zze(str, zzwVar.zzc.zza);
                    }
                }
                zzad zze2 = zze();
                Preconditions.checkNotEmpty(str);
                zze2.zzd();
                zze2.zzak();
                if (j < 0) {
                    zze2.zzr().zzi().zza("Invalid time querying expired conditional properties", zzeu.zza(str), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zze2.zza("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzw zzwVar2 : list2) {
                    if (zzwVar2 != null) {
                        this.zzj.zzr().zzx().zza("User property expired", zzwVar2.zza, this.zzj.zzj().zzc(zzwVar2.zzc.zza), zzwVar2.zzc.zza());
                        zze().zzb(str, zzwVar2.zzc.zza);
                        if (zzwVar2.zzk != null) {
                            arrayList.add(zzwVar2.zzk);
                        }
                        zze().zze(str, zzwVar2.zzc.zza);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzc(new zzao((zzao) obj, j), zznVar);
                }
                zzad zze3 = zze();
                String str2 = zzaoVar2.zza;
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotEmpty(str2);
                zze3.zzd();
                zze3.zzak();
                if (j < 0) {
                    zze3.zzr().zzi().zza("Invalid time querying triggered conditional properties", zzeu.zza(str), zze3.zzo().zza(str2), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zze3.zza("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str, str2, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzw zzwVar3 : list3) {
                    if (zzwVar3 != null) {
                        zzkq zzkqVar = zzwVar3.zzc;
                        zzks zzksVar = new zzks(zzwVar3.zza, zzwVar3.zzb, zzkqVar.zza, j, zzkqVar.zza());
                        if (zze().zza(zzksVar)) {
                            this.zzj.zzr().zzx().zza("User property triggered", zzwVar3.zza, this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                        } else {
                            this.zzj.zzr().zzf().zza("Too many active user properties, ignoring", zzeu.zza(zzwVar3.zza), this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                        }
                        if (zzwVar3.zzi != null) {
                            arrayList3.add(zzwVar3.zzi);
                        }
                        zzwVar3.zzc = new zzkq(zzksVar);
                        zzwVar3.zze = true;
                        zze().zza(zzwVar3);
                    }
                }
                zzc(zzaoVar2, zznVar);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj2 = arrayList4.get(i2);
                    i2++;
                    zzc(new zzao((zzao) obj2, j), zznVar);
                }
                zze().b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:86|(1:88)(1:89)|90|(2:92|(1:94)(3:95|103|(1:105)))(1:96)|97|286|98|102|103|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x02c4, code lost:
        r7.zzr().zzf().zza("Error pruning currencies. appId", com.google.android.gms.measurement.internal.zzeu.zza(r15), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x02c2, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02fc A[Catch: all -> 0x098b, TryCatch #1 {all -> 0x098b, blocks: (B:37:0x013f, B:39:0x0148, B:43:0x0159, B:47:0x0167, B:49:0x0171, B:54:0x017d, B:61:0x0190, B:64:0x019c, B:66:0x01b3, B:72:0x01cf, B:74:0x01d9, B:76:0x01e7, B:80:0x01f2, B:81:0x0210, B:82:0x021a, B:84:0x0220, B:86:0x022e, B:88:0x023e, B:89:0x0243, B:90:0x0248, B:92:0x0253, B:95:0x025e, B:97:0x028c, B:98:0x02a7, B:101:0x02c4, B:102:0x02d5, B:103:0x02f1, B:105:0x02fc, B:110:0x0338, B:114:0x034a, B:116:0x035f, B:118:0x0370, B:120:0x0381, B:122:0x03b4, B:124:0x03ba, B:125:0x03d3, B:129:0x03e4, B:131:0x03f8, B:133:0x03fe, B:134:0x0417, B:138:0x043b, B:142:0x0461, B:143:0x047a, B:146:0x0489, B:149:0x04af, B:150:0x04cc, B:152:0x04d7, B:154:0x04e3, B:156:0x04ea, B:157:0x04f5, B:159:0x0502, B:160:0x0519, B:162:0x0540, B:165:0x055b, B:168:0x05a0, B:169:0x05ba, B:170:0x05c8, B:172:0x0603, B:173:0x0608, B:175:0x0610, B:176:0x0615, B:178:0x061d, B:179:0x0622, B:181:0x062b, B:182:0x0631, B:184:0x063e, B:185:0x0643, B:187:0x0649, B:189:0x0659, B:191:0x0663, B:193:0x066b, B:194:0x0670, B:196:0x067a, B:198:0x0684, B:200:0x068c, B:201:0x0692, B:203:0x069c, B:205:0x06a4, B:206:0x06a9, B:208:0x06b1, B:209:0x06b6, B:211:0x06cc, B:213:0x06d6, B:214:0x06d9, B:216:0x06e8, B:218:0x06f2, B:220:0x06f6, B:222:0x0701, B:223:0x070d, B:225:0x0721, B:227:0x0726, B:229:0x0739, B:230:0x0753, B:232:0x075a, B:233:0x0771, B:234:0x0774, B:236:0x07be, B:237:0x07c3, B:239:0x07cb, B:241:0x07d4, B:242:0x07d9, B:244:0x07e5, B:246:0x084c, B:247:0x0851, B:248:0x085d, B:250:0x0867, B:251:0x086e, B:253:0x0878, B:254:0x087f, B:255:0x088a, B:257:0x0890, B:259:0x08c2, B:260:0x08d3, B:262:0x08db, B:263:0x08e1, B:265:0x08e7, B:269:0x08f6, B:271:0x091c, B:275:0x0932, B:277:0x0938, B:279:0x093c, B:280:0x0956), top: B:288:0x013f, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0338 A[Catch: all -> 0x098b, TRY_LEAVE, TryCatch #1 {all -> 0x098b, blocks: (B:37:0x013f, B:39:0x0148, B:43:0x0159, B:47:0x0167, B:49:0x0171, B:54:0x017d, B:61:0x0190, B:64:0x019c, B:66:0x01b3, B:72:0x01cf, B:74:0x01d9, B:76:0x01e7, B:80:0x01f2, B:81:0x0210, B:82:0x021a, B:84:0x0220, B:86:0x022e, B:88:0x023e, B:89:0x0243, B:90:0x0248, B:92:0x0253, B:95:0x025e, B:97:0x028c, B:98:0x02a7, B:101:0x02c4, B:102:0x02d5, B:103:0x02f1, B:105:0x02fc, B:110:0x0338, B:114:0x034a, B:116:0x035f, B:118:0x0370, B:120:0x0381, B:122:0x03b4, B:124:0x03ba, B:125:0x03d3, B:129:0x03e4, B:131:0x03f8, B:133:0x03fe, B:134:0x0417, B:138:0x043b, B:142:0x0461, B:143:0x047a, B:146:0x0489, B:149:0x04af, B:150:0x04cc, B:152:0x04d7, B:154:0x04e3, B:156:0x04ea, B:157:0x04f5, B:159:0x0502, B:160:0x0519, B:162:0x0540, B:165:0x055b, B:168:0x05a0, B:169:0x05ba, B:170:0x05c8, B:172:0x0603, B:173:0x0608, B:175:0x0610, B:176:0x0615, B:178:0x061d, B:179:0x0622, B:181:0x062b, B:182:0x0631, B:184:0x063e, B:185:0x0643, B:187:0x0649, B:189:0x0659, B:191:0x0663, B:193:0x066b, B:194:0x0670, B:196:0x067a, B:198:0x0684, B:200:0x068c, B:201:0x0692, B:203:0x069c, B:205:0x06a4, B:206:0x06a9, B:208:0x06b1, B:209:0x06b6, B:211:0x06cc, B:213:0x06d6, B:214:0x06d9, B:216:0x06e8, B:218:0x06f2, B:220:0x06f6, B:222:0x0701, B:223:0x070d, B:225:0x0721, B:227:0x0726, B:229:0x0739, B:230:0x0753, B:232:0x075a, B:233:0x0771, B:234:0x0774, B:236:0x07be, B:237:0x07c3, B:239:0x07cb, B:241:0x07d4, B:242:0x07d9, B:244:0x07e5, B:246:0x084c, B:247:0x0851, B:248:0x085d, B:250:0x0867, B:251:0x086e, B:253:0x0878, B:254:0x087f, B:255:0x088a, B:257:0x0890, B:259:0x08c2, B:260:0x08d3, B:262:0x08db, B:263:0x08e1, B:265:0x08e7, B:269:0x08f6, B:271:0x091c, B:275:0x0932, B:277:0x0938, B:279:0x093c, B:280:0x0956), top: B:288:0x013f, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x035f A[Catch: all -> 0x098b, TryCatch #1 {all -> 0x098b, blocks: (B:37:0x013f, B:39:0x0148, B:43:0x0159, B:47:0x0167, B:49:0x0171, B:54:0x017d, B:61:0x0190, B:64:0x019c, B:66:0x01b3, B:72:0x01cf, B:74:0x01d9, B:76:0x01e7, B:80:0x01f2, B:81:0x0210, B:82:0x021a, B:84:0x0220, B:86:0x022e, B:88:0x023e, B:89:0x0243, B:90:0x0248, B:92:0x0253, B:95:0x025e, B:97:0x028c, B:98:0x02a7, B:101:0x02c4, B:102:0x02d5, B:103:0x02f1, B:105:0x02fc, B:110:0x0338, B:114:0x034a, B:116:0x035f, B:118:0x0370, B:120:0x0381, B:122:0x03b4, B:124:0x03ba, B:125:0x03d3, B:129:0x03e4, B:131:0x03f8, B:133:0x03fe, B:134:0x0417, B:138:0x043b, B:142:0x0461, B:143:0x047a, B:146:0x0489, B:149:0x04af, B:150:0x04cc, B:152:0x04d7, B:154:0x04e3, B:156:0x04ea, B:157:0x04f5, B:159:0x0502, B:160:0x0519, B:162:0x0540, B:165:0x055b, B:168:0x05a0, B:169:0x05ba, B:170:0x05c8, B:172:0x0603, B:173:0x0608, B:175:0x0610, B:176:0x0615, B:178:0x061d, B:179:0x0622, B:181:0x062b, B:182:0x0631, B:184:0x063e, B:185:0x0643, B:187:0x0649, B:189:0x0659, B:191:0x0663, B:193:0x066b, B:194:0x0670, B:196:0x067a, B:198:0x0684, B:200:0x068c, B:201:0x0692, B:203:0x069c, B:205:0x06a4, B:206:0x06a9, B:208:0x06b1, B:209:0x06b6, B:211:0x06cc, B:213:0x06d6, B:214:0x06d9, B:216:0x06e8, B:218:0x06f2, B:220:0x06f6, B:222:0x0701, B:223:0x070d, B:225:0x0721, B:227:0x0726, B:229:0x0739, B:230:0x0753, B:232:0x075a, B:233:0x0771, B:234:0x0774, B:236:0x07be, B:237:0x07c3, B:239:0x07cb, B:241:0x07d4, B:242:0x07d9, B:244:0x07e5, B:246:0x084c, B:247:0x0851, B:248:0x085d, B:250:0x0867, B:251:0x086e, B:253:0x0878, B:254:0x087f, B:255:0x088a, B:257:0x0890, B:259:0x08c2, B:260:0x08d3, B:262:0x08db, B:263:0x08e1, B:265:0x08e7, B:269:0x08f6, B:271:0x091c, B:275:0x0932, B:277:0x0938, B:279:0x093c, B:280:0x0956), top: B:288:0x013f, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03b4 A[Catch: all -> 0x098b, TryCatch #1 {all -> 0x098b, blocks: (B:37:0x013f, B:39:0x0148, B:43:0x0159, B:47:0x0167, B:49:0x0171, B:54:0x017d, B:61:0x0190, B:64:0x019c, B:66:0x01b3, B:72:0x01cf, B:74:0x01d9, B:76:0x01e7, B:80:0x01f2, B:81:0x0210, B:82:0x021a, B:84:0x0220, B:86:0x022e, B:88:0x023e, B:89:0x0243, B:90:0x0248, B:92:0x0253, B:95:0x025e, B:97:0x028c, B:98:0x02a7, B:101:0x02c4, B:102:0x02d5, B:103:0x02f1, B:105:0x02fc, B:110:0x0338, B:114:0x034a, B:116:0x035f, B:118:0x0370, B:120:0x0381, B:122:0x03b4, B:124:0x03ba, B:125:0x03d3, B:129:0x03e4, B:131:0x03f8, B:133:0x03fe, B:134:0x0417, B:138:0x043b, B:142:0x0461, B:143:0x047a, B:146:0x0489, B:149:0x04af, B:150:0x04cc, B:152:0x04d7, B:154:0x04e3, B:156:0x04ea, B:157:0x04f5, B:159:0x0502, B:160:0x0519, B:162:0x0540, B:165:0x055b, B:168:0x05a0, B:169:0x05ba, B:170:0x05c8, B:172:0x0603, B:173:0x0608, B:175:0x0610, B:176:0x0615, B:178:0x061d, B:179:0x0622, B:181:0x062b, B:182:0x0631, B:184:0x063e, B:185:0x0643, B:187:0x0649, B:189:0x0659, B:191:0x0663, B:193:0x066b, B:194:0x0670, B:196:0x067a, B:198:0x0684, B:200:0x068c, B:201:0x0692, B:203:0x069c, B:205:0x06a4, B:206:0x06a9, B:208:0x06b1, B:209:0x06b6, B:211:0x06cc, B:213:0x06d6, B:214:0x06d9, B:216:0x06e8, B:218:0x06f2, B:220:0x06f6, B:222:0x0701, B:223:0x070d, B:225:0x0721, B:227:0x0726, B:229:0x0739, B:230:0x0753, B:232:0x075a, B:233:0x0771, B:234:0x0774, B:236:0x07be, B:237:0x07c3, B:239:0x07cb, B:241:0x07d4, B:242:0x07d9, B:244:0x07e5, B:246:0x084c, B:247:0x0851, B:248:0x085d, B:250:0x0867, B:251:0x086e, B:253:0x0878, B:254:0x087f, B:255:0x088a, B:257:0x0890, B:259:0x08c2, B:260:0x08d3, B:262:0x08db, B:263:0x08e1, B:265:0x08e7, B:269:0x08f6, B:271:0x091c, B:275:0x0932, B:277:0x0938, B:279:0x093c, B:280:0x0956), top: B:288:0x013f, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0938 A[Catch: all -> 0x098b, TryCatch #1 {all -> 0x098b, blocks: (B:37:0x013f, B:39:0x0148, B:43:0x0159, B:47:0x0167, B:49:0x0171, B:54:0x017d, B:61:0x0190, B:64:0x019c, B:66:0x01b3, B:72:0x01cf, B:74:0x01d9, B:76:0x01e7, B:80:0x01f2, B:81:0x0210, B:82:0x021a, B:84:0x0220, B:86:0x022e, B:88:0x023e, B:89:0x0243, B:90:0x0248, B:92:0x0253, B:95:0x025e, B:97:0x028c, B:98:0x02a7, B:101:0x02c4, B:102:0x02d5, B:103:0x02f1, B:105:0x02fc, B:110:0x0338, B:114:0x034a, B:116:0x035f, B:118:0x0370, B:120:0x0381, B:122:0x03b4, B:124:0x03ba, B:125:0x03d3, B:129:0x03e4, B:131:0x03f8, B:133:0x03fe, B:134:0x0417, B:138:0x043b, B:142:0x0461, B:143:0x047a, B:146:0x0489, B:149:0x04af, B:150:0x04cc, B:152:0x04d7, B:154:0x04e3, B:156:0x04ea, B:157:0x04f5, B:159:0x0502, B:160:0x0519, B:162:0x0540, B:165:0x055b, B:168:0x05a0, B:169:0x05ba, B:170:0x05c8, B:172:0x0603, B:173:0x0608, B:175:0x0610, B:176:0x0615, B:178:0x061d, B:179:0x0622, B:181:0x062b, B:182:0x0631, B:184:0x063e, B:185:0x0643, B:187:0x0649, B:189:0x0659, B:191:0x0663, B:193:0x066b, B:194:0x0670, B:196:0x067a, B:198:0x0684, B:200:0x068c, B:201:0x0692, B:203:0x069c, B:205:0x06a4, B:206:0x06a9, B:208:0x06b1, B:209:0x06b6, B:211:0x06cc, B:213:0x06d6, B:214:0x06d9, B:216:0x06e8, B:218:0x06f2, B:220:0x06f6, B:222:0x0701, B:223:0x070d, B:225:0x0721, B:227:0x0726, B:229:0x0739, B:230:0x0753, B:232:0x075a, B:233:0x0771, B:234:0x0774, B:236:0x07be, B:237:0x07c3, B:239:0x07cb, B:241:0x07d4, B:242:0x07d9, B:244:0x07e5, B:246:0x084c, B:247:0x0851, B:248:0x085d, B:250:0x0867, B:251:0x086e, B:253:0x0878, B:254:0x087f, B:255:0x088a, B:257:0x0890, B:259:0x08c2, B:260:0x08d3, B:262:0x08db, B:263:0x08e1, B:265:0x08e7, B:269:0x08f6, B:271:0x091c, B:275:0x0932, B:277:0x0938, B:279:0x093c, B:280:0x0956), top: B:288:0x013f, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0190 A[Catch: all -> 0x098b, TRY_LEAVE, TryCatch #1 {all -> 0x098b, blocks: (B:37:0x013f, B:39:0x0148, B:43:0x0159, B:47:0x0167, B:49:0x0171, B:54:0x017d, B:61:0x0190, B:64:0x019c, B:66:0x01b3, B:72:0x01cf, B:74:0x01d9, B:76:0x01e7, B:80:0x01f2, B:81:0x0210, B:82:0x021a, B:84:0x0220, B:86:0x022e, B:88:0x023e, B:89:0x0243, B:90:0x0248, B:92:0x0253, B:95:0x025e, B:97:0x028c, B:98:0x02a7, B:101:0x02c4, B:102:0x02d5, B:103:0x02f1, B:105:0x02fc, B:110:0x0338, B:114:0x034a, B:116:0x035f, B:118:0x0370, B:120:0x0381, B:122:0x03b4, B:124:0x03ba, B:125:0x03d3, B:129:0x03e4, B:131:0x03f8, B:133:0x03fe, B:134:0x0417, B:138:0x043b, B:142:0x0461, B:143:0x047a, B:146:0x0489, B:149:0x04af, B:150:0x04cc, B:152:0x04d7, B:154:0x04e3, B:156:0x04ea, B:157:0x04f5, B:159:0x0502, B:160:0x0519, B:162:0x0540, B:165:0x055b, B:168:0x05a0, B:169:0x05ba, B:170:0x05c8, B:172:0x0603, B:173:0x0608, B:175:0x0610, B:176:0x0615, B:178:0x061d, B:179:0x0622, B:181:0x062b, B:182:0x0631, B:184:0x063e, B:185:0x0643, B:187:0x0649, B:189:0x0659, B:191:0x0663, B:193:0x066b, B:194:0x0670, B:196:0x067a, B:198:0x0684, B:200:0x068c, B:201:0x0692, B:203:0x069c, B:205:0x06a4, B:206:0x06a9, B:208:0x06b1, B:209:0x06b6, B:211:0x06cc, B:213:0x06d6, B:214:0x06d9, B:216:0x06e8, B:218:0x06f2, B:220:0x06f6, B:222:0x0701, B:223:0x070d, B:225:0x0721, B:227:0x0726, B:229:0x0739, B:230:0x0753, B:232:0x075a, B:233:0x0771, B:234:0x0774, B:236:0x07be, B:237:0x07c3, B:239:0x07cb, B:241:0x07d4, B:242:0x07d9, B:244:0x07e5, B:246:0x084c, B:247:0x0851, B:248:0x085d, B:250:0x0867, B:251:0x086e, B:253:0x0878, B:254:0x087f, B:255:0x088a, B:257:0x0890, B:259:0x08c2, B:260:0x08d3, B:262:0x08db, B:263:0x08e1, B:265:0x08e7, B:269:0x08f6, B:271:0x091c, B:275:0x0932, B:277:0x0938, B:279:0x093c, B:280:0x0956), top: B:288:0x013f, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzc(com.google.android.gms.measurement.internal.zzao r27, com.google.android.gms.measurement.internal.zzn r28) {
        /*
            Method dump skipped, instructions count: 2455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zzc(com.google.android.gms.measurement.internal.zzao, com.google.android.gms.measurement.internal.zzn):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzl() {
        zzf zzb;
        String str;
        String str2;
        zzw();
        zzk();
        this.zzs = true;
        try {
            this.zzj.zzu();
            Boolean zzag = this.zzj.zzw().zzag();
            if (zzag == null) {
                this.zzj.zzr().zzi().zza("Upload data called on the client side before use of service was decided");
            } else if (zzag.booleanValue()) {
                this.zzj.zzr().zzf().zza("Upload called in the client side when service should be used");
            } else if (this.zzm > 0) {
                zzz();
            } else {
                zzw();
                if (this.zzv != null) {
                    this.zzj.zzr().zzx().zza("Uploading requested multiple times");
                } else if (!zzd().zzf()) {
                    this.zzj.zzr().zzx().zza("Network not connected, ignoring upload request");
                    zzz();
                } else {
                    long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
                    int zzb2 = this.zzj.zzb().zzb(null, zzaq.zzap);
                    long zzv = currentTimeMillis - zzy.zzv();
                    for (int i = 0; i < zzb2 && zza((String) null, zzv); i++) {
                    }
                    long zza2 = this.zzj.zzc().zzc.zza();
                    if (zza2 != 0) {
                        this.zzj.zzr().zzw().zza("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - zza2)));
                    }
                    String d_ = zze().d_();
                    if (!TextUtils.isEmpty(d_)) {
                        if (this.zzx == -1) {
                            this.zzx = zze().zzaa();
                        }
                        List<Pair<zzcb.zzg, Long>> zza3 = zze().zza(d_, this.zzj.zzb().zzb(d_, zzaq.zzf), Math.max(0, this.zzj.zzb().zzb(d_, zzaq.zzg)));
                        if (!zza3.isEmpty()) {
                            Iterator<Pair<zzcb.zzg, Long>> it = zza3.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    str = null;
                                    break;
                                }
                                zzcb.zzg zzgVar = (zzcb.zzg) it.next().first;
                                if (!TextUtils.isEmpty(zzgVar.zzad())) {
                                    str = zzgVar.zzad();
                                    break;
                                }
                            }
                            if (str != null) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= zza3.size()) {
                                        break;
                                    }
                                    zzcb.zzg zzgVar2 = (zzcb.zzg) zza3.get(i2).first;
                                    if (!(TextUtils.isEmpty(zzgVar2.zzad()) || zzgVar2.zzad().equals(str))) {
                                        zza3 = zza3.subList(0, i2);
                                        break;
                                    }
                                    i2++;
                                }
                            }
                            zzcb.zzf.zza zzb3 = zzcb.zzf.zzb();
                            int size = zza3.size();
                            ArrayList arrayList = new ArrayList(zza3.size());
                            boolean zzg = this.zzj.zzb().zzg(d_);
                            for (int i3 = 0; i3 < size; i3++) {
                                zzcb.zzg.zza zzbl = ((zzcb.zzg) zza3.get(i3).first).zzbl();
                                arrayList.add((Long) zza3.get(i3).second);
                                zzcb.zzg.zza zza4 = zzbl.zzg(this.zzj.zzb().zzf()).zza(currentTimeMillis);
                                this.zzj.zzu();
                                zza4.zzb(false);
                                if (!zzg) {
                                    zzbl.zzn();
                                }
                                if (this.zzj.zzb().zze(d_, zzaq.zzay)) {
                                    zzbl.zzl(zzh().zza(((zzcb.zzg) ((zzfo) zzbl.zzv())).zzbi()));
                                }
                                zzb3.zza(zzbl);
                            }
                            if (this.zzj.zzr().zza(2)) {
                                str2 = zzh().zza((zzcb.zzf) ((zzfo) zzb3.zzv()));
                            } else {
                                str2 = null;
                            }
                            zzh();
                            byte[] zzbi = ((zzcb.zzf) ((zzfo) zzb3.zzv())).zzbi();
                            String zza5 = zzaq.zzp.zza(null);
                            try {
                                URL url = new URL(zza5);
                                Preconditions.checkArgument(!arrayList.isEmpty());
                                if (this.zzv != null) {
                                    this.zzj.zzr().zzf().zza("Set uploading progress before finishing the previous upload");
                                } else {
                                    this.zzv = new ArrayList(arrayList);
                                }
                                this.zzj.zzc().zzd.zza(currentTimeMillis);
                                String str3 = "?";
                                if (size > 0) {
                                    str3 = zzb3.zza(0).zzx();
                                }
                                this.zzj.zzr().zzx().zza("Uploading data. app, uncompressed size, data", str3, Integer.valueOf(zzbi.length), str2);
                                this.zzr = true;
                                zzfb zzd = zzd();
                                zzkl zzklVar = new zzkl(this, d_);
                                zzd.zzd();
                                zzd.zzak();
                                Preconditions.checkNotNull(url);
                                Preconditions.checkNotNull(zzbi);
                                Preconditions.checkNotNull(zzklVar);
                                zzd.zzq().zzb(new zzff(zzd, d_, url, zzbi, null, zzklVar));
                            } catch (MalformedURLException e) {
                                this.zzj.zzr().zzf().zza("Failed to parse upload URL. Not uploading. appId", zzeu.zza(d_), zza5);
                            }
                        }
                    } else {
                        this.zzx = -1L;
                        String zza6 = zze().zza(currentTimeMillis - zzy.zzv());
                        if (!TextUtils.isEmpty(zza6) && (zzb = zze().zzb(zza6)) != null) {
                            zza(zzb);
                        }
                    }
                }
            }
        } finally {
            this.zzs = false;
            zzaa();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x028a A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x05f7 A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:232:0x06c5  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x06dc A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x089c  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x08a8 A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x08b9 A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:298:0x08d3 A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0ff6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0273 A[Catch: all -> 0x1013, TRY_ENTER, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x027b A[Catch: all -> 0x1013, TryCatch #4 {all -> 0x1013, blocks: (B:3:0x000d, B:22:0x008d, B:38:0x00e9, B:45:0x0132, B:59:0x01d8, B:67:0x0213, B:74:0x0233, B:79:0x024c, B:92:0x0273, B:93:0x0276, B:95:0x027b, B:101:0x028a, B:102:0x02ba, B:105:0x02d3, B:108:0x02fc, B:110:0x0334, B:116:0x034a, B:118:0x0355, B:120:0x037b, B:123:0x0394, B:130:0x03b6, B:133:0x03c0, B:136:0x03ca, B:152:0x03f7, B:155:0x0403, B:157:0x0412, B:158:0x0434, B:160:0x0444, B:162:0x0466, B:166:0x0476, B:169:0x04b1, B:171:0x04e3, B:173:0x0519, B:175:0x051f, B:178:0x052c, B:180:0x0562, B:181:0x0582, B:183:0x0588, B:185:0x0597, B:186:0x05a2, B:189:0x05ad, B:192:0x05b5, B:194:0x05bc, B:195:0x05da, B:197:0x05f7, B:198:0x0605, B:201:0x060f, B:204:0x0621, B:207:0x0632, B:210:0x0638, B:212:0x0644, B:214:0x0651, B:218:0x0676, B:222:0x068a, B:224:0x0690, B:227:0x069d, B:230:0x06a7, B:233:0x06c7, B:235:0x06dc, B:237:0x06e9, B:240:0x06fc, B:242:0x070e, B:244:0x071e, B:250:0x0751, B:252:0x0761, B:255:0x0776, B:257:0x0788, B:259:0x0798, B:265:0x07b8, B:267:0x07d0, B:269:0x07dc, B:272:0x07ef, B:274:0x0803, B:276:0x0853, B:278:0x085a, B:280:0x0860, B:282:0x086b, B:284:0x0872, B:286:0x0878, B:288:0x0883, B:289:0x0894, B:293:0x08a8, B:295:0x08b2, B:297:0x08b9, B:298:0x08d3, B:300:0x08e9, B:301:0x0903, B:303:0x090c, B:304:0x0926, B:309:0x0940, B:311:0x094e, B:313:0x0957, B:314:0x095f, B:316:0x0969, B:318:0x096f, B:321:0x097b, B:323:0x0985, B:324:0x098a, B:327:0x0991, B:328:0x099e, B:330:0x09a4, B:336:0x09bf, B:337:0x09ca, B:342:0x09d7, B:343:0x09dc, B:345:0x09e3, B:346:0x0a00, B:348:0x0a1f, B:350:0x0a2d, B:352:0x0a33, B:354:0x0a3d, B:355:0x0a70, B:357:0x0a77, B:359:0x0a85, B:360:0x0a8b, B:363:0x0a91, B:364:0x0a94, B:366:0x0aa8, B:367:0x0aab, B:375:0x0b22, B:377:0x0b3f, B:378:0x0b50, B:380:0x0b54, B:382:0x0b60, B:383:0x0b69, B:385:0x0b6d, B:387:0x0b75, B:388:0x0b84, B:389:0x0b8f, B:394:0x0bd2, B:395:0x0bda, B:397:0x0be0, B:399:0x0bf2, B:401:0x0bf6, B:403:0x0c04, B:405:0x0c08, B:407:0x0c12, B:409:0x0c16, B:417:0x0c32, B:420:0x0c49, B:47:0x013b), top: B:551:0x000d, inners: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zza(java.lang.String r44, long r45) {
        /*
            Method dump skipped, instructions count: 4128
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zza(java.lang.String, long):boolean");
    }

    private static void zza(zzcb.zzg.zza zzaVar) {
        zzaVar.zzb(Long.MAX_VALUE).zzc(Long.MIN_VALUE);
        for (int i = 0; i < zzaVar.zzb(); i++) {
            zzcb.zzc zzb = zzaVar.zzb(i);
            if (zzb.zze() < zzaVar.zzf()) {
                zzaVar.zzb(zzb.zze());
            }
            if (zzb.zze() > zzaVar.zzg()) {
                zzaVar.zzc(zzb.zze());
            }
        }
    }

    private final void zza(zzcb.zzg.zza zzaVar, long j, boolean z) {
        String str;
        zzks zzksVar;
        String str2;
        if (z) {
            str = "_se";
        } else {
            str = "_lte";
        }
        zzks zzc = zze().zzc(zzaVar.zzj(), str);
        if (zzc == null || zzc.zze == null) {
            zzksVar = new zzks(zzaVar.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(j));
        } else {
            zzksVar = new zzks(zzaVar.zzj(), "auto", str, this.zzj.zzm().currentTimeMillis(), Long.valueOf(((Long) zzc.zze).longValue() + j));
        }
        zzcb.zzk zzkVar = (zzcb.zzk) ((zzfo) zzcb.zzk.zzj().zza(str).zza(this.zzj.zzm().currentTimeMillis()).zzb(((Long) zzksVar.zze).longValue()).zzv());
        boolean z2 = false;
        int zza2 = zzkn.zza(zzaVar, str);
        if (zza2 >= 0) {
            zzaVar.zza(zza2, zzkVar);
            z2 = true;
        }
        if (!z2) {
            zzaVar.zza(zzkVar);
        }
        if (j > 0) {
            zze().zza(zzksVar);
            if (z) {
                str2 = "session-scoped";
            } else {
                str2 = "lifetime";
            }
            this.zzj.zzr().zzx().zza("Updated engagement user property. scope, value", str2, zzksVar.zze);
        }
    }

    private final boolean zza(zzcb.zzc.zza zzaVar, zzcb.zzc.zza zzaVar2) {
        Preconditions.checkArgument("_e".equals(zzaVar.zzd()));
        zzh();
        zzcb.zze zza2 = zzkn.zza((zzcb.zzc) ((zzfo) zzaVar.zzv()), "_sc");
        String str = null;
        String zzd = zza2 == null ? null : zza2.zzd();
        zzh();
        zzcb.zze zza3 = zzkn.zza((zzcb.zzc) ((zzfo) zzaVar2.zzv()), "_pc");
        if (zza3 != null) {
            str = zza3.zzd();
        }
        if (str == null || !str.equals(zzd)) {
            return false;
        }
        zzb(zzaVar, zzaVar2);
        return true;
    }

    private final void zzb(zzcb.zzc.zza zzaVar, zzcb.zzc.zza zzaVar2) {
        Preconditions.checkArgument("_e".equals(zzaVar.zzd()));
        zzh();
        zzcb.zze zza2 = zzkn.zza((zzcb.zzc) ((zzfo) zzaVar.zzv()), "_et");
        if (zza2.zze() && zza2.zzf() > 0) {
            long zzf = zza2.zzf();
            zzh();
            zzcb.zze zza3 = zzkn.zza((zzcb.zzc) ((zzfo) zzaVar2.zzv()), "_et");
            if (zza3 != null && zza3.zzf() > 0) {
                zzf += zza3.zzf();
            }
            zzh().zza(zzaVar2, "_et", Long.valueOf(zzf));
            zzh().zza(zzaVar, "_fr", (Object) 1L);
        }
    }

    private static void zza(zzcb.zzc.zza zzaVar, String str) {
        List<zzcb.zze> zza2 = zzaVar.zza();
        for (int i = 0; i < zza2.size(); i++) {
            if (str.equals(zza2.get(i).zzb())) {
                zzaVar.zzb(i);
                return;
            }
        }
    }

    private static void zza(zzcb.zzc.zza zzaVar, int i, String str) {
        List<zzcb.zze> zza2 = zzaVar.zza();
        for (int i2 = 0; i2 < zza2.size(); i2++) {
            if ("_err".equals(zza2.get(i2).zzb())) {
                return;
            }
        }
        zzaVar.zza((zzcb.zze) ((zzfo) zzcb.zze.zzm().zza("_err").zza(Long.valueOf(i).longValue()).zzv())).zza((zzcb.zze) ((zzfo) zzcb.zze.zzm().zza("_ev").zzb(str).zzv()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0162, code lost:
        r8.zzj.zzc().zze.zza(r8.zzj.zzm().currentTimeMillis());
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(int r9, java.lang.Throwable r10, byte[] r11, java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zza(int, java.lang.Throwable, byte[], java.lang.String):void");
    }

    private final boolean zzy() {
        zzw();
        zzk();
        return zze().zzy() || !TextUtils.isEmpty(zze().d_());
    }

    private final void zza(zzf zzfVar) {
        ArrayMap arrayMap;
        zzw();
        if (!zzlm.zzb() || !this.zzj.zzb().zze(zzfVar.zzc(), zzaq.zzbn)) {
            if (TextUtils.isEmpty(zzfVar.zze()) && TextUtils.isEmpty(zzfVar.zzf())) {
                zza(zzfVar.zzc(), CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, null, null, null);
                return;
            }
        } else if (TextUtils.isEmpty(zzfVar.zze()) && TextUtils.isEmpty(zzfVar.zzg()) && TextUtils.isEmpty(zzfVar.zzf())) {
            zza(zzfVar.zzc(), CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE, null, null, null);
            return;
        }
        String zza2 = this.zzj.zzb().zza(zzfVar);
        try {
            URL url = new URL(zza2);
            this.zzj.zzr().zzx().zza("Fetching remote configuration", zzfVar.zzc());
            zzby.zzb zza3 = zzc().zza(zzfVar.zzc());
            String zzb = zzc().zzb(zzfVar.zzc());
            if (zza3 == null || TextUtils.isEmpty(zzb)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put("If-Modified-Since", zzb);
                arrayMap = arrayMap2;
            }
            this.zzq = true;
            zzfb zzd = zzd();
            String zzc = zzfVar.zzc();
            zzkk zzkkVar = new zzkk(this);
            zzd.zzd();
            zzd.zzak();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzkkVar);
            zzd.zzq().zzb(new zzff(zzd, zzc, url, null, arrayMap, zzkkVar));
        } catch (MalformedURLException e) {
            this.zzj.zzr().zzf().zza("Failed to parse config URL. Not fetching. appId", zzeu.zza(zzfVar.zzc()), zza2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b6, code lost:
        r6.zzj.zzc().zze.zza(r6.zzj.zzm().currentTimeMillis());
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0142 A[Catch: all -> 0x0198, TryCatch #2 {all -> 0x01a1, blocks: (B:4:0x000c, B:5:0x000e, B:43:0x00fc, B:50:0x011d, B:64:0x018a, B:6:0x002a, B:15:0x0047, B:20:0x0063, B:27:0x00b6, B:28:0x00cb, B:31:0x00d3, B:34:0x00df, B:36:0x00e5, B:41:0x00f2, B:46:0x0109, B:48:0x0113, B:53:0x012b, B:55:0x0142, B:56:0x0152, B:57:0x016b, B:59:0x0175, B:61:0x017b, B:62:0x017f, B:63:0x0183), top: B:70:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0152 A[Catch: all -> 0x0198, TryCatch #2 {all -> 0x01a1, blocks: (B:4:0x000c, B:5:0x000e, B:43:0x00fc, B:50:0x011d, B:64:0x018a, B:6:0x002a, B:15:0x0047, B:20:0x0063, B:27:0x00b6, B:28:0x00cb, B:31:0x00d3, B:34:0x00df, B:36:0x00e5, B:41:0x00f2, B:46:0x0109, B:48:0x0113, B:53:0x012b, B:55:0x0142, B:56:0x0152, B:57:0x016b, B:59:0x0175, B:61:0x017b, B:62:0x017f, B:63:0x0183), top: B:70:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    private final void zzz() {
        long j;
        long j2;
        zzw();
        zzk();
        if (this.zzm > 0) {
            long abs = 3600000 - Math.abs(this.zzj.zzm().elapsedRealtime() - this.zzm);
            if (abs > 0) {
                this.zzj.zzr().zzx().zza("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(abs));
                zzt().zzb();
                zzv().zzf();
                return;
            }
            this.zzm = 0L;
        }
        if (!this.zzj.zzag() || !zzy()) {
            this.zzj.zzr().zzx().zza("Nothing to upload or uploading impossible");
            zzt().zzb();
            zzv().zzf();
            return;
        }
        long currentTimeMillis = this.zzj.zzm().currentTimeMillis();
        long max = Math.max(0L, zzaq.zzz.zza(null).longValue());
        boolean z = zze().zzz() || zze().zzk();
        if (z) {
            String zzw = this.zzj.zzb().zzw();
            if (TextUtils.isEmpty(zzw) || ".none.".equals(zzw)) {
                j = Math.max(0L, zzaq.zzt.zza(null).longValue());
            } else {
                j = Math.max(0L, zzaq.zzu.zza(null).longValue());
            }
        } else {
            j = Math.max(0L, zzaq.zzs.zza(null).longValue());
        }
        long zza2 = this.zzj.zzc().zzc.zza();
        long zza3 = this.zzj.zzc().zzd.zza();
        long max2 = Math.max(zze().zzw(), zze().zzx());
        if (max2 == 0) {
            j2 = 0;
        } else {
            long abs2 = currentTimeMillis - Math.abs(max2 - currentTimeMillis);
            long abs3 = currentTimeMillis - Math.abs(zza2 - currentTimeMillis);
            long abs4 = currentTimeMillis - Math.abs(zza3 - currentTimeMillis);
            long max3 = Math.max(abs3, abs4);
            j2 = abs2 + max;
            if (z && max3 > 0) {
                j2 = Math.min(abs2, max3) + j;
            }
            if (!zzh().zza(max3, j)) {
                j2 = max3 + j;
            }
            if (abs4 != 0 && abs4 >= abs2) {
                int i = 0;
                while (true) {
                    if (i >= Math.min(20, Math.max(0, zzaq.zzab.zza(null).intValue()))) {
                        j2 = 0;
                        break;
                    }
                    j2 += Math.max(0L, zzaq.zzaa.zza(null).longValue()) * (1 << i);
                    if (j2 > abs4) {
                        break;
                    }
                    i++;
                }
            }
        }
        if (j2 == 0) {
            this.zzj.zzr().zzx().zza("Next upload time is 0");
            zzt().zzb();
            zzv().zzf();
        } else if (!zzd().zzf()) {
            this.zzj.zzr().zzx().zza("No network");
            zzt().zza();
            zzv().zzf();
        } else {
            long zza4 = this.zzj.zzc().zze.zza();
            long max4 = Math.max(0L, zzaq.zzq.zza(null).longValue());
            if (!zzh().zza(zza4, max4)) {
                j2 = Math.max(j2, zza4 + max4);
            }
            zzt().zzb();
            long currentTimeMillis2 = j2 - this.zzj.zzm().currentTimeMillis();
            if (currentTimeMillis2 <= 0) {
                currentTimeMillis2 = Math.max(0L, zzaq.zzv.zza(null).longValue());
                this.zzj.zzc().zzc.zza(this.zzj.zzm().currentTimeMillis());
            }
            this.zzj.zzr().zzx().zza("Upload scheduled in approximately ms", Long.valueOf(currentTimeMillis2));
            zzv().zza(currentTimeMillis2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(Runnable runnable) {
        zzw();
        if (this.zzn == null) {
            this.zzn = new ArrayList();
        }
        this.zzn.add(runnable);
    }

    private final void zzaa() {
        zzw();
        if (this.zzq || this.zzr || this.zzs) {
            this.zzj.zzr().zzx().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzq), Boolean.valueOf(this.zzr), Boolean.valueOf(this.zzs));
            return;
        }
        this.zzj.zzr().zzx().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzn;
        if (list != null) {
            for (Runnable runnable : list) {
                runnable.run();
            }
            this.zzn.clear();
        }
    }

    private final Boolean zzb(zzf zzfVar) {
        try {
            if (zzfVar.zzm() != -2147483648L) {
                if (zzfVar.zzm() == Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzfVar.zzc(), 0).versionCode) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(this.zzj.zzn()).getPackageInfo(zzfVar.zzc(), 0).versionName;
                if (zzfVar.zzl() != null && zzfVar.zzl().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo() {
        zzw();
        zzk();
        if (!this.zzl) {
            this.zzl = true;
            if (zzab()) {
                int zza2 = zza(this.zzu);
                int zzaf = this.zzj.zzy().zzaf();
                zzw();
                if (zza2 > zzaf) {
                    this.zzj.zzr().zzf().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                } else if (zza2 >= zzaf) {
                } else {
                    if (zza(zzaf, this.zzu)) {
                        this.zzj.zzr().zzx().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                    } else {
                        this.zzj.zzr().zzf().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzaf));
                    }
                }
            }
        }
    }

    private final boolean zzab() {
        FileLock fileLock;
        zzw();
        if (!this.zzj.zzb().zza(zzaq.zzbl) || (fileLock = this.zzt) == null || !fileLock.isValid()) {
            try {
                FileChannel channel = new RandomAccessFile(new File(this.zzj.zzn().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
                this.zzu = channel;
                FileLock tryLock = channel.tryLock();
                this.zzt = tryLock;
                if (tryLock != null) {
                    this.zzj.zzr().zzx().zza("Storage concurrent access okay");
                    return true;
                }
                this.zzj.zzr().zzf().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e) {
                this.zzj.zzr().zzf().zza("Failed to acquire storage lock", e);
                return false;
            } catch (IOException e2) {
                this.zzj.zzr().zzf().zza("Failed to access storage lock file", e2);
                return false;
            } catch (OverlappingFileLockException e3) {
                this.zzj.zzr().zzi().zza("Storage lock already acquired", e3);
                return false;
            }
        } else {
            this.zzj.zzr().zzx().zza("Storage concurrent access okay");
            return true;
        }
    }

    private final int zza(FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0L);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    this.zzj.zzr().zzi().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            this.zzj.zzr().zzf().zza("Failed to read from channel", e);
            return 0;
        }
    }

    private final boolean zza(int i, FileChannel fileChannel) {
        zzw();
        if (fileChannel == null || !fileChannel.isOpen()) {
            this.zzj.zzr().zzf().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0L);
            if (this.zzj.zzb().zza(zzaq.zzby) && Build.VERSION.SDK_INT <= 19) {
                fileChannel.position(0L);
            }
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                this.zzj.zzr().zzf().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            this.zzj.zzr().zzf().zza("Failed to write to channel", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzn zznVar) {
        if (this.zzv != null) {
            ArrayList arrayList = new ArrayList();
            this.zzw = arrayList;
            arrayList.addAll(this.zzv);
        }
        zzad zze = zze();
        String str = zznVar.zza;
        Preconditions.checkNotEmpty(str);
        zze.zzd();
        zze.zzak();
        try {
            SQLiteDatabase c_ = zze.c_();
            String[] strArr = {str};
            int delete = c_.delete("apps", "app_id=?", strArr) + 0 + c_.delete("events", "app_id=?", strArr) + c_.delete("user_attributes", "app_id=?", strArr) + c_.delete("conditional_properties", "app_id=?", strArr) + c_.delete("raw_events", "app_id=?", strArr) + c_.delete("raw_events_metadata", "app_id=?", strArr) + c_.delete("queue", "app_id=?", strArr) + c_.delete("audience_filter_values", "app_id=?", strArr) + c_.delete("main_event_params", "app_id=?", strArr) + c_.delete("default_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zze.zzr().zzx().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zze.zzr().zzf().zza("Error resetting analytics data. appId, error", zzeu.zza(str), e);
        }
        if (zznVar.zzh) {
            zzb(zznVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzkq zzkqVar, zzn zznVar) {
        int i;
        zzw();
        zzk();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            int zzc = this.zzj.zzi().zzc(zzkqVar.zza);
            if (zzc != 0) {
                this.zzj.zzi();
                this.zzj.zzi().zza(zznVar.zza, zzc, "_ev", zzkr.zza(zzkqVar.zza, 24, true), zzkqVar.zza != null ? zzkqVar.zza.length() : 0);
                return;
            }
            int zzb = this.zzj.zzi().zzb(zzkqVar.zza, zzkqVar.zza());
            if (zzb != 0) {
                this.zzj.zzi();
                String zza2 = zzkr.zza(zzkqVar.zza, 24, true);
                Object zza3 = zzkqVar.zza();
                if (zza3 == null || (!(zza3 instanceof String) && !(zza3 instanceof CharSequence))) {
                    i = 0;
                } else {
                    i = String.valueOf(zza3).length();
                }
                this.zzj.zzi().zza(zznVar.zza, zzb, "_ev", zza2, i);
                return;
            }
            Object zzc2 = this.zzj.zzi().zzc(zzkqVar.zza, zzkqVar.zza());
            if (zzc2 != null) {
                if ("_sid".equals(zzkqVar.zza)) {
                    long j = zzkqVar.zzb;
                    String str = zzkqVar.zze;
                    long j2 = 0;
                    zzks zzc3 = zze().zzc(zznVar.zza, "_sno");
                    if (zzc3 == null || !(zzc3.zze instanceof Long)) {
                        if (zzc3 != null) {
                            this.zzj.zzr().zzi().zza("Retrieved last session number from database does not contain a valid (long) value", zzc3.zze);
                        }
                        zzak zza4 = zze().zza(zznVar.zza, "_s");
                        if (zza4 != null) {
                            j2 = zza4.zzc;
                            this.zzj.zzr().zzx().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                        }
                    } else {
                        j2 = ((Long) zzc3.zze).longValue();
                    }
                    zza(new zzkq("_sno", j, Long.valueOf(j2 + 1), str), zznVar);
                }
                zzks zzksVar = new zzks(zznVar.zza, zzkqVar.zze, zzkqVar.zza, zzkqVar.zzb, zzc2);
                this.zzj.zzr().zzx().zza("Setting user property", this.zzj.zzj().zzc(zzksVar.zzc), zzc2);
                zze().zzf();
                try {
                    zzc(zznVar);
                    boolean zza5 = zze().zza(zzksVar);
                    zze().b_();
                    if (!zza5) {
                        this.zzj.zzr().zzf().zza("Too many unique user properties are set. Ignoring user property", this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                        this.zzj.zzi().zza(zznVar.zza, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zze().zzh();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzkq zzkqVar, zzn zznVar) {
        zzw();
        zzk();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
            } else if (!"_npa".equals(zzkqVar.zza) || zznVar.zzs == null) {
                this.zzj.zzr().zzw().zza("Removing user property", this.zzj.zzj().zzc(zzkqVar.zza));
                zze().zzf();
                try {
                    zzc(zznVar);
                    zze().zzb(zznVar.zza, zzkqVar.zza);
                    zze().b_();
                    this.zzj.zzr().zzw().zza("User property removed", this.zzj.zzj().zzc(zzkqVar.zza));
                } finally {
                    zze().zzh();
                }
            } else {
                this.zzj.zzr().zzw().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzkq("_npa", this.zzj.zzm().currentTimeMillis(), Long.valueOf(zznVar.zzs.booleanValue() ? 1L : 0L), "auto"), zznVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzkg zzkgVar) {
        this.zzo++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzp() {
        this.zzp++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzfy zzs() {
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:151:0x04b0 A[Catch: all -> 0x04df, TryCatch #3 {all -> 0x04df, blocks: (B:24:0x00a3, B:26:0x00b1, B:30:0x00c0, B:32:0x00c4, B:36:0x00d5, B:38:0x00ef, B:40:0x00f9, B:43:0x0103, B:44:0x0115, B:46:0x0122, B:48:0x013a, B:49:0x0162, B:51:0x01ac, B:53:0x01bf, B:56:0x01d4, B:58:0x01df, B:63:0x01ee, B:65:0x01f7, B:67:0x01fd, B:71:0x020c, B:73:0x020f, B:75:0x0233, B:77:0x023a, B:80:0x024b, B:83:0x025b, B:86:0x026f, B:88:0x0294, B:89:0x02a2, B:91:0x02d7, B:92:0x02dc, B:94:0x02e0, B:95:0x02e5, B:97:0x0309, B:98:0x0322, B:100:0x0336, B:102:0x034e, B:104:0x0357, B:106:0x0362, B:110:0x0376, B:112:0x037f, B:115:0x0387, B:119:0x0392, B:122:0x03a8, B:124:0x03bc, B:126:0x03d4, B:128:0x03da, B:129:0x03df, B:131:0x03e5, B:134:0x03f0, B:135:0x03f3, B:138:0x040b, B:140:0x0443, B:141:0x0448, B:143:0x044c, B:144:0x0451, B:145:0x0467, B:147:0x0479, B:149:0x0494, B:150:0x049b, B:151:0x04b0, B:153:0x04b5, B:154:0x04d0), top: B:166:0x00a3, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0122 A[Catch: all -> 0x04df, TryCatch #3 {all -> 0x04df, blocks: (B:24:0x00a3, B:26:0x00b1, B:30:0x00c0, B:32:0x00c4, B:36:0x00d5, B:38:0x00ef, B:40:0x00f9, B:43:0x0103, B:44:0x0115, B:46:0x0122, B:48:0x013a, B:49:0x0162, B:51:0x01ac, B:53:0x01bf, B:56:0x01d4, B:58:0x01df, B:63:0x01ee, B:65:0x01f7, B:67:0x01fd, B:71:0x020c, B:73:0x020f, B:75:0x0233, B:77:0x023a, B:80:0x024b, B:83:0x025b, B:86:0x026f, B:88:0x0294, B:89:0x02a2, B:91:0x02d7, B:92:0x02dc, B:94:0x02e0, B:95:0x02e5, B:97:0x0309, B:98:0x0322, B:100:0x0336, B:102:0x034e, B:104:0x0357, B:106:0x0362, B:110:0x0376, B:112:0x037f, B:115:0x0387, B:119:0x0392, B:122:0x03a8, B:124:0x03bc, B:126:0x03d4, B:128:0x03da, B:129:0x03df, B:131:0x03e5, B:134:0x03f0, B:135:0x03f3, B:138:0x040b, B:140:0x0443, B:141:0x0448, B:143:0x044c, B:144:0x0451, B:145:0x0467, B:147:0x0479, B:149:0x0494, B:150:0x049b, B:151:0x04b0, B:153:0x04b5, B:154:0x04d0), top: B:166:0x00a3, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01d4 A[Catch: all -> 0x04df, TryCatch #3 {all -> 0x04df, blocks: (B:24:0x00a3, B:26:0x00b1, B:30:0x00c0, B:32:0x00c4, B:36:0x00d5, B:38:0x00ef, B:40:0x00f9, B:43:0x0103, B:44:0x0115, B:46:0x0122, B:48:0x013a, B:49:0x0162, B:51:0x01ac, B:53:0x01bf, B:56:0x01d4, B:58:0x01df, B:63:0x01ee, B:65:0x01f7, B:67:0x01fd, B:71:0x020c, B:73:0x020f, B:75:0x0233, B:77:0x023a, B:80:0x024b, B:83:0x025b, B:86:0x026f, B:88:0x0294, B:89:0x02a2, B:91:0x02d7, B:92:0x02dc, B:94:0x02e0, B:95:0x02e5, B:97:0x0309, B:98:0x0322, B:100:0x0336, B:102:0x034e, B:104:0x0357, B:106:0x0362, B:110:0x0376, B:112:0x037f, B:115:0x0387, B:119:0x0392, B:122:0x03a8, B:124:0x03bc, B:126:0x03d4, B:128:0x03da, B:129:0x03df, B:131:0x03e5, B:134:0x03f0, B:135:0x03f3, B:138:0x040b, B:140:0x0443, B:141:0x0448, B:143:0x044c, B:144:0x0451, B:145:0x0467, B:147:0x0479, B:149:0x0494, B:150:0x049b, B:151:0x04b0, B:153:0x04b5, B:154:0x04d0), top: B:166:0x00a3, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020f A[Catch: all -> 0x04df, TryCatch #3 {all -> 0x04df, blocks: (B:24:0x00a3, B:26:0x00b1, B:30:0x00c0, B:32:0x00c4, B:36:0x00d5, B:38:0x00ef, B:40:0x00f9, B:43:0x0103, B:44:0x0115, B:46:0x0122, B:48:0x013a, B:49:0x0162, B:51:0x01ac, B:53:0x01bf, B:56:0x01d4, B:58:0x01df, B:63:0x01ee, B:65:0x01f7, B:67:0x01fd, B:71:0x020c, B:73:0x020f, B:75:0x0233, B:77:0x023a, B:80:0x024b, B:83:0x025b, B:86:0x026f, B:88:0x0294, B:89:0x02a2, B:91:0x02d7, B:92:0x02dc, B:94:0x02e0, B:95:0x02e5, B:97:0x0309, B:98:0x0322, B:100:0x0336, B:102:0x034e, B:104:0x0357, B:106:0x0362, B:110:0x0376, B:112:0x037f, B:115:0x0387, B:119:0x0392, B:122:0x03a8, B:124:0x03bc, B:126:0x03d4, B:128:0x03da, B:129:0x03df, B:131:0x03e5, B:134:0x03f0, B:135:0x03f3, B:138:0x040b, B:140:0x0443, B:141:0x0448, B:143:0x044c, B:144:0x0451, B:145:0x0467, B:147:0x0479, B:149:0x0494, B:150:0x049b, B:151:0x04b0, B:153:0x04b5, B:154:0x04d0), top: B:166:0x00a3, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x023a A[Catch: all -> 0x04df, TryCatch #3 {all -> 0x04df, blocks: (B:24:0x00a3, B:26:0x00b1, B:30:0x00c0, B:32:0x00c4, B:36:0x00d5, B:38:0x00ef, B:40:0x00f9, B:43:0x0103, B:44:0x0115, B:46:0x0122, B:48:0x013a, B:49:0x0162, B:51:0x01ac, B:53:0x01bf, B:56:0x01d4, B:58:0x01df, B:63:0x01ee, B:65:0x01f7, B:67:0x01fd, B:71:0x020c, B:73:0x020f, B:75:0x0233, B:77:0x023a, B:80:0x024b, B:83:0x025b, B:86:0x026f, B:88:0x0294, B:89:0x02a2, B:91:0x02d7, B:92:0x02dc, B:94:0x02e0, B:95:0x02e5, B:97:0x0309, B:98:0x0322, B:100:0x0336, B:102:0x034e, B:104:0x0357, B:106:0x0362, B:110:0x0376, B:112:0x037f, B:115:0x0387, B:119:0x0392, B:122:0x03a8, B:124:0x03bc, B:126:0x03d4, B:128:0x03da, B:129:0x03df, B:131:0x03e5, B:134:0x03f0, B:135:0x03f3, B:138:0x040b, B:140:0x0443, B:141:0x0448, B:143:0x044c, B:144:0x0451, B:145:0x0467, B:147:0x0479, B:149:0x0494, B:150:0x049b, B:151:0x04b0, B:153:0x04b5, B:154:0x04d0), top: B:166:0x00a3, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x025b A[Catch: all -> 0x04df, TRY_LEAVE, TryCatch #3 {all -> 0x04df, blocks: (B:24:0x00a3, B:26:0x00b1, B:30:0x00c0, B:32:0x00c4, B:36:0x00d5, B:38:0x00ef, B:40:0x00f9, B:43:0x0103, B:44:0x0115, B:46:0x0122, B:48:0x013a, B:49:0x0162, B:51:0x01ac, B:53:0x01bf, B:56:0x01d4, B:58:0x01df, B:63:0x01ee, B:65:0x01f7, B:67:0x01fd, B:71:0x020c, B:73:0x020f, B:75:0x0233, B:77:0x023a, B:80:0x024b, B:83:0x025b, B:86:0x026f, B:88:0x0294, B:89:0x02a2, B:91:0x02d7, B:92:0x02dc, B:94:0x02e0, B:95:0x02e5, B:97:0x0309, B:98:0x0322, B:100:0x0336, B:102:0x034e, B:104:0x0357, B:106:0x0362, B:110:0x0376, B:112:0x037f, B:115:0x0387, B:119:0x0392, B:122:0x03a8, B:124:0x03bc, B:126:0x03d4, B:128:0x03da, B:129:0x03df, B:131:0x03e5, B:134:0x03f0, B:135:0x03f3, B:138:0x040b, B:140:0x0443, B:141:0x0448, B:143:0x044c, B:144:0x0451, B:145:0x0467, B:147:0x0479, B:149:0x0494, B:150:0x049b, B:151:0x04b0, B:153:0x04b5, B:154:0x04d0), top: B:166:0x00a3, inners: #0, #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzb(com.google.android.gms.measurement.internal.zzn r22) {
        /*
            Method dump skipped, instructions count: 1256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkj.zzb(com.google.android.gms.measurement.internal.zzn):void");
    }

    private final zzn zza(String str) {
        String str2;
        zzf zzb = zze().zzb(str);
        if (zzb == null || TextUtils.isEmpty(zzb.zzl())) {
            this.zzj.zzr().zzw().zza("No app data available; dropping", str);
            return null;
        }
        Boolean zzb2 = zzb(zzb);
        if (zzb2 == null || zzb2.booleanValue()) {
            String zze = zzb.zze();
            String zzl = zzb.zzl();
            long zzm = zzb.zzm();
            String zzn = zzb.zzn();
            long zzo = zzb.zzo();
            long zzp = zzb.zzp();
            boolean zzr = zzb.zzr();
            String zzi = zzb.zzi();
            long zzae = zzb.zzae();
            boolean zzaf = zzb.zzaf();
            boolean zzag = zzb.zzag();
            String zzf = zzb.zzf();
            Boolean zzah = zzb.zzah();
            long zzq = zzb.zzq();
            List<String> zzai = zzb.zzai();
            if (!zzlm.zzb() || !this.zzj.zzb().zze(str, zzaq.zzbn)) {
                str2 = null;
            } else {
                str2 = zzb.zzg();
            }
            return new zzn(str, zze, zzl, zzm, zzn, zzo, zzp, (String) null, zzr, false, zzi, zzae, 0L, 0, zzaf, zzag, false, zzf, zzah, zzq, zzai, str2);
        }
        this.zzj.zzr().zzf().zza("App version does not match; dropping. appId", zzeu.zza(str));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzw zzwVar) {
        zzn zza2 = zza(zzwVar.zza);
        if (zza2 != null) {
            zza(zzwVar, zza2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(zzw zzwVar, zzn zznVar) {
        Preconditions.checkNotNull(zzwVar);
        Preconditions.checkNotEmpty(zzwVar.zza);
        Preconditions.checkNotNull(zzwVar.zzb);
        Preconditions.checkNotNull(zzwVar.zzc);
        Preconditions.checkNotEmpty(zzwVar.zzc.zza);
        zzw();
        zzk();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            zzw zzwVar2 = new zzw(zzwVar);
            boolean z = false;
            zzwVar2.zze = false;
            zze().zzf();
            try {
                zzw zzd = zze().zzd(zzwVar2.zza, zzwVar2.zzc.zza);
                if (zzd != null && !zzd.zzb.equals(zzwVar2.zzb)) {
                    this.zzj.zzr().zzi().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzj.zzj().zzc(zzwVar2.zzc.zza), zzwVar2.zzb, zzd.zzb);
                }
                if (zzd != null && zzd.zze) {
                    zzwVar2.zzb = zzd.zzb;
                    zzwVar2.zzd = zzd.zzd;
                    zzwVar2.zzh = zzd.zzh;
                    zzwVar2.zzf = zzd.zzf;
                    zzwVar2.zzi = zzd.zzi;
                    zzwVar2.zze = zzd.zze;
                    zzwVar2.zzc = new zzkq(zzwVar2.zzc.zza, zzd.zzc.zzb, zzwVar2.zzc.zza(), zzd.zzc.zze);
                } else if (TextUtils.isEmpty(zzwVar2.zzf)) {
                    zzwVar2.zzc = new zzkq(zzwVar2.zzc.zza, zzwVar2.zzd, zzwVar2.zzc.zza(), zzwVar2.zzc.zze);
                    zzwVar2.zze = true;
                    z = true;
                }
                if (zzwVar2.zze) {
                    zzkq zzkqVar = zzwVar2.zzc;
                    zzks zzksVar = new zzks(zzwVar2.zza, zzwVar2.zzb, zzkqVar.zza, zzkqVar.zzb, zzkqVar.zza());
                    if (zze().zza(zzksVar)) {
                        this.zzj.zzr().zzw().zza("User property updated immediately", zzwVar2.zza, this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                    } else {
                        this.zzj.zzr().zzf().zza("(2)Too many active user properties, ignoring", zzeu.zza(zzwVar2.zza), this.zzj.zzj().zzc(zzksVar.zzc), zzksVar.zze);
                    }
                    if (z && zzwVar2.zzi != null) {
                        zzc(new zzao(zzwVar2.zzi, zzwVar2.zzd), zznVar);
                    }
                }
                if (zze().zza(zzwVar2)) {
                    this.zzj.zzr().zzw().zza("Conditional property added", zzwVar2.zza, this.zzj.zzj().zzc(zzwVar2.zzc.zza), zzwVar2.zzc.zza());
                } else {
                    this.zzj.zzr().zzf().zza("Too many conditional properties, ignoring", zzeu.zza(zzwVar2.zza), this.zzj.zzj().zzc(zzwVar2.zzc.zza), zzwVar2.zzc.zza());
                }
                zze().b_();
            } finally {
                zze().zzh();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzw zzwVar) {
        zzn zza2 = zza(zzwVar.zza);
        if (zza2 != null) {
            zzb(zzwVar, zza2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzw zzwVar, zzn zznVar) {
        Bundle bundle;
        Preconditions.checkNotNull(zzwVar);
        Preconditions.checkNotEmpty(zzwVar.zza);
        Preconditions.checkNotNull(zzwVar.zzc);
        Preconditions.checkNotEmpty(zzwVar.zzc.zza);
        zzw();
        zzk();
        if (zze(zznVar)) {
            if (!zznVar.zzh) {
                zzc(zznVar);
                return;
            }
            zze().zzf();
            try {
                zzc(zznVar);
                zzw zzd = zze().zzd(zzwVar.zza, zzwVar.zzc.zza);
                if (zzd != null) {
                    this.zzj.zzr().zzw().zza("Removing conditional user property", zzwVar.zza, this.zzj.zzj().zzc(zzwVar.zzc.zza));
                    zze().zze(zzwVar.zza, zzwVar.zzc.zza);
                    if (zzd.zze) {
                        zze().zzb(zzwVar.zza, zzwVar.zzc.zza);
                    }
                    if (zzwVar.zzk != null) {
                        if (zzwVar.zzk.zzb != null) {
                            bundle = zzwVar.zzk.zzb.zzb();
                        } else {
                            bundle = null;
                        }
                        zzc(this.zzj.zzi().zza(zzwVar.zza, zzwVar.zzk.zza, bundle, zzd.zzb, zzwVar.zzk.zzd, true, false), zznVar);
                    }
                } else {
                    this.zzj.zzr().zzi().zza("Conditional user property doesn't exist", zzeu.zza(zzwVar.zza), this.zzj.zzj().zzc(zzwVar.zzc.zza));
                }
                zze().b_();
            } finally {
                zze().zzh();
            }
        }
    }

    private final zzf zza(zzn zznVar, zzf zzfVar, String str) {
        boolean z;
        boolean z2 = true;
        if (zzfVar == null) {
            zzfVar = new zzf(this.zzj, zznVar.zza);
            zzfVar.zza(this.zzj.zzi().zzk());
            zzfVar.zze(str);
            z = true;
        } else if (!str.equals(zzfVar.zzh())) {
            zzfVar.zze(str);
            zzfVar.zza(this.zzj.zzi().zzk());
            z = true;
        } else {
            z = false;
        }
        if (!TextUtils.equals(zznVar.zzb, zzfVar.zze())) {
            zzfVar.zzb(zznVar.zzb);
            z = true;
        }
        if (!TextUtils.equals(zznVar.zzr, zzfVar.zzf())) {
            zzfVar.zzc(zznVar.zzr);
            z = true;
        }
        if (zzlm.zzb() && this.zzj.zzb().zze(zzfVar.zzc(), zzaq.zzbn) && !TextUtils.equals(zznVar.zzv, zzfVar.zzg())) {
            zzfVar.zzd(zznVar.zzv);
            z = true;
        }
        if (!TextUtils.isEmpty(zznVar.zzk) && !zznVar.zzk.equals(zzfVar.zzi())) {
            zzfVar.zzf(zznVar.zzk);
            z = true;
        }
        if (!(zznVar.zze == 0 || zznVar.zze == zzfVar.zzo())) {
            zzfVar.zzd(zznVar.zze);
            z = true;
        }
        if (!TextUtils.isEmpty(zznVar.zzc) && !zznVar.zzc.equals(zzfVar.zzl())) {
            zzfVar.zzg(zznVar.zzc);
            z = true;
        }
        if (zznVar.zzj != zzfVar.zzm()) {
            zzfVar.zzc(zznVar.zzj);
            z = true;
        }
        if (zznVar.zzd != null && !zznVar.zzd.equals(zzfVar.zzn())) {
            zzfVar.zzh(zznVar.zzd);
            z = true;
        }
        if (zznVar.zzf != zzfVar.zzp()) {
            zzfVar.zze(zznVar.zzf);
            z = true;
        }
        if (zznVar.zzh != zzfVar.zzr()) {
            zzfVar.zza(zznVar.zzh);
            z = true;
        }
        if (!TextUtils.isEmpty(zznVar.zzg) && !zznVar.zzg.equals(zzfVar.zzac())) {
            zzfVar.zzi(zznVar.zzg);
            z = true;
        }
        if (!this.zzj.zzb().zza(zzaq.zzcl) && zznVar.zzl != zzfVar.zzae()) {
            zzfVar.zzp(zznVar.zzl);
            z = true;
        }
        if (zznVar.zzo != zzfVar.zzaf()) {
            zzfVar.zzb(zznVar.zzo);
            z = true;
        }
        if (zznVar.zzp != zzfVar.zzag()) {
            zzfVar.zzc(zznVar.zzp);
            z = true;
        }
        if (zznVar.zzs != zzfVar.zzah()) {
            zzfVar.zza(zznVar.zzs);
            z = true;
        }
        if (zznVar.zzt == 0 || zznVar.zzt == zzfVar.zzq()) {
            z2 = z;
        } else {
            zzfVar.zzf(zznVar.zzt);
        }
        if (z2) {
            zze().zza(zzfVar);
        }
        return zzfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzf zzc(zzn zznVar) {
        zzw();
        zzk();
        Preconditions.checkNotNull(zznVar);
        Preconditions.checkNotEmpty(zznVar.zza);
        zzf zzb = zze().zzb(zznVar.zza);
        String zzb2 = this.zzj.zzc().zzb(zznVar.zza);
        if (!zzkz.zzb() || !this.zzj.zzb().zza(zzaq.zzbt)) {
            return zza(zznVar, zzb, zzb2);
        }
        if (zzb == null) {
            zzb = new zzf(this.zzj, zznVar.zza);
            zzb.zza(this.zzj.zzi().zzk());
            zzb.zze(zzb2);
        } else if (!zzb2.equals(zzb.zzh())) {
            zzb.zze(zzb2);
            zzb.zza(this.zzj.zzi().zzk());
        }
        zzb.zzb(zznVar.zzb);
        zzb.zzc(zznVar.zzr);
        if (zzlm.zzb() && this.zzj.zzb().zze(zzb.zzc(), zzaq.zzbn)) {
            zzb.zzd(zznVar.zzv);
        }
        if (!TextUtils.isEmpty(zznVar.zzk)) {
            zzb.zzf(zznVar.zzk);
        }
        if (zznVar.zze != 0) {
            zzb.zzd(zznVar.zze);
        }
        if (!TextUtils.isEmpty(zznVar.zzc)) {
            zzb.zzg(zznVar.zzc);
        }
        zzb.zzc(zznVar.zzj);
        if (zznVar.zzd != null) {
            zzb.zzh(zznVar.zzd);
        }
        zzb.zze(zznVar.zzf);
        zzb.zza(zznVar.zzh);
        if (!TextUtils.isEmpty(zznVar.zzg)) {
            zzb.zzi(zznVar.zzg);
        }
        if (!this.zzj.zzb().zza(zzaq.zzcl)) {
            zzb.zzp(zznVar.zzl);
        }
        zzb.zzb(zznVar.zzo);
        zzb.zzc(zznVar.zzp);
        zzb.zza(zznVar.zzs);
        zzb.zzf(zznVar.zzt);
        if (zzb.zza()) {
            zze().zza(zzb);
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzd(zzn zznVar) {
        try {
            return (String) this.zzj.zzq().zza(new zzkm(this, zznVar)).get(30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzj.zzr().zzf().zza("Failed to get app instance id. appId", zzeu.zza(zznVar.zza), e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzz();
    }

    private final boolean zze(zzn zznVar) {
        return (!zzlm.zzb() || !this.zzj.zzb().zze(zznVar.zza, zzaq.zzbn)) ? !TextUtils.isEmpty(zznVar.zzb) || !TextUtils.isEmpty(zznVar.zzr) : !TextUtils.isEmpty(zznVar.zzb) || !TextUtils.isEmpty(zznVar.zzv) || !TextUtils.isEmpty(zznVar.zzr);
    }
}
