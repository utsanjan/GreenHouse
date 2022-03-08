package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    static AsyncTimeout head;
    private boolean inQueue;
    @Nullable
    private AsyncTimeout next;
    private long timeoutAt;

    public final void enter() {
        if (!this.inQueue) {
            long timeoutNanos = timeoutNanos();
            boolean hasDeadline = hasDeadline();
            if (timeoutNanos != 0 || hasDeadline) {
                this.inQueue = true;
                scheduleTimeout(this, timeoutNanos, hasDeadline);
                return;
            }
            return;
        }
        throw new IllegalStateException("Unbalanced enter/exit");
    }

    private static synchronized void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
        synchronized (AsyncTimeout.class) {
            if (head == null) {
                head = new AsyncTimeout();
                new Watchdog().start();
            }
            long now = System.nanoTime();
            if (timeoutNanos != 0 && hasDeadline) {
                node.timeoutAt = Math.min(timeoutNanos, node.deadlineNanoTime() - now) + now;
            } else if (timeoutNanos != 0) {
                node.timeoutAt = now + timeoutNanos;
            } else if (hasDeadline) {
                node.timeoutAt = node.deadlineNanoTime();
            } else {
                throw new AssertionError();
            }
            long remainingNanos = node.remainingNanos(now);
            AsyncTimeout prev = head;
            while (prev.next != null && remainingNanos >= prev.next.remainingNanos(now)) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
            if (prev == head) {
                AsyncTimeout.class.notify();
            }
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    private static synchronized boolean cancelScheduledTimeout(AsyncTimeout node) {
        synchronized (AsyncTimeout.class) {
            for (AsyncTimeout prev = head; prev != null; prev = prev.next) {
                if (prev.next == node) {
                    prev.next = node.next;
                    node.next = null;
                    return false;
                }
            }
            return true;
        }
    }

    private long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    protected void timedOut() {
    }

    public final Sink sink(final Sink sink) {
        return new Sink() { // from class: okio.AsyncTimeout.1
            @Override // okio.Sink
            public void write(Buffer source, long byteCount) throws IOException {
                Util.checkOffsetAndCount(source.size, 0L, byteCount);
                while (byteCount > 0) {
                    long toWrite = 0;
                    Segment s = source.head;
                    while (true) {
                        if (toWrite >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            break;
                        }
                        int segmentSize = s.limit - s.pos;
                        toWrite += segmentSize;
                        if (toWrite >= byteCount) {
                            toWrite = byteCount;
                            break;
                        }
                        s = s.next;
                    }
                    boolean throwOnTimeout = false;
                    AsyncTimeout.this.enter();
                    try {
                        try {
                            sink.write(source, toWrite);
                            byteCount -= toWrite;
                            throwOnTimeout = true;
                        } catch (IOException e) {
                            throw AsyncTimeout.this.exit(e);
                        }
                    } finally {
                        AsyncTimeout.this.exit(throwOnTimeout);
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    try {
                        sink.flush();
                        throwOnTimeout = true;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    try {
                        sink.close();
                        throwOnTimeout = true;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + ")";
            }
        };
    }

    public final Source source(final Source source) {
        return new Source() { // from class: okio.AsyncTimeout.2
            @Override // okio.Source
            public long read(Buffer sink, long byteCount) throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    try {
                        long result = source.read(sink, byteCount);
                        throwOnTimeout = true;
                        return result;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                boolean throwOnTimeout = false;
                try {
                    try {
                        source.close();
                        throwOnTimeout = true;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Source
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ")";
            }
        };
    }

    final void exit(boolean throwOnTimeout) throws IOException {
        boolean timedOut = exit();
        if (timedOut && throwOnTimeout) {
            throw newTimeoutException(null);
        }
    }

    final IOException exit(IOException cause) throws IOException {
        return !exit() ? cause : newTimeoutException(cause);
    }

    protected IOException newTimeoutException(@Nullable IOException cause) {
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class Watchdog extends Thread {
        Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
            r1.timedOut();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                java.lang.Class<okio.AsyncTimeout> r0 = okio.AsyncTimeout.class
                monitor-enter(r0)     // Catch: InterruptedException -> 0x001c
                okio.AsyncTimeout r1 = okio.AsyncTimeout.awaitTimeout()     // Catch: all -> 0x0019
                if (r1 != 0) goto L_0x000b
                monitor-exit(r0)     // Catch: all -> 0x0019
                goto L_0x0000
            L_0x000b:
                okio.AsyncTimeout r2 = okio.AsyncTimeout.head     // Catch: all -> 0x0019
                if (r1 != r2) goto L_0x0014
                r2 = 0
                okio.AsyncTimeout.head = r2     // Catch: all -> 0x0019
                monitor-exit(r0)     // Catch: all -> 0x0019
                return
            L_0x0014:
                monitor-exit(r0)     // Catch: all -> 0x0019
                r1.timedOut()     // Catch: InterruptedException -> 0x001c
                goto L_0x001d
            L_0x0019:
                r1 = move-exception
                monitor-exit(r0)     // Catch: all -> 0x0019
                throw r1     // Catch: InterruptedException -> 0x001c
            L_0x001c:
                r0 = move-exception
            L_0x001d:
                goto L_0x0000
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    @Nullable
    static AsyncTimeout awaitTimeout() throws InterruptedException {
        AsyncTimeout node = head.next;
        if (node == null) {
            long startNanos = System.nanoTime();
            AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next != null || System.nanoTime() - startNanos < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return head;
        }
        long startNanos2 = System.nanoTime();
        long waitNanos = node.remainingNanos(startNanos2);
        if (waitNanos > 0) {
            long waitMillis = waitNanos / 1000000;
            AsyncTimeout.class.wait(waitMillis, (int) (waitNanos - (1000000 * waitMillis)));
            return null;
        }
        head.next = node.next;
        node.next = null;
        return node;
    }
}
