package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public class NPC extends Actor {

    public NPC(TextureRegion texture, Position3D position) {
        this.texture = texture;
        this.position = position;
    }

    @Override
    public Command getNextCommand() {
        return super.getNextCommand();
    }
}
