package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelChange;

import java.util.UUID;

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
    public void process(Level level) {
        super.process(level);
        level.getTileAt(this.atPosition).setFeature(new LevelChange(toLevel, atPosition, true));
    }
}
