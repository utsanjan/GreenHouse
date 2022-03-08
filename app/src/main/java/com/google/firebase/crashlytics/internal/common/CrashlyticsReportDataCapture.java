package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class CrashlyticsReportDataCapture {
    private static final Map<String, Integer> ARCHITECTURES_BY_NAME;
    private static final String GENERATOR = String.format(Locale.US, "Crashlytics Android SDK/%s", BuildConfig.VERSION_NAME);
    private static final int GENERATOR_TYPE = 3;
    private static final int REPORT_ANDROID_PLATFORM = 4;
    private static final int SESSION_ANDROID_PLATFORM = 3;
    private static final String SIGNAL_DEFAULT = "0";
    private final AppData appData;
    private final Context context;
    private final IdManager idManager;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;

    static {
        HashMap hashMap = new HashMap();
        ARCHITECTURES_BY_NAME = hashMap;
        hashMap.put("armeabi", 5);
        ARCHITECTURES_BY_NAME.put("armeabi-v7a", 6);
        ARCHITECTURES_BY_NAME.put("arm64-v8a", 9);
        ARCHITECTURES_BY_NAME.put("x86", 0);
        ARCHITECTURES_BY_NAME.put("x86_64", 1);
    }

    public CrashlyticsReportDataCapture(Context context, IdManager idManager, AppData appData, StackTraceTrimmingStrategy stackTraceTrimmingStrategy) {
        this.context = context;
        this.idManager = idManager;
        this.appData = appData;
        this.stackTraceTrimmingStrategy = stackTraceTrimmingStrategy;
    }

    public CrashlyticsReport captureReportData(String identifier, long timestamp) {
        return buildReportData().setSession(populateSessionData(identifier, timestamp)).build();
    }

    public CrashlyticsReport captureReportData() {
        return buildReportData().build();
    }

    public CrashlyticsReport.Session.Event captureEventData(Throwable event, Thread eventThread, String type, long timestamp, int eventThreadImportance, int maxChainedExceptions, boolean includeAllThreads) {
        int orientation = this.context.getResources().getConfiguration().orientation;
        TrimmedThrowableData trimmedEvent = new TrimmedThrowableData(event, this.stackTraceTrimmingStrategy);
        return CrashlyticsReport.Session.Event.builder().setType(type).setTimestamp(timestamp).setApp(populateEventApplicationData(orientation, trimmedEvent, eventThread, eventThreadImportance, maxChainedExceptions, includeAllThreads)).setDevice(populateEventDeviceData(orientation)).build();
    }

    private CrashlyticsReport.Builder buildReportData() {
        return CrashlyticsReport.builder().setSdkVersion(BuildConfig.VERSION_NAME).setGmpAppId(this.appData.googleAppId).setInstallationUuid(this.idManager.getCrashlyticsInstallId()).setBuildVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setPlatform(4);
    }

    private CrashlyticsReport.Session populateSessionData(String identifier, long timestamp) {
        return CrashlyticsReport.Session.builder().setStartedAt(timestamp).setIdentifier(identifier).setGenerator(GENERATOR).setApp(populateSessionApplicationData()).setOs(populateSessionOperatingSystemData()).setDevice(populateSessionDeviceData()).setGeneratorType(3).build();
    }

    private CrashlyticsReport.Session.Application populateSessionApplicationData() {
        return CrashlyticsReport.Session.Application.builder().setIdentifier(this.idManager.getAppIdentifier()).setVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setInstallationUuid(this.idManager.getCrashlyticsInstallId()).build();
    }

    private CrashlyticsReport.Session.OperatingSystem populateSessionOperatingSystemData() {
        return CrashlyticsReport.Session.OperatingSystem.builder().setPlatform(3).setVersion(Build.VERSION.RELEASE).setBuildVersion(Build.VERSION.CODENAME).setJailbroken(CommonUtils.isRooted(this.context)).build();
    }

    private CrashlyticsReport.Session.Device populateSessionDeviceData() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int arch = getDeviceArchitecture();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long totalRam = CommonUtils.getTotalRamInBytes();
        long diskSpace = statFs.getBlockCount() * statFs.getBlockSize();
        boolean isEmulator = CommonUtils.isEmulator(this.context);
        int state = CommonUtils.getDeviceState(this.context);
        String manufacturer = Build.MANUFACTURER;
        String modelClass = Build.PRODUCT;
        return CrashlyticsReport.Session.Device.builder().setArch(arch).setModel(Build.MODEL).setCores(availableProcessors).setRam(totalRam).setDiskSpace(diskSpace).setSimulator(isEmulator).setState(state).setManufacturer(manufacturer).setModelClass(modelClass).build();
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int orientation, TrimmedThrowableData trimmedEvent, Thread eventThread, int eventThreadImportance, int maxChainedExceptions, boolean includeAllThreads) {
        Boolean isBackground = null;
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = CommonUtils.getAppProcessInfo(this.appData.packageName, this.context);
        if (runningAppProcessInfo != null) {
            isBackground = Boolean.valueOf(runningAppProcessInfo.importance != 100);
        }
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(isBackground).setUiOrientation(orientation).setExecution(populateExecutionData(trimmedEvent, eventThread, eventThreadImportance, maxChainedExceptions, includeAllThreads)).build();
    }

    private CrashlyticsReport.Session.Event.Device populateEventDeviceData(int orientation) {
        BatteryState battery = BatteryState.get(this.context);
        Float batteryLevel = battery.getBatteryLevel();
        Double batteryLevelDouble = batteryLevel != null ? Double.valueOf(batteryLevel.doubleValue()) : null;
        int batteryVelocity = battery.getBatteryVelocity();
        boolean proximityEnabled = CommonUtils.getProximitySensorEnabled(this.context);
        long usedRamBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(this.context);
        long diskUsedBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        return CrashlyticsReport.Session.Event.Device.builder().setBatteryLevel(batteryLevelDouble).setBatteryVelocity(batteryVelocity).setProximityOn(proximityEnabled).setOrientation(orientation).setRamUsed(usedRamBytes).setDiskUsed(diskUsedBytes).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(TrimmedThrowableData trimmedEvent, Thread eventThread, int eventThreadImportance, int maxChainedExceptions, boolean includeAllThreads) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setThreads(populateThreadsList(trimmedEvent, eventThread, eventThreadImportance, includeAllThreads)).setException(populateExceptionData(trimmedEvent, eventThreadImportance, maxChainedExceptions)).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread> populateThreadsList(TrimmedThrowableData trimmedEvent, Thread eventThread, int eventThreadImportance, boolean includeAllThreads) {
        List<CrashlyticsReport.Session.Event.Application.Execution.Thread> threadsList = new ArrayList<>();
        threadsList.add(populateThreadData(eventThread, trimmedEvent.stacktrace, eventThreadImportance));
        if (includeAllThreads) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                Thread thread = entry.getKey();
                if (!thread.equals(eventThread)) {
                    threadsList.add(populateThreadData(thread, this.stackTraceTrimmingStrategy.getTrimmedStackTrace(entry.getValue())));
                }
            }
        }
        return ImmutableList.from(threadsList);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stacktrace) {
        return populateThreadData(thread, stacktrace, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stacktrace, int importance) {
        return CrashlyticsReport.Session.Event.Application.Execution.Thread.builder().setName(thread.getName()).setImportance(importance).setFrames(ImmutableList.from(populateFramesList(stacktrace, importance))).build();
    }

    private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> populateFramesList(StackTraceElement[] stacktrace, int importance) {
        List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> framesList = new ArrayList<>();
        for (StackTraceElement element : stacktrace) {
            framesList.add(populateFrameData(element, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder().setImportance(importance)));
        }
        return ImmutableList.from(framesList);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedEvent, int eventThreadImportance, int maxChainedExceptions) {
        return populateExceptionData(trimmedEvent, eventThreadImportance, maxChainedExceptions, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedEvent, int eventThreadImportance, int maxChainedExceptions, int chainDepth) {
        String type = trimmedEvent.className;
        String reason = trimmedEvent.localizedMessage;
        StackTraceElement[] stacktrace = trimmedEvent.stacktrace != null ? trimmedEvent.stacktrace : new StackTraceElement[0];
        TrimmedThrowableData cause = trimmedEvent.cause;
        int overflowCount = 0;
        if (chainDepth >= maxChainedExceptions) {
            TrimmedThrowableData skipped = cause;
            while (skipped != null) {
                skipped = skipped.cause;
                overflowCount++;
            }
        }
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder builder = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder().setType(type).setReason(reason).setFrames(ImmutableList.from(populateFramesList(stacktrace, eventThreadImportance))).setOverflowCount(overflowCount);
        if (cause != null && overflowCount == 0) {
            builder.setCausedBy(populateExceptionData(cause, eventThreadImportance, maxChainedExceptions, chainDepth + 1));
        }
        return builder.build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame populateFrameData(StackTraceElement element, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder frameBuilder) {
        long pc = 0;
        if (element.isNativeMethod()) {
            pc = Math.max(element.getLineNumber(), 0L);
        }
        String symbol = element.getClassName() + "." + element.getMethodName();
        String file = element.getFileName();
        long offset = 0;
        if (!element.isNativeMethod() && element.getLineNumber() > 0) {
            offset = element.getLineNumber();
        }
        return frameBuilder.setPc(pc).setSymbol(symbol).setFile(file).setOffset(offset).build();
    }

    private ImmutableList<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> populateBinaryImagesList() {
        return ImmutableList.from(populateBinaryImageData());
    }

    private CrashlyticsReport.Session.Event.Application.Execution.BinaryImage populateBinaryImageData() {
        return CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder().setBaseAddress(0L).setSize(0L).setName(this.appData.packageName).setUuid(this.appData.buildId).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Signal populateSignalData() {
        return CrashlyticsReport.Session.Event.Application.Execution.Signal.builder().setName(SIGNAL_DEFAULT).setCode(SIGNAL_DEFAULT).setAddress(0L).build();
    }

    private static int getDeviceArchitecture() {
        Integer arch;
        String primaryAbi = Build.CPU_ABI;
        if (!TextUtils.isEmpty(primaryAbi) && (arch = ARCHITECTURES_BY_NAME.get(primaryAbi.toLowerCase(Locale.US))) != null) {
            return arch.intValue();
        }
        return 7;
    }
}
