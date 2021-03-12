package com.jscisco.lom.domain;

import com.jscisco.lom.domain.event.Event;

public interface Subject {

    void notify(Event event);

}
