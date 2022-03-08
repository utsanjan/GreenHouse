package com.google.firebase.crashlytics.internal.stacktrace;

/* loaded from: classes.dex */
public class MiddleOutStrategy implements StackTraceTrimmingStrategy {
    private final int trimmedSize;

    public MiddleOutStrategy(int trimmedSize) {
        this.trimmedSize = trimmedSize;
    }

    @Override // com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy
    public StackTraceElement[] getTrimmedStackTrace(StackTraceElement[] stacktrace) {
        int length = stacktrace.length;
        int i = this.trimmedSize;
        if (length <= i) {
            return stacktrace;
        }
        int backHalf = i / 2;
        int frontHalf = i - backHalf;
        StackTraceElement[] trimmed = new StackTraceElement[i];
        System.arraycopy(stacktrace, 0, trimmed, 0, frontHalf);
        System.arraycopy(stacktrace, stacktrace.length - backHalf, trimmed, frontHalf, backHalf);
        return trimmed;
    }
}
