package com.jscisco.lom.ai;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Selector;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.jscisco.lom.attributes.ai.btree.PickDirectionTask;
import com.jscisco.lom.attributes.ai.btree.WalkTask;
import com.jscisco.lom.entity.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBTree {

    private static final Logger logger = LoggerFactory.getLogger(TestBTree.class);

    BehaviorTree<NPC> tree;

    @BeforeEach
    public void setUp() {
        BehaviorTreeLibraryManager libraryManager = BehaviorTreeLibraryManager.getInstance();
        BehaviorTreeLibrary library = new BehaviorTreeLibrary(BehaviorTreeParser.DEBUG_HIGH);
        registerNpcBehavior(library);
        libraryManager.setLibrary(library);

        tree = libraryManager.createBehaviorTree("test", new NPC(null, null, null));
    }

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

    private void registerNpcBehavior(BehaviorTreeLibrary library) {
        BehaviorTree<NPC> behavior = new BehaviorTree<NPC>(createNpcBehavior());
        library.registerArchetypeTree("test", behavior);
    }

    private Task<NPC> createNpcBehavior() {
        Selector<NPC> selector = new Selector<>();
        selector.addChild(new PickDirectionTask());
        selector.addChild(new WalkTask());

        return selector;
    }

}
