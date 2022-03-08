package com.google.firebase.database;

import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.core.ChildEventRegistration;
import com.google.firebase.database.core.EventRegistration;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.Repo;
import com.google.firebase.database.core.ValueEventRegistration;
import com.google.firebase.database.core.ZombieEventManager;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.utilities.Validation;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.KeyIndex;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.PathIndex;
import com.google.firebase.database.snapshot.PriorityIndex;
import com.google.firebase.database.snapshot.PriorityUtilities;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.database.snapshot.ValueIndex;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class Query {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final boolean orderByCalled;
    protected final QueryParams params;
    protected final Path path;
    protected final Repo repo;

    Query(Repo repo, Path path, QueryParams params, boolean orderByCalled) throws DatabaseException {
        this.repo = repo;
        this.path = path;
        this.params = params;
        this.orderByCalled = orderByCalled;
        Utilities.hardAssert(params.isValid(), "Validation of queries failed.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Query(Repo repo, Path path) {
        this.repo = repo;
        this.path = path;
        this.params = QueryParams.DEFAULT_PARAMS;
        this.orderByCalled = false;
    }

    private void validateQueryEndpoints(QueryParams params) {
        if (params.getIndex().equals(KeyIndex.getInstance())) {
            if (params.hasStart()) {
                Node startNode = params.getIndexStartValue();
                ChildKey startName = params.getIndexStartName();
                if (!Objects.equal(startName, ChildKey.getMinName()) || !(startNode instanceof StringNode)) {
                    throw new IllegalArgumentException("You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
            if (params.hasEnd()) {
                Node endNode = params.getIndexEndValue();
                ChildKey endName = params.getIndexEndName();
                if (!endName.equals(ChildKey.getMaxName()) || !(endNode instanceof StringNode)) {
                    throw new IllegalArgumentException("You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
        } else if (!params.getIndex().equals(PriorityIndex.getInstance())) {
        } else {
            if ((params.hasStart() && !PriorityUtilities.isValidPriority(params.getIndexStartValue())) || (params.hasEnd() && !PriorityUtilities.isValidPriority(params.getIndexEndValue()))) {
                throw new IllegalArgumentException("When using orderByPriority(), values provided to startAt(), endAt(), or equalTo() must be valid priorities.");
            }
        }
    }

    private void validateLimit(QueryParams params) {
        if (params.hasStart() && params.hasEnd() && params.hasLimit() && !params.hasAnchoredLimit()) {
            throw new IllegalArgumentException("Can't combine startAt(), endAt() and limit(). Use limitToFirst() or limitToLast() instead");
        }
    }

    private void validateEqualToCall() {
        if (this.params.hasStart()) {
            throw new IllegalArgumentException("Can't call equalTo() and startAt() combined");
        } else if (this.params.hasEnd()) {
            throw new IllegalArgumentException("Can't call equalTo() and endAt() combined");
        }
    }

    private void validateNoOrderByCall() {
        if (this.orderByCalled) {
            throw new IllegalArgumentException("You can't combine multiple orderBy calls!");
        }
    }

    public ValueEventListener addValueEventListener(ValueEventListener listener) {
        addEventRegistration(new ValueEventRegistration(this.repo, listener, getSpec()));
        return listener;
    }

    public ChildEventListener addChildEventListener(ChildEventListener listener) {
        addEventRegistration(new ChildEventRegistration(this.repo, listener, getSpec()));
        return listener;
    }

    public void addListenerForSingleValueEvent(final ValueEventListener listener) {
        addEventRegistration(new ValueEventRegistration(this.repo, new ValueEventListener() { // from class: com.google.firebase.database.Query.1
            @Override // com.google.firebase.database.ValueEventListener
            public void onDataChange(DataSnapshot snapshot) {
                Query.this.removeEventListener(this);
                listener.onDataChange(snapshot);
            }

            @Override // com.google.firebase.database.ValueEventListener
            public void onCancelled(DatabaseError error) {
                listener.onCancelled(error);
            }
        }, getSpec()));
    }

    public void removeEventListener(ValueEventListener listener) {
        if (listener != null) {
            removeEventRegistration(new ValueEventRegistration(this.repo, listener, getSpec()));
            return;
        }
        throw new NullPointerException("listener must not be null");
    }

    public void removeEventListener(ChildEventListener listener) {
        if (listener != null) {
            removeEventRegistration(new ChildEventRegistration(this.repo, listener, getSpec()));
            return;
        }
        throw new NullPointerException("listener must not be null");
    }

    private void removeEventRegistration(final EventRegistration registration) {
        ZombieEventManager.getInstance().zombifyForRemove(registration);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.Query.2
            @Override // java.lang.Runnable
            public void run() {
                Query.this.repo.removeEventCallback(registration);
            }
        });
    }

    private void addEventRegistration(final EventRegistration listener) {
        ZombieEventManager.getInstance().recordEventRegistration(listener);
        this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.Query.3
            @Override // java.lang.Runnable
            public void run() {
                Query.this.repo.addEventCallback(listener);
            }
        });
    }

    public void keepSynced(final boolean keepSynced) {
        if (this.path.isEmpty() || !this.path.getFront().equals(ChildKey.getInfoKey())) {
            this.repo.scheduleNow(new Runnable() { // from class: com.google.firebase.database.Query.4
                @Override // java.lang.Runnable
                public void run() {
                    Query.this.repo.keepSynced(Query.this.getSpec(), keepSynced);
                }
            });
            return;
        }
        throw new DatabaseException("Can't call keepSynced() on .info paths.");
    }

    public Query startAt(String value) {
        return startAt(value, (String) null);
    }

    public Query startAt(double value) {
        return startAt(value, (String) null);
    }

    public Query startAt(boolean value) {
        return startAt(value, (String) null);
    }

    public Query startAt(String value, String key) {
        Node node = value != null ? new StringNode(value, PriorityUtilities.NullPriority()) : EmptyNode.Empty();
        return startAt(node, key);
    }

    public Query startAt(double value, String key) {
        return startAt(new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    public Query startAt(boolean value, String key) {
        return startAt(new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    private Query startAt(Node node, String key) {
        Validation.validateNullableKey(key);
        if (!node.isLeafNode() && !node.isEmpty()) {
            throw new IllegalArgumentException("Can only use simple values for startAt()");
        } else if (!this.params.hasStart()) {
            ChildKey childKey = key != null ? ChildKey.fromString(key) : null;
            QueryParams newParams = this.params.startAt(node, childKey);
            validateLimit(newParams);
            validateQueryEndpoints(newParams);
            return new Query(this.repo, this.path, newParams, this.orderByCalled);
        } else {
            throw new IllegalArgumentException("Can't call startAt() or equalTo() multiple times");
        }
    }

    public Query endAt(String value) {
        return endAt(value, (String) null);
    }

    public Query endAt(double value) {
        return endAt(value, (String) null);
    }

    public Query endAt(boolean value) {
        return endAt(value, (String) null);
    }

    public Query endAt(String value, String key) {
        Node node = value != null ? new StringNode(value, PriorityUtilities.NullPriority()) : EmptyNode.Empty();
        return endAt(node, key);
    }

    public Query endAt(double value, String key) {
        return endAt(new DoubleNode(Double.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    public Query endAt(boolean value, String key) {
        return endAt(new BooleanNode(Boolean.valueOf(value), PriorityUtilities.NullPriority()), key);
    }

    private Query endAt(Node node, String key) {
        Validation.validateNullableKey(key);
        if (node.isLeafNode() || node.isEmpty()) {
            ChildKey childKey = key != null ? ChildKey.fromString(key) : null;
            if (!this.params.hasEnd()) {
                QueryParams newParams = this.params.endAt(node, childKey);
                validateLimit(newParams);
                validateQueryEndpoints(newParams);
                return new Query(this.repo, this.path, newParams, this.orderByCalled);
            }
            throw new IllegalArgumentException("Can't call endAt() or equalTo() multiple times");
        }
        throw new IllegalArgumentException("Can only use simple values for endAt()");
    }

    public Query equalTo(String value) {
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(double value) {
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(boolean value) {
        validateEqualToCall();
        return startAt(value).endAt(value);
    }

    public Query equalTo(String value, String key) {
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query equalTo(double value, String key) {
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query equalTo(boolean value, String key) {
        validateEqualToCall();
        return startAt(value, key).endAt(value, key);
    }

    public Query limitToFirst(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        } else if (!this.params.hasLimit()) {
            return new Query(this.repo, this.path, this.params.limitToFirst(limit), this.orderByCalled);
        } else {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
    }

    public Query limitToLast(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        } else if (!this.params.hasLimit()) {
            return new Query(this.repo, this.path, this.params.limitToLast(limit), this.orderByCalled);
        } else {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
    }

    public Query orderByChild(String path) {
        if (path == null) {
            throw new NullPointerException("Key can't be null");
        } else if (path.equals("$key") || path.equals(".key")) {
            throw new IllegalArgumentException("Can't use '" + path + "' as path, please use orderByKey() instead!");
        } else if (path.equals("$priority") || path.equals(".priority")) {
            throw new IllegalArgumentException("Can't use '" + path + "' as path, please use orderByPriority() instead!");
        } else if (path.equals("$value") || path.equals(".value")) {
            throw new IllegalArgumentException("Can't use '" + path + "' as path, please use orderByValue() instead!");
        } else {
            Validation.validatePathString(path);
            validateNoOrderByCall();
            Path indexPath = new Path(path);
            if (indexPath.size() != 0) {
                Index index = new PathIndex(indexPath);
                return new Query(this.repo, this.path, this.params.orderBy(index), true);
            }
            throw new IllegalArgumentException("Can't use empty path, use orderByValue() instead!");
        }
    }

    public Query orderByPriority() {
        validateNoOrderByCall();
        QueryParams newParams = this.params.orderBy(PriorityIndex.getInstance());
        validateQueryEndpoints(newParams);
        return new Query(this.repo, this.path, newParams, true);
    }

    public Query orderByKey() {
        validateNoOrderByCall();
        QueryParams newParams = this.params.orderBy(KeyIndex.getInstance());
        validateQueryEndpoints(newParams);
        return new Query(this.repo, this.path, newParams, true);
    }

    public Query orderByValue() {
        validateNoOrderByCall();
        return new Query(this.repo, this.path, this.params.orderBy(ValueIndex.getInstance()), true);
    }

    public DatabaseReference getRef() {
        return new DatabaseReference(this.repo, getPath());
    }

    public Path getPath() {
        return this.path;
    }

    public Repo getRepo() {
        return this.repo;
    }

    public QuerySpec getSpec() {
        return new QuerySpec(this.path, this.params);
    }
}
