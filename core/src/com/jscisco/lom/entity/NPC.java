package com.jscisco.lom.entity;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.attributes.ai.AI;
import com.jscisco.lom.attributes.ai.goap.GOAPAgent;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPAction;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class NPC extends Entity {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private BehaviorTree<NPC> behaviorTree;
    private AI ai;
    private GOAPAgent agent;
    private Set<GOAPAction> actions;

    public NPC(Stage stage, TextureRegion texture, Position position) {
        super(stage);
        this.texture = texture;
        this.position = position;
        this.health = new Health(40);
        this.behaviorTree = Config.repository.retrieveTree("wander", this);
//        // Hunter Seeker actions
//        this.actions = new HashSet<>();
//        this.actions.add(new MoveToAction());
//        this.actions.add(new AcquireRandomTargetAction());
//        this.actions.add(new AcquireTargetAction());
//        this.actions.add(new AttackTargetAction());
//        this.agent = new GOAPAgent(this);

        // NPC needs to get in position
//        this.setGoal(GOAPGoal.IN_POSITION, true);
//        // NPC needs target
//        this.updateWorldState(GOAPGoal.NEEDS_TARGET, true);
//        this.updateWorldState(GOAPGoal.HAS_TARGET, false);
//        // The NPC is not in position
//        this.updateWorldState(GOAPGoal.IN_POSITION, false);

    }

//    @Override
//    public Action getNextAction() {
//////        return this.ai.nextAction();
////        this.agent.update();
//        return this.nextAction;
//    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    @Override
    public Action getNextAction() {
        this.behaviorTree.step();
        return super.getNextAction();
    }

    public Set<GOAPAction> getAvailableActions() {
        return actions;
    }

    // TODO: Remove this access?
    public GOAPAgent getAgent() {
        return agent;
    }
}
