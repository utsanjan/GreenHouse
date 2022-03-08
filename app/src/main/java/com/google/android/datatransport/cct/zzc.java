package com.google.android.datatransport.cct;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.cct.a.zzo;
import com.google.android.datatransport.cct.a.zzp;
import com.google.android.datatransport.cct.a.zzq;
import com.google.android.datatransport.cct.a.zzr;
import com.google.android.datatransport.cct.a.zzs;
import com.google.android.datatransport.cct.a.zzt;
import com.google.android.datatransport.cct.a.zzu;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzc implements TransportBackend {
    private final ConnectivityManager zzb;
    private final Clock zzd;
    private final Clock zze;
    private final DataEncoder zza = new JsonDataEncoderBuilder().configureWith(com.google.android.datatransport.cct.a.zzb.zza).ignoreNullValues(true).build();
    final URL zzc = zza(CCTDestination.zza);
    private final int zzf = 40000;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class zza {
        final URL zza;
        final zzo zzb;
        final String zzc;

        zza(URL url, zzo zzoVar, String str) {
            this.zza = url;
            this.zzb = zzoVar;
            this.zzc = str;
        }

        zza zza(URL url) {
            return new zza(url, this.zzb, this.zzc);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class zzb {
        final int zza;
        final URL zzb;
        final long zzc;

        zzb(int i, URL url, long j) {
            this.zza = i;
            this.zzb = url;
            this.zzc = j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(Context context, Clock clock, Clock clock2) {
        this.zzb = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzd = clock2;
        this.zze = clock;
    }

    private static URL zza(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + str, e);
        }
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public EventInternal decorate(EventInternal eventInternal) {
        int i;
        int i2;
        NetworkInfo activeNetworkInfo = this.zzb.getActiveNetworkInfo();
        EventInternal.Builder addMetadata = eventInternal.toBuilder().addMetadata("sdk-version", Build.VERSION.SDK_INT).addMetadata("model", Build.MODEL).addMetadata("hardware", Build.HARDWARE).addMetadata("device", Build.DEVICE).addMetadata("product", Build.PRODUCT).addMetadata("os-uild", Build.ID).addMetadata("manufacturer", Build.MANUFACTURER).addMetadata("fingerprint", Build.FINGERPRINT);
        Calendar.getInstance();
        EventInternal.Builder addMetadata2 = addMetadata.addMetadata("tz-offset", TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000);
        if (activeNetworkInfo == null) {
            i = zzt.zzc.zzs.zza();
        } else {
            i = activeNetworkInfo.getType();
        }
        EventInternal.Builder addMetadata3 = addMetadata2.addMetadata("net-type", i);
        if (activeNetworkInfo == null) {
            i2 = zzt.zzb.zza.zza();
        } else {
            i2 = activeNetworkInfo.getSubtype();
            if (i2 == -1) {
                i2 = zzt.zzb.zzu.zza();
            } else if (zzt.zzb.zza(i2) == null) {
                i2 = 0;
            }
        }
        return addMetadata3.addMetadata("mobile-subtype", i2).build();
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public BackendResponse send(BackendRequest backendRequest) {
        zzq.zza zza2;
        HashMap hashMap = new HashMap();
        for (EventInternal eventInternal : backendRequest.getEvents()) {
            String transportName = eventInternal.getTransportName();
            if (!hashMap.containsKey(transportName)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(eventInternal);
                hashMap.put(transportName, arrayList);
            } else {
                ((List) hashMap.get(transportName)).add(eventInternal);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            EventInternal eventInternal2 = (EventInternal) ((List) entry.getValue()).get(0);
            zzr.zza zza3 = zzr.zza().zza(zzu.zza).zza(this.zze.getTime()).zzb(this.zzd.getTime()).zza(zzp.zza().zza(zzp.zzb.zzb).zza(com.google.android.datatransport.cct.a.zza.zza().zza(Integer.valueOf(eventInternal2.getInteger("sdk-version"))).zze(eventInternal2.get("model")).zzc(eventInternal2.get("hardware")).zza(eventInternal2.get("device")).zzg(eventInternal2.get("product")).zzf(eventInternal2.get("os-uild")).zzd(eventInternal2.get("manufacturer")).zzb(eventInternal2.get("fingerprint")).zza()).zza());
            try {
                zza3.zza(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException e) {
                zza3.zzb((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (EventInternal eventInternal3 : (List) entry.getValue()) {
                EncodedPayload encodedPayload = eventInternal3.getEncodedPayload();
                Encoding encoding = encodedPayload.getEncoding();
                if (encoding.equals(Encoding.of("proto"))) {
                    zza2 = zzq.zza(encodedPayload.getBytes());
                } else if (encoding.equals(Encoding.of("json"))) {
                    zza2 = zzq.zza(new String(encodedPayload.getBytes(), Charset.forName("UTF-8")));
                } else {
                    Logging.w("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", encoding);
                }
                zza2.zza(eventInternal3.getEventMillis()).zzb(eventInternal3.getUptimeMillis()).zzc(eventInternal3.getLong("tz-offset")).zza(zzt.zza().zza(zzt.zzc.zza(eventInternal3.getInteger("net-type"))).zza(zzt.zzb.zza(eventInternal3.getInteger("mobile-subtype"))).zza());
                if (eventInternal3.getCode() != null) {
                    zza2.zza(eventInternal3.getCode());
                }
                arrayList3.add(zza2.zza());
            }
            zza3.zza(arrayList3);
            arrayList2.add(zza3.zza());
        }
        zzo zza4 = zzo.zza(arrayList2);
        String str = null;
        URL url = this.zzc;
        if (backendRequest.getExtras() != null) {
            try {
                CCTDestination fromByteArray = CCTDestination.fromByteArray(backendRequest.getExtras());
                if (fromByteArray.getAPIKey() != null) {
                    str = fromByteArray.getAPIKey();
                }
                if (fromByteArray.getEndPoint() != null) {
                    url = zza(fromByteArray.getEndPoint());
                }
            } catch (IllegalArgumentException e2) {
                return BackendResponse.fatalError();
            }
        }
        try {
            zzb zzbVar = (zzb) Retries.retry(5, new zza(url, zza4, str), zza.zza(this), zzb.zza());
            if (zzbVar.zza == 200) {
                return BackendResponse.ok(zzbVar.zzc);
            }
            int i = zzbVar.zza;
            if (i < 500 && i != 404) {
                return BackendResponse.fatalError();
            }
            return BackendResponse.transientError();
        } catch (IOException e3) {
            Logging.e("CctTransportBackend", "Could not make request to the backend", e3);
            return BackendResponse.transientError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public zzb zza(zza zzaVar) throws IOException {
        Logging.d("CctTransportBackend", "Making request to: %s", zzaVar.zza);
        HttpURLConnection httpURLConnection = (HttpURLConnection) zzaVar.zza.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.zzf);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty(AbstractSpiCall.HEADER_USER_AGENT, String.format("datatransport/%s android/", "2.2.3"));
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", AbstractSpiCall.ACCEPT_JSON_VALUE);
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = zzaVar.zzc;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                this.zza.encode(zzaVar.zzb, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                gZIPOutputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                Logging.i("CctTransportBackend", "Status Code: " + responseCode);
                Logging.i("CctTransportBackend", "Content-Type: " + httpURLConnection.getHeaderField("Content-Type"));
                Logging.i("CctTransportBackend", "Content-Encoding: " + httpURLConnection.getHeaderField("Content-Encoding"));
                if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                    return new zzb(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                }
                if (responseCode != 200) {
                    return new zzb(responseCode, null, 0L);
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    InputStream gZIPInputStream = "gzip".equals(httpURLConnection.getHeaderField("Content-Encoding")) ? new GZIPInputStream(inputStream) : inputStream;
                    zzb zzbVar = new zzb(responseCode, null, zzs.zza(new BufferedReader(new InputStreamReader(gZIPInputStream))).zza());
                    if (gZIPInputStream != null) {
                        gZIPInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return zzbVar;
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                    }
                }
                throw th3;
            }
        } catch (EncodingException | IOException e) {
            Logging.e("CctTransportBackend", "Couldn't encode request, returning with 400", e);
            return new zzb(400, null, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ zza zza(zza zzaVar, zzb zzbVar) {
        URL url = zzbVar.zzb;
        if (url == null) {
            return null;
        }
        Logging.d("CctTransportBackend", "Following redirect to: %s", url);
        return zzaVar.zza(zzbVar.zzb);
    }
}
