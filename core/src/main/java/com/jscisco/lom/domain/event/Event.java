package com.jscisco.lom.domain.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public abstract class Event {

    @Id
    @GeneratedValue
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    private Long id;

}
