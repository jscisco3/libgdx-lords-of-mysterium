package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Position;

import javax.annotation.Nonnull;
import java.util.UUID;

public class LevelChange extends Feature {

    @Nonnull
    UUID toLevelId;

    @Nonnull
    Position toPosition;

    public LevelChange(@Nonnull UUID toLevelId, @Nonnull Position toPosition, boolean descend) {
        if (descend) {
            this.glyph = Assets.stairsDown;
        } else {
            this.glyph = Assets.stairsUp;
        }
        this.toPosition = toPosition;
        this.toLevelId = toLevelId;
    }

    @Nonnull
    public UUID getToLevelId() {
        return toLevelId;
    }

    @Nonnull
    public Position getToPosition() {
        return toPosition;
    }

    @Override
    public String toString() {
        return "LevelChange{" +
                "toLevelId=" + toLevelId +
                ", toPosition=" + toPosition +
                '}';
    }
}
