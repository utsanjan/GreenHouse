package com.google.firebase.database.core.utilities.tuple;

import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class NameAndPriority implements Comparable<NameAndPriority> {
    private ChildKey name;
    private Node priority;

    public NameAndPriority(ChildKey name, Node priority) {
        this.name = name;
        this.priority = priority;
    }

    public ChildKey getName() {
        return this.name;
    }

    public Node getPriority() {
        return this.priority;
    }

    public int compareTo(NameAndPriority o) {
        return NodeUtilities.nameAndPriorityCompare(this.name, this.priority, o.name, o.priority);
    }
}
