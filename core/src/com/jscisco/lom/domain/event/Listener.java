package com.jscisco.lom.domain.event;

import com.jscisco.lom.domain.event.Event;

public interface Listener {

    public void handle(Event event);

}
