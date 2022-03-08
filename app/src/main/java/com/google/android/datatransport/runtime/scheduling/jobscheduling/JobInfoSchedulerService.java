package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* loaded from: classes.dex */
public class JobInfoSchedulerService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters params) {
        String backendName = params.getExtras().getString("backendName");
        String extras = params.getExtras().getString("extras");
        int priority = params.getExtras().getInt("priority");
        int attemptNumber = params.getExtras().getInt("attemptNumber");
        TransportRuntime.initialize(getApplicationContext());
        TransportContext.Builder transportContext = TransportContext.builder().setBackendName(backendName).setPriority(PriorityMapping.valueOf(priority));
        if (extras != null) {
            transportContext.setExtras(Base64.decode(extras, 0));
        }
        TransportRuntime.getInstance().getUploader().upload(transportContext.build(), attemptNumber, JobInfoSchedulerService$$Lambda$1.lambdaFactory$(this, params));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onStartJob$0(JobInfoSchedulerService jobInfoSchedulerService, JobParameters params) {
        jobInfoSchedulerService.jobFinished(params, false);
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
