package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzlv {

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zza extends zzif<zza, C0032zza> implements zzjp {
        private static final zza zzl;
        private static volatile zzjx<zza> zzm;
        private String zzc = "";
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private zzio<String> zzg = zzif.zzac();
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";

        private zza() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* renamed from: com.google.android.gms.internal.firebase_auth.zzlv$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0032zza extends zzif.zza<zza, C0032zza> implements zzjp {
            private C0032zza() {
                super(zza.zzl);
            }

            public final C0032zza zza(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(str);
                return this;
            }

            public final C0032zza zzb(String str) {
                if (this.zzb) {
                    zzb();
                    this.zzb = false;
                }
                ((zza) this.zza).zzb(str);
                return this;
            }

            /* synthetic */ C0032zza(zzlu zzluVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zze = str;
        }

        public static C0032zza zza() {
            return zzl.zzz();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzlu.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0032zza(null);
                case 3:
                    return zza(zzl, "\u0000\t\u0000\u0000\u0001\u000b\t\u0000\u0001\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0006Ȉ\u0007Ț\bȈ\tȈ\nȈ\u000bȈ", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
                case 4:
                    return zzl;
                case 5:
                    zzjx<zza> zzjxVar = zzm;
                    if (zzjxVar == null) {
                        synchronized (zza.class) {
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
            zza zzaVar = new zza();
            zzl = zzaVar;
            zzif.zza(zza.class, zzaVar);
        }
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static final class zzb extends zzif<zzb, zza> implements zzjp {
        private static final zzb zzk;
        private static volatile zzjx<zzb> zzl;
        private long zzd;
        private long zzi;
        private String zzc = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzj = "";

        private zzb() {
        }

        /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
        /* loaded from: classes.dex */
        public static final class zza extends zzif.zza<zzb, zza> implements zzjp {
            private zza() {
                super(zzb.zzk);
            }

            /* synthetic */ zza(zzlu zzluVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzc;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final String zzd() {
            return this.zze;
        }

        public final String zze() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.firebase_auth.zzif
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzlu.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzk, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001Ȉ\u0002\u0002\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007\u0002\bȈ", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
                case 4:
                    return zzk;
                case 5:
                    zzjx<zzb> zzjxVar = zzl;
                    if (zzjxVar == null) {
                        synchronized (zzb.class) {
                            zzjxVar = zzl;
                            if (zzjxVar == null) {
                                zzjxVar = new zzif.zzc<>(zzk);
                                zzl = zzjxVar;
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

        public static zzjx<zzb> zzf() {
            return (zzjx) zzk.zza(zzif.zzf.zzg, (Object) null, (Object) null);
        }

        static {
            zzb zzbVar = new zzb();
            zzk = zzbVar;
            zzif.zza(zzb.class, zzbVar);
        }
    }
}
