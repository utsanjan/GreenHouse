package com.google.firebase.database.connection;

import java.util.List;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class RangeMerge {
    private final List<String> optExclusiveStart;
    private final List<String> optInclusiveEnd;
    private final Object snap;

    public RangeMerge(List<String> optExclusiveStart, List<String> optInclusiveEnd, Object snap) {
        this.optExclusiveStart = optExclusiveStart;
        this.optInclusiveEnd = optInclusiveEnd;
        this.snap = snap;
    }

    public List<String> getOptExclusiveStart() {
        return this.optExclusiveStart;
    }

    public List<String> getOptInclusiveEnd() {
        return this.optInclusiveEnd;
    }

    public Object getSnap() {
        return this.snap;
    }
}
