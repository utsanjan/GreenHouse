package com.google.firebase.crashlytics.internal.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public final class ImmutableList<E> implements List<E>, RandomAccess {
    private final List<E> immutableList;

    public static <E> ImmutableList<E> from(E... elements) {
        return new ImmutableList<>(Arrays.asList(elements));
    }

    public static <E> ImmutableList<E> from(List<E> mutableList) {
        return new ImmutableList<>(mutableList);
    }

    private ImmutableList(List<E> mutableList) {
        this.immutableList = Collections.unmodifiableList(mutableList);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.immutableList.size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.immutableList.isEmpty();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return this.immutableList.contains(o);
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.immutableList.iterator();
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.immutableList.toArray();
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] a) {
        return (T[]) this.immutableList.toArray(a);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        return this.immutableList.add(e);
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object o) {
        return this.immutableList.remove(o);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        return this.immutableList.containsAll(c);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        return this.immutableList.addAll(c);
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection<? extends E> c) {
        return this.immutableList.addAll(index, c);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        return this.immutableList.removeAll(c);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        return this.immutableList.retainAll(c);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.immutableList.clear();
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object o) {
        return this.immutableList.equals(o);
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.immutableList.hashCode();
    }

    @Override // java.util.List
    public E get(int index) {
        return this.immutableList.get(index);
    }

    @Override // java.util.List
    public E set(int index, E element) {
        return this.immutableList.set(index, element);
    }

    @Override // java.util.List
    public void add(int index, E element) {
        this.immutableList.add(index, element);
    }

    @Override // java.util.List
    public E remove(int index) {
        return this.immutableList.remove(index);
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        return this.immutableList.indexOf(o);
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        return this.immutableList.lastIndexOf(o);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return this.immutableList.listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int index) {
        return this.immutableList.listIterator(index);
    }

    @Override // java.util.List
    public List<E> subList(int fromIndex, int toIndex) {
        return this.immutableList.subList(fromIndex, toIndex);
    }
}
