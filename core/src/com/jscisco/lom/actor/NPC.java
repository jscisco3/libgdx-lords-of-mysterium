package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.commands.RestCommand;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPC extends Actor {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);

    public NPC(TextureRegion texture, Position3D position) {
        this.texture = texture;
        this.position = position;
    }

    @Override
    public Command getNextCommand() {
        logger.info("{} is resting!", this);
        return new RestCommand(null, this);
    }
}
