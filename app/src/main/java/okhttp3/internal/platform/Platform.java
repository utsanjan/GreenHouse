package okhttp3.internal.platform;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;

/* loaded from: classes.dex */
public class Platform {
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static final Platform PLATFORM = findPlatform();
    private static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());

    public static Platform get() {
        return PLATFORM;
    }

    public String getPrefix() {
        return "OkHttp";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        try {
            Class<?> sslContextClass = Class.forName("sun.security.ssl.SSLContextImpl");
            Object context = readFieldOrNull(sslSocketFactory, sslContextClass, "context");
            if (context == null) {
                return null;
            }
            return (X509TrustManager) readFieldOrNull(context, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void configureTlsExtensions(SSLSocket sslSocket, @Nullable String hostname, List<Protocol> protocols) {
    }

    public void afterHandshake(SSLSocket sslSocket) {
    }

    @Nullable
    public String getSelectedProtocol(SSLSocket socket) {
        return null;
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        socket.connect(address, connectTimeout);
    }

    public void log(int level, String message, @Nullable Throwable t) {
        Level logLevel = level == 5 ? Level.WARNING : Level.INFO;
        logger.log(logLevel, message, t);
    }

    public boolean isCleartextTrafficPermitted(String hostname) {
        return true;
    }

    public Object getStackTraceForCloseable(String closer) {
        if (logger.isLoggable(Level.FINE)) {
            return new Throwable(closer);
        }
        return null;
    }

    public void logCloseableLeak(String message, Object stackTrace) {
        if (stackTrace == null) {
            message = message + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        log(5, message, (Throwable) stackTrace);
    }

    public static List<String> alpnProtocolNames(List<Protocol> protocols) {
        List<String> names = new ArrayList<>(protocols.size());
        int size = protocols.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = protocols.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                names.add(protocol.toString());
            }
        }
        return names;
    }

    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager trustManager) {
        return new BasicCertificateChainCleaner(buildTrustRootIndex(trustManager));
    }

    public CertificateChainCleaner buildCertificateChainCleaner(SSLSocketFactory sslSocketFactory) {
        X509TrustManager trustManager = trustManager(sslSocketFactory);
        if (trustManager != null) {
            return buildCertificateChainCleaner(trustManager);
        }
        throw new IllegalStateException("Unable to extract the trust manager on " + get() + ", sslSocketFactory is " + sslSocketFactory.getClass());
    }

    public static boolean isConscryptPreferred() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        String preferredProvider = Security.getProviders()[0].getName();
        return "Conscrypt".equals(preferredProvider);
    }

    private static Platform findPlatform() {
        Platform conscrypt;
        Platform android2 = AndroidPlatform.buildIfSupported();
        if (android2 != null) {
            return android2;
        }
        if (isConscryptPreferred() && (conscrypt = ConscryptPlatform.buildIfSupported()) != null) {
            return conscrypt;
        }
        Platform jdk9 = Jdk9Platform.buildIfSupported();
        if (jdk9 != null) {
            return jdk9;
        }
        Platform jdkWithJettyBoot = JdkWithJettyBootPlatform.buildIfSupported();
        if (jdkWithJettyBoot != null) {
            return jdkWithJettyBoot;
        }
        return new Platform();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] concatLengthPrefixed(List<Protocol> protocols) {
        Buffer result = new Buffer();
        int size = protocols.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = protocols.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                result.writeByte(protocol.toString().length());
                result.writeUtf8(protocol.toString());
            }
        }
        return result.readByteArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static <T> T readFieldOrNull(Object instance, Class<T> fieldType, String fieldName) {
        Object delegate;
        for (Class<?> c = instance.getClass(); c != Object.class; c = c.getSuperclass()) {
            try {
                Field field = c.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(instance);
                if (value != null && fieldType.isInstance(value)) {
                    return fieldType.cast(value);
                }
                return null;
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            } catch (NoSuchFieldException e2) {
            }
        }
        if (fieldName.equals("delegate") || (delegate = readFieldOrNull(instance, Object.class, "delegate")) == null) {
            return null;
        }
        return (T) readFieldOrNull(delegate, fieldType, fieldName);
    }

    public SSLContext getSSLContext() {
        String jvmVersion = System.getProperty("java.specification.version");
        if ("1.7".equals(jvmVersion)) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException e) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    public TrustRootIndex buildTrustRootIndex(X509TrustManager trustManager) {
        return new BasicTrustRootIndex(trustManager.getAcceptedIssuers());
    }

    public void configureSslSocketFactory(SSLSocketFactory socketFactory) {
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
