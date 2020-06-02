package com.jscisco.lom.domain.event;

public interface Publisher {

    public void register(Listener listener);

    public void unregister(Listener listener);

    public void publish(Event event);

}
