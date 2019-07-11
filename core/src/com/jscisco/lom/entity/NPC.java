package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.attributes.ai.AI;
import com.jscisco.lom.attributes.ai.WanderAI;
import com.jscisco.lom.attributes.ai.goap.GOAPAgent;
import com.jscisco.lom.attributes.ai.goap.actions.AcquireTargetAction;
import com.jscisco.lom.attributes.ai.goap.actions.AttackTargetAction;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPAction;
import com.jscisco.lom.attributes.ai.goap.actions.WanderAction;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class NPC extends Entity {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private AI ai;
    private GOAPAgent agent;
    private Set<GOAPAction> actions;

    public NPC(Stage stage, TextureRegion texture, Position position) {
        super(stage);
        this.texture = texture;
        this.position = position;
        this.health = new Health(40);
        this.ai = new WanderAI(this);
        // Hunter Seeker actions
        this.actions = new HashSet<>();
        this.actions.add(new WanderAction());
        this.actions.add(new AcquireTargetAction());
        this.actions.add(new AttackTargetAction());
        this.agent = new GOAPAgent(this);
    }

    @Override
    public Action getNextAction() {
//        return this.ai.nextAction();
        this.agent.update();
        return this.nextAction;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public Set<GOAPAction> getAvailableActions() {
        return actions;
    }
}
