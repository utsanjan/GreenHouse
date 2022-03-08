package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public final class DynamiteModule {
    private static Boolean zziu;
    private static zzj zziv;
    private static zzl zziw;
    private static String zzix;
    private final Context zzjc;
    private static int zziy = -1;
    private static final ThreadLocal<zza> zziz = new ThreadLocal<>();
    private static final VersionPolicy.zzb zzja = new zza();
    public static final VersionPolicy PREFER_REMOTE = new zzc();
    public static final VersionPolicy PREFER_LOCAL = new zzb();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zze();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzd();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzg();
    private static final VersionPolicy zzjb = new zzf();

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public interface VersionPolicy {

        /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
        /* loaded from: classes.dex */
        public static class zza {
            public int zzjg = 0;
            public int zzjh = 0;
            public int zzji = 0;
        }

        /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
        /* loaded from: classes.dex */
        public interface zzb {
            int getLocalVersion(Context context, String str);

            int zza(Context context, String str, boolean z) throws LoadingException;
        }

        zza zza(Context context, String str, zzb zzbVar) throws LoadingException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public static class zza {
        public Cursor zzjd;

        private zza() {
        }

        /* synthetic */ zza(zza zzaVar) {
            this();
        }
    }

    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        zza zzaVar = zziz.get();
        zza zzaVar2 = new zza(null);
        zziz.set(zzaVar2);
        try {
            VersionPolicy.zza zza2 = versionPolicy.zza(context, str, zzja);
            int i = zza2.zzjg;
            int i2 = zza2.zzjh;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            if (zza2.zzji == 0 || ((zza2.zzji == -1 && zza2.zzjg == 0) || (zza2.zzji == 1 && zza2.zzjh == 0))) {
                int i3 = zza2.zzjg;
                int i4 = zza2.zzjh;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i3);
                sb2.append(" and remote version is ");
                sb2.append(i4);
                sb2.append(".");
                throw new LoadingException(sb2.toString(), (zza) null);
            } else if (zza2.zzji == -1) {
                DynamiteModule zze = zze(context, str);
                if (zzaVar2.zzjd != null) {
                    zzaVar2.zzjd.close();
                }
                zziz.set(zzaVar);
                return zze;
            } else if (zza2.zzji == 1) {
                try {
                    DynamiteModule zza3 = zza(context, str, zza2.zzjh);
                    if (zzaVar2.zzjd != null) {
                        zzaVar2.zzjd.close();
                    }
                    zziz.set(zzaVar);
                    return zza3;
                } catch (LoadingException e) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                    if (zza2.zzjg == 0 || versionPolicy.zza(context, str, new zzb(zza2.zzjg, 0)).zzji != -1) {
                        throw new LoadingException("Remote load failed. No local fallback found.", e, null);
                    }
                    DynamiteModule zze2 = zze(context, str);
                    if (zzaVar2.zzjd != null) {
                        zzaVar2.zzjd.close();
                    }
                    zziz.set(zzaVar);
                    return zze2;
                }
            } else {
                int i5 = zza2.zzji;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i5);
                throw new LoadingException(sb3.toString(), (zza) null);
            }
        } catch (Throwable th) {
            if (zzaVar2.zzjd != null) {
                zzaVar2.zzjd.close();
            }
            zziz.set(zzaVar);
            throw th;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public static class LoadingException extends Exception {
        private LoadingException(String str) {
            super(str);
        }

        private LoadingException(String str, Throwable th) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, zza zzaVar) {
            this(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zza zzaVar) {
            this(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public static class zzb implements VersionPolicy.zzb {
        private final int zzje;
        private final int zzjf = 0;

        public zzb(int i, int i2) {
            this.zzje = i;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
        public final int zza(Context context, String str, boolean z) {
            return 0;
        }

        @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zzb
        public final int getLocalVersion(Context context, String str) {
            return this.zzje;
        }
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String valueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException e) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String valueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", valueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zza(Context context, String str, boolean z) {
        Class<?> loadClass;
        Field declaredField;
        ClassLoader classLoader;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zziu;
                if (bool == null) {
                    try {
                        loadClass = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName());
                        declaredField = loadClass.getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        String valueOf = String.valueOf(e);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(valueOf);
                        Log.w("DynamiteModule", sb.toString());
                        bool = Boolean.FALSE;
                    }
                    synchronized (loadClass) {
                        ClassLoader classLoader2 = (ClassLoader) declaredField.get(null);
                        if (classLoader2 != null) {
                            if (classLoader2 == ClassLoader.getSystemClassLoader()) {
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    zza(classLoader2);
                                } catch (LoadingException e2) {
                                }
                                bool = Boolean.TRUE;
                            }
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                            bool = Boolean.FALSE;
                        } else {
                            try {
                                int zzc = zzc(context, str, z);
                                if (zzix != null && !zzix.isEmpty()) {
                                    if (Build.VERSION.SDK_INT >= 29) {
                                        classLoader = new DelegateLastClassLoader(zzix, ClassLoader.getSystemClassLoader());
                                    } else {
                                        classLoader = new zzh(zzix, ClassLoader.getSystemClassLoader());
                                    }
                                    zza(classLoader);
                                    declaredField.set(null, classLoader);
                                    zziu = Boolean.TRUE;
                                    return zzc;
                                }
                                return zzc;
                            } catch (LoadingException e3) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            }
                        }
                        zziu = bool;
                    }
                }
                if (!bool.booleanValue()) {
                    return zzb(context, str, z);
                }
                try {
                    return zzc(context, str, z);
                } catch (LoadingException e4) {
                    String valueOf2 = String.valueOf(e4.getMessage());
                    Log.w("DynamiteModule", valueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf2) : new String("Failed to retrieve remote module version: "));
                    return 0;
                }
            }
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw th;
        }
    }

    private static int zzb(Context context, String str, boolean z) {
        zzj zzk = zzk(context);
        if (zzk == null) {
            return 0;
        }
        try {
            if (zzk.zzak() >= 2) {
                return zzk.zzb(ObjectWrapper.wrap(context), str, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzk.zza(ObjectWrapper.wrap(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int zzc(android.content.Context r8, java.lang.String r9, boolean r10) throws com.google.android.gms.dynamite.DynamiteModule.LoadingException {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: all -> 0x00a3, Exception -> 0x00a5
            if (r10 == 0) goto L_0x000d
            java.lang.String r8 = "api_force_staging"
            goto L_0x000f
        L_0x000d:
            java.lang.String r8 = "api"
        L_0x000f:
            java.lang.String r10 = java.lang.String.valueOf(r8)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            int r10 = r10.length()     // Catch: all -> 0x00a3, Exception -> 0x00a5
            int r10 = r10 + 42
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            int r2 = r2.length()     // Catch: all -> 0x00a3, Exception -> 0x00a5
            int r10 = r10 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: all -> 0x00a3, Exception -> 0x00a5
            r2.<init>(r10)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            java.lang.String r10 = "content://com.google.android.gms.chimera/"
            r2.append(r10)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            r2.append(r8)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            r2.append(r9)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            java.lang.String r8 = r2.toString()     // Catch: all -> 0x00a3, Exception -> 0x00a5
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: all -> 0x00a3, Exception -> 0x00a5
            if (r8 == 0) goto L_0x008b
            boolean r9 = r8.moveToFirst()     // Catch: all -> 0x009a, Exception -> 0x009e
            if (r9 == 0) goto L_0x008b
            r9 = 0
            int r9 = r8.getInt(r9)     // Catch: all -> 0x009a, Exception -> 0x009e
            if (r9 <= 0) goto L_0x0084
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r10 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r10)     // Catch: all -> 0x009a, Exception -> 0x009e
            r1 = 2
            java.lang.String r1 = r8.getString(r1)     // Catch: all -> 0x0081
            com.google.android.gms.dynamite.DynamiteModule.zzix = r1     // Catch: all -> 0x0081
            java.lang.String r1 = "loaderVersion"
            int r1 = r8.getColumnIndex(r1)     // Catch: all -> 0x0081
            if (r1 < 0) goto L_0x006f
            int r1 = r8.getInt(r1)     // Catch: all -> 0x0081
            com.google.android.gms.dynamite.DynamiteModule.zziy = r1     // Catch: all -> 0x0081
        L_0x006f:
            monitor-exit(r10)     // Catch: all -> 0x0081
            java.lang.ThreadLocal<com.google.android.gms.dynamite.DynamiteModule$zza> r10 = com.google.android.gms.dynamite.DynamiteModule.zziz     // Catch: all -> 0x009a, Exception -> 0x009e
            java.lang.Object r10 = r10.get()     // Catch: all -> 0x009a, Exception -> 0x009e
            com.google.android.gms.dynamite.DynamiteModule$zza r10 = (com.google.android.gms.dynamite.DynamiteModule.zza) r10     // Catch: all -> 0x009a, Exception -> 0x009e
            if (r10 == 0) goto L_0x0084
            android.database.Cursor r1 = r10.zzjd     // Catch: all -> 0x009a, Exception -> 0x009e
            if (r1 != 0) goto L_0x0084
            r10.zzjd = r8     // Catch: all -> 0x009a, Exception -> 0x009e
            goto L_0x0085
        L_0x0081:
            r9 = move-exception
            monitor-exit(r10)     // Catch: all -> 0x0081
            throw r9     // Catch: all -> 0x009a, Exception -> 0x009e
        L_0x0084:
            r0 = r8
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            r0.close()
        L_0x008a:
            return r9
        L_0x008b:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch: all -> 0x009a, Exception -> 0x009e
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r9 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: all -> 0x009a, Exception -> 0x009e
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>(r10, r0)     // Catch: all -> 0x009a, Exception -> 0x009e
            throw r9     // Catch: all -> 0x009a, Exception -> 0x009e
        L_0x009a:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto L_0x00b6
        L_0x009e:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x00a7
        L_0x00a3:
            r8 = move-exception
            goto L_0x00b6
        L_0x00a5:
            r8 = move-exception
            r9 = r0
        L_0x00a7:
            boolean r10 = r8 instanceof com.google.android.gms.dynamite.DynamiteModule.LoadingException     // Catch: all -> 0x00b4
            if (r10 == 0) goto L_0x00ac
            throw r8     // Catch: all -> 0x00b4
        L_0x00ac:
            com.google.android.gms.dynamite.DynamiteModule$LoadingException r10 = new com.google.android.gms.dynamite.DynamiteModule$LoadingException     // Catch: all -> 0x00b4
            java.lang.String r1 = "V2 version check failed"
            r10.<init>(r1, r8, r0)     // Catch: all -> 0x00b4
            throw r10     // Catch: all -> 0x00b4
        L_0x00b4:
            r8 = move-exception
            r0 = r9
        L_0x00b6:
            if (r0 == 0) goto L_0x00bb
            r0.close()
        L_0x00bb:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzc(android.content.Context, java.lang.String, boolean):int");
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    private static DynamiteModule zze(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static DynamiteModule zza(Context context, String str, int i) throws LoadingException {
        Boolean bool;
        IObjectWrapper iObjectWrapper;
        try {
            synchronized (DynamiteModule.class) {
                bool = zziu;
            }
            if (bool == null) {
                throw new LoadingException("Failed to determine which loading route to use.", (zza) null);
            } else if (bool.booleanValue()) {
                return zzb(context, str, i);
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
                sb.append("Selected remote version of ");
                sb.append(str);
                sb.append(", version >= ");
                sb.append(i);
                Log.i("DynamiteModule", sb.toString());
                zzj zzk = zzk(context);
                if (zzk != null) {
                    if (zzk.zzak() >= 2) {
                        iObjectWrapper = zzk.zzb(ObjectWrapper.wrap(context), str, i);
                    } else {
                        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                        iObjectWrapper = zzk.zza(ObjectWrapper.wrap(context), str, i);
                    }
                    if (ObjectWrapper.unwrap(iObjectWrapper) != null) {
                        return new DynamiteModule((Context) ObjectWrapper.unwrap(iObjectWrapper));
                    }
                    throw new LoadingException("Failed to load remote module.", (zza) null);
                }
                throw new LoadingException("Failed to create IDynamiteLoader.", (zza) null);
            }
        } catch (RemoteException e) {
            throw new LoadingException("Failed to load remote module.", e, null);
        } catch (LoadingException e2) {
            throw e2;
        } catch (Throwable th) {
            CrashUtils.addDynamiteErrorToDropBox(context, th);
            throw new LoadingException("Failed to load remote module.", th, null);
        }
    }

    private static zzj zzk(Context context) {
        zzj zzjVar;
        synchronized (DynamiteModule.class) {
            if (zziv != null) {
                return zziv;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzjVar = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    if (queryLocalInterface instanceof zzj) {
                        zzjVar = (zzj) queryLocalInterface;
                    } else {
                        zzjVar = new zzi(iBinder);
                    }
                }
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            if (zzjVar == null) {
                return null;
            }
            zziv = zzjVar;
            return zzjVar;
        }
    }

    public final Context getModuleContext() {
        return this.zzjc;
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws LoadingException, RemoteException {
        zzl zzlVar;
        IObjectWrapper iObjectWrapper;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            zzlVar = zziw;
        }
        if (zzlVar != null) {
            zza zzaVar = zziz.get();
            if (zzaVar == null || zzaVar.zzjd == null) {
                throw new LoadingException("No result cursor", (zza) null);
            }
            Context applicationContext = context.getApplicationContext();
            Cursor cursor = zzaVar.zzjd;
            ObjectWrapper.wrap(null);
            if (zzaj().booleanValue()) {
                Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                iObjectWrapper = zzlVar.zzb(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                iObjectWrapper = zzlVar.zza(ObjectWrapper.wrap(applicationContext), str, i, ObjectWrapper.wrap(cursor));
            }
            Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapper);
            if (context2 != null) {
                return new DynamiteModule(context2);
            }
            throw new LoadingException("Failed to get module context", (zza) null);
        }
        throw new LoadingException("DynamiteLoaderV2 was not cached.", (zza) null);
    }

    private static Boolean zzaj() {
        Boolean valueOf;
        synchronized (DynamiteModule.class) {
            valueOf = Boolean.valueOf(zziy >= 2);
        }
        return valueOf;
    }

    private static void zza(ClassLoader classLoader) throws LoadingException {
        zzl zzlVar;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzlVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                if (queryLocalInterface instanceof zzl) {
                    zzlVar = (zzl) queryLocalInterface;
                } else {
                    zzlVar = new zzk(iBinder);
                }
            }
            zziw = zzlVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, null);
        }
    }

    public final IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzjc.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new LoadingException(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e, null);
        }
    }

    private DynamiteModule(Context context) {
        this.zzjc = (Context) Preconditions.checkNotNull(context);
    }
}
