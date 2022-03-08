package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;

/* loaded from: classes.dex */
final class AutoValue_TransportContext extends TransportContext {
    private final String backendName;
    private final byte[] extras;
    private final Priority priority;

    private AutoValue_TransportContext(String backendName, byte[] extras, Priority priority) {
        this.backendName = backendName;
        this.extras = extras;
        this.priority = priority;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public String getBackendName() {
        return this.backendName;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public byte[] getExtras() {
        return this.extras;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public Priority getPriority() {
        return this.priority;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TransportContext)) {
            return false;
        }
        TransportContext that = (TransportContext) o;
        if (this.backendName.equals(that.getBackendName())) {
            if (Arrays.equals(this.extras, that instanceof AutoValue_TransportContext ? ((AutoValue_TransportContext) that).extras : that.getExtras()) && this.priority.equals(that.getPriority())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((h$ ^ this.backendName.hashCode()) * 1000003) ^ Arrays.hashCode(this.extras)) * 1000003) ^ this.priority.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends TransportContext.Builder {
        private String backendName;
        private byte[] extras;
        private Priority priority;

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setBackendName(String backendName) {
            if (backendName != null) {
                this.backendName = backendName;
                return this;
            }
            throw new NullPointerException("Null backendName");
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setExtras(byte[] extras) {
            this.extras = extras;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setPriority(Priority priority) {
            if (priority != null) {
                this.priority = priority;
                return this;
            }
            throw new NullPointerException("Null priority");
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext build() {
            String missing = "";
            if (this.backendName == null) {
                missing = missing + " backendName";
            }
            if (this.priority == null) {
                missing = missing + " priority";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TransportContext(this.backendName, this.extras, this.priority);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
