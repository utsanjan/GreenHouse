package okhttp3.internal.platform;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AndroidPlatform extends Platform {
    private static final int MAX_LOG_LENGTH = 4000;
    private final CloseGuard closeGuard = CloseGuard.get();
    private final OptionalMethod<Socket> getAlpnSelectedProtocol;
    private final OptionalMethod<Socket> setAlpnProtocols;
    private final OptionalMethod<Socket> setHostname;
    private final OptionalMethod<Socket> setUseSessionTickets;
    private final Class<?> sslParametersClass;

    AndroidPlatform(Class<?> sslParametersClass, OptionalMethod<Socket> setUseSessionTickets, OptionalMethod<Socket> setHostname, OptionalMethod<Socket> getAlpnSelectedProtocol, OptionalMethod<Socket> setAlpnProtocols) {
        this.sslParametersClass = sslParametersClass;
        this.setUseSessionTickets = setUseSessionTickets;
        this.setHostname = setHostname;
        this.getAlpnSelectedProtocol = getAlpnSelectedProtocol;
        this.setAlpnProtocols = setAlpnProtocols;
    }

    @Override // okhttp3.internal.platform.Platform
    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        try {
            socket.connect(address, connectTimeout);
        } catch (AssertionError e) {
            if (Util.isAndroidGetsocknameError(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (ClassCastException e2) {
            if (Build.VERSION.SDK_INT == 26) {
                IOException ioException = new IOException("Exception in connect");
                ioException.initCause(e2);
                throw ioException;
            }
            throw e2;
        } catch (SecurityException e3) {
            IOException ioException2 = new IOException("Exception in connect");
            ioException2.initCause(e3);
            throw ioException2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // okhttp3.internal.platform.Platform
    @Nullable
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        Object context = readFieldOrNull(sslSocketFactory, this.sslParametersClass, "sslParameters");
        if (context == null) {
            try {
                Class<?> gmsSslParametersClass = Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sslSocketFactory.getClass().getClassLoader());
                context = readFieldOrNull(sslSocketFactory, gmsSslParametersClass, "sslParameters");
            } catch (ClassNotFoundException e) {
                return super.trustManager(sslSocketFactory);
            }
        }
        X509TrustManager x509TrustManager = (X509TrustManager) readFieldOrNull(context, X509TrustManager.class, "x509TrustManager");
        return x509TrustManager != null ? x509TrustManager : (X509TrustManager) readFieldOrNull(context, X509TrustManager.class, "trustManager");
    }

    @Override // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sslSocket, String hostname, List<Protocol> protocols) {
        if (hostname != null) {
            this.setUseSessionTickets.invokeOptionalWithoutCheckedException(sslSocket, true);
            this.setHostname.invokeOptionalWithoutCheckedException(sslSocket, hostname);
        }
        OptionalMethod<Socket> optionalMethod = this.setAlpnProtocols;
        if (optionalMethod != null && optionalMethod.isSupported(sslSocket)) {
            Object[] parameters = {concatLengthPrefixed(protocols)};
            this.setAlpnProtocols.invokeWithoutCheckedException(sslSocket, parameters);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    @Nullable
    public String getSelectedProtocol(SSLSocket socket) {
        byte[] alpnResult;
        OptionalMethod<Socket> optionalMethod = this.getAlpnSelectedProtocol;
        if (optionalMethod == null || !optionalMethod.isSupported(socket) || (alpnResult = (byte[]) this.getAlpnSelectedProtocol.invokeWithoutCheckedException(socket, new Object[0])) == null) {
            return null;
        }
        return new String(alpnResult, Util.UTF_8);
    }

    @Override // okhttp3.internal.platform.Platform
    public void log(int level, String message, @Nullable Throwable t) {
        int logLevel = 5;
        if (level != 5) {
            logLevel = 3;
        }
        if (t != null) {
            message = message + '\n' + Log.getStackTraceString(t);
        }
        int i = 0;
        int length = message.length();
        while (i < length) {
            int newline = message.indexOf(10, i);
            int newline2 = newline != -1 ? newline : length;
            do {
                int end = Math.min(newline2, i + MAX_LOG_LENGTH);
                Log.println(logLevel, "OkHttp", message.substring(i, end));
                i = end;
            } while (i < newline2);
            i++;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public Object getStackTraceForCloseable(String closer) {
        return this.closeGuard.createAndOpen(closer);
    }

    @Override // okhttp3.internal.platform.Platform
    public void logCloseableLeak(String message, Object stackTrace) {
        boolean reported = this.closeGuard.warnIfOpen(stackTrace);
        if (!reported) {
            log(5, message, null);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public boolean isCleartextTrafficPermitted(String hostname) {
        Exception e;
        try {
            Class<?> networkPolicyClass = Class.forName("android.security.NetworkSecurityPolicy");
            Method getInstanceMethod = networkPolicyClass.getMethod("getInstance", new Class[0]);
            Object networkSecurityPolicy = getInstanceMethod.invoke(null, new Object[0]);
            return api24IsCleartextTrafficPermitted(hostname, networkPolicyClass, networkSecurityPolicy);
        } catch (ClassNotFoundException e2) {
            return super.isCleartextTrafficPermitted(hostname);
        } catch (IllegalAccessException e3) {
            e = e3;
            throw Util.assertionError("unable to determine cleartext support", e);
        } catch (IllegalArgumentException e4) {
            e = e4;
            throw Util.assertionError("unable to determine cleartext support", e);
        } catch (NoSuchMethodException e5) {
            return super.isCleartextTrafficPermitted(hostname);
        } catch (InvocationTargetException e6) {
            e = e6;
            throw Util.assertionError("unable to determine cleartext support", e);
        }
    }

    private boolean api24IsCleartextTrafficPermitted(String hostname, Class<?> networkPolicyClass, Object networkSecurityPolicy) throws InvocationTargetException, IllegalAccessException {
        try {
            Method isCleartextTrafficPermittedMethod = networkPolicyClass.getMethod("isCleartextTrafficPermitted", String.class);
            return ((Boolean) isCleartextTrafficPermittedMethod.invoke(networkSecurityPolicy, hostname)).booleanValue();
        } catch (NoSuchMethodException e) {
            return api23IsCleartextTrafficPermitted(hostname, networkPolicyClass, networkSecurityPolicy);
        }
    }

    private boolean api23IsCleartextTrafficPermitted(String hostname, Class<?> networkPolicyClass, Object networkSecurityPolicy) throws InvocationTargetException, IllegalAccessException {
        try {
            Method isCleartextTrafficPermittedMethod = networkPolicyClass.getMethod("isCleartextTrafficPermitted", new Class[0]);
            return ((Boolean) isCleartextTrafficPermittedMethod.invoke(networkSecurityPolicy, new Object[0])).booleanValue();
        } catch (NoSuchMethodException e) {
            return super.isCleartextTrafficPermitted(hostname);
        }
    }

    private static boolean supportsAlpn() {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager trustManager) {
        try {
            Class<?> extensionsClass = Class.forName("android.net.http.X509TrustManagerExtensions");
            Constructor<?> constructor = extensionsClass.getConstructor(X509TrustManager.class);
            Object extensions = constructor.newInstance(trustManager);
            Method checkServerTrusted = extensionsClass.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class);
            return new AndroidCertificateChainCleaner(extensions, checkServerTrusted);
        } catch (Exception e) {
            return super.buildCertificateChainCleaner(trustManager);
        }
    }

    public static Platform buildIfSupported() {
        Class<?> sslParametersClass;
        OptionalMethod<Socket> setAlpnProtocols;
        OptionalMethod<Socket> getAlpnSelectedProtocol;
        try {
            try {
                sslParametersClass = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException e) {
                sslParametersClass = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            OptionalMethod<Socket> setUseSessionTickets = new OptionalMethod<>(null, "setUseSessionTickets", Boolean.TYPE);
            OptionalMethod<Socket> setHostname = new OptionalMethod<>(null, "setHostname", String.class);
            if (supportsAlpn()) {
                OptionalMethod<Socket> getAlpnSelectedProtocol2 = new OptionalMethod<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                setAlpnProtocols = new OptionalMethod<>(null, "setAlpnProtocols", byte[].class);
                getAlpnSelectedProtocol = getAlpnSelectedProtocol2;
            } else {
                getAlpnSelectedProtocol = null;
                setAlpnProtocols = null;
            }
            return new AndroidPlatform(sslParametersClass, setUseSessionTickets, setHostname, getAlpnSelectedProtocol, setAlpnProtocols);
        } catch (ClassNotFoundException e2) {
            return null;
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public TrustRootIndex buildTrustRootIndex(X509TrustManager trustManager) {
        try {
            Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            method.setAccessible(true);
            return new AndroidTrustRootIndex(trustManager, method);
        } catch (NoSuchMethodException e) {
            return super.buildTrustRootIndex(trustManager);
        }
    }

    /* loaded from: classes.dex */
    static final class AndroidCertificateChainCleaner extends CertificateChainCleaner {
        private final Method checkServerTrusted;
        private final Object x509TrustManagerExtensions;

        AndroidCertificateChainCleaner(Object x509TrustManagerExtensions, Method checkServerTrusted) {
            this.x509TrustManagerExtensions = x509TrustManagerExtensions;
            this.checkServerTrusted = checkServerTrusted;
        }

        @Override // okhttp3.internal.tls.CertificateChainCleaner
        public List<Certificate> clean(List<Certificate> chain, String hostname) throws SSLPeerUnverifiedException {
            try {
                X509Certificate[] certificates = (X509Certificate[]) chain.toArray(new X509Certificate[chain.size()]);
                return (List) this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, certificates, "RSA", hostname);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                SSLPeerUnverifiedException exception = new SSLPeerUnverifiedException(e2.getMessage());
                exception.initCause(e2);
                throw exception;
            }
        }

        public boolean equals(Object other) {
            return other instanceof AndroidCertificateChainCleaner;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* loaded from: classes.dex */
    static final class CloseGuard {
        private final Method getMethod;
        private final Method openMethod;
        private final Method warnIfOpenMethod;

        CloseGuard(Method getMethod, Method openMethod, Method warnIfOpenMethod) {
            this.getMethod = getMethod;
            this.openMethod = openMethod;
            this.warnIfOpenMethod = warnIfOpenMethod;
        }

        Object createAndOpen(String closer) {
            Method method = this.getMethod;
            if (method != null) {
                try {
                    Object closeGuardInstance = method.invoke(null, new Object[0]);
                    this.openMethod.invoke(closeGuardInstance, closer);
                    return closeGuardInstance;
                } catch (Exception e) {
                }
            }
            return null;
        }

        boolean warnIfOpen(Object closeGuardInstance) {
            if (closeGuardInstance == null) {
                return false;
            }
            try {
                this.warnIfOpenMethod.invoke(closeGuardInstance, new Object[0]);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        static CloseGuard get() {
            Method openMethod;
            Method getMethod;
            Method warnIfOpenMethod;
            try {
                Class<?> closeGuardClass = Class.forName("dalvik.system.CloseGuard");
                getMethod = closeGuardClass.getMethod("get", new Class[0]);
                openMethod = closeGuardClass.getMethod("open", String.class);
                warnIfOpenMethod = closeGuardClass.getMethod("warnIfOpen", new Class[0]);
            } catch (Exception e) {
                getMethod = null;
                openMethod = null;
                warnIfOpenMethod = null;
            }
            return new CloseGuard(getMethod, openMethod, warnIfOpenMethod);
        }
    }

    /* loaded from: classes.dex */
    static final class AndroidTrustRootIndex implements TrustRootIndex {
        private final Method findByIssuerAndSignatureMethod;
        private final X509TrustManager trustManager;

        AndroidTrustRootIndex(X509TrustManager trustManager, Method findByIssuerAndSignatureMethod) {
            this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
            this.trustManager = trustManager;
        }

        @Override // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate findByIssuerAndSignature(X509Certificate cert) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.findByIssuerAndSignatureMethod.invoke(this.trustManager, cert);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException e) {
                throw Util.assertionError("unable to get issues and signature", e);
            } catch (InvocationTargetException e2) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AndroidTrustRootIndex)) {
                return false;
            }
            AndroidTrustRootIndex that = (AndroidTrustRootIndex) obj;
            return this.trustManager.equals(that.trustManager) && this.findByIssuerAndSignatureMethod.equals(that.findByIssuerAndSignatureMethod);
        }

        public int hashCode() {
            return this.trustManager.hashCode() + (this.findByIssuerAndSignatureMethod.hashCode() * 31);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0015 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // okhttp3.internal.platform.Platform
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public javax.net.ssl.SSLContext getSSLContext() {
        /*
            r4 = this;
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: NoClassDefFoundError -> 0x0010
            r1 = 16
            if (r0 < r1) goto L_0x000e
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: NoClassDefFoundError -> 0x0010
            r1 = 22
            if (r0 >= r1) goto L_0x000e
            r0 = 1
            goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            goto L_0x0013
        L_0x0010:
            r0 = move-exception
            r1 = 1
            r0 = r1
        L_0x0013:
            if (r0 == 0) goto L_0x001d
            java.lang.String r1 = "TLSv1.2"
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch: NoSuchAlgorithmException -> 0x001c
            return r1
        L_0x001c:
            r1 = move-exception
        L_0x001d:
            java.lang.String r1 = "TLS"
            javax.net.ssl.SSLContext r1 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch: NoSuchAlgorithmException -> 0x0024
            return r1
        L_0x0024:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "No TLS provider"
            r2.<init>(r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.platform.AndroidPlatform.getSSLContext():javax.net.ssl.SSLContext");
    }
}
