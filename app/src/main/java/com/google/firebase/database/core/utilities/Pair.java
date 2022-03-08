package com.google.firebase.database.core.utilities;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public final class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        T t = this.first;
        if (t == null ? pair.first != null : !t.equals(pair.first)) {
            return false;
        }
        U u = this.second;
        if (u == null ? pair.second == null : u.equals(pair.second)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        T t = this.first;
        int i = 0;
        int result = t != null ? t.hashCode() : 0;
        int i2 = result * 31;
        U u = this.second;
        if (u != null) {
            i = u.hashCode();
        }
        int result2 = i2 + i;
        return result2;
    }

    public String toString() {
        return "Pair(" + this.first + "," + this.second + ")";
    }
}
