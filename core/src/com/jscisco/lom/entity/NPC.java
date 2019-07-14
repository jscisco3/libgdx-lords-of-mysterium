package com.jscisco.lom.entity;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPC extends Entity {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private BehaviorTree<NPC> behaviorTree;

    public NPC(Stage stage, TextureRegion texture, Position position) {
        super(stage);
        this.texture = texture;
        this.position = position;
        this.health = new Health(40);
        this.behaviorTree = Config.repository.retrieveTree("wander", this);
    }

    @Override
    public Action getNextAction() {
        this.behaviorTree.step();
        return super.getNextAction();
    }

}
