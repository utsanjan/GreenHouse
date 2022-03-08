package com.google.firebase.crashlytics.internal.stacktrace;

/* loaded from: classes.dex */
public class MiddleOutFallbackStrategy implements StackTraceTrimmingStrategy {
    private final int maximumStackSize;
    private final MiddleOutStrategy middleOutStrategy;
    private final StackTraceTrimmingStrategy[] trimmingStrategies;

    public MiddleOutFallbackStrategy(int maximumStackSize, StackTraceTrimmingStrategy... strategies) {
        this.maximumStackSize = maximumStackSize;
        this.trimmingStrategies = strategies;
        this.middleOutStrategy = new MiddleOutStrategy(maximumStackSize);
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stacktrace) {
        StackTraceTrimmingStrategy[] stackTraceTrimmingStrategyArr;
        if (stacktrace.length <= this.maximumStackSize) {
            return stacktrace;
        }
        StackTraceElement[] trimmed = stacktrace;
        for (StackTraceTrimmingStrategy strategy : this.trimmingStrategies) {
            if (trimmed.length <= this.maximumStackSize) {
                break;
            }
            trimmed = strategy.getTrimmedStackTrace(stacktrace);
        }
        if (trimmed.length > this.maximumStackSize) {
            return this.middleOutStrategy.getTrimmedStackTrace(trimmed);
        }
        return trimmed;
    }
}
