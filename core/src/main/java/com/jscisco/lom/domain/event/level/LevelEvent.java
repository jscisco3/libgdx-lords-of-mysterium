package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public abstract class LevelEvent extends Event {

    private static final Logger logger = LoggerFactory.getLogger(LevelEvent.class);

    UUID levelId;

    protected LevelEvent() {
    }

    public UUID getLevelId() {
        return levelId;
    }

    public void setLevelId(UUID levelId) {
        this.levelId = levelId;
    }

    public void process(Level level) {
        // todo: Asset level.id == levelid
        logger.debug("Processing " + this + " for level with id: " + level.getId());
        assert level.getId().equals(levelId);
    }

    @Override
    public String toString() {
        return "LevelEvent{" +
                "id=" + id +
                ", eventTime=" + eventTime +
                ", levelId=" + levelId +
                '}';
    }
}
