package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzif implements Runnable {
    private final URL zza;
    private final zzic zzc;
    private final String zzd;
    private final /* synthetic */ zzid zzf;
    private final byte[] zzb = null;
    private final Map<String, String> zze = null;

    public zzif(zzid zzidVar, String str, URL url, byte[] bArr, Map<String, String> map, zzic zzicVar) {
        this.zzf = zzidVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzicVar);
        this.zza = url;
        this.zzc = zzicVar;
        this.zzd = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Map<String, List<String>> map;
        IOException e;
        HttpURLConnection httpURLConnection;
        Map<String, List<String>> map2;
        Throwable th;
        byte[] zza;
        this.zzf.zzc();
        int i = 0;
        try {
            httpURLConnection = this.zzf.zza(this.zza);
            try {
                if (this.zze != null) {
                    for (Map.Entry<String, String> entry : this.zze.entrySet()) {
                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                i = httpURLConnection.getResponseCode();
                Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                try {
                    zzid zzidVar = this.zzf;
                    zza = zzid.zza(httpURLConnection);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i, null, zza, headerFields);
                } catch (IOException e2) {
                    map = headerFields;
                    e = e2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i, e, null, map);
                } catch (Throwable th2) {
                    map2 = headerFields;
                    th = th2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    zzb(i, null, null, map2);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                map = null;
            } catch (Throwable th3) {
                th = th3;
                map2 = null;
            }
        } catch (IOException e4) {
            e = e4;
            httpURLConnection = null;
            map = null;
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
            map2 = null;
        }
    }

    private final void zzb(final int i, final Exception exc, final byte[] bArr, final Map<String, List<String>> map) {
        this.zzf.zzq().zza(new Runnable(this, i, exc, bArr, map) { // from class: com.google.android.gms.measurement.internal.zzie
            private final zzif zza;
            private final int zzb;
            private final Exception zzc;
            private final byte[] zzd;
            private final Map zze;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
                this.zzb = i;
                this.zzc = exc;
                this.zzd = bArr;
                this.zze = map;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        this.zzc.zza(this.zzd, i, exc, bArr, map);
    }
}
