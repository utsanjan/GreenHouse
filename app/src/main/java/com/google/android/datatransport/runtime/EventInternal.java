package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.AutoValue_EventInternal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class EventInternal {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Map<String, String> getAutoMetadata();

    public abstract Integer getCode();

    public abstract EncodedPayload getEncodedPayload();

    public abstract long getEventMillis();

    public abstract String getTransportName();

    public abstract long getUptimeMillis();

    @Deprecated
    public byte[] getPayload() {
        return getEncodedPayload().getBytes();
    }

    public final Map<String, String> getMetadata() {
        return Collections.unmodifiableMap(getAutoMetadata());
    }

    public final String getOrDefault(String key, String defaultValue) {
        String value = getAutoMetadata().get(key);
        return value == null ? defaultValue : value;
    }

    public final int getInteger(String key) {
        String value = getAutoMetadata().get(key);
        if (value == null) {
            return 0;
        }
        return Integer.valueOf(value).intValue();
    }

    public final long getLong(String key) {
        String value = getAutoMetadata().get(key);
        if (value == null) {
            return 0L;
        }
        return Long.valueOf(value).longValue();
    }

    public final String get(String key) {
        String value = getAutoMetadata().get(key);
        return value == null ? "" : value;
    }

    public Builder toBuilder() {
        return new AutoValue_EventInternal.Builder().setTransportName(getTransportName()).setCode(getCode()).setEncodedPayload(getEncodedPayload()).setEventMillis(getEventMillis()).setUptimeMillis(getUptimeMillis()).setAutoMetadata(new HashMap(getAutoMetadata()));
    }

    public static Builder builder() {
        return new AutoValue_EventInternal.Builder().setAutoMetadata(new HashMap());
    }

    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract EventInternal build();

        protected abstract Map<String, String> getAutoMetadata();

        protected abstract Builder setAutoMetadata(Map<String, String> map);

        public abstract Builder setCode(Integer num);

        public abstract Builder setEncodedPayload(EncodedPayload encodedPayload);

        public abstract Builder setEventMillis(long j);

        public abstract Builder setTransportName(String str);

        public abstract Builder setUptimeMillis(long j);

        public final Builder addMetadata(String key, String value) {
            getAutoMetadata().put(key, value);
            return this;
        }

        public final Builder addMetadata(String key, long value) {
            getAutoMetadata().put(key, String.valueOf(value));
            return this;
        }

        public final Builder addMetadata(String key, int value) {
            getAutoMetadata().put(key, String.valueOf(value));
            return this;
        }
    }
}
