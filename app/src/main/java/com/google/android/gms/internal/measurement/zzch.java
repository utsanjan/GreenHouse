package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzch implements zzcl {
    private static final Map<Uri, zzch> zza = new ArrayMap();
    private static final String[] zzh = {"key", "value"};
    private final ContentResolver zzb;
    private final Uri zzc;
    private volatile Map<String, String> zzf;
    private final ContentObserver zzd = new zzcj(this, null);
    private final Object zze = new Object();
    private final List<zzcm> zzg = new ArrayList();

    private zzch(ContentResolver contentResolver, Uri uri) {
        this.zzb = contentResolver;
        this.zzc = uri;
        contentResolver.registerContentObserver(uri, false, this.zzd);
    }

    public static zzch zza(ContentResolver contentResolver, Uri uri) {
        zzch zzchVar;
        synchronized (zzch.class) {
            zzchVar = zza.get(uri);
            if (zzchVar == null) {
                try {
                    zzch zzchVar2 = new zzch(contentResolver, uri);
                    try {
                        zza.put(uri, zzchVar2);
                        zzchVar = zzchVar2;
                    } catch (SecurityException e) {
                        zzchVar = zzchVar2;
                    }
                } catch (SecurityException e2) {
                }
            }
        }
        return zzchVar;
    }

    public final Map<String, String> zza() {
        Map<String, String> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                map = this.zzf;
                if (map == null) {
                    map = zze();
                    this.zzf = map;
                }
            }
        }
        return map != null ? map : Collections.emptyMap();
    }

    public final void zzb() {
        synchronized (this.zze) {
            this.zzf = null;
            zzcv.zza();
        }
        synchronized (this) {
            for (zzcm zzcmVar : this.zzg) {
                zzcmVar.zza();
            }
        }
    }

    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) zzco.zza(new zzcn(this) { // from class: com.google.android.gms.internal.measurement.zzck
                    private final zzch zza;

                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        this.zza = this;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzcn
                    public final Object zza() {
                        return this.zza.zzd();
                    }
                });
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        } catch (SQLiteException | IllegalStateException | SecurityException e) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void zzc() {
        synchronized (zzch.class) {
            for (zzch zzchVar : zza.values()) {
                zzchVar.zzb.unregisterContentObserver(zzchVar.zzd);
            }
            zza.clear();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcl
    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Map zzd() {
        Map map;
        Cursor query = this.zzb.query(this.zzc, zzh, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            return map;
        } finally {
            query.close();
        }
    }
}
