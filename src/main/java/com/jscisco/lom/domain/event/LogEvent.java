package com.jscisco.lom.domain.event;

public class LogEvent extends Event {

    private final String message;

    public LogEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LogEvent{" + "message='" + message + '\'' + '}';
    }
}
