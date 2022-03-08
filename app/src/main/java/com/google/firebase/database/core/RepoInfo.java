package com.google.firebase.database.core;

import java.net.URI;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public final class RepoInfo {
    private static final String LAST_SESSION_ID_PARAM = "ls";
    private static final String VERSION_PARAM = "v";
    public String host;
    public String internalHost;
    public String namespace;
    public boolean secure;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("http");
        sb.append(this.secure ? "s" : "");
        sb.append("://");
        sb.append(this.host);
        return sb.toString();
    }

    public String toDebugString() {
        return "(host=" + this.host + ", secure=" + this.secure + ", ns=" + this.namespace + " internal=" + this.internalHost + ")";
    }

    public URI getConnectionURL(String optLastSessionId) {
        String scheme = this.secure ? "wss" : "ws";
        String url = scheme + "://" + this.internalHost + "/.ws?ns=" + this.namespace + "&" + VERSION_PARAM + "=5";
        if (optLastSessionId != null) {
            url = url + "&ls=" + optLastSessionId;
        }
        return URI.create(url);
    }

    public boolean isCacheableHost() {
        return this.internalHost.startsWith("s-");
    }

    public boolean isSecure() {
        return this.secure;
    }

    public boolean isDemoHost() {
        return this.host.contains(".firebaseio-demo.com");
    }

    public boolean isCustomHost() {
        return !this.host.contains(".firebaseio.com") && !this.host.contains(".firebaseio-demo.com");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepoInfo repoInfo = (RepoInfo) o;
        if (this.secure == repoInfo.secure && this.host.equals(repoInfo.host)) {
            return this.namespace.equals(repoInfo.namespace);
        }
        return false;
    }

    public int hashCode() {
        int result = this.host.hashCode();
        return (((result * 31) + (this.secure ? 1 : 0)) * 31) + this.namespace.hashCode();
    }
}
