package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.ai.AI;
import com.jscisco.lom.attributes.ai.RestAI;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPC extends Entity {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private AI ai;

    public NPC(Dungeon dungeon, TextureRegion texture, Position3D position) {
        super(dungeon);
        this.texture = texture;
        this.position = position;
        this.ai = new RestAI();
    }

    @Override
    public Action getNextAction() {
        return this.ai.nextAction(this);
    }
}
