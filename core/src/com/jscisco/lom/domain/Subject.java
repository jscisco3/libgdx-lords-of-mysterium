package com.jscisco.lom.domain;

public interface Subject {

    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(Event event);

}
