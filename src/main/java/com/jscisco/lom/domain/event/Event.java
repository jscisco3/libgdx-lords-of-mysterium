package com.jscisco.lom.domain.event;

import java.time.LocalDateTime;

public abstract class Event {
    protected Long id;

    protected LocalDateTime eventTime = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
