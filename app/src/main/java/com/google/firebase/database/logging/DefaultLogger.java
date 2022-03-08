package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class DefaultLogger implements Logger {
    private final Set<String> enabledComponents;
    private final Logger.Level minLevel;

    public DefaultLogger(Logger.Level level, List<String> enabledComponents) {
        if (enabledComponents != null) {
            this.enabledComponents = new HashSet(enabledComponents);
        } else {
            this.enabledComponents = null;
        }
        this.minLevel = level;
    }

    @Override // com.google.firebase.database.logging.Logger
    public Logger.Level getLogLevel() {
        return this.minLevel;
    }

    @Override // com.google.firebase.database.logging.Logger
    public void onLogMessage(Logger.Level level, String tag, String message, long msTimestamp) {
        if (shouldLog(level, tag)) {
            String toLog = buildLogMessage(level, tag, message, msTimestamp);
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$logging$Logger$Level[level.ordinal()];
            if (i == 1) {
                error(tag, toLog);
            } else if (i == 2) {
                warn(tag, toLog);
            } else if (i == 3) {
                info(tag, toLog);
            } else if (i == 4) {
                debug(tag, toLog);
            } else {
                throw new RuntimeException("Should not reach here!");
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.logging.DefaultLogger$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$logging$Logger$Level;

        static {
            int[] iArr = new int[Logger.Level.values().length];
            $SwitchMap$com$google$firebase$database$logging$Logger$Level = iArr;
            try {
                iArr[Logger.Level.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.DEBUG.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    protected String buildLogMessage(Logger.Level level, String tag, String message, long msTimestamp) {
        Date now = new Date(msTimestamp);
        return now.toString() + " [" + level + "] " + tag + ": " + message;
    }

    protected void error(String tag, String toLog) {
        System.err.println(toLog);
    }

    protected void warn(String tag, String toLog) {
        System.out.println(toLog);
    }

    protected void info(String tag, String toLog) {
        System.out.println(toLog);
    }

    protected void debug(String tag, String toLog) {
        System.out.println(toLog);
    }

    protected boolean shouldLog(Logger.Level level, String tag) {
        return level.ordinal() >= this.minLevel.ordinal() && (this.enabledComponents == null || level.ordinal() > Logger.Level.DEBUG.ordinal() || this.enabledComponents.contains(tag));
    }
}
