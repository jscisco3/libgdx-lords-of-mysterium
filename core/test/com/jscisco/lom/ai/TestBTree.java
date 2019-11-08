package com.jscisco.lom.ai;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.ai.btree.ChooseTargetPosition;
import com.jscisco.lom.attributes.ai.btree.MoveToTargetPosition;
import com.jscisco.lom.attributes.ai.btree.PickDirectionTask;
import com.jscisco.lom.attributes.ai.btree.WalkTask;
import com.jscisco.lom.entity.EntityName;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.StageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBTree {

    private static final Logger logger = LoggerFactory.getLogger(TestBTree.class);

    BehaviorTree<NPC> tree;
    BehaviorTreeLibrary library;

    @BeforeEach
    public void setUp() {
        BehaviorTreeLibraryManager libraryManager = BehaviorTreeLibraryManager.getInstance();
        library = new BehaviorTreeLibrary(BehaviorTreeParser.DEBUG_HIGH);
        registerNpcBehavior(library);
        libraryManager.setLibrary(library);
//        tree = libraryManager.createBehaviorTree("test", new NPC(null, null, null));
    }

    @Disabled
    @Test
    public void test() {
        logger.info("{}", tree.getObject());
        tree.run();
        tree.step();
        tree.run();
        tree.step();
        tree.run();
        tree.step();
        logger.info("# children: {}", tree.getChildCount());
        logger.info("Status: {}", tree.getStatus());
    }

    @Test
    @Disabled
    public void testWanderBehavior() {
        Stage stage = new StageImpl(10, 10);
        NPC npc = new NPC.Builder(new EntityName("NPC"))
                .withStage(stage)
                .withPosition(new Position(5, 5))
                .withBehaviorTree(library.createBehaviorTree("wander"))
                .build();
        npc.updatePathingMap();
        for (int i = 0; i < 10; i++) {
            npc.getBehaviorTree().step();
            Action action = npc.getNextAction();
            if (action != null) {
                action.invoke();
            }
            logger.info("Status: {}", npc.getBehaviorTree().getStatus());
        }

    }

    private void registerNpcBehavior(BehaviorTreeLibrary library) {
        BehaviorTree<NPC> behavior = new BehaviorTree<NPC>(createNpcBehavior());
        library.registerArchetypeTree("test", behavior);
        library.registerArchetypeTree("wander", new BehaviorTree<>(createWanderBehavior()));
    }

    private Task<NPC> createNpcBehavior() {
        Selector<NPC> selector = new Selector<>();
        selector.addChild(new PickDirectionTask());
        selector.addChild(new WalkTask());

        return selector;
    }

    private Task<NPC> createWanderBehavior() {
        Sequence<NPC> sequence = new Sequence<>();
        sequence.addChild(new ChooseTargetPosition());
        sequence.addChild(new MoveToTargetPosition());
        return sequence;
    }

}
