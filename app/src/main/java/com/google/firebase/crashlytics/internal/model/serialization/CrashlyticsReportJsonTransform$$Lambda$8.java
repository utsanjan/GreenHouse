package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;

/* loaded from: classes.dex */
final /* synthetic */ class CrashlyticsReportJsonTransform$$Lambda$8 implements CrashlyticsReportJsonTransform.ObjectParser {
    private static final CrashlyticsReportJsonTransform$$Lambda$8 instance = new CrashlyticsReportJsonTransform$$Lambda$8();

    private CrashlyticsReportJsonTransform$$Lambda$8() {
    }

    @Override // com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.ObjectParser
    public Object parse(JsonReader jsonReader) {
        return CrashlyticsReportJsonTransform.access$lambda$5(jsonReader);
    }
}
