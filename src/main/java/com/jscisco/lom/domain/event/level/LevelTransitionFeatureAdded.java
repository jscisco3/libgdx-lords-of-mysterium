package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelChange;

import java.util.UUID;

public class LevelTransitionFeatureAdded extends LevelEvent {

    // Level we are transitioning to
    private UUID toLevel;
    // Position to place the feature
    private Position atPosition;
    // Position feature should put us at
    private Position toPosition;
    // Whether we are descending or not - used for the asset.
    // TODO: Should this just be an asset string?
    private boolean descending;

    protected LevelTransitionFeatureAdded() {
    }

    public LevelTransitionFeatureAdded(UUID toLevel, Position atPosition, Position toPosition, boolean descending) {
        this.toLevel = toLevel;
        this.atPosition = atPosition;
        this.toPosition = toPosition;
        this.descending = descending;
    }

    @Override
    public void process(Level level) {
        super.process(level);
        level.getTileAt(this.atPosition).setFeature(
                new LevelChange(toLevel, toPosition, descending)
        );
    }
}
