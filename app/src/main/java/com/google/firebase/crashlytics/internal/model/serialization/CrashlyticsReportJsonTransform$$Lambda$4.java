package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;

/* loaded from: classes.dex */
final /* synthetic */ class CrashlyticsReportJsonTransform$$Lambda$4 implements CrashlyticsReportJsonTransform.ObjectParser {
    private static final CrashlyticsReportJsonTransform$$Lambda$4 instance = new CrashlyticsReportJsonTransform$$Lambda$4();

    private CrashlyticsReportJsonTransform$$Lambda$4() {
    }

    @Override // com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.ObjectParser
    public Object parse(JsonReader jsonReader) {
        return CrashlyticsReportJsonTransform.access$lambda$1(jsonReader);
    }
}
