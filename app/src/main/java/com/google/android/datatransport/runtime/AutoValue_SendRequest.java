package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;

/* loaded from: classes.dex */
final class AutoValue_SendRequest extends SendRequest {
    private final Encoding encoding;
    private final Event<?> event;
    private final Transformer<?, byte[]> transformer;
    private final TransportContext transportContext;
    private final String transportName;

    private AutoValue_SendRequest(TransportContext transportContext, String transportName, Event<?> event, Transformer<?, byte[]> transformer, Encoding encoding) {
        this.transportContext = transportContext;
        this.transportName = transportName;
        this.event = event;
        this.transformer = transformer;
        this.encoding = encoding;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public String getTransportName() {
        return this.transportName;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    Event<?> getEvent() {
        return this.event;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    Transformer<?, byte[]> getTransformer() {
        return this.transformer;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Encoding getEncoding() {
        return this.encoding;
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.transportContext + ", transportName=" + this.transportName + ", event=" + this.event + ", transformer=" + this.transformer + ", encoding=" + this.encoding + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SendRequest)) {
            return false;
        }
        SendRequest that = (SendRequest) o;
        return this.transportContext.equals(that.getTransportContext()) && this.transportName.equals(that.getTransportName()) && this.event.equals(that.getEvent()) && this.transformer.equals(that.getTransformer()) && this.encoding.equals(that.getEncoding());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((((((((h$ ^ this.transportContext.hashCode()) * 1000003) ^ this.transportName.hashCode()) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.transformer.hashCode()) * 1000003) ^ this.encoding.hashCode();
    }

    /* loaded from: classes.dex */
    static final class Builder extends SendRequest.Builder {
        private Encoding encoding;
        private Event<?> event;
        private Transformer<?, byte[]> transformer;
        private TransportContext transportContext;
        private String transportName;

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportContext(TransportContext transportContext) {
            if (transportContext != null) {
                this.transportContext = transportContext;
                return this;
            }
            throw new NullPointerException("Null transportContext");
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportName(String transportName) {
            if (transportName != null) {
                this.transportName = transportName;
                return this;
            }
            throw new NullPointerException("Null transportName");
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        SendRequest.Builder setEvent(Event<?> event) {
            if (event != null) {
                this.event = event;
                return this;
            }
            throw new NullPointerException("Null event");
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        SendRequest.Builder setTransformer(Transformer<?, byte[]> transformer) {
            if (transformer != null) {
                this.transformer = transformer;
                return this;
            }
            throw new NullPointerException("Null transformer");
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        SendRequest.Builder setEncoding(Encoding encoding) {
            if (encoding != null) {
                this.encoding = encoding;
                return this;
            }
            throw new NullPointerException("Null encoding");
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest build() {
            String missing = "";
            if (this.transportContext == null) {
                missing = missing + " transportContext";
            }
            if (this.transportName == null) {
                missing = missing + " transportName";
            }
            if (this.event == null) {
                missing = missing + " event";
            }
            if (this.transformer == null) {
                missing = missing + " transformer";
            }
            if (this.encoding == null) {
                missing = missing + " encoding";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
