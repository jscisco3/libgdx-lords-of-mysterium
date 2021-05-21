package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class LevelEvent extends Event {

    private static final Logger logger = LoggerFactory.getLogger(LevelEvent.class);

    @ManyToOne
    Level level;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void process() {
        logger.info("Processing " + this);
    }

}
