package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.zone.Level;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class LevelEvent extends Event {

    @ManyToOne
    Level level;

}
