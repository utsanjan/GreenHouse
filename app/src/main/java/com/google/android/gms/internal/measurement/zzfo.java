package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfo.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzfo<MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdw<MessageType, BuilderType> {
    private static Map<Object, zzfo<?, ?>> zzd = new ConcurrentHashMap();
    protected zzig zzb = zzig.zza();
    private int zzc = -1;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    public static class zzc<T extends zzfo<T, ?>> extends zzdx<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    public static class zzd<ContainingType extends zzgw, Type> extends zzez<ContainingType, Type> {
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    static final class zze implements zzfg<zze> {
        @Override // com.google.android.gms.internal.measurement.zzfg
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzfg
        public final zziu zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzfg
        public final zzjb zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzfg
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzfg
        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzfg
        public final zzgz zza(zzgz zzgzVar, zzgw zzgwVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.measurement.zzfg
        public final zzhf zza(zzhf zzhfVar, zzhf zzhfVar2) {
            throw new NoSuchMethodError();
        }

        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    public static final class zzf {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzl = {1, 2, 3, 4, 5, 6, 7};
        public static final int zzh = 1;
        public static final int zzi = 2;
        private static final /* synthetic */ int[] zzm = {1, 2};
        public static final int zzj = 1;
        public static final int zzk = 2;
        private static final /* synthetic */ int[] zzn = {1, 2};

        public static int[] zza() {
            return (int[]) zzl.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzfo<MessageType, BuilderType> implements zzgy {
        protected zzfe<zze> zzc = zzfe.zza();

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzfe<zze> zza() {
            if (this.zzc.zzc()) {
                this.zzc = (zzfe) this.zzc.clone();
            }
            return this.zzc;
        }
    }

    public String toString() {
        return zzhb.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        this.zza = zzhl.zza().zza((zzhl) this).zza(this);
        return this.zza;
    }

    /* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
    /* loaded from: classes.dex */
    public static abstract class zza<MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdv<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc;

        /* JADX INFO: Access modifiers changed from: protected */
        public zza(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (MessageType) ((zzfo) messagetype.zza(zzf.zzd, null, null));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void zzq() {
            MessageType messagetype = (MessageType) ((zzfo) this.zza.zza(zzf.zzd, null, null));
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override // com.google.android.gms.internal.measurement.zzgy
        public final boolean g_() {
            return zzfo.zza(this.zza, false);
        }

        /* renamed from: zzs */
        public MessageType zzu() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzhl.zza().zza((zzhl) messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        /* renamed from: zzt */
        public final MessageType zzv() {
            MessageType messagetype = (MessageType) ((zzfo) zzu());
            if (messagetype.g_()) {
                return messagetype;
            }
            throw new zzie(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzhl.zza().zza((zzhl) messagetype).zzb(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzfb zzfbVar) throws zzfw {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            try {
                zzhl.zza().zza((zzhl) this.zza).zza(this.zza, bArr, 0, i2 + 0, new zzeb(zzfbVar));
                return this;
            } catch (zzfw e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException e3) {
                throw zzfw.zza();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: zzb */
        public final BuilderType zza(zzes zzesVar, zzfb zzfbVar) throws IOException {
            if (this.zzb) {
                zzq();
                this.zzb = false;
            }
            try {
                zzhl.zza().zza((zzhl) this.zza).zza(this.zza, zzet.zza(zzesVar), zzfbVar);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.measurement.zzdv
        protected final /* synthetic */ zzdv zza(zzdw zzdwVar) {
            return zza((zza<MessageType, BuilderType>) ((zzfo) zzdwVar));
        }

        @Override // com.google.android.gms.internal.measurement.zzdv
        public final /* synthetic */ zzdv zza(byte[] bArr, int i, int i2, zzfb zzfbVar) throws zzfw {
            return zzb(bArr, 0, i2, zzfbVar);
        }

        @Override // com.google.android.gms.internal.measurement.zzdv
        public final /* synthetic */ zzdv zza(byte[] bArr, int i, int i2) throws zzfw {
            return zzb(bArr, 0, i2, zzfb.zza());
        }

        @Override // com.google.android.gms.internal.measurement.zzdv
        public final /* synthetic */ zzdv zzp() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.measurement.zzgy
        public final /* synthetic */ zzgw h_() {
            return this.zzc;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.measurement.zzdv
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzc.zza(zzf.zze, null, null);
            zzaVar.zza((zza) ((zzfo) zzu()));
            return zzaVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzhl.zza().zza((zzhl) this).zza(this, (zzfo) obj);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzbk() {
        return (BuilderType) ((zza) zza(zzf.zze, (Object) null, (Object) null));
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final boolean g_() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    public final BuilderType zzbl() {
        BuilderType buildertype = (BuilderType) ((zza) zza(zzf.zze, (Object) null, (Object) null));
        buildertype.zza(this);
        return buildertype;
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    final int zzbj() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzdw
    final void zzc(int i) {
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.measurement.zzgw
    public final void zza(zzev zzevVar) throws IOException {
        zzhl.zza().zza((zzhl) this).zza((zzhp) this, (zzja) zzey.zza(zzevVar));
    }

    @Override // com.google.android.gms.internal.measurement.zzgw
    public final int zzbm() {
        if (this.zzc == -1) {
            this.zzc = zzhl.zza().zza((zzhl) this).zzb(this);
        }
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzfo<?, ?>> T zza(Class<T> cls) {
        zzfo<?, ?> zzfoVar = zzd.get(cls);
        if (zzfoVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzfoVar = zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzfoVar == null) {
            zzfoVar = (T) ((zzfo) ((zzfo) zzin.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null));
            if (zzfoVar != null) {
                zzd.put(cls, zzfoVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzfoVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzfo<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zza(zzgw zzgwVar, String str, Object[] objArr) {
        return new zzhn(zzgwVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzfo<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zza, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzd2 = zzhl.zza().zza((zzhl) t).zzd(t);
        if (z) {
            t.zza(zzf.zzb, zzd2 ? t : null, null);
        }
        return zzd2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzfv zzbn() {
        return zzfp.zzd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzfu zzbo() {
        return zzgk.zzd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzfu zza(zzfu zzfuVar) {
        int size = zzfuVar.size();
        return zzfuVar.zzc(size == 0 ? 10 : size << 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzfx<E> zzbp() {
        return zzhk.zzd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzfx<E> zza(zzfx<E> zzfxVar) {
        int size = zzfxVar.size();
        return zzfxVar.zza(size == 0 ? 10 : size << 1);
    }

    @Override // com.google.android.gms.internal.measurement.zzgw
    public final /* synthetic */ zzgz zzbq() {
        zza zzaVar = (zza) zza(zzf.zze, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzgw
    public final /* synthetic */ zzgz zzbr() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.measurement.zzgy
    public final /* synthetic */ zzgw h_() {
        return (zzfo) zza(zzf.zzf, (Object) null, (Object) null);
    }
}
