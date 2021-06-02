package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class TileExplored extends LevelEvent {

    @Embedded
    private Position position;

    public TileExplored() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void process(Level level) {
        super.process(level);
        level.getTileAt(position).explore();
    }

    @Override
    public String toString() {
        return "TileExplored{" +
                "id=" + id +
                ", eventTime=" + eventTime +
                ", position=" + position +
                '}';
    }
}
