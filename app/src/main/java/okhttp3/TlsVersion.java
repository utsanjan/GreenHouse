package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    final String javaName;

    TlsVersion(String javaName) {
        this.javaName = javaName;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static TlsVersion forJavaName(String javaName) {
        char c;
        int hashCode = javaName.hashCode();
        if (hashCode == 79201641) {
            if (javaName.equals("SSLv3")) {
                c = 4;
            }
            c = 65535;
        } else if (hashCode != 79923350) {
            switch (hashCode) {
                case -503070503:
                    if (javaName.equals("TLSv1.1")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -503070502:
                    if (javaName.equals("TLSv1.2")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -503070501:
                    if (javaName.equals("TLSv1.3")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (javaName.equals("TLSv1")) {
                c = 3;
            }
            c = 65535;
        }
        if (c == 0) {
            return TLS_1_3;
        }
        if (c == 1) {
            return TLS_1_2;
        }
        if (c == 2) {
            return TLS_1_1;
        }
        if (c == 3) {
            return TLS_1_0;
        }
        if (c == 4) {
            return SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: " + javaName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TlsVersion> forJavaNames(String... tlsVersions) {
        List<TlsVersion> result = new ArrayList<>(tlsVersions.length);
        for (String tlsVersion : tlsVersions) {
            result.add(forJavaName(tlsVersion));
        }
        return Collections.unmodifiableList(result);
    }

    public String javaName() {
        return this.javaName;
    }
}
