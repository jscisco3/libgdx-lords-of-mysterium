package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.LevelChange;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class LevelTransitionFeatureAdded extends LevelEvent {

    private UUID toLevel;
    private Position atPosition;
    private boolean descending;

    protected LevelTransitionFeatureAdded() {
    }

    public LevelTransitionFeatureAdded(UUID toLevel, Position atPosition, boolean descending) {
        this.toLevel = toLevel;
        this.atPosition = atPosition;
        this.descending = descending;
    }

    @Override
    public void process() {
        super.process();
        level.getTileAt(this.atPosition).setFeature(
                new LevelChange(toLevel, atPosition, descending)
        );
    }
}
