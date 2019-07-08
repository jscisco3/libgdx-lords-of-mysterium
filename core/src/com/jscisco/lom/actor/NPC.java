package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.attributes.ai.AI;
import com.jscisco.lom.attributes.ai.RestAI;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPC extends Actor {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private AI ai;

    public NPC(TextureRegion texture, Position3D position) {
        this.texture = texture;
        this.position = position;
        this.ai = new RestAI();
    }

    @Override
    public Command getNextCommand() {
        return this.ai.nextAction(null, this);
    }
}
