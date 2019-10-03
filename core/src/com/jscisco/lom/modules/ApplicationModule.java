package com.jscisco.lom.modules;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    private EventBus eventBus = new EventBus();

    @Override
    protected void configure() {
        bind(EventBus.class).toInstance(eventBus);
    }
}
