package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* compiled from: com.google.firebase:firebase-encoders-json@@16.1.0 */
/* loaded from: classes.dex */
final /* synthetic */ class JsonDataEncoderBuilder$$Lambda$1 implements ObjectEncoder {
    private static final JsonDataEncoderBuilder$$Lambda$1 instance = new JsonDataEncoderBuilder$$Lambda$1();

    private JsonDataEncoderBuilder$$Lambda$1() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public void encode(Object obj, ObjectEncoderContext objectEncoderContext) {
        JsonDataEncoderBuilder.lambda$static$0(obj, objectEncoderContext);
    }
}
