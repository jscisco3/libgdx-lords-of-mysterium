package com.jscisco.lom.util.heap;

public interface Heap<T extends Comparable<T>> {

    void add(T entry);

    T peek();

    T remove();

}
