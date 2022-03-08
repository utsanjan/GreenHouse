package com.google.firebase.storage;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
final /* synthetic */ class TaskListenerImpl$$Lambda$1 implements Runnable {
    private final TaskListenerImpl arg$1;
    private final Object arg$2;

    private TaskListenerImpl$$Lambda$1(TaskListenerImpl taskListenerImpl, Object obj) {
        this.arg$1 = taskListenerImpl;
        this.arg$2 = obj;
    }

    public static Runnable lambdaFactory$(TaskListenerImpl taskListenerImpl, Object obj) {
        return new TaskListenerImpl$$Lambda$1(taskListenerImpl, obj);
    }

    @Override // java.lang.Runnable
    public void run() {
        TaskListenerImpl.lambda$addListener$0(this.arg$1, this.arg$2);
    }
}
