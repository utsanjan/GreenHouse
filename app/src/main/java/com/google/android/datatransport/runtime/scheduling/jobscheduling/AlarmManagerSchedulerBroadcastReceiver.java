package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* loaded from: classes.dex */
public class AlarmManagerSchedulerBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Runnable runnable;
        String backendName = intent.getData().getQueryParameter("backendName");
        String extras = intent.getData().getQueryParameter("extras");
        int priority = Integer.valueOf(intent.getData().getQueryParameter("priority")).intValue();
        int attemptNumber = intent.getExtras().getInt("attemptNumber");
        TransportRuntime.initialize(context);
        TransportContext.Builder transportContext = TransportContext.builder().setBackendName(backendName).setPriority(PriorityMapping.valueOf(priority));
        if (extras != null) {
            transportContext.setExtras(Base64.decode(extras, 0));
        }
        Uploader uploader = TransportRuntime.getInstance().getUploader();
        TransportContext build = transportContext.build();
        runnable = AlarmManagerSchedulerBroadcastReceiver$$Lambda$1.instance;
        uploader.upload(build, attemptNumber, runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onReceive$0() {
    }
}
