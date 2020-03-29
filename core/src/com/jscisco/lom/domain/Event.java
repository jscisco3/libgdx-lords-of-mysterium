package com.jscisco.lom.domain;

import java.time.LocalDate;

public abstract class Event {

    private LocalDate occurred = LocalDate.now();

    public abstract void process();

}
