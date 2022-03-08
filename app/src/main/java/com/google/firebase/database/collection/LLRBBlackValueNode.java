package com.google.firebase.database.collection;

import com.google.firebase.database.collection.LLRBNode;

/* compiled from: com.google.firebase:firebase-database-collection@@17.0.1 */
/* loaded from: classes.dex */
public class LLRBBlackValueNode<K, V> extends LLRBValueNode<K, V> {
    private int size = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LLRBBlackValueNode(K key, V value, LLRBNode<K, V> left, LLRBNode<K, V> right) {
        super(key, value, left, right);
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    protected LLRBNode.Color getColor() {
        return LLRBNode.Color.BLACK;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public boolean isRed() {
        return false;
    }

    @Override // com.google.firebase.database.collection.LLRBNode
    public int size() {
        if (this.size == -1) {
            this.size = getLeft().size() + 1 + getRight().size();
        }
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.database.collection.LLRBValueNode
    public void setLeft(LLRBNode<K, V> left) {
        if (this.size == -1) {
            super.setLeft(left);
            return;
        }
        throw new IllegalStateException("Can't set left after using size");
    }

    @Override // com.google.firebase.database.collection.LLRBValueNode
    protected LLRBValueNode<K, V> copy(K key, V value, LLRBNode<K, V> left, LLRBNode<K, V> right) {
        K newKey = key == null ? getKey() : key;
        V newValue = value == null ? getValue() : value;
        LLRBNode<K, V> newLeft = left == null ? getLeft() : left;
        LLRBNode<K, V> newRight = right == null ? getRight() : right;
        return new LLRBBlackValueNode(newKey, newValue, newLeft, newRight);
    }
}
