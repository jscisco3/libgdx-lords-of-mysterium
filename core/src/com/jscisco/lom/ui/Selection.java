package com.jscisco.lom.ui;

public class Selection<T> {

    private T value;

    public Selection(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
