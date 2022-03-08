package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.EventInternal;
import java.util.Map;

/* loaded from: classes.dex */
final class AutoValue_EventInternal extends EventInternal {
    private final Map<String, String> autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final String transportName;
    private final long uptimeMillis;

    private AutoValue_EventInternal(String transportName, Integer code, EncodedPayload encodedPayload, long eventMillis, long uptimeMillis, Map<String, String> autoMetadata) {
        this.transportName = transportName;
        this.code = code;
        this.encodedPayload = encodedPayload;
        this.eventMillis = eventMillis;
        this.uptimeMillis = uptimeMillis;
        this.autoMetadata = autoMetadata;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public String getTransportName() {
        return this.transportName;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public Integer getCode() {
        return this.code;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long getEventMillis() {
        return this.eventMillis;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.datatransport.runtime.EventInternal
    public Map<String, String> getAutoMetadata() {
        return this.autoMetadata;
    }

    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + "}";
    }

    public boolean equals(Object o) {
        Integer num;
        if (o == this) {
            return true;
        }
        if (!(o instanceof EventInternal)) {
            return false;
        }
        EventInternal that = (EventInternal) o;
        return this.transportName.equals(that.getTransportName()) && ((num = this.code) != null ? num.equals(that.getCode()) : that.getCode() == null) && this.encodedPayload.equals(that.getEncodedPayload()) && this.eventMillis == that.getEventMillis() && this.uptimeMillis == that.getUptimeMillis() && this.autoMetadata.equals(that.getAutoMetadata());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        int h$2 = (h$ ^ this.transportName.hashCode()) * 1000003;
        Integer num = this.code;
        int hashCode = num == null ? 0 : num.hashCode();
        long j = this.eventMillis;
        long j2 = this.uptimeMillis;
        return ((((((((h$2 ^ hashCode) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends EventInternal.Builder {
        private Map<String, String> autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private String transportName;
        private Long uptimeMillis;

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setTransportName(String transportName) {
            if (transportName != null) {
                this.transportName = transportName;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setCode(Integer code) {
            this.code = code;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setEncodedPayload(EncodedPayload encodedPayload) {
            if (encodedPayload != null) {
                this.encodedPayload = encodedPayload;
                return this;
            }
            throw new NullPointerException("Null encodedPayload");
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setEventMillis(long eventMillis) {
            this.eventMillis = Long.valueOf(eventMillis);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setUptimeMillis(long uptimeMillis) {
            this.uptimeMillis = Long.valueOf(uptimeMillis);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setAutoMetadata(Map<String, String> autoMetadata) {
            if (autoMetadata != null) {
                this.autoMetadata = autoMetadata;
                return this;
            }
            throw new NullPointerException("Null autoMetadata");
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        protected Map<String, String> getAutoMetadata() {
            Map<String, String> map = this.autoMetadata;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal build() {
            String missing = "";
            if (this.transportName == null) {
                missing = missing + " transportName";
            }
            if (this.encodedPayload == null) {
                missing = missing + " encodedPayload";
            }
            if (this.eventMillis == null) {
                missing = missing + " eventMillis";
            }
            if (this.uptimeMillis == null) {
                missing = missing + " uptimeMillis";
            }
            if (this.autoMetadata == null) {
                missing = missing + " autoMetadata";
            }
            if (missing.isEmpty()) {
                return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
