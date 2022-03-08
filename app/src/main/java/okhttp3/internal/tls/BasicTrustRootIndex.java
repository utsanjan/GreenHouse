package okhttp3.internal.tls;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* loaded from: classes.dex */
public final class BasicTrustRootIndex implements TrustRootIndex {
    private final Map<X500Principal, Set<X509Certificate>> subjectToCaCerts = new LinkedHashMap();

    public BasicTrustRootIndex(X509Certificate... caCerts) {
        for (X509Certificate caCert : caCerts) {
            X500Principal subject = caCert.getSubjectX500Principal();
            Set<X509Certificate> subjectCaCerts = this.subjectToCaCerts.get(subject);
            if (subjectCaCerts == null) {
                subjectCaCerts = new LinkedHashSet(1);
                this.subjectToCaCerts.put(subject, subjectCaCerts);
            }
            subjectCaCerts.add(caCert);
        }
    }

    @Override // okhttp3.internal.tls.TrustRootIndex
    public X509Certificate findByIssuerAndSignature(X509Certificate cert) {
        X500Principal issuer = cert.getIssuerX500Principal();
        Set<X509Certificate> subjectCaCerts = this.subjectToCaCerts.get(issuer);
        if (subjectCaCerts == null) {
            return null;
        }
        for (X509Certificate caCert : subjectCaCerts) {
            PublicKey publicKey = caCert.getPublicKey();
            try {
                cert.verify(publicKey);
                return caCert;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof BasicTrustRootIndex) && ((BasicTrustRootIndex) other).subjectToCaCerts.equals(this.subjectToCaCerts);
    }

    public int hashCode() {
        return this.subjectToCaCerts.hashCode();
    }
}
