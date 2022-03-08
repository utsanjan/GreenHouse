package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzp {

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif<zza, C0033zza> implements zzjp {
        private static final zza zzs;
        private static volatile zzjx<zza> zzt;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private zzio<zzl> zzq = zzac();
        private String zzr = "";

        private zza() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0033zza extends zzif.zza<zza, C0033zza> implements zzjp {
            private C0033zza() {
                super(zza.zzs);
            }

            public final C0033zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }

            public final C0033zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zzb(str);
                return this;
            }

            public final C0033zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ C0033zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 8192;
            this.zzr = str;
        }

        public static C0033zza zza() {
            return zzs.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0033zza(null);
                case 3:
                    return zza(zzs, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t\u000bဈ\n\fဈ\u000b\rဈ\f\u000e\u001b\u000fဈ\r", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzl.class, "zzr"});
                case 4:
                    return zzs;
                case 5:
                    zzjx<zza> zzjxVar = zzt;
                    if (zzjxVar == null) {
                        synchronized (zza.class) {
                            zzjxVar = zzt;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzs);
                                zzt = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zzaVar = new zza();
            zzs = zzaVar;
            zzif.zza(zza.class, zzaVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzb extends zzif<zzb, zza> implements zzjp {
        private static final zzb zzn;
        private static volatile zzjx<zzb> zzo;
        private int zzc;
        private boolean zzg;
        private boolean zzi;
        private boolean zzj;
        private byte zzm = 2;
        private String zzd = "";
        private String zze = "";
        private zzio<String> zzf = zzif.zzac();
        private String zzh = "";
        private String zzk = "";
        private zzio<String> zzl = zzif.zzac();

        private zzb() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzb, zza> implements zzjp {
            private zza() {
                super(zzb.zzn);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final List<String> zzb() {
            return this.zzf;
        }

        public final int zzc() {
            return this.zzf.size();
        }

        public final boolean zzd() {
            return this.zzg;
        }

        public final String zze() {
            return this.zzh;
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final List<String> zzg() {
            return this.zzl;
        }

        public final int a_() {
            return this.zzl.size();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzn, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003\u001a\u0004ဇ\u0002\u0005ဈ\u0003\u0006ဇ\u0004\u0007ဇ\u0005\bဈ\u0006\t\u001a", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
                case 4:
                    return zzn;
                case 5:
                    zzjx<zzb> zzjxVar = zzo;
                    if (zzjxVar == null) {
                        synchronized (zzb.class) {
                            zzjxVar = zzo;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzn);
                                zzo = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzm);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzm = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzb> zzi() {
            return (zzjx) zzn.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzb zzbVar = new zzb();
            zzn = zzbVar;
            zzif.zza(zzb.class, zzbVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzc extends zzif<zzc, zza> implements zzjp {
        private static final zzc zzg;
        private static volatile zzjx<zzc> zzh;
        private int zzc;
        private long zze;
        private String zzd = "";
        private String zzf = "";

        private zzc() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzc, zza> implements zzjp {
            private zza() {
                super(zzc.zzg);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        public static zza zza() {
            return zzg.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဈ\u0002", new Object[]{"zzc", "zzd", "zze", "zzf"});
                case 4:
                    return zzg;
                case 5:
                    zzjx<zzc> zzjxVar = zzh;
                    if (zzjxVar == null) {
                        synchronized (zzc.class) {
                            zzjxVar = zzh;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzg);
                                zzh = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzcVar = new zzc();
            zzg = zzcVar;
            zzif.zza(zzc.class, zzcVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzd extends zzif<zzd, zza> implements zzjp {
        private static final zzd zzh;
        private static volatile zzjx<zzd> zzi;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";

        private zzd() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzd, zza> implements zzjp {
            private zza() {
                super(zzd.zzh);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzd) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzd) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzd) this.zza).zzd(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 8;
            this.zzg = str;
        }

        public static zza zza() {
            return zzh.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0006\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0006ဈ\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjx<zzd> zzjxVar = zzi;
                    if (zzjxVar == null) {
                        synchronized (zzd.class) {
                            zzjxVar = zzi;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzh);
                                zzi = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzd zzdVar = new zzd();
            zzh = zzdVar;
            zzif.zza(zzd.class, zzdVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zze extends zzif<zze, zza> implements zzjp {
        private static final zze zzn;
        private static volatile zzjx<zze> zzo;
        private int zzc;
        private long zzh;
        private boolean zzj;
        private byte zzm = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzi = "";
        private String zzk = "";
        private zzio<zzr> zzl = zzac();

        private zze() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zze, zza> implements zzjp {
            private zza() {
                super(zze.zzn);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final long zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzi;
        }

        public final boolean zzf() {
            return this.zzj;
        }

        public final String zzg() {
            return this.zzk;
        }

        public final List<zzr> c_() {
            return this.zzl;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzn, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0001\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဂ\u0004\u0006ဈ\u0005\u0007ဇ\u0006\bဈ\u0007\t\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzr.class});
                case 4:
                    return zzn;
                case 5:
                    zzjx<zze> zzjxVar = zzo;
                    if (zzjxVar == null) {
                        synchronized (zze.class) {
                            zzjxVar = zzo;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzn);
                                zzo = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzm);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzm = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zze> zzi() {
            return (zzjx) zzn.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zze zzeVar = new zze();
            zzn = zzeVar;
            zzif.zza(zze.class, zzeVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzf extends zzif<zzf, zza> implements zzjp {
        private static final zzf zzh;
        private static volatile zzjx<zzf> zzi;
        private int zzc;
        private String zzd = "";
        private zzio<String> zze = zzif.zzac();
        private zzio<String> zzf = zzif.zzac();
        private long zzg;

        private zzf() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzf, zza> implements zzjp {
            private zza() {
                super(zzf.zzh);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzf) this.zza).zza(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        public static zza zza() {
            return zzh.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဈ\u0000\u0002\u001a\u0003\u001a\u0004ဂ\u0001", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjx<zzf> zzjxVar = zzi;
                    if (zzjxVar == null) {
                        synchronized (zzf.class) {
                            zzjxVar = zzi;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzh);
                                zzi = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzf zzfVar = new zzf();
            zzh = zzfVar;
            zzif.zza(zzf.class, zzfVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzg extends zzif<zzg, zza> implements zzjp {
        private static final zzg zzg;
        private static volatile zzjx<zzg> zzh;
        private int zzc;
        private byte zzf = 2;
        private String zzd = "";
        private zzio<zzz> zze = zzac();

        private zzg() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzg, zza> implements zzjp {
            private zza() {
                super(zzg.zzg);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final int zza() {
            return this.zze.size();
        }

        public final zzz zza(int i) {
            return this.zze.get(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzg, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0001\u0001ᔈ\u0000\u0002\u001b", new Object[]{"zzc", "zzd", "zze", zzz.class});
                case 4:
                    return zzg;
                case 5:
                    zzjx<zzg> zzjxVar = zzh;
                    if (zzjxVar == null) {
                        synchronized (zzg.class) {
                            zzjxVar = zzh;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzg);
                                zzh = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzf);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzf = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzg> zzb() {
            return (zzjx) zzg.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzg zzgVar = new zzg();
            zzg = zzgVar;
            zzif.zza(zzg.class, zzgVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzh extends zzif<zzh, zza> implements zzjp {
        private static final zzh zzu;
        private static volatile zzjx<zzh> zzv;
        private int zzc;
        private int zzd;
        private boolean zzo;
        private boolean zzq;
        private boolean zzt;
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzn = "";
        private String zzp = "";
        private String zzr = "";
        private String zzs = "";

        private zzh() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzh, zza> implements zzjp {
            private zza() {
                super(zzh.zzu);
            }

            public final zza zza(zzgj zzgjVar) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zza(zzgjVar);
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzd(str);
                return this;
            }

            public final zza zze(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zze(str);
                return this;
            }

            public final zza zzf(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzf(str);
                return this;
            }

            public final zza zzg(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzg(str);
                return this;
            }

            public final zza zza(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zza(z);
                return this;
            }

            public final zza zzh(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzh(str);
                return this;
            }

            public final zza zzb(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzb(z);
                return this;
            }

            public final zza zzi(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzi(str);
                return this;
            }

            public final zza zzj(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzh) this.zza).zzj(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzgj zzgjVar) {
            this.zzd = zzgjVar.zza();
            this.zzc |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 32;
            this.zzi = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 64;
            this.zzj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 128;
            this.zzk = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 256;
            this.zzl = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzc |= 512;
            this.zzm = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            str.getClass();
            this.zzc |= 1024;
            this.zzn = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 2048;
            this.zzo = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(String str) {
            str.getClass();
            this.zzc |= 4096;
            this.zzp = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzc |= 8192;
            this.zzq = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(String str) {
            str.getClass();
            this.zzc |= 16384;
            this.zzr = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(String str) {
            str.getClass();
            this.zzc |= 32768;
            this.zzs = str;
        }

        public static zza zza() {
            return zzu.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzu, "\u0001\u0011\u0000\u0001\u0001\u0013\u0011\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t\u000bဈ\n\fဇ\u000b\rဈ\f\u000eဇ\r\u000fဈ\u000e\u0012ဈ\u000f\u0013ဇ\u0010", new Object[]{"zzc", "zzd", zzgj.zzb(), "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt"});
                case 4:
                    return zzu;
                case 5:
                    zzjx<zzh> zzjxVar = zzv;
                    if (zzjxVar == null) {
                        synchronized (zzh.class) {
                            zzjxVar = zzv;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzu);
                                zzv = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzh zzhVar = new zzh();
            zzu = zzhVar;
            zzif.zza(zzh.class, zzhVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzi extends zzif<zzi, zza> implements zzjp {
        private static final zzi zzi;
        private static volatile zzjx<zzi> zzj;
        private int zzc;
        private byte zzh = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";

        private zzi() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzi, zza> implements zzjp {
            private zza() {
                super(zzi.zzi);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzi, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzi;
                case 5:
                    zzjx<zzi> zzjxVar = zzj;
                    if (zzjxVar == null) {
                        synchronized (zzi.class) {
                            zzjxVar = zzj;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzi);
                                zzj = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzh);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzh = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzi> zza() {
            return (zzjx) zzi.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzi zziVar = new zzi();
            zzi = zziVar;
            zzif.zza(zzi.class, zziVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzj extends zzif<zzj, zza> implements zzjp {
        private static final zzj zzi;
        private static volatile zzjx<zzj> zzj;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";

        private zzj() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzj, zza> implements zzjp {
            private zza() {
                super(zzj.zzi);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzj) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzj) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzj) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 16;
            this.zzh = str;
        }

        public static zza zza() {
            return zzi.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0006ဈ\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjx<zzj> zzjxVar = zzj;
                    if (zzjxVar == null) {
                        synchronized (zzj.class) {
                            zzjxVar = zzj;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzi);
                                zzj = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzj zzjVar = new zzj();
            zzi = zzjVar;
            zzif.zza(zzj.class, zzjVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzk extends zzif<zzk, zza> implements zzjp {
        private static final zzk zzj;
        private static volatile zzjx<zzk> zzk;
        private int zzc;
        private int zzg;
        private zzr zzh;
        private byte zzi = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";

        private zzk() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzk, zza> implements zzjp {
            private zza() {
                super(zzk.zzj);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final zzgj zzc() {
            zzgj zza2 = zzgj.zza(this.zzg);
            return zza2 == null ? zzgj.OOB_REQ_TYPE_UNSPECIFIED : zza2;
        }

        public final boolean zzd() {
            return (this.zzc & 16) != 0;
        }

        public final zzr zze() {
            zzr zzrVar = this.zzh;
            return zzrVar == null ? zzr.zzf() : zzrVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzj, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဌ\u0003\u0005ဉ\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzgj.zzb(), "zzh"});
                case 4:
                    return zzj;
                case 5:
                    zzjx<zzk> zzjxVar = zzk;
                    if (zzjxVar == null) {
                        synchronized (zzk.class) {
                            zzjxVar = zzk;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzj);
                                zzk = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzi);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzi = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzk> zzf() {
            return (zzjx) zzj.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzk zzkVar = new zzk();
            zzj = zzkVar;
            zzif.zza(zzk.class, zzkVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzl extends zzif<zzl, zzb> implements zzjp {
        private static final zzl zzj;
        private static volatile zzjx<zzl> zzk;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private zza zzi;

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif<zza, C0034zza> implements zzjp {
            private static final zza zze;
            private static volatile zzjx<zza> zzf;
            private int zzc;
            private String zzd = "";

            private zza() {
            }

            /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
            /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzl$zza$zza  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C0034zza extends zzif.zza<zza, C0034zza> implements zzjp {
                private C0034zza() {
                    super(zza.zze);
                }

                /* synthetic */ C0034zza(zzo zzoVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.internal.firebase_auth.zzif
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzo.zza[i - 1]) {
                    case 1:
                        return new zza();
                    case 2:
                        return new C0034zza(null);
                    case 3:
                        return zza(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzc", "zzd"});
                    case 4:
                        return zze;
                    case 5:
                        zzjx<zza> zzjxVar = zzf;
                        if (zzjxVar == null) {
                            synchronized (zza.class) {
                                zzjxVar = zzf;
                                if (zzjxVar == null) {
                                    zzjxVar = new zzif.zzc<>(zze);
                                    zzf = zzjxVar;
                                }
                            }
                        }
                        return zzjxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zza zzaVar = new zza();
                zze = zzaVar;
                zzif.zza(zza.class, zzaVar);
            }
        }

        private zzl() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zzb extends zzif.zza<zzl, zzb> implements zzjp {
            private zzb() {
                super(zzl.zzj);
            }

            public final zzb zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzl) this.zza).zza(str);
                return this;
            }

            public final zzb zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzl) this.zza).zzb(str);
                return this;
            }

            public final zzb zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzl) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zzb(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 8;
            this.zzg = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 16;
            this.zzh = str;
        }

        public static zzb zza() {
            return zzj.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zzb(null);
                case 3:
                    return zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0007ဉ\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzj;
                case 5:
                    zzjx<zzl> zzjxVar = zzk;
                    if (zzjxVar == null) {
                        synchronized (zzl.class) {
                            zzjxVar = zzk;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzj);
                                zzk = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzl zzlVar = new zzl();
            zzj = zzlVar;
            zzif.zza(zzl.class, zzlVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzm extends zzif<zzm, zza> implements zzjp {
        private static final zzm zze;
        private static volatile zzjx<zzm> zzf;
        private int zzc;
        private String zzd = "";

        private zzm() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzm, zza> implements zzjp {
            private zza() {
                super(zzm.zze);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzc", "zzd"});
                case 4:
                    return zze;
                case 5:
                    zzjx<zzm> zzjxVar = zzf;
                    if (zzjxVar == null) {
                        synchronized (zzm.class) {
                            zzjxVar = zzf;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zze);
                                zzf = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzm> zzb() {
            return (zzjx) zze.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzm zzmVar = new zzm();
            zze = zzmVar;
            zzif.zza(zzm.class, zzmVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzn extends zzif<zzn, zzb> implements zzjp {
        private static final zzn zzab;
        private static volatile zzjx<zzn> zzac;
        private static final zzil<Integer, zzv> zzu = new zzq();
        private zza zzaa;
        private int zzc;
        private boolean zzk;
        private boolean zzl;
        private zzkw zzo;
        private boolean zzp;
        private long zzr;
        private boolean zzv;
        private long zzx;
        private long zzy;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private zzio<String> zzi = zzif.zzac();
        private String zzj = "";
        private String zzm = "";
        private String zzn = "";
        private String zzq = "";
        private String zzs = "";
        private zzim zzt = zzab();
        private zzio<String> zzw = zzif.zzac();
        private String zzz = "";

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif<zza, C0035zza> implements zzjp {
            private static final zza zzd;
            private static volatile zzjx<zza> zze;
            private zzio<zzr> zzc = zzac();

            private zza() {
            }

            /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
            /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzn$zza$zza  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public static final class C0035zza extends zzif.zza<zza, C0035zza> implements zzjp {
                private C0035zza() {
                    super(zza.zzd);
                }

                /* synthetic */ C0035zza(zzo zzoVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.internal.firebase_auth.zzif
            public final Object zza(int i, Object obj, Object obj2) {
                switch (zzo.zza[i - 1]) {
                    case 1:
                        return new zza();
                    case 2:
                        return new C0035zza(null);
                    case 3:
                        return zza(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzr.class});
                    case 4:
                        return zzd;
                    case 5:
                        zzjx<zza> zzjxVar = zze;
                        if (zzjxVar == null) {
                            synchronized (zza.class) {
                                zzjxVar = zze;
                                if (zzjxVar == null) {
                                    zzjxVar = new zzif.zzc<>(zzd);
                                    zze = zzjxVar;
                                }
                            }
                        }
                        return zzjxVar;
                    case 6:
                        return (byte) 1;
                    case 7:
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zza zzaVar = new zza();
                zzd = zzaVar;
                zzif.zza(zza.class, zzaVar);
            }
        }

        private zzn() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zzb extends zzif.zza<zzn, zzb> implements zzjp {
            private zzb() {
                super(zzn.zzab);
            }

            public final zzb zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zza(str);
                return this;
            }

            public final zzb zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zzb(str);
                return this;
            }

            public final zzb zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zzc(str);
                return this;
            }

            public final zzb zzd(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zzd(str);
                return this;
            }

            public final zzb zze(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zze(str);
                return this;
            }

            public final zzb zzf(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zzf(str);
                return this;
            }

            public final zzb zza(Iterable<? extends zzv> iterable) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zza(iterable);
                return this;
            }

            public final zzb zza(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zza(z);
                return this;
            }

            public final zzb zzb(Iterable<String> iterable) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zzb(iterable);
                return this;
            }

            public final zzb zzg(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzn) this.zza).zzg(str);
                return this;
            }

            /* synthetic */ zzb(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 8;
            this.zzg = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 16;
            this.zzh = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 32;
            this.zzj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzc |= 16384;
            this.zzs = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zzv> iterable) {
            if (!this.zzt.zza()) {
                zzim zzimVar = this.zzt;
                int size = zzimVar.size();
                this.zzt = zzimVar.zzb(size == 0 ? 10 : size << 1);
            }
            for (zzv zzvVar : iterable) {
                this.zzt.zzd(zzvVar.zza());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 32768;
            this.zzv = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<String> iterable) {
            if (!this.zzw.zza()) {
                zzio<String> zzioVar = this.zzw;
                int size = zzioVar.size();
                this.zzw = zzioVar.zza(size == 0 ? 10 : size << 1);
            }
            zzgn.zza(iterable, this.zzw);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            str.getClass();
            this.zzc |= 262144;
            this.zzz = str;
        }

        public static zzb zza() {
            return zzab.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zzb(null);
                case 3:
                    return zza(zzab, "\u0001\u0017\u0000\u0001\u0002\u001c\u0017\u0000\u0003\u0000\u0002ဈ\u0000\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဈ\u0003\u0006ဈ\u0004\u0007\u001a\bဈ\u0005\tဇ\u0006\nဇ\u0007\u000bဈ\b\fဈ\t\rဉ\n\u000eဇ\u000b\u000fဈ\f\u0010ဂ\r\u0011ဈ\u000e\u0012\u001e\u0013ဇ\u000f\u0014\u001a\u0015ဂ\u0010\u0016ဂ\u0011\u0019ဈ\u0012\u001cဉ\u0013", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", zzv.zzb(), "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa"});
                case 4:
                    return zzab;
                case 5:
                    zzjx<zzn> zzjxVar = zzac;
                    if (zzjxVar == null) {
                        synchronized (zzn.class) {
                            zzjxVar = zzac;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzab);
                                zzac = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzn zznVar = new zzn();
            zzab = zznVar;
            zzif.zza(zzn.class, zznVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzo extends zzif<zzo, zza> implements zzjp {
        private static final zzo zzr;
        private static volatile zzjx<zzo> zzs;
        private int zzc;
        private long zzn;
        private boolean zzp;
        private byte zzq = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private zzio<String> zzh = zzif.zzac();
        private String zzi = "";
        private zzio<zzu> zzj = zzac();
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzo = "";

        private zzo() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzo, zza> implements zzjp {
            private zza() {
                super(zzo.zzr);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        @Deprecated
        public final String zza() {
            return this.zzf;
        }

        @Deprecated
        public final String zzb() {
            return this.zzg;
        }

        public final String zzc() {
            return this.zzi;
        }

        @Deprecated
        public final List<zzu> zzd() {
            return this.zzj;
        }

        @Deprecated
        public final String zze() {
            return this.zzl;
        }

        public final String zzf() {
            return this.zzm;
        }

        public final long zzg() {
            return this.zzn;
        }

        public final String d_() {
            return this.zzo;
        }

        public final boolean zzi() {
            return this.zzp;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzr, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0002\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005\u001a\u0006ဈ\u0004\u0007\u001b\bဈ\u0005\tဈ\u0006\nဈ\u0007\u000bဂ\b\fဈ\t\rဇ\n", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzu.class, "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
                case 4:
                    return zzr;
                case 5:
                    zzjx<zzo> zzjxVar = zzs;
                    if (zzjxVar == null) {
                        synchronized (zzo.class) {
                            zzjxVar = zzs;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzr);
                                zzs = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzq);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzq = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzo> zzj() {
            return (zzjx) zzr.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzo zzoVar = new zzo();
            zzr = zzoVar;
            zzif.zza(zzo.class, zzoVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzp  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0036zzp extends zzif<C0036zzp, zza> implements zzjp {
        private static final C0036zzp zzo;
        private static volatile zzjx<C0036zzp> zzp;
        private int zzc;
        private boolean zzk;
        private boolean zzm;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzl = "";
        private String zzn = "";

        private C0036zzp() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzp$zza */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<C0036zzp, zza> implements zzjp {
            private zza() {
                super(C0036zzp.zzo);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((C0036zzp) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((C0036zzp) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((C0036zzp) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 1024;
            this.zzn = str;
        }

        public static zza zza() {
            return zzo.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new C0036zzp();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzo, "\u0001\u000b\u0000\u0001\u0001\r\u000b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဇ\u0007\tဈ\b\nဇ\t\rဈ\n", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
                case 4:
                    return zzo;
                case 5:
                    zzjx<C0036zzp> zzjxVar = zzp;
                    if (zzjxVar == null) {
                        synchronized (C0036zzp.class) {
                            zzjxVar = zzp;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzo);
                                zzp = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            C0036zzp zzpVar = new C0036zzp();
            zzo = zzpVar;
            zzif.zza(C0036zzp.class, zzpVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzq extends zzif<zzq, zza> implements zzjp {
        private static final zzq zzl;
        private static volatile zzjx<zzq> zzm;
        private int zzc;
        private long zzi;
        private byte zzk = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzj = "";

        private zzq() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzq, zza> implements zzjp {
            private zza() {
                super(zzq.zzl);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final String zzd() {
            return this.zzh;
        }

        public final long zze() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzl, "\u0001\u0007\u0000\u0001\u0001\b\u0007\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0004ဈ\u0002\u0005ဈ\u0003\u0006ဈ\u0004\u0007ဂ\u0005\bဈ\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
                case 4:
                    return zzl;
                case 5:
                    zzjx<zzq> zzjxVar = zzm;
                    if (zzjxVar == null) {
                        synchronized (zzq.class) {
                            zzjxVar = zzm;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzl);
                                zzm = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzk);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzk = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzq> zzf() {
            return (zzjx) zzl.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzq zzqVar = new zzq();
            zzl = zzqVar;
            zzif.zza(zzq.class, zzqVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzr extends zzif<zzr, zza> implements zzjp {
        private static final zzr zzq;
        private static volatile zzjx<zzr> zzr;
        private int zzc;
        private boolean zzg;
        private long zzj;
        private boolean zzl;
        private boolean zzm;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzh = "";
        private String zzi = "";
        private String zzk = "";
        private boolean zzn = true;
        private String zzo = "";
        private String zzp = "";

        private zzr() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzr, zza> implements zzjp {
            private zza() {
                super(zzr.zzq);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zzd(str);
                return this;
            }

            public final zza zza(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zza(z);
                return this;
            }

            public final zza zzb(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zzb(z);
                return this;
            }

            public final zza zzc(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zzc(z);
                return this;
            }

            public final zza zze(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zze(str);
                return this;
            }

            public final zza zzf(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzr) this.zza).zzf(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 16;
            this.zzh = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 128;
            this.zzk = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 256;
            this.zzl = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzc |= 512;
            this.zzm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(boolean z) {
            this.zzc |= 1024;
            this.zzn = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 2048;
            this.zzo = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzc |= 4096;
            this.zzp = str;
        }

        public static zza zza() {
            return zzq.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzq, "\u0001\r\u0000\u0001\u0001\u000f\r\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဇ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဂ\u0006\bဈ\u0007\tဇ\b\nဇ\t\u000bဇ\n\rဈ\u000b\u000fဈ\f", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
                case 4:
                    return zzq;
                case 5:
                    zzjx<zzr> zzjxVar = zzr;
                    if (zzjxVar == null) {
                        synchronized (zzr.class) {
                            zzjxVar = zzr;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzq);
                                zzr = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzr zzrVar = new zzr();
            zzq = zzrVar;
            zzif.zza(zzr.class, zzrVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzs extends zzif<zzs, zza> implements zzjp {
        private static final zzs zzau;
        private static volatile zzjx<zzs> zzav;
        private boolean zzab;
        private long zzaf;
        private boolean zzah;
        private long zzak;
        private boolean zzap;
        private int zzc;
        private int zzd;
        private boolean zzh;
        private boolean zzv;
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private String zzq = "";
        private String zzr = "";
        private String zzs = "";
        private String zzt = "";
        private String zzu = "";
        private String zzw = "";
        private String zzx = "";
        private String zzy = "";
        private String zzz = "";
        private zzio<String> zzaa = zzif.zzac();
        private String zzac = "";
        private String zzad = "";
        private String zzae = "";
        private String zzag = "";
        private String zzai = "";
        private String zzaj = "";
        private String zzal = "";
        private String zzam = "";
        private String zzan = "";
        private String zzao = "";
        private String zzaq = "";
        private String zzar = "";
        private String zzas = "";
        private zzio<zzr> zzat = zzac();

        private zzs() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzs, zza> implements zzjp {
            private zza() {
                super(zzs.zzau);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzf;
        }

        public final String zzb() {
            return this.zzg;
        }

        public final String zzc() {
            return this.zzo;
        }

        public final String zzd() {
            return this.zzu;
        }

        public final String zze() {
            return this.zzw;
        }

        public final String zzf() {
            return this.zzx;
        }

        public final boolean zzg() {
            return this.zzab;
        }

        public final String e_() {
            return this.zzad;
        }

        public final boolean zzi() {
            return this.zzah;
        }

        public final String zzj() {
            return this.zzai;
        }

        public final String zzk() {
            return this.zzaj;
        }

        public final long zzl() {
            return this.zzak;
        }

        public final String zzm() {
            return this.zzal;
        }

        public final String zzn() {
            return this.zzan;
        }

        public final String zzo() {
            return this.zzao;
        }

        public final boolean zzp() {
            return this.zzap;
        }

        public final String zzq() {
            return this.zzaq;
        }

        public final String zzr() {
            return this.zzar;
        }

        public final String zzs() {
            return this.zzas;
        }

        public final List<zzr> zzt() {
            return this.zzat;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzau, "\u0001*\u0000\u0002\u0001-*\u0000\u0002\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဇ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t\u000bဈ\n\fဈ\u000b\rဈ\f\u000eဈ\r\u000fဈ\u000e\u0010ဈ\u000f\u0011ဈ\u0010\u0012ဇ\u0011\u0013ဈ\u0012\u0014ဈ\u0013\u0015ဈ\u0014\u0017ဈ\u0015\u0018\u001a\u0019ဇ\u0016\u001aဈ\u0017\u001cဈ\u0018\u001dဈ\u0019\u001eဂ\u001a\u001fဈ\u001b ဇ\u001c!ဈ\u001d\"ဈ\u001e#ဂ\u001f$ဈ %ဈ!&ဈ\"'ဈ#(ဇ$*ဈ%+ဈ&,ဈ'-\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", zzr.class});
                case 4:
                    return zzau;
                case 5:
                    zzjx<zzs> zzjxVar = zzav;
                    if (zzjxVar == null) {
                        synchronized (zzs.class) {
                            zzjxVar = zzav;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzau);
                                zzav = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzs> zzu() {
            return (zzjx) zzau.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzs zzsVar = new zzs();
            zzau = zzsVar;
            zzif.zza(zzs.class, zzsVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzt extends zzif<zzt, zza> implements zzjp {
        private static final zzt zzi;
        private static volatile zzjx<zzt> zzj;
        private int zzc;
        private boolean zzf;
        private long zzg;
        private String zzd = "";
        private String zze = "";
        private String zzh = "";

        private zzt() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzt, zza> implements zzjp {
            private zza() {
                super(zzt.zzi);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzt) this.zza).zza(str);
                return this;
            }

            public final zza zza(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzt) this.zza).zza(true);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzt) this.zza).zzb(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 4;
            this.zzf = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 16;
            this.zzh = str;
        }

        public static zza zza() {
            return zzi.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဂ\u0003\u0005ဈ\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjx<zzt> zzjxVar = zzj;
                    if (zzjxVar == null) {
                        synchronized (zzt.class) {
                            zzjxVar = zzj;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzi);
                                zzj = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzt zztVar = new zzt();
            zzi = zztVar;
            zzif.zza(zzt.class, zztVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzu extends zzif<zzu, zza> implements zzjp {
        private static final zzu zzj;
        private static volatile zzjx<zzu> zzk;
        private int zzc;
        private long zzg;
        private boolean zzh;
        private byte zzi = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";

        private zzu() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzu, zza> implements zzjp {
            private zza() {
                super(zzu.zzj);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final long zzc() {
            return this.zzg;
        }

        public final boolean zzd() {
            return this.zzh;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzj, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဇ\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzj;
                case 5:
                    zzjx<zzu> zzjxVar = zzk;
                    if (zzjxVar == null) {
                        synchronized (zzu.class) {
                            zzjxVar = zzk;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzj);
                                zzk = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzi);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzi = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzu> zze() {
            return (zzjx) zzj.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzu zzuVar = new zzu();
            zzj = zzuVar;
            zzif.zza(zzu.class, zzuVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzv extends zzif<zzv, zza> implements zzjp {
        private static final zzv zzo;
        private static volatile zzjx<zzv> zzp;
        private int zzc;
        private long zzk;
        private boolean zzm;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzl = "";
        private String zzn = "";

        private zzv() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzv, zza> implements zzjp {
            private zza() {
                super(zzv.zzo);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzv) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzv) this.zza).zzb(str);
                return this;
            }

            public final zza zza(boolean z) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzv) this.zza).zza(z);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzv) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 512;
            this.zzm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 1024;
            this.zzn = str;
        }

        public static zza zza() {
            return zzo.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzo, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007\tဈ\b\nဇ\t\u000bဈ\n", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
                case 4:
                    return zzo;
                case 5:
                    zzjx<zzv> zzjxVar = zzp;
                    if (zzjxVar == null) {
                        synchronized (zzv.class) {
                            zzjxVar = zzp;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzo);
                                zzp = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzv zzvVar = new zzv();
            zzo = zzvVar;
            zzif.zza(zzv.class, zzvVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzw extends zzif<zzw, zza> implements zzjp {
        private static final zzw zzs;
        private static volatile zzjx<zzw> zzt;
        private int zzc;
        private boolean zzi;
        private long zzl;
        private long zzo;
        private byte zzr = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzj = "";
        private String zzk = "";
        private String zzm = "";
        private String zzn = "";
        private String zzp = "";
        private zzio<zzr> zzq = zzac();

        private zzw() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzw, zza> implements zzjp {
            private zza() {
                super(zzw.zzs);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzj;
        }

        public final String zzf() {
            return this.zzn;
        }

        public final long zzg() {
            return this.zzo;
        }

        public final String f_() {
            return this.zzp;
        }

        public final List<zzr> zzi() {
            return this.zzq;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            int i2 = 1;
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzw();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzs, "\u0001\u000e\u0000\u0001\u0001\u000f\u000e\u0000\u0001\u0001\u0001ᔈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဇ\u0005\u0007ဈ\u0006\bဈ\u0007\tဂ\b\nဈ\t\u000bဈ\n\fဂ\u000b\u000eဈ\f\u000f\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzr.class});
                case 4:
                    return zzs;
                case 5:
                    zzjx<zzw> zzjxVar = zzt;
                    if (zzjxVar == null) {
                        synchronized (zzw.class) {
                            zzjxVar = zzt;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzs);
                                zzt = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return Byte.valueOf(this.zzr);
                case 7:
                    if (obj == null) {
                        i2 = 0;
                    }
                    this.zzr = (byte) i2;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjx<zzw> zzj() {
            return (zzjx) zzs.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzw zzwVar = new zzw();
            zzs = zzwVar;
            zzif.zza(zzw.class, zzwVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzx extends zzif<zzx, zza> implements zzjp {
        private static final zzx zzl;
        private static volatile zzjx<zzx> zzm;
        private int zzc;
        private int zzj;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzk = "";

        private zzx() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzx, zza> implements zzjp {
            private zza() {
                super(zzx.zzl);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzx) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzx) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzx) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzx) this.zza).zzd(str);
                return this;
            }

            public final zza zze(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzx) this.zza).zze(str);
                return this;
            }

            public final zza zza(zzaa zzaaVar) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zzx) this.zza).zza(zzaaVar);
                return this;
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 8;
            this.zzg = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 32;
            this.zzi = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzaa zzaaVar) {
            this.zzj = zzaaVar.zza();
            this.zzc |= 64;
        }

        public static zza zza() {
            return zzl.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzx();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဌ\u0006\bဈ\u0007", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzaa.zzb(), "zzk"});
                case 4:
                    return zzl;
                case 5:
                    zzjx<zzx> zzjxVar = zzm;
                    if (zzjxVar == null) {
                        synchronized (zzx.class) {
                            zzjxVar = zzm;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzl);
                                zzm = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzx zzxVar = new zzx();
            zzl = zzxVar;
            zzif.zza(zzx.class, zzxVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzy extends zzif<zzy, zza> implements zzjp {
        private static final zzy zzn;
        private static volatile zzjx<zzy> zzo;
        private int zzc;
        private long zzf;
        private boolean zzh;
        private long zzk;
        private long zzm;
        private String zzd = "";
        private String zze = "";
        private String zzg = "";
        private String zzi = "";
        private String zzj = "";
        private String zzl = "";

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzy, zza> implements zzjp {
            private zza() {
                super(zzy.zzn);
            }

            /* synthetic */ zza(zzo zzoVar) {
                this();
            }
        }

        static {
            zzy zzyVar = new zzy();
            zzn = zzyVar;
            zzif.zza(zzy.class, zzyVar);
        }

        private zzy() {
        }

        public final long g_() {
            return this.zzk;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzo.zza[i - 1]) {
                case 1:
                    return new zzy();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzn, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဈ\u0003\u0005ဇ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဂ\u0007\tဈ\b\nဂ\t", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
                case 4:
                    return zzn;
                case 5:
                    zzjx<zzy> zzjxVar = zzo;
                    if (zzjxVar == null) {
                        synchronized (zzy.class) {
                            zzjxVar = zzo;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzn);
                                zzo = zzjxVar;
                            }
                        }
                    }
                    return zzjxVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public final String zzd() {
            return this.zzg;
        }

        public final boolean zze() {
            return this.zzh;
        }

        public final String zzf() {
            return this.zzi;
        }

        public final String zzg() {
            return this.zzj;
        }

        public final String zzi() {
            return this.zzl;
        }

        public final String zza() {
            return this.zzd;
        }

        public final String zzb() {
            return this.zze;
        }

        public final long zzc() {
            return this.zzf;
        }

        public static zzjx<zzy> zzj() {
            return (zzjx) zzn.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }
    }
}
