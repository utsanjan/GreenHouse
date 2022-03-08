package com.google.firebase.database.core.operation;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.snapshot.ChildKey;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class ListenComplete extends Operation {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public ListenComplete(OperationSource source, Path path) {
        super(Operation.OperationType.ListenComplete, source, path);
    }

    @Override // com.google.firebase.database.core.operation.Operation
    public Operation operationForChild(ChildKey childKey) {
        if (this.path.isEmpty()) {
            return new ListenComplete(this.source, Path.getEmptyPath());
        }
        return new ListenComplete(this.source, this.path.popFront());
    }

    public String toString() {
        return String.format("ListenComplete { path=%s, source=%s }", getPath(), getSource());
    }
}
