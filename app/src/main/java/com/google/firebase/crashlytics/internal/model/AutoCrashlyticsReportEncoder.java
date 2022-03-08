package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;

/* loaded from: classes.dex */
public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 1;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    private AutoCrashlyticsReportEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> cfg) {
        cfg.registerEncoder(CrashlyticsReport.class, CrashlyticsReportEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport.class, CrashlyticsReportEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.class, CrashlyticsReportSessionEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session.class, CrashlyticsReportSessionEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Application.class, CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Application.class, CrashlyticsReportSessionApplicationEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Application.Organization.class, CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Application_Organization.class, CrashlyticsReportSessionApplicationOrganizationEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.User.class, CrashlyticsReportSessionUserEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_User.class, CrashlyticsReportSessionUserEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.OperatingSystem.class, CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_OperatingSystem.class, CrashlyticsReportSessionOperatingSystemEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Device.class, CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Device.class, CrashlyticsReportSessionDeviceEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.class, CrashlyticsReportSessionEventEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event.class, CrashlyticsReportSessionEventEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.class, CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application.class, CrashlyticsReportSessionEventApplicationEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.class, CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution.class, CrashlyticsReportSessionEventApplicationExecutionEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.class, CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread.class, CrashlyticsReportSessionEventApplicationExecutionThreadEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Thread_Frame.class, CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Exception.class, CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Exception.class, CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Signal.class, CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_Signal.class, CrashlyticsReportSessionEventApplicationExecutionSignalEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Application_Execution_BinaryImage.class, CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.CustomAttribute.class, CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_CustomAttribute.class, CrashlyticsReportCustomAttributeEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Device.class, CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Device.class, CrashlyticsReportSessionEventDeviceEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.Session.Event.Log.class, CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_Session_Event_Log.class, CrashlyticsReportSessionEventLogEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.FilesPayload.class, CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload.class, CrashlyticsReportFilesPayloadEncoder.INSTANCE);
        cfg.registerEncoder(CrashlyticsReport.FilesPayload.File.class, CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
        cfg.registerEncoder(AutoValue_CrashlyticsReport_FilesPayload_File.class, CrashlyticsReportFilesPayloadFileEncoder.INSTANCE);
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportEncoder implements ObjectEncoder<CrashlyticsReport> {
        static final CrashlyticsReportEncoder INSTANCE = new CrashlyticsReportEncoder();

        private CrashlyticsReportEncoder() {
        }

        public void encode(CrashlyticsReport value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("sdkVersion", value.getSdkVersion());
            ctx.add("gmpAppId", value.getGmpAppId());
            ctx.add("platform", value.getPlatform());
            ctx.add("installationUuid", value.getInstallationUuid());
            ctx.add("buildVersion", value.getBuildVersion());
            ctx.add("displayVersion", value.getDisplayVersion());
            ctx.add("session", value.getSession());
            ctx.add("ndkPayload", value.getNdkPayload());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEncoder implements ObjectEncoder<CrashlyticsReport.Session> {
        static final CrashlyticsReportSessionEncoder INSTANCE = new CrashlyticsReportSessionEncoder();

        private CrashlyticsReportSessionEncoder() {
        }

        public void encode(CrashlyticsReport.Session value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("generator", value.getGenerator());
            ctx.add("identifier", value.getIdentifierUtf8Bytes());
            ctx.add("startedAt", value.getStartedAt());
            ctx.add("endedAt", value.getEndedAt());
            ctx.add("crashed", value.isCrashed());
            ctx.add("app", value.getApp());
            ctx.add("user", value.getUser());
            ctx.add("os", value.getOs());
            ctx.add("device", value.getDevice());
            ctx.add("events", value.getEvents());
            ctx.add("generatorType", value.getGeneratorType());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application> {
        static final CrashlyticsReportSessionApplicationEncoder INSTANCE = new CrashlyticsReportSessionApplicationEncoder();

        private CrashlyticsReportSessionApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("identifier", value.getIdentifier());
            ctx.add("version", value.getVersion());
            ctx.add("displayVersion", value.getDisplayVersion());
            ctx.add("organization", value.getOrganization());
            ctx.add("installationUuid", value.getInstallationUuid());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionApplicationOrganizationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {
        static final CrashlyticsReportSessionApplicationOrganizationEncoder INSTANCE = new CrashlyticsReportSessionApplicationOrganizationEncoder();

        private CrashlyticsReportSessionApplicationOrganizationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Application.Organization value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("clsId", value.getClsId());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionUserEncoder implements ObjectEncoder<CrashlyticsReport.Session.User> {
        static final CrashlyticsReportSessionUserEncoder INSTANCE = new CrashlyticsReportSessionUserEncoder();

        private CrashlyticsReportSessionUserEncoder() {
        }

        public void encode(CrashlyticsReport.Session.User value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("identifier", value.getIdentifier());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionOperatingSystemEncoder implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {
        static final CrashlyticsReportSessionOperatingSystemEncoder INSTANCE = new CrashlyticsReportSessionOperatingSystemEncoder();

        private CrashlyticsReportSessionOperatingSystemEncoder() {
        }

        public void encode(CrashlyticsReport.Session.OperatingSystem value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("platform", value.getPlatform());
            ctx.add("version", value.getVersion());
            ctx.add("buildVersion", value.getBuildVersion());
            ctx.add("jailbroken", value.isJailbroken());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Device> {
        static final CrashlyticsReportSessionDeviceEncoder INSTANCE = new CrashlyticsReportSessionDeviceEncoder();

        private CrashlyticsReportSessionDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Device value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("arch", value.getArch());
            ctx.add("model", value.getModel());
            ctx.add("cores", value.getCores());
            ctx.add("ram", value.getRam());
            ctx.add("diskSpace", value.getDiskSpace());
            ctx.add("simulator", value.isSimulator());
            ctx.add("state", value.getState());
            ctx.add("manufacturer", value.getManufacturer());
            ctx.add("modelClass", value.getModelClass());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event> {
        static final CrashlyticsReportSessionEventEncoder INSTANCE = new CrashlyticsReportSessionEventEncoder();

        private CrashlyticsReportSessionEventEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("timestamp", value.getTimestamp());
            ctx.add("type", value.getType());
            ctx.add("app", value.getApp());
            ctx.add("device", value.getDevice());
            ctx.add("log", value.getLog());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {
        static final CrashlyticsReportSessionEventApplicationEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationEncoder();

        private CrashlyticsReportSessionEventApplicationEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("execution", value.getExecution());
            ctx.add("customAttributes", value.getCustomAttributes());
            ctx.add("background", value.getBackground());
            ctx.add("uiOrientation", value.getUiOrientation());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {
        static final CrashlyticsReportSessionEventApplicationExecutionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("threads", value.getThreads());
            ctx.add("exception", value.getException());
            ctx.add("signal", value.getSignal());
            ctx.add("binaries", value.getBinaries());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionThreadEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("name", value.getName());
            ctx.add("importance", value.getImportance());
            ctx.add("frames", value.getFrames());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {
        static final CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionThreadFrameEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("pc", value.getPc());
            ctx.add("symbol", value.getSymbol());
            ctx.add("file", value.getFile());
            ctx.add("offset", value.getOffset());
            ctx.add("importance", value.getImportance());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {
        static final CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionExceptionEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("type", value.getType());
            ctx.add("reason", value.getReason());
            ctx.add("frames", value.getFrames());
            ctx.add("causedBy", value.getCausedBy());
            ctx.add("overflowCount", value.getOverflowCount());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionSignalEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {
        static final CrashlyticsReportSessionEventApplicationExecutionSignalEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionSignalEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionSignalEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("name", value.getName());
            ctx.add("code", value.getCode());
            ctx.add("address", value.getAddress());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {
        static final CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder INSTANCE = new CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder();

        private CrashlyticsReportSessionEventApplicationExecutionBinaryImageEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("baseAddress", value.getBaseAddress());
            ctx.add("size", value.getSize());
            ctx.add("name", value.getName());
            ctx.add("uuid", value.getUuidUtf8Bytes());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportCustomAttributeEncoder implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {
        static final CrashlyticsReportCustomAttributeEncoder INSTANCE = new CrashlyticsReportCustomAttributeEncoder();

        private CrashlyticsReportCustomAttributeEncoder() {
        }

        public void encode(CrashlyticsReport.CustomAttribute value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("key", value.getKey());
            ctx.add("value", value.getValue());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventDeviceEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {
        static final CrashlyticsReportSessionEventDeviceEncoder INSTANCE = new CrashlyticsReportSessionEventDeviceEncoder();

        private CrashlyticsReportSessionEventDeviceEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Device value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("batteryLevel", value.getBatteryLevel());
            ctx.add("batteryVelocity", value.getBatteryVelocity());
            ctx.add("proximityOn", value.isProximityOn());
            ctx.add("orientation", value.getOrientation());
            ctx.add("ramUsed", value.getRamUsed());
            ctx.add("diskUsed", value.getDiskUsed());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportSessionEventLogEncoder implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {
        static final CrashlyticsReportSessionEventLogEncoder INSTANCE = new CrashlyticsReportSessionEventLogEncoder();

        private CrashlyticsReportSessionEventLogEncoder() {
        }

        public void encode(CrashlyticsReport.Session.Event.Log value, ObjectEncoderContext ctx) throws IOException {
            ctx.add(FirebaseAnalytics.Param.CONTENT, value.getContent());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportFilesPayloadEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload> {
        static final CrashlyticsReportFilesPayloadEncoder INSTANCE = new CrashlyticsReportFilesPayloadEncoder();

        private CrashlyticsReportFilesPayloadEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("files", value.getFiles());
            ctx.add("orgId", value.getOrgId());
        }
    }

    /* loaded from: classes.dex */
    private static final class CrashlyticsReportFilesPayloadFileEncoder implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {
        static final CrashlyticsReportFilesPayloadFileEncoder INSTANCE = new CrashlyticsReportFilesPayloadFileEncoder();

        private CrashlyticsReportFilesPayloadFileEncoder() {
        }

        public void encode(CrashlyticsReport.FilesPayload.File value, ObjectEncoderContext ctx) throws IOException {
            ctx.add("filename", value.getFilename());
            ctx.add("contents", value.getContents());
        }
    }
}
