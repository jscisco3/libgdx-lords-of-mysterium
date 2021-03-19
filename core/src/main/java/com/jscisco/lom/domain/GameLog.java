package com.jscisco.lom.domain;

import com.google.common.eventbus.Subscribe;
import com.jscisco.lom.domain.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GameLog {

    private static final Logger logger = LoggerFactory.getLogger(GameLog.class);
    private final List<String> log;
    private final Subject subject;

    public GameLog() {
        this.log = new ArrayList<>();
        this.subject = new Subject();
    }

    public void log(String message) {
        this.log.add(message);
        subject.notify(null);
    }

    public List<String> getLogs(int num) {
        return log.subList(Math.max(0, log.size() - num), log.size());
    }

    public Subject getSubject() {
        return subject;
    }

    public List<String> getLogs() {
        return this.log;
    }

    @Subscribe
    public void handleLogEvent(LogEvent event) {
        logger.debug("Received Log Event: " + event.toString());
        this.log(event.getMessage());
    }
}
