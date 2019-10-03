package com.jscisco.lom.events;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventBusTest {

    private static final Logger logger = LoggerFactory.getLogger(EventBusTest.class);

    private EventBus eventBus = new EventBus();

    @Test
    public void testOneListener() {
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);
        eventBus.post("New event");
        Assertions.assertThat(1).isEqualTo(eventListener.getEventsHandled());
    }

    @Test
    public void testTwoListenersStringEvents() {
        EventListener eventListener1 = new EventListener();
        EventListener eventListener2 = new EventListener();
        eventBus.register(eventListener1);
        eventBus.register(eventListener2);
        eventBus.post("New event");
        Assertions.assertThat(1).isEqualTo(eventListener1.getEventsHandled());
        Assertions.assertThat(1).isEqualTo(eventListener2.getEventsHandled());
    }

    @Test
    public void testCustomEvents() {
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);
        eventBus.post(new CustomEvent("Test action"));
        Assertions.assertThat("Test action").isEqualToIgnoringCase(eventListener.getAction());
    }

    private class EventListener {
        private int eventsHandled = 0;
        private String action = null;

        @Subscribe
        public void stringEvent(String event) {
            eventsHandled++;
        }

        @Subscribe
        public void customEvent(CustomEvent customEvent) {
            this.action = customEvent.getAction();
        }

        public int getEventsHandled() {
            return eventsHandled;
        }

        public String getAction() {
            return action;
        }
    }

    private class CustomEvent {
        private String action;

        public CustomEvent(String action) {
            this.action = action;
        }

        public String getAction() {
            return action;
        }
    }
}
