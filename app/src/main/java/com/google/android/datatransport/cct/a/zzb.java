package com.google.android.datatransport.cct.a;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzb implements Configurator {
    public static final Configurator zza = new zzb();

    /* loaded from: classes.dex */
    private static final class zza implements ObjectEncoder<zza> {
        static final zza zza = new zza();

        private zza() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
            zza zzaVar = (zza) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            objectEncoderContext2.add("sdkVersion", zzaVar.zzi());
            objectEncoderContext2.add("model", zzaVar.zzf());
            objectEncoderContext2.add("hardware", zzaVar.zzd());
            objectEncoderContext2.add("device", zzaVar.zzb());
            objectEncoderContext2.add("product", zzaVar.zzh());
            objectEncoderContext2.add("osBuild", zzaVar.zzg());
            objectEncoderContext2.add("manufacturer", zzaVar.zze());
            objectEncoderContext2.add("fingerprint", zzaVar.zzc());
        }
    }

    /* renamed from: com.google.android.datatransport.cct.a.zzb$zzb  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0030zzb implements ObjectEncoder<zzo> {
        static final C0030zzb zza = new C0030zzb();

        private C0030zzb() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add("logRequest", ((zzo) obj).zza());
        }
    }

    /* loaded from: classes.dex */
    private static final class zzc implements ObjectEncoder<zzp> {
        static final zzc zza = new zzc();

        private zzc() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
            zzp zzpVar = (zzp) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            objectEncoderContext2.add("clientType", zzpVar.zzc());
            objectEncoderContext2.add("androidClientInfo", zzpVar.zzb());
        }
    }

    /* loaded from: classes.dex */
    private static final class zzd implements ObjectEncoder<zzq> {
        static final zzd zza = new zzd();

        private zzd() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
            zzq zzqVar = (zzq) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            objectEncoderContext2.add("eventTimeMs", zzqVar.zzb());
            objectEncoderContext2.add("eventCode", zzqVar.zza());
            objectEncoderContext2.add("eventUptimeMs", zzqVar.zzc());
            objectEncoderContext2.add("sourceExtension", zzqVar.zze());
            objectEncoderContext2.add("sourceExtensionJsonProto3", zzqVar.zzf());
            objectEncoderContext2.add("timezoneOffsetSeconds", zzqVar.zzg());
            objectEncoderContext2.add("networkConnectionInfo", zzqVar.zzd());
        }
    }

    /* loaded from: classes.dex */
    private static final class zze implements ObjectEncoder<zzr> {
        static final zze zza = new zze();

        private zze() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
            zzr zzrVar = (zzr) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            objectEncoderContext2.add("requestTimeMs", zzrVar.zzg());
            objectEncoderContext2.add("requestUptimeMs", zzrVar.zzh());
            objectEncoderContext2.add("clientInfo", zzrVar.zzb());
            objectEncoderContext2.add("logSource", zzrVar.zzd());
            objectEncoderContext2.add("logSourceName", zzrVar.zze());
            objectEncoderContext2.add("logEvent", zzrVar.zzc());
            objectEncoderContext2.add("qosTier", zzrVar.zzf());
        }
    }

    /* loaded from: classes.dex */
    private static final class zzf implements ObjectEncoder<zzt> {
        static final zzf zza = new zzf();

        private zzf() {
        }

        @Override // com.google.firebase.encoders.Encoder
        public void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
            zzt zztVar = (zzt) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            objectEncoderContext2.add("networkType", zztVar.zzc());
            objectEncoderContext2.add("mobileSubtype", zztVar.zzb());
        }
    }

    private zzb() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        encoderConfig.registerEncoder(zzo.class, C0030zzb.zza);
        encoderConfig.registerEncoder(zze.class, C0030zzb.zza);
        encoderConfig.registerEncoder(zzr.class, zze.zza);
        encoderConfig.registerEncoder(zzk.class, zze.zza);
        encoderConfig.registerEncoder(zzp.class, zzc.zza);
        encoderConfig.registerEncoder(zzg.class, zzc.zza);
        encoderConfig.registerEncoder(zza.class, zza.zza);
        encoderConfig.registerEncoder(zzd.class, zza.zza);
        encoderConfig.registerEncoder(zzq.class, zzd.zza);
        encoderConfig.registerEncoder(zzi.class, zzd.zza);
        encoderConfig.registerEncoder(zzt.class, zzf.zza);
        encoderConfig.registerEncoder(zzn.class, zzf.zza);
    }
}
