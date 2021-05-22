package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelChange;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class LevelTransitionFeatureAdded extends LevelEvent {

    // Level we are transitioning to
    private UUID toLevel;
    // Position to place the feature
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "at_x")),
            @AttributeOverride(name = "y", column = @Column(name = "at_y"))
    })
    private Position atPosition;
    // Position feature should put us at
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "to_x")),
            @AttributeOverride(name = "y", column = @Column(name = "to_y"))
    })
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
