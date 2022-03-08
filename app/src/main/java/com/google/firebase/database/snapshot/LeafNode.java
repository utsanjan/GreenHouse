package com.google.firebase.database.snapshot;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.snapshot.LeafNode;
import com.google.firebase.database.snapshot.Node;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public abstract class LeafNode<T extends LeafNode> implements Node {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String lazyHash;
    protected final Node priority;

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    public enum LeafType {
        DeferredValue,
        Boolean,
        Number,
        String
    }

    protected abstract int compareLeafValues(T t);

    public abstract boolean equals(Object obj);

    protected abstract LeafType getLeafType();

    public abstract int hashCode();

    /* JADX INFO: Access modifiers changed from: package-private */
    public LeafNode(Node priority) {
        this.priority = priority;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public boolean hasChild(ChildKey childKey) {
        return false;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public boolean isLeafNode() {
        return true;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Node getPriority() {
        return this.priority;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Node getChild(Path path) {
        if (path.isEmpty()) {
            return this;
        }
        if (path.getFront().isPriorityChildName()) {
            return this.priority;
        }
        return EmptyNode.Empty();
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Node updateChild(Path path, Node node) {
        ChildKey front = path.getFront();
        if (front == null) {
            return node;
        }
        if (!node.isEmpty() || front.isPriorityChildName()) {
            return updateImmediateChild(front, EmptyNode.Empty().updateChild(path.popFront(), node));
        }
        return this;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public boolean isEmpty() {
        return false;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public int getChildCount() {
        return 0;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public ChildKey getPredecessorChildKey(ChildKey childKey) {
        return null;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public ChildKey getSuccessorChildKey(ChildKey childKey) {
        return null;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Node getImmediateChild(ChildKey name) {
        if (name.isPriorityChildName()) {
            return this.priority;
        }
        return EmptyNode.Empty();
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Object getValue(boolean useExportFormat) {
        if (!useExportFormat || this.priority.isEmpty()) {
            return getValue();
        }
        Map<String, Object> result = new HashMap<>();
        result.put(".value", getValue());
        result.put(".priority", this.priority.getValue());
        return result;
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Node updateImmediateChild(ChildKey name, Node node) {
        if (name.isPriorityChildName()) {
            return updatePriority(node);
        }
        if (node.isEmpty()) {
            return this;
        }
        return EmptyNode.Empty().updateImmediateChild(name, node).updatePriority(this.priority);
    }

    @Override // com.google.firebase.database.snapshot.Node
    public String getHash() {
        if (this.lazyHash == null) {
            this.lazyHash = Utilities.sha1HexDigest(getHashRepresentation(Node.HashVersion.V1));
        }
        return this.lazyHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.snapshot.LeafNode$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$snapshot$Node$HashVersion;

        static {
            int[] iArr = new int[Node.HashVersion.values().length];
            $SwitchMap$com$google$firebase$database$snapshot$Node$HashVersion = iArr;
            try {
                iArr[Node.HashVersion.V1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$snapshot$Node$HashVersion[Node.HashVersion.V2.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getPriorityHash(Node.HashVersion version) {
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$snapshot$Node$HashVersion[version.ordinal()];
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("Unknown hash version: " + version);
        } else if (this.priority.isEmpty()) {
            return "";
        } else {
            return "priority:" + this.priority.getHashRepresentation(version) + ":";
        }
    }

    @Override // java.lang.Iterable
    public Iterator<NamedNode> iterator() {
        return Collections.emptyList().iterator();
    }

    @Override // com.google.firebase.database.snapshot.Node
    public Iterator<NamedNode> reverseIterator() {
        return Collections.emptyList().iterator();
    }

    private static int compareLongDoubleNodes(LongNode longNode, DoubleNode doubleNode) {
        Double longDoubleValue = Double.valueOf(((Long) longNode.getValue()).longValue());
        return longDoubleValue.compareTo((Double) doubleNode.getValue());
    }

    public int compareTo(Node other) {
        if (other.isEmpty()) {
            return 1;
        }
        if (other instanceof ChildrenNode) {
            return -1;
        }
        if ((this instanceof LongNode) && (other instanceof DoubleNode)) {
            return compareLongDoubleNodes((LongNode) this, (DoubleNode) other);
        }
        if (!(this instanceof DoubleNode) || !(other instanceof LongNode)) {
            return leafCompare((LeafNode) other);
        }
        return compareLongDoubleNodes((LongNode) other, (DoubleNode) this) * (-1);
    }

    protected int leafCompare(LeafNode<?> other) {
        LeafType thisLeafType = getLeafType();
        LeafType otherLeafType = other.getLeafType();
        if (thisLeafType.equals(otherLeafType)) {
            int value = compareLeafValues(other);
            return value;
        }
        int value2 = thisLeafType.compareTo(otherLeafType);
        return value2;
    }

    public String toString() {
        String str = getValue(true).toString();
        if (str.length() <= 100) {
            return str;
        }
        return str.substring(0, 100) + "...";
    }
}
