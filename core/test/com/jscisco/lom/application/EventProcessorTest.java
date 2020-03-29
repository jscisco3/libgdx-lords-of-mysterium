package com.jscisco.lom.application;

import com.jscisco.lom.domain.Event;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class EventProcessorTest {

    EventProcessor processor;
    Deque<Event> eventQueue = new ArrayDeque<>();

    @Test
    public void eventProcessorShouldHaveNoEventsWhenNewlyCreated() {
        processor = new EventProcessor(eventQueue);
        Assertions.assertThat(processor.hasEvents()).isFalse();
    }

    @Test
    public void eventProcessorShouldHaveEventsIfOneIsCreated() {
        Event event = new TestEvent();
        processor = new EventProcessor(eventQueue);
        processor.enqueue(event);
        Assertions.assertThat(processor.hasEvents()).isTrue();
    }

    @Test
    public void eventProcessorShouldBeEmptyAfterProcessing() {
        Event event = new TestEvent();
        processor = new EventProcessor(eventQueue);
        processor.enqueue(event);
        processor.process();
        Assertions.assertThat(processor.hasEvents()).isFalse();
    }

    private class TestEvent extends Event {

        @Override
        public void process() {

        }
    }

}
