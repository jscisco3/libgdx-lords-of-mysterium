package com.jscisco.lom.domain.event;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "event_type",
//        discriminatorType = DiscriminatorType.STRING
//)
public abstract class Event {

    @Id
    @GeneratedValue
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    protected Long id;

    @Column(name = "event_time", columnDefinition = "TIMESTAMP")
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
