package com.jscisco.lom.domain;

import java.util.ArrayList;
import java.util.Collection;

public class ObservableList<E> extends ArrayList<E> {

    private final Subject subject;

    public ObservableList() {
        this.subject = new Subject();
    }

    public ObservableList(Collection<E> c) {
        super(c);
        this.subject = new Subject();
    }

    public ObservableList(int initialCapacity) {
        super(initialCapacity);
        this.subject = new Subject();
    }

    @Override
    public boolean add(E e) {
        subject.notify(null);
        return super.add(e);
    }

    @Override
    public boolean remove(Object o) {
        subject.notify(null);
        return super.remove(o);
    }

    public Subject getSubject() {
        return subject;
    }
}
