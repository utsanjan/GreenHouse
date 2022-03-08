package com.google.firebase.database.core.view;

import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.filter.IndexedFilter;
import com.google.firebase.database.core.view.filter.LimitedFilter;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.core.view.filter.RangedFilter;
import com.google.firebase.database.snapshot.BooleanNode;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.LongNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import com.google.firebase.database.snapshot.PriorityIndex;
import com.google.firebase.database.snapshot.PriorityUtilities;
import com.google.firebase.database.snapshot.StringNode;
import com.google.firebase.database.util.JsonMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public final class QueryParams {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final QueryParams DEFAULT_PARAMS = new QueryParams();
    private static final String INDEX = "i";
    private static final String INDEX_END_NAME = "en";
    private static final String INDEX_END_VALUE = "ep";
    private static final String INDEX_START_NAME = "sn";
    private static final String INDEX_START_VALUE = "sp";
    private static final String LIMIT = "l";
    private static final String VIEW_FROM = "vf";
    private Integer limit;
    private ViewFrom viewFrom;
    private Node indexStartValue = null;
    private ChildKey indexStartName = null;
    private Node indexEndValue = null;
    private ChildKey indexEndName = null;
    private Index index = PriorityIndex.getInstance();
    private String jsonSerialization = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    public enum ViewFrom {
        LEFT,
        RIGHT
    }

    public boolean hasStart() {
        return this.indexStartValue != null;
    }

    public Node getIndexStartValue() {
        if (hasStart()) {
            return this.indexStartValue;
        }
        throw new IllegalArgumentException("Cannot get index start value if start has not been set");
    }

    public ChildKey getIndexStartName() {
        if (hasStart()) {
            ChildKey childKey = this.indexStartName;
            if (childKey != null) {
                return childKey;
            }
            return ChildKey.getMinName();
        }
        throw new IllegalArgumentException("Cannot get index start name if start has not been set");
    }

    public boolean hasEnd() {
        return this.indexEndValue != null;
    }

    public Node getIndexEndValue() {
        if (hasEnd()) {
            return this.indexEndValue;
        }
        throw new IllegalArgumentException("Cannot get index end value if start has not been set");
    }

    public ChildKey getIndexEndName() {
        if (hasEnd()) {
            ChildKey childKey = this.indexEndName;
            if (childKey != null) {
                return childKey;
            }
            return ChildKey.getMaxName();
        }
        throw new IllegalArgumentException("Cannot get index end name if start has not been set");
    }

    public boolean hasLimit() {
        return this.limit != null;
    }

    public boolean hasAnchoredLimit() {
        return hasLimit() && this.viewFrom != null;
    }

    public int getLimit() {
        if (hasLimit()) {
            return this.limit.intValue();
        }
        throw new IllegalArgumentException("Cannot get limit if limit has not been set");
    }

    public Index getIndex() {
        return this.index;
    }

    private QueryParams copy() {
        QueryParams params = new QueryParams();
        params.limit = this.limit;
        params.indexStartValue = this.indexStartValue;
        params.indexStartName = this.indexStartName;
        params.indexEndValue = this.indexEndValue;
        params.indexEndName = this.indexEndName;
        params.viewFrom = this.viewFrom;
        params.index = this.index;
        return params;
    }

    public QueryParams limitToFirst(int limit) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit);
        copy.viewFrom = ViewFrom.LEFT;
        return copy;
    }

    public QueryParams limitToLast(int limit) {
        QueryParams copy = copy();
        copy.limit = Integer.valueOf(limit);
        copy.viewFrom = ViewFrom.RIGHT;
        return copy;
    }

    public QueryParams startAt(Node indexStartValue, ChildKey indexStartName) {
        Utilities.hardAssert(!(indexStartValue instanceof LongNode));
        QueryParams copy = copy();
        copy.indexStartValue = indexStartValue;
        copy.indexStartName = indexStartName;
        return copy;
    }

    public QueryParams endAt(Node indexEndValue, ChildKey indexEndName) {
        Utilities.hardAssert(!(indexEndValue instanceof LongNode));
        QueryParams copy = copy();
        copy.indexEndValue = indexEndValue;
        copy.indexEndName = indexEndName;
        return copy;
    }

    public QueryParams orderBy(Index index) {
        QueryParams copy = copy();
        copy.index = index;
        return copy;
    }

    public boolean isViewFromLeft() {
        ViewFrom viewFrom = this.viewFrom;
        return viewFrom != null ? viewFrom == ViewFrom.LEFT : hasStart();
    }

    public Map<String, Object> getWireProtocolParams() {
        Map<String, Object> queryObject = new HashMap<>();
        if (hasStart()) {
            queryObject.put(INDEX_START_VALUE, this.indexStartValue.getValue());
            ChildKey childKey = this.indexStartName;
            if (childKey != null) {
                queryObject.put(INDEX_START_NAME, childKey.asString());
            }
        }
        if (hasEnd()) {
            queryObject.put(INDEX_END_VALUE, this.indexEndValue.getValue());
            ChildKey childKey2 = this.indexEndName;
            if (childKey2 != null) {
                queryObject.put(INDEX_END_NAME, childKey2.asString());
            }
        }
        Object obj = this.limit;
        if (obj != null) {
            queryObject.put(LIMIT, obj);
            ViewFrom viewFromToAdd = this.viewFrom;
            if (viewFromToAdd == null) {
                if (hasStart()) {
                    viewFromToAdd = ViewFrom.LEFT;
                } else {
                    viewFromToAdd = ViewFrom.RIGHT;
                }
            }
            int i = AnonymousClass1.$SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom[viewFromToAdd.ordinal()];
            if (i == 1) {
                queryObject.put(VIEW_FROM, LIMIT);
            } else if (i == 2) {
                queryObject.put(VIEW_FROM, "r");
            }
        }
        if (!this.index.equals(PriorityIndex.getInstance())) {
            queryObject.put(INDEX, this.index.getQueryDefinition());
        }
        return queryObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.core.view.QueryParams$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom;

        static {
            int[] iArr = new int[ViewFrom.values().length];
            $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom = iArr;
            try {
                iArr[ViewFrom.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$view$QueryParams$ViewFrom[ViewFrom.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public boolean loadsAllData() {
        return !hasStart() && !hasEnd() && !hasLimit();
    }

    public boolean isDefault() {
        return loadsAllData() && this.index.equals(PriorityIndex.getInstance());
    }

    public boolean isValid() {
        return !hasStart() || !hasEnd() || !hasLimit() || hasAnchoredLimit();
    }

    public String toJSON() {
        if (this.jsonSerialization == null) {
            try {
                this.jsonSerialization = JsonMapper.serializeJson(getWireProtocolParams());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.jsonSerialization;
    }

    public static QueryParams fromQueryObject(Map<String, Object> map) {
        QueryParams params = new QueryParams();
        params.limit = (Integer) map.get(LIMIT);
        if (map.containsKey(INDEX_START_VALUE)) {
            Object indexStartValue = map.get(INDEX_START_VALUE);
            params.indexStartValue = normalizeValue(NodeUtilities.NodeFromJSON(indexStartValue));
            String indexStartName = (String) map.get(INDEX_START_NAME);
            if (indexStartName != null) {
                params.indexStartName = ChildKey.fromString(indexStartName);
            }
        }
        if (map.containsKey(INDEX_END_VALUE)) {
            Object indexEndValue = map.get(INDEX_END_VALUE);
            params.indexEndValue = normalizeValue(NodeUtilities.NodeFromJSON(indexEndValue));
            String indexEndName = (String) map.get(INDEX_END_NAME);
            if (indexEndName != null) {
                params.indexEndName = ChildKey.fromString(indexEndName);
            }
        }
        String viewFrom = (String) map.get(VIEW_FROM);
        if (viewFrom != null) {
            params.viewFrom = viewFrom.equals(LIMIT) ? ViewFrom.LEFT : ViewFrom.RIGHT;
        }
        String indexStr = (String) map.get(INDEX);
        if (indexStr != null) {
            params.index = Index.fromQueryDefinition(indexStr);
        }
        return params;
    }

    public NodeFilter getNodeFilter() {
        if (loadsAllData()) {
            return new IndexedFilter(getIndex());
        }
        if (hasLimit()) {
            return new LimitedFilter(this);
        }
        return new RangedFilter(this);
    }

    public String toString() {
        return getWireProtocolParams().toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QueryParams that = (QueryParams) o;
        Integer num = this.limit;
        if (num == null ? that.limit != null : !num.equals(that.limit)) {
            return false;
        }
        Index index = this.index;
        if (index == null ? that.index != null : !index.equals(that.index)) {
            return false;
        }
        ChildKey childKey = this.indexEndName;
        if (childKey == null ? that.indexEndName != null : !childKey.equals(that.indexEndName)) {
            return false;
        }
        Node node = this.indexEndValue;
        if (node == null ? that.indexEndValue != null : !node.equals(that.indexEndValue)) {
            return false;
        }
        ChildKey childKey2 = this.indexStartName;
        if (childKey2 == null ? that.indexStartName != null : !childKey2.equals(that.indexStartName)) {
            return false;
        }
        Node node2 = this.indexStartValue;
        if (node2 == null ? that.indexStartValue != null : !node2.equals(that.indexStartValue)) {
            return false;
        }
        if (isViewFromLeft() != that.isViewFromLeft()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Integer num = this.limit;
        int i = 0;
        int result = num != null ? num.intValue() : 0;
        int result2 = ((result * 31) + (isViewFromLeft() ? 1231 : 1237)) * 31;
        Node node = this.indexStartValue;
        int result3 = (result2 + (node != null ? node.hashCode() : 0)) * 31;
        ChildKey childKey = this.indexStartName;
        int result4 = (result3 + (childKey != null ? childKey.hashCode() : 0)) * 31;
        Node node2 = this.indexEndValue;
        int result5 = (result4 + (node2 != null ? node2.hashCode() : 0)) * 31;
        ChildKey childKey2 = this.indexEndName;
        int result6 = (result5 + (childKey2 != null ? childKey2.hashCode() : 0)) * 31;
        Index index = this.index;
        if (index != null) {
            i = index.hashCode();
        }
        return result6 + i;
    }

    private static Node normalizeValue(Node value) {
        if ((value instanceof StringNode) || (value instanceof BooleanNode) || (value instanceof DoubleNode) || (value instanceof EmptyNode)) {
            return value;
        }
        if (value instanceof LongNode) {
            return new DoubleNode(Double.valueOf(((Long) value.getValue()).doubleValue()), PriorityUtilities.NullPriority());
        }
        throw new IllegalStateException("Unexpected value passed to normalizeValue: " + value.getValue());
    }
}
