package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
public class SQLiteEventStore implements EventStore, SynchronizationGuard {
    private static final int LOCK_RETRY_BACK_OFF_MILLIS = 50;
    private static final String LOG_TAG = "SQLiteEventStore";
    static final int MAX_RETRIES = 10;
    private static final Encoding PROTOBUF_ENCODING = Encoding.of("proto");
    private final EventStoreConfig config;
    private final Clock monotonicClock;
    private final SchemaManager schemaManager;
    private final Clock wallClock;

    /* loaded from: classes.dex */
    public interface Function<T, U> {
        U apply(T t);
    }

    /* loaded from: classes.dex */
    public interface Producer<T> {
        T produce();
    }

    @Inject
    public SQLiteEventStore(Clock wallClock, Clock clock, EventStoreConfig config, SchemaManager schemaManager) {
        this.schemaManager = schemaManager;
        this.wallClock = wallClock;
        this.monotonicClock = clock;
        this.config = config;
    }

    SQLiteDatabase getDb() {
        Function function;
        SchemaManager schemaManager = this.schemaManager;
        schemaManager.getClass();
        Producer lambdaFactory$ = SQLiteEventStore$$Lambda$1.lambdaFactory$(schemaManager);
        function = SQLiteEventStore$$Lambda$4.instance;
        return (SQLiteDatabase) retryIfDbLocked(lambdaFactory$, function);
    }

