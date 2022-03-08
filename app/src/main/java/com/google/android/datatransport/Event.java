package com.google.android.datatransport;

/* compiled from: com.google.android.datatransport:transport-api@@2.2.0 */
/* loaded from: classes.dex */
public abstract class Event<T> {
    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public static <T> Event<T> ofData(int code, T payload) {
        return new AutoValue_Event(Integer.valueOf(code), payload, Priority.DEFAULT);
    }

    public static <T> Event<T> ofData(T payload) {
        return new AutoValue_Event(null, payload, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(int code, T value) {
        return new AutoValue_Event(Integer.valueOf(code), value, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofTelemetry(T value) {
        return new AutoValue_Event(null, value, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(int code, T value) {
        return new AutoValue_Event(Integer.valueOf(code), value, Priority.HIGHEST);
    }

    public static <T> Event<T> ofUrgent(T value) {
        return new AutoValue_Event(null, value, Priority.HIGHEST);
    }
}
