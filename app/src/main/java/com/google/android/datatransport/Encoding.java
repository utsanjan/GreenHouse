package com.google.android.datatransport;

/* compiled from: com.google.android.datatransport:transport-api@@2.2.0 */
/* loaded from: classes.dex */
public final class Encoding {
    private final String name;

    public static Encoding of(String name) {
        return new Encoding(name);
    }

    public String getName() {
        return this.name;
    }

    private Encoding(String name) {
        if (name != null) {
            this.name = name;
            return;
        }
        throw new NullPointerException("name is null");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Encoding)) {
            return false;
        }
        return this.name.equals(((Encoding) o).name);
    }

    public int hashCode() {
        int h = 1000003 ^ this.name.hashCode();
        return h;
    }

    public String toString() {
        return "Encoding{name=\"" + this.name + "\"}";
    }
}
