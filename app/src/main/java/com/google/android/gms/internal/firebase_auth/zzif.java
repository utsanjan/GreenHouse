package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;
import com.google.android.gms.internal.firebase_auth.zzif.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzif<MessageType extends zzif<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzgn<MessageType, BuilderType> {
    private static Map<Object, zzif<?, ?>> zzd = new ConcurrentHashMap();
    protected zzky zzb = zzky.zza();
    private int zzc = -1;

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzif<MessageType, BuilderType> implements zzjp {
        protected zzhv<zze> zzc = zzhv.zza();
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static class zzd<ContainingType extends zzjn, Type> extends zzhq<ContainingType, Type> {
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    static final class zze implements zzhx<zze> {
        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final int zza() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final zzlm zzb() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final zzlt zzc() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final boolean zzd() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final boolean zze() {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final zzjq zza(zzjq zzjqVar, zzjn zzjnVar) {
            throw new NoSuchMethodError();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        public final zzjt zza(zzjt zzjtVar, zzjt zzjtVar2) {
            throw new NoSuchMethodError();
        }

        @Override // java.lang.Comparable
        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
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

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static class zzc<T extends zzif<T, ?>> extends zzgo<T> {
        private final T zza;

        public zzc(T t) {
            this.zza = t;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzjx
        public final /* synthetic */ Object zza(zzhh zzhhVar, zzhs zzhsVar) throws zzin {
            return zzif.zza(this.zza, zzhhVar, zzhsVar);
        }
    }

    public String toString() {
        return zzjs.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        this.zza = zzjz.zza().zza((zzjz) this).zza(this);
        return this.zza;
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static abstract class zza<MessageType extends zzif<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzgm<MessageType, BuilderType> {
        protected MessageType zza;
        protected boolean zzb = false;
        private final MessageType zzc;

        /* JADX INFO: Access modifiers changed from: protected */
        public zza(MessageType messagetype) {
            this.zzc = messagetype;
            this.zza = (MessageType) ((zzif) messagetype.zza(zzf.zzd, null, null));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void zzb() {
            MessageType messagetype = (MessageType) ((zzif) this.zza.zza(zzf.zzd, null, null));
            zza(messagetype, this.zza);
            this.zza = messagetype;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzjp
        public final boolean b_() {
            return zzif.zza(this.zza, false);
        }

        /* renamed from: zzd */
        public MessageType zzf() {
            if (this.zzb) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzjz.zza().zza((zzjz) messagetype).zzc(messagetype);
            this.zzb = true;
            return this.zza;
        }

        /* renamed from: zze */
        public final MessageType zzg() {
            MessageType messagetype = (MessageType) ((zzif) zzf());
            if (messagetype.b_()) {
                return messagetype;
            }
            throw new zzkx(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzb) {
                zzb();
                this.zzb = false;
            }
            zza(this.zza, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzjz.zza().zza((zzjz) messagetype).zzb(messagetype, messagetype2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.firebase_auth.zzgm
        protected final /* synthetic */ zzgm zza(zzgn zzgnVar) {
            return zza((zza<MessageType, BuilderType>) ((zzif) zzgnVar));
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzgm
        public final /* synthetic */ zzgm zza() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzjp
        public final /* synthetic */ zzjn h_() {
            return this.zzc;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.firebase_auth.zzgm
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzc.zza(zzf.zze, null, null);
            zzaVar.zza((zza) ((zzif) zzf()));
            return zzaVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzjz.zza().zza((zzjz) this).zza(this, (zzif) obj);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <MessageType extends zzif<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzz() {
        return (BuilderType) ((zza) zza(zzf.zze, (Object) null, (Object) null));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjp
    public final boolean b_() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgn
    final int zzy() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzgn
    final void zzb(int i) {
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjn
    public final void zza(zzhm zzhmVar) throws IOException {
        zzjz.zza().zza((zzjz) this).zza((zzkd) this, (zzls) zzhp.zza(zzhmVar));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjn
    public final int zzaa() {
        if (this.zzc == -1) {
            this.zzc = zzjz.zza().zza((zzjz) this).zzb(this);
        }
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzif<?, ?>> T zza(Class<T> cls) {
        zzif<?, ?> zzifVar = zzd.get(cls);
        if (zzifVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzifVar = zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzifVar == null) {
            zzifVar = (T) ((zzif) ((zzif) zzlf.zza(cls)).zza(zzf.zzf, (Object) null, (Object) null));
            if (zzifVar != null) {
                zzd.put(cls, zzifVar);
            } else {
                throw new IllegalStateException();
            }
        }
        return (T) zzifVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzif<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zza(zzjn zzjnVar, String str, Object[] objArr) {
        return new zzkb(zzjnVar, str, objArr);
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

    protected static final <T extends zzif<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzf.zza, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzd2 = zzjz.zza().zza((zzjz) t).zzd(t);
        if (z) {
            t.zza(zzf.zzb, zzd2 ? t : null, null);
        }
        return zzd2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzim zzab() {
        return zzig.zzd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzio<E> zzac() {
        return zzkc.zzd();
    }

    static <T extends zzif<T, ?>> T zza(T t, zzhh zzhhVar, zzhs zzhsVar) throws zzin {
        T t2 = (T) ((zzif) t.zza(zzf.zzd, null, null));
        try {
            zzkd zza2 = zzjz.zza().zza((zzjz) t2);
            zza2.zza(t2, zzhk.zza(zzhhVar), zzhsVar);
            zza2.zzc(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzin) {
                throw ((zzin) e.getCause());
            }
            throw new zzin(e.getMessage()).zza(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzin) {
                throw ((zzin) e2.getCause());
            }
            throw e2;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjn
    public final /* synthetic */ zzjq zzad() {
        zza zzaVar = (zza) zza(zzf.zze, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjn
    public final /* synthetic */ zzjq zzae() {
        return (zza) zza(zzf.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjp
    public final /* synthetic */ zzjn h_() {
        return (zzif) zza(zzf.zzf, (Object) null, (Object) null);
    }
}
