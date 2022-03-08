package pl.droidsonroids.gif;

/* loaded from: classes.dex */
class ConditionVariable {
    private volatile boolean mCondition;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void set(boolean state) {
        if (state) {
            open();
        } else {
            close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void open() {
        boolean old = this.mCondition;
        this.mCondition = true;
        if (!old) {
            notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void close() {
        this.mCondition = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void block() throws InterruptedException {
        while (!this.mCondition) {
            wait();
        }
    }
}
