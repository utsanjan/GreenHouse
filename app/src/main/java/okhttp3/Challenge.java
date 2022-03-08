package okhttp3;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

/* loaded from: classes.dex */
public final class Challenge {
    private final Map<String, String> authParams;
    private final String scheme;

    public Challenge(String scheme, Map<String, String> authParams) {
        if (scheme == null) {
            throw new NullPointerException("scheme == null");
        } else if (authParams != null) {
            this.scheme = scheme;
            Map<String, String> newAuthParams = new LinkedHashMap<>();
            for (Map.Entry<String, String> authParam : authParams.entrySet()) {
                String key = authParam.getKey() == null ? null : authParam.getKey().toLowerCase(Locale.US);
                newAuthParams.put(key, authParam.getValue());
            }
            this.authParams = Collections.unmodifiableMap(newAuthParams);
        } else {
            throw new NullPointerException("authParams == null");
        }
    }

    public Challenge(String scheme, String realm) {
        if (scheme == null) {
            throw new NullPointerException("scheme == null");
        } else if (realm != null) {
            this.scheme = scheme;
            this.authParams = Collections.singletonMap("realm", realm);
        } else {
            throw new NullPointerException("realm == null");
        }
    }

    public Challenge withCharset(Charset charset) {
        if (charset != null) {
            Map<String, String> authParams = new LinkedHashMap<>(this.authParams);
            authParams.put("charset", charset.name());
            return new Challenge(this.scheme, authParams);
        }
        throw new NullPointerException("charset == null");
    }

    public String scheme() {
        return this.scheme;
    }

    public Map<String, String> authParams() {
        return this.authParams;
    }

    public String realm() {
        return this.authParams.get("realm");
    }

    public Charset charset() {
        String charset = this.authParams.get("charset");
        if (charset != null) {
            try {
                return Charset.forName(charset);
            } catch (Exception e) {
            }
        }
        return Util.ISO_8859_1;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof Challenge) && ((Challenge) other).scheme.equals(this.scheme) && ((Challenge) other).authParams.equals(this.authParams);
    }

    public int hashCode() {
        int result = (29 * 31) + this.scheme.hashCode();
        return (result * 31) + this.authParams.hashCode();
    }

    public String toString() {
        return this.scheme + " authParams=" + this.authParams;
    }
}
