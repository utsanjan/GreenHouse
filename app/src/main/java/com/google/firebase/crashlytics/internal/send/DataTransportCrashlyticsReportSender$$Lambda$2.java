package com.google.firebase.crashlytics.internal.send;

import com.google.android.datatransport.Transformer;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final /* synthetic */ class DataTransportCrashlyticsReportSender$$Lambda$2 implements Transformer {
    private static final DataTransportCrashlyticsReportSender$$Lambda$2 instance = new DataTransportCrashlyticsReportSender$$Lambda$2();

    private DataTransportCrashlyticsReportSender$$Lambda$2() {
    }

    @Override // com.google.android.datatransport.Transformer
    public Object apply(Object obj) {
        return DataTransportCrashlyticsReportSender.lambda$static$0((CrashlyticsReport) obj);
    }
}
