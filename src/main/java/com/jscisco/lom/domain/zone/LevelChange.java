package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Position;

import java.util.UUID;

public class LevelChange extends Feature {

    UUID toLevelId;

    Position toPosition;

    public LevelChange(UUID toLevelId, Position toPosition, boolean descend) {
        if (descend) {
            this.glyph = Assets.stairsDown;
        } else {
            this.glyph = Assets.stairsUp;
        }
        this.toPosition = toPosition;
        this.toLevelId = toLevelId;
    }

    public UUID getToLevelId() {
        return toLevelId;
    }

    public Position getToPosition() {
        return toPosition;
    }

    @Override
    public String toString() {
        return "LevelChange{" + "toLevelId=" + toLevelId + ", toPosition=" + toPosition + '}';
    }
}
