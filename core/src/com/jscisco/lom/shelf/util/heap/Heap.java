package com.jscisco.lom.shelf.util.heap;

public interface Heap<T extends Comparable<T>> {

    void add(T entry);

    T peek();

    T remove();

}
