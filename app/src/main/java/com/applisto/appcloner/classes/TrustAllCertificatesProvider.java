package com.applisto.appcloner.classes;

import android.util.Log;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes2.dex */
public class TrustAllCertificatesProvider extends AbstractContentProvider {
    private static final String TAG = TrustAllCertificatesProvider.class.getSimpleName();

    @Override // com.applisto.appcloner.classes.AbstractContentProvider, android.content.ContentProvider
    public boolean onCreate() {
        TrustManager[] trustAllCerts = {new X509TrustManager() { // from class: com.applisto.appcloner.classes.TrustAllCertificatesProvider.1
            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            Log.i(TAG, "onCreate; now trusting all certificates...");
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return true;
    }
}
