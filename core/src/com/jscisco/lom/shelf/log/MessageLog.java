package com.jscisco.lom.shelf.log;

import java.util.ArrayList;
import java.util.List;

public class MessageLog {

    private List<Message> log = new ArrayList<>();

    private MessageLog() {
    }

    private static class Singleton {
        private static final MessageLog instance = new MessageLog();
    }

    public static MessageLog get() {
        return Singleton.instance;
    }

    public List<Message> recentMessages() {
        if (log.size() > 5) {
            return log.subList(0, 5);
        }
        return log;
    }

    public void add(Message message) {
        // Add it to the beginning of the log
        Singleton.instance.log.add(0, message);
    }

    public void clear() {
        log = new ArrayList<>();
    }

}
