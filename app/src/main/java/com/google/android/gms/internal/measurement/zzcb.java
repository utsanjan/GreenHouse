package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfo;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzcb {

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zza extends zzfo<zza, C0039zza> implements zzgy {
        private static final zza zzh;
        private static volatile zzhj<zza> zzi;
        private int zzc;
        private int zzd;
        private zzi zze;
        private zzi zzf;
        private boolean zzg;

        private zza() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* renamed from: com.google.android.gms.internal.measurement.zzcb$zza$zza  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0039zza extends zzfo.zza<zza, C0039zza> implements zzgy {
            private C0039zza() {
                super(zza.zzh);
            }

            public final C0039zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(i);
                return this;
            }

            public final C0039zza zza(zzi.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza((zzi) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final C0039zza zza(zzi zziVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zzb(zziVar);
                return this;
            }

            public final C0039zza zza(boolean z) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zza) this.zza).zza(z);
                return this;
            }

            /* synthetic */ C0039zza(zzcc zzccVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzc |= 1;
            this.zzd = i;
        }

        public final zzi zzc() {
            zzi zziVar = this.zze;
            return zziVar == null ? zzi.zzj() : zziVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzi zziVar) {
            zziVar.getClass();
            this.zze = zziVar;
            this.zzc |= 2;
        }

        public final boolean zzd() {
            return (this.zzc & 4) != 0;
        }

        public final zzi zze() {
            zzi zziVar = this.zzf;
            return zziVar == null ? zzi.zzj() : zziVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzi zziVar) {
            zziVar.getClass();
            this.zzf = zziVar;
            this.zzc |= 4;
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final boolean zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 8;
            this.zzg = z;
        }

        public static C0039zza zzh() {
            return zzh.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0039zza(null);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzhj<zza> zzhjVar = zzi;
                    if (zzhjVar == null) {
                        synchronized (zza.class) {
                            zzhjVar = zzi;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzh);
                                zzi = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
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
            zzh = zzaVar;
            zzfo.zza(zza.class, zzaVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzb extends zzfo<zzb, zza> implements zzgy {
        private static final zzb zzf;
        private static volatile zzhj<zzb> zzg;
        private int zzc;
        private int zzd;
        private long zze;

        private zzb() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzb, zza> implements zzgy {
            private zza() {
                super(zzb.zzf);
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(i);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzb) this.zza).zza(j);
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i) {
            this.zzc |= 1;
            this.zzd = i;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final long zzd() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zze = j;
        }

        public static zza zze() {
            return zzf.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzhj<zzb> zzhjVar = zzg;
                    if (zzhjVar == null) {
                        synchronized (zzb.class) {
                            zzhjVar = zzg;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzf);
                                zzg = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzf = zzbVar;
            zzfo.zza(zzb.class, zzbVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzc extends zzfo<zzc, zza> implements zzgy {
        private static final zzc zzi;
        private static volatile zzhj<zzc> zzj;
        private int zzc;
        private zzfx<zze> zzd = zzbp();
        private String zze = "";
        private long zzf;
        private long zzg;
        private int zzh;

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzc, zza> implements zzgy {
            private zza() {
                super(zzc.zzi);
            }

            public final List<zze> zza() {
                return Collections.unmodifiableList(((zzc) this.zza).zza());
            }

            public final int zzb() {
                return ((zzc) this.zza).zzb();
            }

            public final zze zza(int i) {
                return ((zzc) this.zza).zza(i);
            }

            public final zza zza(int i, zze zzeVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(i, zzeVar);
                return this;
            }

            public final zza zza(int i, zze.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(i, (zze) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final zza zza(zze zzeVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(zzeVar);
                return this;
            }

            public final zza zza(zze.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza((zze) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final zza zza(Iterable<? extends zze> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(iterable);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzm();
                return this;
            }

            public final zza zzb(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzb(i);
                return this;
            }

            public final String zzd() {
                return ((zzc) this.zza).zzc();
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(str);
                return this;
            }

            public final boolean zze() {
                return ((zzc) this.zza).zzd();
            }

            public final long zzf() {
                return ((zzc) this.zza).zze();
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zza(j);
                return this;
            }

            public final long zzg() {
                return ((zzc) this.zza).zzg();
            }

            public final zza zzb(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzc) this.zza).zzb(j);
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final List<zze> zza() {
            return this.zzd;
        }

        public final int zzb() {
            return this.zzd.size();
        }

        public final zze zza(int i) {
            return this.zzd.get(i);
        }

        private final void zzl() {
            zzfx<zze> zzfxVar = this.zzd;
            if (!zzfxVar.zza()) {
                this.zzd = zzfo.zza(zzfxVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zze zzeVar) {
            zzeVar.getClass();
            zzl();
            this.zzd.set(i, zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zze zzeVar) {
            zzeVar.getClass();
            zzl();
            this.zzd.add(zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zze> iterable) {
            zzl();
            zzdw.zza(iterable, this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzd = zzbp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i) {
            zzl();
            this.zzd.remove(i);
        }

        public final String zzc() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zze = str;
        }

        public final boolean zzd() {
            return (this.zzc & 2) != 0;
        }

        public final long zze() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zzf = j;
        }

        public final boolean zzf() {
            return (this.zzc & 4) != 0;
        }

        public final long zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j) {
            this.zzc |= 4;
            this.zzg = j;
        }

        public final boolean zzh() {
            return (this.zzc & 8) != 0;
        }

        public final int zzi() {
            return this.zzh;
        }

        public static zza zzj() {
            return zzi.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zzc", "zzd", zze.class, "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzhj<zzc> zzhjVar = zzj;
                    if (zzhjVar == null) {
                        synchronized (zzc.class) {
                            zzhjVar = zzj;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzi);
                                zzj = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
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
            zzi = zzcVar;
            zzfo.zza(zzc.class, zzcVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzd extends zzfo<zzd, zza> implements zzgy {
        private static final zzd zzf;
        private static volatile zzhj<zzd> zzg;
        private int zzc;
        private String zzd = "";
        private long zze;

        private zzd() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzd, zza> implements zzgy {
            private zza() {
                super(zzd.zzf);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(str);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzd) this.zza).zza(j);
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
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
        public final void zza(long j) {
            this.zzc |= 2;
            this.zze = j;
        }

        public static zza zza() {
            return zzf.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzhj<zzd> zzhjVar = zzg;
                    if (zzhjVar == null) {
                        synchronized (zzd.class) {
                            zzhjVar = zzg;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzf);
                                zzg = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
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
            zzf = zzdVar;
            zzfo.zza(zzd.class, zzdVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zze extends zzfo<zze, zza> implements zzgy {
        private static final zze zzj;
        private static volatile zzhj<zze> zzk;
        private int zzc;
        private long zzf;
        private float zzg;
        private double zzh;
        private String zzd = "";
        private String zze = "";
        private zzfx<zze> zzi = zzbp();

        private zze() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zze, zza> implements zzgy {
            private zza() {
                super(zze.zzj);
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzb(str);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzo();
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(j);
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzp();
                return this;
            }

            public final zza zza(double d) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(d);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzq();
                return this;
            }

            public final int zzd() {
                return ((zze) this.zza).zzl();
            }

            public final zza zza(zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zze((zze) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final zza zza(Iterable<? extends zze> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zza(iterable);
                return this;
            }

            public final zza zze() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zze) this.zza).zzt();
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final String zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 1;
            this.zzd = str;
        }

        public final boolean zzc() {
            return (this.zzc & 2) != 0;
        }

        public final String zzd() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo() {
            this.zzc &= -3;
            this.zze = zzj.zze;
        }

        public final boolean zze() {
            return (this.zzc & 4) != 0;
        }

        public final long zzf() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 4;
            this.zzf = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp() {
            this.zzc &= -5;
            this.zzf = 0L;
        }

        public final boolean zzg() {
            return (this.zzc & 8) != 0;
        }

        public final float zzh() {
            return this.zzg;
        }

        public final boolean zzi() {
            return (this.zzc & 16) != 0;
        }

        public final double zzj() {
            return this.zzh;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(double d) {
            this.zzc |= 16;
            this.zzh = d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzq() {
            this.zzc &= -17;
            this.zzh = 0.0d;
        }

        public final List<zze> zzk() {
            return this.zzi;
        }

        public final int zzl() {
            return this.zzi.size();
        }

        private final void zzs() {
            zzfx<zze> zzfxVar = this.zzi;
            if (!zzfxVar.zza()) {
                this.zzi = zzfo.zza(zzfxVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(zze zzeVar) {
            zzeVar.getClass();
            zzs();
            this.zzi.add(zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zze> iterable) {
            zzs();
            zzdw.zza(iterable, this.zzi);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzt() {
            this.zzi = zzbp();
        }

        public static zza zzm() {
            return zzj.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", zze.class});
                case 4:
                    return zzj;
                case 5:
                    zzhj<zze> zzhjVar = zzk;
                    if (zzhjVar == null) {
                        synchronized (zze.class) {
                            zzhjVar = zzk;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzj);
                                zzk = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zze zzeVar = new zze();
            zzj = zzeVar;
            zzfo.zza(zze.class, zzeVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzf extends zzfo<zzf, zza> implements zzgy {
        private static final zzf zzd;
        private static volatile zzhj<zzf> zze;
        private zzfx<zzg> zzc = zzbp();

        private zzf() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzf, zza> implements zzgy {
            private zza() {
                super(zzf.zzd);
            }

            public final zzg zza(int i) {
                return ((zzf) this.zza).zza(0);
            }

            public final zza zza(zzg.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzf) this.zza).zza((zzg) ((zzfo) zzaVar.zzv()));
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final List<zzg> zza() {
            return this.zzc;
        }

        public final zzg zza(int i) {
            return this.zzc.get(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzg zzgVar) {
            zzgVar.getClass();
            zzfx<zzg> zzfxVar = this.zzc;
            if (!zzfxVar.zza()) {
                this.zzc = zzfo.zza(zzfxVar);
            }
            this.zzc.add(zzgVar);
        }

        public static zza zzb() {
            return zzd.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzd, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzc", zzg.class});
                case 4:
                    return zzd;
                case 5:
                    zzhj<zzf> zzhjVar = zze;
                    if (zzhjVar == null) {
                        synchronized (zzf.class) {
                            zzhjVar = zze;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzd);
                                zze = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
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
            zzd = zzfVar;
            zzfo.zza(zzf.class, zzfVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzg extends zzfo<zzg, zza> implements zzgy {
        private static final zzg zzav;
        private static volatile zzhj<zzg> zzaw;
        private int zzaa;
        private boolean zzad;
        private int zzag;
        private int zzah;
        private int zzai;
        private long zzak;
        private long zzal;
        private int zzao;
        private zzh zzaq;
        private long zzas;
        private long zzat;
        private int zzc;
        private int zzd;
        private int zze;
        private long zzh;
        private long zzi;
        private long zzj;
        private long zzk;
        private long zzl;
        private int zzq;
        private long zzu;
        private long zzv;
        private boolean zzx;
        private long zzz;
        private zzfx<zzc> zzf = zzbp();
        private zzfx<zzk> zzg = zzbp();
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private String zzr = "";
        private String zzs = "";
        private String zzt = "";
        private String zzw = "";
        private String zzy = "";
        private String zzab = "";
        private String zzac = "";
        private zzfx<zza> zzae = zzbp();
        private String zzaf = "";
        private String zzaj = "";
        private String zzam = "";
        private String zzan = "";
        private String zzap = "";
        private zzfv zzar = zzbn();
        private String zzau = "";

        private zzg() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzg, zza> implements zzgy {
            private zza() {
                super(zzg.zzav);
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(1);
                return this;
            }

            public final List<zzc> zza() {
                return Collections.unmodifiableList(((zzg) this.zza).zzc());
            }

            public final int zzb() {
                return ((zzg) this.zza).zzd();
            }

            public final zzc zzb(int i) {
                return ((zzg) this.zza).zza(i);
            }

            public final zza zza(int i, zzc.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(i, (zzc) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final zza zza(zzc.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzc) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final zza zza(Iterable<? extends zzc> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(iterable);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbt();
                return this;
            }

            public final zza zzc(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(i);
                return this;
            }

            public final List<zzk> zzd() {
                return Collections.unmodifiableList(((zzg) this.zza).zze());
            }

            public final int zze() {
                return ((zzg) this.zza).zzf();
            }

            public final zzk zzd(int i) {
                return ((zzg) this.zza).zzb(i);
            }

            public final zza zza(int i, zzk zzkVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(i, zzkVar);
                return this;
            }

            public final zza zza(zzk zzkVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(zzkVar);
                return this;
            }

            public final zza zza(zzk.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzk) ((zzfo) zzaVar.zzv()));
                return this;
            }

            public final zza zzb(Iterable<? extends zzk> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(iterable);
                return this;
            }

            public final zza zze(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(i);
                return this;
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(j);
                return this;
            }

            public final long zzf() {
                return ((zzg) this.zza).zzj();
            }

            public final zza zzb(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(j);
                return this;
            }

            public final long zzg() {
                return ((zzg) this.zza).zzl();
            }

            public final zza zzc(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(j);
                return this;
            }

            public final zza zzd(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(j);
                return this;
            }

            public final zza zzh() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbv();
                return this;
            }

            public final zza zze(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(j);
                return this;
            }

            public final zza zzi() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbw();
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(str);
                return this;
            }

            public final zza zzf(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(i);
                return this;
            }

            public final zza zze(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zze(str);
                return this;
            }

            public final String zzj() {
                return ((zzg) this.zza).zzx();
            }

            public final zza zzf(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(str);
                return this;
            }

            public final zza zzg(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(str);
                return this;
            }

            public final zza zzf(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzf(j);
                return this;
            }

            public final zza zzg(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzg(j);
                return this;
            }

            public final zza zzh(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(str);
                return this;
            }

            public final zza zza(boolean z) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza(z);
                return this;
            }

            public final zza zzi(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(str);
                return this;
            }

            public final zza zzh(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(j);
                return this;
            }

            public final zza zzg(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzh(i);
                return this;
            }

            public final zza zzj(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(str);
                return this;
            }

            public final zza zzk() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbx();
                return this;
            }

            public final String zzl() {
                return ((zzg) this.zza).zzam();
            }

            public final zza zzk(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzk(str);
                return this;
            }

            public final zza zzb(boolean z) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzb(z);
                return this;
            }

            public final zza zzc(Iterable<? extends zza> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzc(iterable);
                return this;
            }

            public final zza zzm() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzby();
                return this;
            }

            public final zza zzl(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzl(str);
                return this;
            }

            public final zza zzh(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(i);
                return this;
            }

            public final zza zzm(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzm(str);
                return this;
            }

            public final zza zzi(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzi(j);
                return this;
            }

            public final zza zzj(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(j);
                return this;
            }

            public final zza zzn(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzn(null);
                return this;
            }

            public final zza zzn() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzbz();
                return this;
            }

            public final zza zzi(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzj(i);
                return this;
            }

            public final zza zzo(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzo(str);
                return this;
            }

            public final zza zza(zzh.zzb zzbVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zza((zzh) ((zzfo) zzbVar.zzv()));
                return this;
            }

            public final zza zzd(Iterable<? extends Integer> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzd(iterable);
                return this;
            }

            public final zza zzk(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzk(j);
                return this;
            }

            public final zza zzl(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzl(j);
                return this;
            }

            public final String zzo() {
                return ((zzg) this.zza).zzbe();
            }

            public final zza zzp(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzg) this.zza).zzp(str);
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(int i) {
            this.zzc |= 1;
            this.zze = i;
        }

        public final List<zzc> zzc() {
            return this.zzf;
        }

        public final int zzd() {
            return this.zzf.size();
        }

        public final zzc zza(int i) {
            return this.zzf.get(i);
        }

        private final void zzbs() {
            zzfx<zzc> zzfxVar = this.zzf;
            if (!zzfxVar.zza()) {
                this.zzf = zzfo.zza(zzfxVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zzc zzcVar) {
            zzcVar.getClass();
            zzbs();
            this.zzf.set(i, zzcVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzc zzcVar) {
            zzcVar.getClass();
            zzbs();
            this.zzf.add(zzcVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends zzc> iterable) {
            zzbs();
            zzdw.zza(iterable, this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbt() {
            this.zzf = zzbp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(int i) {
            zzbs();
            this.zzf.remove(i);
        }

        public final List<zzk> zze() {
            return this.zzg;
        }

        public final int zzf() {
            return this.zzg.size();
        }

        public final zzk zzb(int i) {
            return this.zzg.get(i);
        }

        private final void zzbu() {
            zzfx<zzk> zzfxVar = this.zzg;
            if (!zzfxVar.zza()) {
                this.zzg = zzfo.zza(zzfxVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(int i, zzk zzkVar) {
            zzkVar.getClass();
            zzbu();
            this.zzg.set(i, zzkVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzk zzkVar) {
            zzkVar.getClass();
            zzbu();
            this.zzg.add(zzkVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends zzk> iterable) {
            zzbu();
            zzdw.zza(iterable, this.zzg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(int i) {
            zzbu();
            this.zzg.remove(i);
        }

        public final boolean zzg() {
            return (this.zzc & 2) != 0;
        }

        public final long zzh() {
            return this.zzh;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 2;
            this.zzh = j;
        }

        public final boolean zzi() {
            return (this.zzc & 4) != 0;
        }

        public final long zzj() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j) {
            this.zzc |= 4;
            this.zzi = j;
        }

        public final boolean zzk() {
            return (this.zzc & 8) != 0;
        }

        public final long zzl() {
            return this.zzj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(long j) {
            this.zzc |= 8;
            this.zzj = j;
        }

        public final boolean zzm() {
            return (this.zzc & 16) != 0;
        }

        public final long zzn() {
            return this.zzk;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(long j) {
            this.zzc |= 16;
            this.zzk = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbv() {
            this.zzc &= -17;
            this.zzk = 0L;
        }

        public final boolean zzo() {
            return (this.zzc & 32) != 0;
        }

        public final long zzp() {
            return this.zzl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(long j) {
            this.zzc |= 32;
            this.zzl = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbw() {
            this.zzc &= -33;
            this.zzl = 0L;
        }

        public final String zzq() {
            return this.zzm;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 64;
            this.zzm = str;
        }

        public final String zzr() {
            return this.zzn;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 128;
            this.zzn = str;
        }

        public final String zzs() {
            return this.zzo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            str.getClass();
            this.zzc |= 256;
            this.zzo = str;
        }

        public final String zzt() {
            return this.zzp;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            str.getClass();
            this.zzc |= 512;
            this.zzp = str;
        }

        public final boolean zzu() {
            return (this.zzc & 1024) != 0;
        }

        public final int zzv() {
            return this.zzq;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(int i) {
            this.zzc |= 1024;
            this.zzq = i;
        }

        public final String zzw() {
            return this.zzr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            str.getClass();
            this.zzc |= 2048;
            this.zzr = str;
        }

        public final String zzx() {
            return this.zzs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            str.getClass();
            this.zzc |= 4096;
            this.zzs = str;
        }

        public final String zzy() {
            return this.zzt;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            str.getClass();
            this.zzc |= 8192;
            this.zzt = str;
        }

        public final boolean zzz() {
            return (this.zzc & 16384) != 0;
        }

        public final long zzaa() {
            return this.zzu;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(long j) {
            this.zzc |= 16384;
            this.zzu = j;
        }

        public final boolean zzab() {
            return (this.zzc & 32768) != 0;
        }

        public final long zzac() {
            return this.zzv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzc |= 32768;
            this.zzv = j;
        }

        public final String zzad() {
            return this.zzw;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(String str) {
            str.getClass();
            this.zzc |= 65536;
            this.zzw = str;
        }

        public final boolean zzae() {
            return (this.zzc & 131072) != 0;
        }

        public final boolean zzaf() {
            return this.zzx;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 131072;
            this.zzx = z;
        }

        public final String zzag() {
            return this.zzy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(String str) {
            str.getClass();
            this.zzc |= 262144;
            this.zzy = str;
        }

        public final boolean zzah() {
            return (this.zzc & 524288) != 0;
        }

        public final long zzai() {
            return this.zzz;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(long j) {
            this.zzc |= 524288;
            this.zzz = j;
        }

        public final boolean zzaj() {
            return (this.zzc & 1048576) != 0;
        }

        public final int zzak() {
            return this.zzaa;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(int i) {
            this.zzc |= 1048576;
            this.zzaa = i;
        }

        public final String zzal() {
            return this.zzab;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(String str) {
            str.getClass();
            this.zzc |= 2097152;
            this.zzab = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbx() {
            this.zzc &= -2097153;
            this.zzab = zzav.zzab;
        }

        public final String zzam() {
            return this.zzac;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(String str) {
            str.getClass();
            this.zzc |= 4194304;
            this.zzac = str;
        }

        public final boolean zzan() {
            return (this.zzc & 8388608) != 0;
        }

        public final boolean zzao() {
            return this.zzad;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzc |= 8388608;
            this.zzad = z;
        }

        public final List<zza> zzap() {
            return this.zzae;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(Iterable<? extends zza> iterable) {
            zzfx<zza> zzfxVar = this.zzae;
            if (!zzfxVar.zza()) {
                this.zzae = zzfo.zza(zzfxVar);
            }
            zzdw.zza(iterable, this.zzae);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzby() {
            this.zzae = zzbp();
        }

        public final String zzaq() {
            return this.zzaf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(String str) {
            str.getClass();
            this.zzc |= 16777216;
            this.zzaf = str;
        }

        public final boolean zzar() {
            return (this.zzc & 33554432) != 0;
        }

        public final int zzas() {
            return this.zzag;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(int i) {
            this.zzc |= 33554432;
            this.zzag = i;
        }

        public final String zzat() {
            return this.zzaj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(String str) {
            str.getClass();
            this.zzc |= 268435456;
            this.zzaj = str;
        }

        public final boolean zzau() {
            return (this.zzc & 536870912) != 0;
        }

        public final long zzav() {
            return this.zzak;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(long j) {
            this.zzc |= 536870912;
            this.zzak = j;
        }

        public final boolean zzaw() {
            return (this.zzc & 1073741824) != 0;
        }

        public final long zzax() {
            return this.zzal;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(long j) {
            this.zzc |= 1073741824;
            this.zzal = j;
        }

        public final String zzay() {
            return this.zzam;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(String str) {
            str.getClass();
            this.zzc |= Integer.MIN_VALUE;
            this.zzam = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbz() {
            this.zzc &= Integer.MAX_VALUE;
            this.zzam = zzav.zzam;
        }

        public final boolean zzaz() {
            return (this.zzd & 2) != 0;
        }

        public final int zzba() {
            return this.zzao;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(int i) {
            this.zzd |= 2;
            this.zzao = i;
        }

        public final String zzbb() {
            return this.zzap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(String str) {
            str.getClass();
            this.zzd |= 4;
            this.zzap = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzh zzhVar) {
            zzhVar.getClass();
            this.zzaq = zzhVar;
            this.zzd |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends Integer> iterable) {
            zzfv zzfvVar = this.zzar;
            if (!zzfvVar.zza()) {
                int size = zzfvVar.size();
                this.zzar = zzfvVar.zzb(size == 0 ? 10 : size << 1);
            }
            zzdw.zza(iterable, this.zzar);
        }

        public final boolean zzbc() {
            return (this.zzd & 16) != 0;
        }

        public final long zzbd() {
            return this.zzas;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(long j) {
            this.zzd |= 16;
            this.zzas = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(long j) {
            this.zzd |= 32;
            this.zzat = j;
        }

        public final String zzbe() {
            return this.zzau;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(String str) {
            str.getClass();
            this.zzd |= 64;
            this.zzau = str;
        }

        public static zza zzbf() {
            return zzav.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzav, "\u0001+\u0000\u0002\u00012+\u0000\u0004\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဂ\u0001\u0005ဂ\u0002\u0006ဂ\u0003\u0007ဂ\u0005\bဈ\u0006\tဈ\u0007\nဈ\b\u000bဈ\t\fင\n\rဈ\u000b\u000eဈ\f\u0010ဈ\r\u0011ဂ\u000e\u0012ဂ\u000f\u0013ဈ\u0010\u0014ဇ\u0011\u0015ဈ\u0012\u0016ဂ\u0013\u0017င\u0014\u0018ဈ\u0015\u0019ဈ\u0016\u001aဂ\u0004\u001cဇ\u0017\u001d\u001b\u001eဈ\u0018\u001fင\u0019 င\u001a!င\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဈ\u001f&ဈ 'င!)ဈ\",ဉ#-\u001d.ဂ$/ဂ%2ဈ&", new Object[]{"zzc", "zzd", "zze", "zzf", zzc.class, "zzg", zzk.class, "zzh", "zzi", "zzj", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzk", "zzad", "zzae", zza.class, "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau"});
                case 4:
                    return zzav;
                case 5:
                    zzhj<zzg> zzhjVar = zzaw;
                    if (zzhjVar == null) {
                        synchronized (zzg.class) {
                            zzhjVar = zzaw;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzav);
                                zzaw = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzg zzgVar = new zzg();
            zzav = zzgVar;
            zzfo.zza(zzg.class, zzgVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzh extends zzfo<zzh, zzb> implements zzgy {
        private static final zzh zzf;
        private static volatile zzhj<zzh> zzg;
        private int zzc;
        private int zzd = 1;
        private zzfx<zzd> zze = zzbp();

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public enum zza implements zzfq {
            RADS(1),
            PROVISIONING(2);
            
            private static final zzft<zza> zzc = new zzce();
            private final int zzd;

            @Override // com.google.android.gms.internal.measurement.zzfq
            public final int zza() {
                return this.zzd;
            }

            public static zza zza(int i) {
                if (i == 1) {
                    return RADS;
                }
                if (i != 2) {
                    return null;
                }
                return PROVISIONING;
            }

            public static zzfs zzb() {
                return zzcd.zza;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzd + " name=" + name() + '>';
            }

            zza(int i) {
                this.zzd = i;
            }
        }

        private zzh() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zzb extends zzfo.zza<zzh, zzb> implements zzgy {
            private zzb() {
                super(zzh.zzf);
            }

            public final zzb zza(zzd.zza zzaVar) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzh) this.zza).zza((zzd) ((zzfo) zzaVar.zzv()));
                return this;
            }

            /* synthetic */ zzb(zzcc zzccVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzd zzdVar) {
            zzdVar.getClass();
            zzfx<zzd> zzfxVar = this.zze;
            if (!zzfxVar.zza()) {
                this.zze = zzfo.zza(zzfxVar);
            }
            this.zze.add(zzdVar);
        }

        public static zzb zza() {
            return zzf.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zzb(null);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zzc", "zzd", zza.zzb(), "zze", zzd.class});
                case 4:
                    return zzf;
                case 5:
                    zzhj<zzh> zzhjVar = zzg;
                    if (zzhjVar == null) {
                        synchronized (zzh.class) {
                            zzhjVar = zzg;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzf);
                                zzg = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
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
            zzf = zzhVar;
            zzfo.zza(zzh.class, zzhVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzi extends zzfo<zzi, zza> implements zzgy {
        private static final zzi zzg;
        private static volatile zzhj<zzi> zzh;
        private zzfu zzc = zzbo();
        private zzfu zzd = zzbo();
        private zzfx<zzb> zze = zzbp();
        private zzfx<zzj> zzf = zzbp();

        private zzi() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzi, zza> implements zzgy {
            private zza() {
                super(zzi.zzg);
            }

            public final zza zza(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zza(iterable);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzl();
                return this;
            }

            public final zza zzb(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzb(iterable);
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzm();
                return this;
            }

            public final zza zzc(Iterable<? extends zzb> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzc(iterable);
                return this;
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzd(i);
                return this;
            }

            public final zza zzd(Iterable<? extends zzj> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zzd(iterable);
                return this;
            }

            public final zza zzb(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzi) this.zza).zze(i);
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final List<Long> zza() {
            return this.zzc;
        }

        public final int zzb() {
            return this.zzc.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            zzfu zzfuVar = this.zzc;
            if (!zzfuVar.zza()) {
                this.zzc = zzfo.zza(zzfuVar);
            }
            zzdw.zza(iterable, this.zzc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzc = zzbo();
        }

        public final List<Long> zzc() {
            return this.zzd;
        }

        public final int zzd() {
            return this.zzd.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends Long> iterable) {
            zzfu zzfuVar = this.zzd;
            if (!zzfuVar.zza()) {
                this.zzd = zzfo.zza(zzfuVar);
            }
            zzdw.zza(iterable, this.zzd);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzd = zzbo();
        }

        public final List<zzb> zze() {
            return this.zze;
        }

        public final int zzf() {
            return this.zze.size();
        }

        public final zzb zza(int i) {
            return this.zze.get(i);
        }

        private final void zzn() {
            zzfx<zzb> zzfxVar = this.zze;
            if (!zzfxVar.zza()) {
                this.zze = zzfo.zza(zzfxVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(Iterable<? extends zzb> iterable) {
            zzn();
            zzdw.zza(iterable, this.zze);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(int i) {
            zzn();
            this.zze.remove(i);
        }

        public final List<zzj> zzg() {
            return this.zzf;
        }

        public final int zzh() {
            return this.zzf.size();
        }

        public final zzj zzb(int i) {
            return this.zzf.get(i);
        }

        private final void zzo() {
            zzfx<zzj> zzfxVar = this.zzf;
            if (!zzfxVar.zza()) {
                this.zzf = zzfo.zza(zzfxVar);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(Iterable<? extends zzj> iterable) {
            zzo();
            zzdw.zza(iterable, this.zzf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(int i) {
            zzo();
            this.zzf.remove(i);
        }

        public static zza zzi() {
            return zzg.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzg, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zzc", "zzd", "zze", zzb.class, "zzf", zzj.class});
                case 4:
                    return zzg;
                case 5:
                    zzhj<zzi> zzhjVar = zzh;
                    if (zzhjVar == null) {
                        synchronized (zzi.class) {
                            zzhjVar = zzh;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzg);
                                zzh = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzi zzj() {
            return zzg;
        }

        static {
            zzi zziVar = new zzi();
            zzg = zziVar;
            zzfo.zza(zzi.class, zziVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzj extends zzfo<zzj, zza> implements zzgy {
        private static final zzj zzf;
        private static volatile zzhj<zzj> zzg;
        private int zzc;
        private int zzd;
        private zzfu zze = zzbo();

        private zzj() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzj, zza> implements zzgy {
            private zza() {
                super(zzj.zzf);
            }

            public final zza zza(int i) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzj) this.zza).zzb(i);
                return this;
            }

            public final zza zza(Iterable<? extends Long> iterable) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzj) this.zza).zza(iterable);
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final int zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(int i) {
            this.zzc |= 1;
            this.zzd = i;
        }

        public final List<Long> zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zze.size();
        }

        public final long zza(int i) {
            return this.zze.zzb(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends Long> iterable) {
            zzfu zzfuVar = this.zze;
            if (!zzfuVar.zza()) {
                this.zze = zzfo.zza(zzfuVar);
            }
            zzdw.zza(iterable, this.zze);
        }

        public static zza zze() {
            return zzf.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zzc", "zzd", "zze"});
                case 4:
                    return zzf;
                case 5:
                    zzhj<zzj> zzhjVar = zzg;
                    if (zzhjVar == null) {
                        synchronized (zzj.class) {
                            zzhjVar = zzg;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzf);
                                zzg = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
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
            zzf = zzjVar;
            zzfo.zza(zzj.class, zzjVar);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzk extends zzfo<zzk, zza> implements zzgy {
        private static final zzk zzj;
        private static volatile zzhj<zzk> zzk;
        private int zzc;
        private long zzd;
        private String zze = "";
        private String zzf = "";
        private long zzg;
        private float zzh;
        private double zzi;

        private zzk() {
        }

        /* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
        /* loaded from: classes.dex */
        public static final class zza extends zzfo.zza<zzk, zza> implements zzgy {
            private zza() {
                super(zzk.zzj);
            }

            public final zza zza(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(j);
                return this;
            }

            public final zza zza(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzb(str);
                return this;
            }

            public final zza zza() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzl();
                return this;
            }

            public final zza zzb(long j) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzb(j);
                return this;
            }

            public final zza zzb() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzm();
                return this;
            }

            public final zza zza(double d) {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zza(d);
                return this;
            }

            public final zza zzc() {
                if (this.zzb) {
                    zzq();
                    this.zzb = false;
                }
                ((zzk) this.zza).zzn();
                return this;
            }

            /* synthetic */ zza(zzcc zzccVar) {
                this();
            }
        }

        public final boolean zza() {
            return (this.zzc & 1) != 0;
        }

        public final long zzb() {
            return this.zzd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(long j) {
            this.zzc |= 1;
            this.zzd = j;
        }

        public final String zzc() {
            return this.zze;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            str.getClass();
            this.zzc |= 2;
            this.zze = str;
        }

        public final boolean zzd() {
            return (this.zzc & 4) != 0;
        }

        public final String zze() {
            return this.zzf;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            str.getClass();
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl() {
            this.zzc &= -5;
            this.zzf = zzj.zzf;
        }

        public final boolean zzf() {
            return (this.zzc & 8) != 0;
        }

        public final long zzg() {
            return this.zzg;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(long j) {
            this.zzc |= 8;
            this.zzg = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm() {
            this.zzc &= -9;
            this.zzg = 0L;
        }

        public final boolean zzh() {
            return (this.zzc & 32) != 0;
        }

        public final double zzi() {
            return this.zzi;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(double d) {
            this.zzc |= 32;
            this.zzi = d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn() {
            this.zzc &= -33;
            this.zzi = 0.0d;
        }

        public static zza zzj() {
            return zzj.zzbk();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.measurement.zzfo
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzcc.zza[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(null);
                case 3:
                    return zza(zzj, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzj;
                case 5:
                    zzhj<zzk> zzhjVar = zzk;
                    if (zzhjVar == null) {
                        synchronized (zzk.class) {
                            zzhjVar = zzk;
                            if (zzhjVar == null) {
                                zzhjVar = new zzfo.zzc<>(zzj);
                                zzk = zzhjVar;
                            }
                        }
                    }
                    return zzhjVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzk zzkVar = new zzk();
            zzj = zzkVar;
            zzfo.zza(zzk.class, zzkVar);
        }
    }
}
