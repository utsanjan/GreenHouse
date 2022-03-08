package com.google.firebase.database.snapshot;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class KeyIndex extends Index {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final KeyIndex INSTANCE = new KeyIndex();

    public static KeyIndex getInstance() {
        return INSTANCE;
    }

    private KeyIndex() {
    }

    @Override // com.google.firebase.database.snapshot.Index
    public boolean isDefinedOn(Node a) {
        return true;
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode makePost(ChildKey name, Node value) {
        return new NamedNode(ChildKey.fromString((String) value.getValue()), EmptyNode.Empty());
    }

    @Override // com.google.firebase.database.snapshot.Index
    public NamedNode maxPost() {
        return NamedNode.getMaxNode();
    }

    @Override // com.google.firebase.database.snapshot.Index
    public String getQueryDefinition() {
        return ".key";
    }

    public int compare(NamedNode o1, NamedNode o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override // java.util.Comparator
    public boolean equals(Object o) {
        return o instanceof KeyIndex;
    }

    public int hashCode() {
        return 37;
    }

    public String toString() {
        return "KeyIndex";
    }
}
