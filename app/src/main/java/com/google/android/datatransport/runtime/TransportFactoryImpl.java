package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import java.util.Set;

/* loaded from: classes.dex */
final class TransportFactoryImpl implements TransportFactory {
    private final Set<Encoding> supportedPayloadEncodings;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransportFactoryImpl(Set<Encoding> supportedPayloadEncodings, TransportContext transportContext, TransportInternal transportInternal) {
        this.supportedPayloadEncodings = supportedPayloadEncodings;
        this.transportContext = transportContext;
        this.transportInternal = transportInternal;
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String name, Class<T> payloadType, Transformer<T, byte[]> payloadTransformer) {
        return getTransport(name, payloadType, Encoding.of("proto"), payloadTransformer);
    }

    @Override // com.google.android.datatransport.TransportFactory
    public <T> Transport<T> getTransport(String name, Class<T> payloadType, Encoding payloadEncoding, Transformer<T, byte[]> payloadTransformer) {
        if (this.supportedPayloadEncodings.contains(payloadEncoding)) {
            return new TransportImpl(this.transportContext, name, payloadEncoding, payloadTransformer, this.transportInternal);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", payloadEncoding, this.supportedPayloadEncodings));
    }
}
