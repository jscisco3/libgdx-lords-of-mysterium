package com.jscisco.lom.shelf.domain;

public interface Subject {

    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(Event event);

}
