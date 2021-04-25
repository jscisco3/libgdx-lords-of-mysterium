package com.jscisco.lom.domain;

public abstract class ValueObject<T> {
    protected T value;

    public T get() {
        return value;
    }
}
