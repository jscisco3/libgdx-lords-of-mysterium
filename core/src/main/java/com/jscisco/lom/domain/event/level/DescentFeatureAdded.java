package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.LevelChange;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class DescentFeatureAdded extends LevelEvent {

    private UUID toLevel;
    private Position atPosition;

    protected DescentFeatureAdded() {
    }

    public DescentFeatureAdded(UUID toLevel, Position atPosition) {
        this.toLevel = toLevel;
        this.atPosition = atPosition;
    }

    @Override
    public void process() {
        level.getTileAt(this.atPosition).setFeature(
                new LevelChange(toLevel, atPosition, true)
        );
    }
}
