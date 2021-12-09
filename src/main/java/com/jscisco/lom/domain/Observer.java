package com.jscisco.lom.domain;

import com.jscisco.lom.domain.event.Event;

public interface Observer {

    void onNotify(Event event);

}
