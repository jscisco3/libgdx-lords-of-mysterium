package com.jscisco.lom.application;

import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.Listener;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class EventPublisherTest {

    @Test
    public void canRegisterAListener() {
        EventPublisher publisher = new EventPublisher();
        EventPublisher spy = Mockito.spy(publisher);
        Listener testListener = Mockito.mock(Listener.class);
        spy.register(testListener);
        assertThat(spy.getListeners()).isNotEmpty();
        verify(spy).register(testListener);
    }

    @Test
    public void canUnregisterARegisteredListener() {
        EventPublisher publisher = new EventPublisher();
        Listener testListener = Mockito.mock(Listener.class);

        publisher.register(testListener);
        publisher.unregister(testListener);
        assertThat(publisher.getListeners()).isEmpty();
    }

    @Test
    public void publishingAMessageIsHandledByListeners() {
        EventPublisher publisher = new EventPublisher();
        Listener testListener = Mockito.mock(Listener.class);
        Event testEvent = new Event() {
        };

        publisher.register(testListener);
        publisher.publish(testEvent);
        verify(testListener).handle(testEvent);
    }


}