    public static /* synthetic */ SQLiteDatabase lambda$getDb$0(Throwable ex) {
        throw new SynchronizationException("Timed out while trying to open db.", ex);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public PersistedEvent persist(TransportContext transportContext, EventInternal event) {
        Logging.d(LOG_TAG, "Storing event with priority=%s, name=%s for destination %s", transportContext.getPriority(), event.getTransportName(), transportContext.getBackendName());
        long newRowId = ((Long) inTransaction(SQLiteEventStore$$Lambda$5.lambdaFactory$(this, transportContext, event))).longValue();
        if (newRowId < 1) {
            return null;
        }
        return PersistedEvent.create(newRowId, transportContext, event);
    }

    public static /* synthetic */ Long lambda$persist$1(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, EventInternal event, SQLiteDatabase db) {
        long newEventId;
        if (sQLiteEventStore.isStorageAtLimit()) {
            return -1L;
        }
        long contextId = sQLiteEventStore.ensureTransportContext(db, transportContext);
        int maxBlobSizePerRow = sQLiteEventStore.config.getMaxBlobByteSizePerRow();
        byte[] payloadBytes = event.getEncodedPayload().getBytes();
        boolean inline = payloadBytes.length <= maxBlobSizePerRow;
        ContentValues values = new ContentValues();
        values.put("context_id", Long.valueOf(contextId));
        values.put("transport_name", event.getTransportName());
        values.put("timestamp_ms", Long.valueOf(event.getEventMillis()));
        values.put("uptime_ms", Long.valueOf(event.getUptimeMillis()));
        values.put("payload_encoding", event.getEncodedPayload().getEncoding().getName());
        values.put("code", event.getCode());
        values.put("num_attempts", (Integer) 0);
        values.put("inline", Boolean.valueOf(inline));
        values.put("payload", inline ? payloadBytes : new byte[0]);
        long newEventId2 = db.insert("events", null, values);
        if (!inline) {
            double length = payloadBytes.length;
            newEventId = newEventId2;
            double d = maxBlobSizePerRow;
            Double.isNaN(length);
            Double.isNaN(d);
            int numChunks = (int) Math.ceil(length / d);
            for (int chunk = 1; chunk <= numChunks; chunk++) {
                byte[] chunkBytes = Arrays.copyOfRange(payloadBytes, (chunk - 1) * maxBlobSizePerRow, Math.min(chunk * maxBlobSizePerRow, payloadBytes.length));
                ContentValues payloadValues = new ContentValues();
                payloadValues.put("event_id", Long.valueOf(newEventId));
                payloadValues.put("sequence_num", Integer.valueOf(chunk));
                payloadValues.put("bytes", chunkBytes);
                db.insert("event_payloads", null, payloadValues);
            }
        } else {
            newEventId = newEventId2;
        }
        for (Map.Entry<String, String> entry : event.getMetadata().entrySet()) {
            ContentValues metadata = new ContentValues();
            metadata.put("event_id", Long.valueOf(newEventId));
            metadata.put("name", entry.getKey());
            metadata.put("value", entry.getValue());
            db.insert("event_metadata", null, metadata);
        }
        return Long.valueOf(newEventId);
    }

    private long ensureTransportContext(SQLiteDatabase db, TransportContext transportContext) {
        Long existingId = getTransportContextId(db, transportContext);
        if (existingId != null) {
            return existingId.longValue();
        }
        ContentValues record = new ContentValues();
        record.put("backend_name", transportContext.getBackendName());
        record.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        record.put("next_request_ms", (Integer) 0);
        if (transportContext.getExtras() != null) {
            record.put("extras", Base64.encodeToString(transportContext.getExtras(), 0));
        }
        return db.insert("transport_contexts", null, record);
    }

    private Long getTransportContextId(SQLiteDatabase db, TransportContext transportContext) {
        Function function;
        StringBuilder selection = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList<String> selectionArgs = new ArrayList<>(Arrays.asList(transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))));
        if (transportContext.getExtras() != null) {
            selection.append(" and extras = ?");
            selectionArgs.add(Base64.encodeToString(transportContext.getExtras(), 0));
        }
        Cursor query = db.query("transport_contexts", new String[]{"_id"}, selection.toString(), (String[]) selectionArgs.toArray(new String[0]), null, null, null);
        function = SQLiteEventStore$$Lambda$6.instance;
        return (Long) tryWithCursor(query, function);
    }

    public static /* synthetic */ Long lambda$getTransportContextId$2(Cursor cursor) {
        if (!cursor.moveToNext()) {
            return null;
        }
        return Long.valueOf(cursor.getLong(0));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordFailure(Iterable<PersistedEvent> events) {
        if (events.iterator().hasNext()) {
            String query = "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + toIdList(events);
            inTransaction(SQLiteEventStore$$Lambda$7.lambdaFactory$(query));
        }
    }

    public static /* synthetic */ Object lambda$recordFailure$3(String query, SQLiteDatabase db) {
        db.compileStatement(query).execute();
        db.compileStatement("DELETE FROM events WHERE num_attempts >= 10").execute();
        return null;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordSuccess(Iterable<PersistedEvent> events) {
        if (events.iterator().hasNext()) {
            String query = "DELETE FROM events WHERE _id in " + toIdList(events);
            getDb().compileStatement(query).execute();
        }
    }

    private static String toIdList(Iterable<PersistedEvent> events) {
        StringBuilder idList = new StringBuilder("(");
        Iterator<PersistedEvent> iterator = events.iterator();
        while (iterator.hasNext()) {
            idList.append(iterator.next().getId());
            if (iterator.hasNext()) {
                idList.append(',');
            }
        }
        idList.append(')');
        return idList.toString();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public long getNextCallTime(TransportContext transportContext) {
        Function function;
        Cursor rawQuery = getDb().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))});
        function = SQLiteEventStore$$Lambda$8.instance;
        return ((Long) tryWithCursor(rawQuery, function)).longValue();
    }

    public static /* synthetic */ Long lambda$getNextCallTime$4(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return 0L;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public boolean hasPendingEventsFor(TransportContext transportContext) {
        return ((Boolean) inTransaction(SQLiteEventStore$$Lambda$9.lambdaFactory$(this, transportContext))).booleanValue();
    }

    public static /* synthetic */ Boolean lambda$hasPendingEventsFor$5(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, SQLiteDatabase db) {
        Function function;
        Long contextId = sQLiteEventStore.getTransportContextId(db, transportContext);
        if (contextId == null) {
            return false;
        }
        Cursor rawQuery = sQLiteEventStore.getDb().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{contextId.toString()});
        function = SQLiteEventStore$$Lambda$21.instance;
        return (Boolean) tryWithCursor(rawQuery, function);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public void recordNextCallTime(TransportContext transportContext, long timestampMs) {
        inTransaction(SQLiteEventStore$$Lambda$10.lambdaFactory$(timestampMs, transportContext));
    }

    public static /* synthetic */ Object lambda$recordNextCallTime$6(long timestampMs, TransportContext transportContext, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("next_request_ms", Long.valueOf(timestampMs));
        int rowsUpdated = db.update("transport_contexts", values, "backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))});
        if (rowsUpdated < 1) {
            values.put("backend_name", transportContext.getBackendName());
            values.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
            db.insert("transport_contexts", null, values);
        }
        return null;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<PersistedEvent> loadBatch(TransportContext transportContext) {
        return (Iterable) inTransaction(SQLiteEventStore$$Lambda$11.lambdaFactory$(this, transportContext));
    }

    public static /* synthetic */ List lambda$loadBatch$7(SQLiteEventStore sQLiteEventStore, TransportContext transportContext, SQLiteDatabase db) {
        List<PersistedEvent> events = sQLiteEventStore.loadEvents(db, transportContext);
        return sQLiteEventStore.join(events, sQLiteEventStore.loadMetadata(db, events));
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public Iterable<TransportContext> loadActiveContexts() {
        Function function;
        function = SQLiteEventStore$$Lambda$12.instance;
        return (Iterable) inTransaction(function);
    }

    public static /* synthetic */ List lambda$loadActiveContexts$9(SQLiteDatabase db) {
        Function function;
        Cursor rawQuery = db.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]);
        function = SQLiteEventStore$$Lambda$20.instance;
        return (List) tryWithCursor(rawQuery, function);
    }

    public static /* synthetic */ List lambda$loadActiveContexts$8(Cursor cursor) {
        List<TransportContext> results = new ArrayList<>();
        while (cursor.moveToNext()) {
            results.add(TransportContext.builder().setBackendName(cursor.getString(1)).setPriority(PriorityMapping.valueOf(cursor.getInt(2))).setExtras(maybeBase64Decode(cursor.getString(3))).build());
        }
        return results;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStore
    public int cleanUp() {
        long oneWeekAgo = this.wallClock.getTime() - this.config.getEventCleanUpAge();
        return ((Integer) inTransaction(SQLiteEventStore$$Lambda$13.lambdaFactory$(oneWeekAgo))).intValue();
    }

    public static /* synthetic */ Integer lambda$cleanUp$10(long oneWeekAgo, SQLiteDatabase db) {
        return Integer.valueOf(db.delete("events", "timestamp_ms < ?", new String[]{String.valueOf(oneWeekAgo)}));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.schemaManager.close();
    }

    public void clearDb() {
        Function function;
        function = SQLiteEventStore$$Lambda$14.instance;
        inTransaction(function);
    }

    public static /* synthetic */ Object lambda$clearDb$11(SQLiteDatabase db) {
        db.delete("events", null, new String[0]);
        db.delete("transport_contexts", null, new String[0]);
        return null;
    }

    private static byte[] maybeBase64Decode(String value) {
        if (value == null) {
            return null;
        }
        return Base64.decode(value, 0);
    }

    public List<PersistedEvent> loadEvents(SQLiteDatabase db, TransportContext transportContext) {
        List<PersistedEvent> events = new ArrayList<>();
        Long contextId = getTransportContextId(db, transportContext);
        if (contextId == null) {
            return events;
        }
        tryWithCursor(db.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline"}, "context_id = ?", new String[]{contextId.toString()}, null, null, null, String.valueOf(this.config.getLoadBatchSize())), SQLiteEventStore$$Lambda$15.lambdaFactory$(this, events, transportContext));
        return events;
    }

    public static /* synthetic */ Object lambda$loadEvents$12(SQLiteEventStore sQLiteEventStore, List events, TransportContext transportContext, Cursor cursor) {
        while (cursor.moveToNext()) {
            boolean inline = false;
            long id = cursor.getLong(0);
            if (cursor.getInt(7) != 0) {
                inline = true;
            }
            EventInternal.Builder event = EventInternal.builder().setTransportName(cursor.getString(1)).setEventMillis(cursor.getLong(2)).setUptimeMillis(cursor.getLong(3));
            if (inline) {
                event.setEncodedPayload(new EncodedPayload(toEncoding(cursor.getString(4)), cursor.getBlob(5)));
            } else {
                event.setEncodedPayload(new EncodedPayload(toEncoding(cursor.getString(4)), sQLiteEventStore.readPayload(id)));
            }
            if (!cursor.isNull(6)) {
                event.setCode(Integer.valueOf(cursor.getInt(6)));
            }
            events.add(PersistedEvent.create(id, transportContext, event.build()));
        }
        return null;
    }

    private byte[] readPayload(long eventId) {
        Function function;
        Cursor query = getDb().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(eventId)}, null, null, "sequence_num");
        function = SQLiteEventStore$$Lambda$16.instance;
        return (byte[]) tryWithCursor(query, function);
    }

    public static /* synthetic */ byte[] lambda$readPayload$13(Cursor cursor) {
        List<byte[]> chunks = new ArrayList<>();
        int totalLength = 0;
        while (cursor.moveToNext()) {
            byte[] chunk = cursor.getBlob(0);
            chunks.add(chunk);
            totalLength += chunk.length;
        }
        byte[] payloadBytes = new byte[totalLength];
        int offset = 0;
        for (int i = 0; i < chunks.size(); i++) {
            byte[] chunk2 = chunks.get(i);
            System.arraycopy(chunk2, 0, payloadBytes, offset, chunk2.length);
            offset += chunk2.length;
        }
        return payloadBytes;
    }

    private static Encoding toEncoding(String value) {
        if (value == null) {
            return PROTOBUF_ENCODING;
        }
        return Encoding.of(value);
    }

    private Map<Long, Set<Metadata>> loadMetadata(SQLiteDatabase db, List<PersistedEvent> events) {
        Map<Long, Set<Metadata>> metadataIndex = new HashMap<>();
        StringBuilder whereClause = new StringBuilder("event_id IN (");
        for (int i = 0; i < events.size(); i++) {
            whereClause.append(events.get(i).getId());
            if (i < events.size() - 1) {
                whereClause.append(',');
            }
        }
        whereClause.append(')');
        tryWithCursor(db.query("event_metadata", new String[]{"event_id", "name", "value"}, whereClause.toString(), null, null, null, null), SQLiteEventStore$$Lambda$17.lambdaFactory$(metadataIndex));
        return metadataIndex;
    }

    public static /* synthetic */ Object lambda$loadMetadata$14(Map metadataIndex, Cursor cursor) {
        while (cursor.moveToNext()) {
            long eventId = cursor.getLong(0);
            Set<Metadata> currentSet = (Set) metadataIndex.get(Long.valueOf(eventId));
            if (currentSet == null) {
                currentSet = new HashSet<>();
                metadataIndex.put(Long.valueOf(eventId), currentSet);
            }
            currentSet.add(new Metadata(cursor.getString(1), cursor.getString(2)));
        }
        return null;
    }

    private List<PersistedEvent> join(List<PersistedEvent> events, Map<Long, Set<Metadata>> metadataIndex) {
        ListIterator<PersistedEvent> iterator = events.listIterator();
        while (iterator.hasNext()) {
            PersistedEvent current = iterator.next();
            if (metadataIndex.containsKey(Long.valueOf(current.getId()))) {
                EventInternal.Builder newEvent = current.getEvent().toBuilder();
                for (Metadata metadata : metadataIndex.get(Long.valueOf(current.getId()))) {
                    newEvent.addMetadata(metadata.key, metadata.value);
                }
                iterator.set(PersistedEvent.create(current.getId(), current.getTransportContext(), newEvent.build()));
            }
        }
        return events;
    }

    private <T> T retryIfDbLocked(Producer<T> retriable, Function<Throwable, T> failureHandler) {
        long startTime = this.monotonicClock.getTime();
        while (true) {
            try {
                return retriable.produce();
            } catch (SQLiteDatabaseLockedException ex) {
                if (this.monotonicClock.getTime() >= this.config.getCriticalSectionEnterTimeoutMs() + startTime) {
                    return failureHandler.apply(ex);
                }
                SystemClock.sleep(50L);
            }
        }
    }

    private void ensureBeginTransaction(SQLiteDatabase db) {
        Function function;
        Producer lambdaFactory$ = SQLiteEventStore$$Lambda$18.lambdaFactory$(db);
        function = SQLiteEventStore$$Lambda$19.instance;
        retryIfDbLocked(lambdaFactory$, function);
    }

    public static /* synthetic */ Object lambda$ensureBeginTransaction$15(SQLiteDatabase db) {
        db.beginTransaction();
        return null;
    }

    public static /* synthetic */ Object lambda$ensureBeginTransaction$16(Throwable ex) {
        throw new SynchronizationException("Timed out while trying to acquire the lock.", ex);
    }

    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard
    public <T> T runCriticalSection(SynchronizationGuard.CriticalSection<T> criticalSection) {
        SQLiteDatabase db = getDb();
        ensureBeginTransaction(db);
        try {
            T result = criticalSection.execute();
            db.setTransactionSuccessful();
            return result;
        } finally {
            db.endTransaction();
        }
    }

    private <T> T inTransaction(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase db = getDb();
        db.beginTransaction();
        try {
            T result = function.apply(db);
            db.setTransactionSuccessful();
            return result;
        } finally {
            db.endTransaction();
        }
    }

    /* loaded from: classes.dex */
    public static class Metadata {
        final String key;
        final String value;

        private Metadata(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private boolean isStorageAtLimit() {
        long byteSize = getPageCount() * getPageSize();
        return byteSize >= this.config.getMaxStorageSizeInBytes();
    }

    long getByteSize() {
        return getPageCount() * getPageSize();
    }

    private long getPageSize() {
        return getDb().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    private long getPageCount() {
        return getDb().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    private static <T> T tryWithCursor(Cursor c, Function<Cursor, T> function) {
        try {
            return function.apply(c);
        } finally {
            c.close();
        }
    }
}
