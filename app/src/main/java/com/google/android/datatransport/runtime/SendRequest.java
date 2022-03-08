package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.AutoValue_SendRequest;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class SendRequest {
    public abstract Encoding getEncoding();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Event<?> getEvent();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Transformer<?, byte[]> getTransformer();

    public abstract TransportContext getTransportContext();

    public abstract String getTransportName();

    public byte[] getPayload() {
        return getTransformer().apply(getEvent().getPayload());
    }

    public static Builder builder() {
        return new AutoValue_SendRequest.Builder();
    }

    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract SendRequest build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setEncoding(Encoding encoding);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setEvent(Event<?> event);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setTransformer(Transformer<?, byte[]> transformer);

        public abstract Builder setTransportContext(TransportContext transportContext);

        public abstract Builder setTransportName(String str);

        public <T> Builder setEvent(Event<T> event, Encoding encoding, Transformer<T, byte[]> transformer) {
            setEvent(event);
            setEncoding(encoding);
            setTransformer(transformer);
            return this;
        }
    }
}
