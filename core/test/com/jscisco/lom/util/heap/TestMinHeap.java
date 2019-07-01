package com.jscisco.lom.util.heap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMinHeap {

    private MinHeap<Integer> minHeap;

    @BeforeEach
    void setUp() {
        this.minHeap = new MinHeap<>();
    }

    @Test
    void PeekingAnEmptyHeapShouldReturnNull() {
        Assertions.assertThat(this.minHeap.peek()).isNull();
    }

    @Test
    void AfterAnEntryIsAddedPeekShouldReturnThatEntry() {
        int entry = 10;
        this.minHeap.add(entry);
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(entry);
    }

    @Test
    void RemovingAnEntryFromAnEmptyHeapShouldReturnNull() {
        Assertions.assertThat(this.minHeap.remove()).isNull();
    }

    @Test
    void RemovingAnEntryFromAHeapWithOneEntryShouldReturnThatEntry() {
        int entry = 100;
        this.minHeap.add(entry);
        Assertions.assertThat(this.minHeap.remove()).isEqualTo(entry);
    }

    @Test
    void AddingALargerElementWillNotChangeTheTopOfTheHeap() {
        int entry1 = 10;
        int entry2 = 1000;
        this.minHeap.add(entry1);
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(entry1);
        this.minHeap.add(entry2);
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(entry1);
    }

    @Test
    void AddingASmallerElementWillPutThatElementOnTopOfTheHeap() {
        int entry1 = 1000;
        int entry2 = 10;
        this.minHeap.add(entry1);
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(entry1);
        this.minHeap.add(entry2);
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(entry2);
    }

    @Test
    void AddingLotsOfElementAndThenRemovingOneShouldWorkAsExpected() {
        for (int i = 0; i < 100; i++) {
            this.minHeap.add(i);
        }
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(0);
        int entry = this.minHeap.remove();
        Assertions.assertThat(entry).isEqualTo(0);
        Assertions.assertThat(this.minHeap.peek()).isEqualTo(1);
    }
}
