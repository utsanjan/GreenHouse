package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.core.Path;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class PathAndId {
    private long id;
    private Path path;

    public PathAndId(Path path, long id) {
        this.path = path;
        this.id = id;
    }

    public Path getPath() {
        return this.path;
    }

    public long getId() {
        return this.id;
    }
}
