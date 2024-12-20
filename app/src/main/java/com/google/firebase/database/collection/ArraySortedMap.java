package com.google.firebase.database.collection;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.LLRBNode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-database-collection@@17.0.1 */
/* loaded from: classes.dex */
public class ArraySortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private final Comparator<K> comparator;
    private final K[] keys;
    private final V[] values;

    public static <A, B, C> ArraySortedMap<A, C> buildFrom(List<A> keys, Map<B, C> values, ImmutableSortedMap.Builder.KeyTranslator<A, B> translator, Comparator<A> comparator) {
        Collections.sort(keys, comparator);
        int size = keys.size();
        Object[] objArr = new Object[size];
        Object[] objArr2 = new Object[size];
        int pos = 0;
        for (A k : keys) {
            objArr[pos] = k;
            C value = values.get(translator.translate(k));
            objArr2[pos] = value;
            pos++;
        }
        return new ArraySortedMap<>(comparator, objArr, objArr2);
    }

    public static <K, V> ArraySortedMap<K, V> fromMap(Map<K, V> map, Comparator<K> comparator) {
        return buildFrom(new ArrayList(map.keySet()), map, ImmutableSortedMap.Builder.identityTranslator(), comparator);
    }

    public ArraySortedMap(Comparator<K> comparator) {
        this.keys = (K[]) new Object[0];
        this.values = (V[]) new Object[0];
        this.comparator = comparator;
    }

    private ArraySortedMap(Comparator<K> comparator, K[] keys, V[] values) {
        this.keys = keys;
        this.values = values;
        this.comparator = comparator;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public boolean containsKey(K key) {
        return findKey(key) != -1;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public V get(K key) {
        int pos = findKey(key);
        if (pos != -1) {
            return this.values[pos];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public ImmutableSortedMap<K, V> remove(K key) {
        int pos = findKey(key);
        if (pos == -1) {
            return this;
        }
        return new ArraySortedMap(this.comparator, removeFromArray(this.keys, pos), removeFromArray(this.values, pos));
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public ImmutableSortedMap<K, V> insert(K key, V value) {
        int pos = findKey(key);
        if (pos == -1) {
            K[] newKeys = this.keys;
            if (newKeys.length > 25) {
                Map<K, V> map = new HashMap<>(this.keys.length + 1);
                int i = 0;
                while (true) {
                    K[] kArr = this.keys;
                    if (i < kArr.length) {
                        map.put(kArr[i], this.values[i]);
                        i++;
                    } else {
                        map.put(key, value);
                        return RBTreeSortedMap.fromMap(map, this.comparator);
                    }
                }
            } else {
                int newPos = findKeyOrInsertPosition(key);
                return new ArraySortedMap(this.comparator, addToArray(this.keys, newPos, key), addToArray(this.values, newPos, value));
            }
        } else if (this.keys[pos] == key && this.values[pos] == value) {
            return this;
        } else {
            return new ArraySortedMap(this.comparator, replaceInArray(this.keys, pos, key), replaceInArray(this.values, pos, value));
        }
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getMinKey() {
        K[] kArr = this.keys;
        if (kArr.length > 0) {
            return kArr[0];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getMaxKey() {
        K[] kArr = this.keys;
        if (kArr.length > 0) {
            return kArr[kArr.length - 1];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public int size() {
        return this.keys.length;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public boolean isEmpty() {
        return this.keys.length == 0;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> visitor) {
        int i = 0;
        while (true) {
            K[] kArr = this.keys;
            if (i < kArr.length) {
                visitor.visitEntry(kArr[i], this.values[i]);
                i++;
            } else {
                return;
            }
        }
    }

    private Iterator<Map.Entry<K, V>> iterator(final int pos, final boolean reverse) {
        return new Iterator<Map.Entry<K, V>>() { // from class: com.google.firebase.database.collection.ArraySortedMap.1
            int currentPos;

            {
                this.currentPos = pos;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (reverse) {
                    if (this.currentPos >= 0) {
                        return true;
                    }
                } else if (this.currentPos < ArraySortedMap.this.keys.length) {
                    return true;
                }
                return false;
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                Object obj = ArraySortedMap.this.keys[this.currentPos];
                Object[] objArr = ArraySortedMap.this.values;
                int i = this.currentPos;
                Object obj2 = objArr[i];
                this.currentPos = reverse ? i - 1 : i + 1;
                return new AbstractMap.SimpleImmutableEntry(obj, obj2);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
            }
        };
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap, java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return iterator(0, false);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> iteratorFrom(K key) {
        int pos = findKeyOrInsertPosition(key);
        return iterator(pos, false);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> reverseIteratorFrom(K key) {
        int pos = findKeyOrInsertPosition(key);
        K[] kArr = this.keys;
        if (pos >= kArr.length || this.comparator.compare(kArr[pos], key) != 0) {
            return iterator(pos - 1, true);
        }
        return iterator(pos, true);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> reverseIterator() {
        return iterator(this.keys.length - 1, true);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getPredecessorKey(K key) {
        int pos = findKey(key);
        if (pos == -1) {
            throw new IllegalArgumentException("Can't find predecessor of nonexistent key");
        } else if (pos > 0) {
            return this.keys[pos - 1];
        } else {
            return null;
        }
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getSuccessorKey(K key) {
        int pos = findKey(key);
        if (pos != -1) {
            K[] kArr = this.keys;
            if (pos < kArr.length - 1) {
                return kArr[pos + 1];
            }
            return null;
        }
        throw new IllegalArgumentException("Can't find successor of nonexistent key");
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public int indexOf(K key) {
        return findKey(key);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Comparator<K> getComparator() {
        return this.comparator;
    }

    private static <T> T[] removeFromArray(T[] arr, int pos) {
        int newSize = arr.length - 1;
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, pos);
        System.arraycopy(arr, pos + 1, newArray, pos, newSize - pos);
        return newArray;
    }

    private static <T> T[] addToArray(T[] arr, int pos, T value) {
        int newSize = arr.length + 1;
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(arr, 0, newArray, 0, pos);
        newArray[pos] = value;
        System.arraycopy(arr, pos, newArray, pos + 1, (newSize - pos) - 1);
        return newArray;
    }

    private static <T> T[] replaceInArray(T[] arr, int pos, T value) {
        int size = arr.length;
        T[] newArray = (T[]) new Object[size];
        System.arraycopy(arr, 0, newArray, 0, size);
        newArray[pos] = value;
        return newArray;
    }

    private int findKeyOrInsertPosition(K key) {
        int newPos = 0;
        while (true) {
            K[] kArr = this.keys;
            if (newPos >= kArr.length || this.comparator.compare(kArr[newPos], key) >= 0) {
                break;
            }
            newPos++;
        }
        return newPos;
    }

    private int findKey(K key) {
        K[] kArr;
        int i = 0;
        for (K otherKey : this.keys) {
            if (this.comparator.compare(key, otherKey) == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
