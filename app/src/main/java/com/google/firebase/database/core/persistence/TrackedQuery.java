package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.view.QuerySpec;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public final class TrackedQuery {
    public final boolean active;
    public final boolean complete;
    public final long id;
    public final long lastUse;
    public final QuerySpec querySpec;

    public TrackedQuery(long id, QuerySpec querySpec, long lastUse, boolean complete, boolean active) {
        this.id = id;
        if (!querySpec.loadsAllData() || querySpec.isDefault()) {
            this.querySpec = querySpec;
            this.lastUse = lastUse;
            this.complete = complete;
            this.active = active;
            return;
        }
        throw new IllegalArgumentException("Can't create TrackedQuery for a non-default query that loads all data");
    }

    public TrackedQuery updateLastUse(long lastUse) {
        return new TrackedQuery(this.id, this.querySpec, lastUse, this.complete, this.active);
    }

    public TrackedQuery setComplete() {
        return new TrackedQuery(this.id, this.querySpec, this.lastUse, true, this.active);
    }

    public TrackedQuery setActiveState(boolean isActive) {
        return new TrackedQuery(this.id, this.querySpec, this.lastUse, this.complete, isActive);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        TrackedQuery query = (TrackedQuery) o;
        return this.id == query.id && this.querySpec.equals(query.querySpec) && this.lastUse == query.lastUse && this.complete == query.complete && this.active == query.active;
    }

    public int hashCode() {
        int result = Long.valueOf(this.id).hashCode();
        return (((((((result * 31) + this.querySpec.hashCode()) * 31) + Long.valueOf(this.lastUse).hashCode()) * 31) + Boolean.valueOf(this.complete).hashCode()) * 31) + Boolean.valueOf(this.active).hashCode();
    }

    public String toString() {
        return "TrackedQuery{id=" + this.id + ", querySpec=" + this.querySpec + ", lastUse=" + this.lastUse + ", complete=" + this.complete + ", active=" + this.active + "}";
    }
}
