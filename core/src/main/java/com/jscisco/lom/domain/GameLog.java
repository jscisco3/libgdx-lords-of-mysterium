package com.jscisco.lom.domain;

import java.util.ArrayList;
import java.util.List;

public class GameLog {

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
        return log.subList(log.size() - 1 - num, log.size() - 1);
    }

    public Subject getSubject() {
        return subject;
    }
}
