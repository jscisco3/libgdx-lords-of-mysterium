package com.jscisco.lom.config;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;
import com.jscisco.lom.attributes.ai.btree.ChooseTargetPosition;
import com.jscisco.lom.attributes.ai.btree.MoveToTargetPosition;
import com.jscisco.lom.entity.NPC;

public class BehaviorRepository {

    private final BehaviorTreeLibrary library = new BehaviorTreeLibrary();

    public BehaviorRepository() {
        BehaviorTreeLibraryManager libraryManager = BehaviorTreeLibraryManager.getInstance();
        registerNpcBehavior(library);
        libraryManager.setLibrary(library);
    }

    public BehaviorTree<NPC> retrieveTree(String identifier, NPC npc) {
        return library.createBehaviorTree(identifier, npc);
    }

    private void registerNpcBehavior(BehaviorTreeLibrary library) {
        library.registerArchetypeTree("wander", new BehaviorTree<>(createWanderBehavior()));
    }

    private Task<NPC> createWanderBehavior() {
        Sequence<NPC> sequence = new Sequence<>();
        sequence.addChild(new ChooseTargetPosition());
        sequence.addChild(new MoveToTargetPosition());
        return sequence;
    }

}
