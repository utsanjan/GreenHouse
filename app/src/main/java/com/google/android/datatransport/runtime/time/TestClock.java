package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public class TestClock implements Clock {
    private final AtomicLong timestamp;

    public TestClock(long initialTimestamp) {
        this.timestamp = new AtomicLong(initialTimestamp);
    }

    @Override // com.google.android.datatransport.runtime.time.Clock
    public long getTime() {
        return this.timestamp.get();
    }

    public void tick() {
        advance(1L);
    }

    public void advance(long value) {
        if (value >= 0) {
            this.timestamp.addAndGet(value);
            return;
        }
        throw new IllegalArgumentException("cannot advance time backwards.");
    }
}
