package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.attributes.ai.AI;
import com.jscisco.lom.attributes.ai.WanderAI;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPC extends Entity {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private AI ai;

    public NPC(Stage stage, TextureRegion texture, Position position) {
        super(stage);
        this.texture = texture;
        this.position = position;
        this.health = new Health(40);
        this.ai = new WanderAI(this);
    }

    @Override
    public Action getNextAction() {
        return this.ai.nextAction();
    }
}
