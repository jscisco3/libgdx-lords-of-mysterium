package com.jscisco.lom.shelf.util.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {

    private List<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    @Override
    public void add(T entry) {
        heap.add(entry);
        siftUp(heap.size() - 1);
    }

    @Override
    public T peek() {
        if (this.heap.isEmpty()) {
            return null;
        }
        return this.heap.get(0);
    }

    @Override
    public T remove() {
        if (this.heap.isEmpty()) {
            return null;
        }
        T max = this.peek();
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);
        siftDown(0);
        return max;
    }

    private void siftUp(int idx) {
        if (idx == 0) {
            return;
        }
        int parentIndex = parentIndex(idx);
        // If the entry is smaller than it's parent, we need to swap them
        if (this.heap.get(idx).compareTo(this.heap.get(parentIndex)) < 0) {
            swap(idx, parentIndex);
            siftUp(parentIndex);
        }
    }

    private void siftDown(int idx) {
        // Cannot sift down leaf nodes
        if (idx >= this.heap.size() * 2) {
            return;
        }
        // Find the larger child value
        // Left child would be guaranteed to exist since this is not a leaf node
        // Left node must be filled before right node
        int leftIdx = leftChildIndex(idx);
        int rightIdx = rightChildIndex(idx);
        int smallerChildIdx = leftIdx;
        if (rightIdx < this.heap.size() && (this.heap.get(rightIdx).compareTo(this.heap.get(leftIdx)) < 0)) {
            smallerChildIdx = rightIdx;
        }
        // If this is larger than the smaller child, sift it down!
        if (leftIdx < this.heap.size() && (this.heap.get(idx).compareTo(this.heap.get(smallerChildIdx)) > 0)) {
            swap(idx, smallerChildIdx);
            siftDown(smallerChildIdx);
        }
    }

    private int parentIndex(int idx) {
        return idx / 2;
    }

    private int leftChildIndex(int idx) {
        return idx * 2 + 1;
    }

    private int rightChildIndex(int idx) {
        return idx * 2 + 2;
    }

    private void swap(int left, int right) {
        T temp = this.heap.get(left);
        this.heap.set(left, this.heap.get(right));
        this.heap.set(right, temp);
    }

}
