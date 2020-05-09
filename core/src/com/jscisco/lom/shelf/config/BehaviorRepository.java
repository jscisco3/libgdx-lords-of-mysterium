package com.jscisco.lom.shelf.config;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.branch.DynamicGuardSelector;
import com.badlogic.gdx.ai.btree.branch.Sequence;
import com.badlogic.gdx.ai.btree.decorator.Invert;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibrary;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;
import com.jscisco.lom.shelf.ai.btree.ChooseTargetPosition;
import com.jscisco.lom.shelf.ai.btree.GetTargetEntity;
import com.jscisco.lom.shelf.ai.btree.MoveToTargetPosition;
import com.jscisco.lom.shelf.ai.btree.guards.HasTargetEntityGuard;
import com.jscisco.lom.shelf.entity.NPC;

public class BehaviorRepository {

    private final BehaviorTreeLibrary library = new BehaviorTreeLibrary();

    public BehaviorRepository() {
        BehaviorTreeLibraryManager libraryManager = BehaviorTreeLibraryManager.getInstance();
        registerNpcBehavior(library);
        libraryManager.setLibrary(library);
    }

    public BehaviorTree<NPC> retrieveTree(String identifier) {
        return library.createBehaviorTree(identifier);
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

    private Task<NPC> createHunterSeekerBehavior() {
        DynamicGuardSelector<NPC> hunterSeeker = new DynamicGuardSelector<>();

        // Only get the target if we do not have a target entity
        GetTargetEntity getTargetEntity = new GetTargetEntity();
        getTargetEntity.setGuard(new Invert<>(new HasTargetEntityGuard()));

        hunterSeeker.addChild(getTargetEntity);

        // Only move towards the target if we have a target.
        HasTargetEntityGuard hasTargetEntityGuard = new HasTargetEntityGuard();
        Sequence<NPC> moveTowardsTargetEntity = new Sequence<>();
        moveTowardsTargetEntity.setGuard(hasTargetEntityGuard);


        hunterSeeker.addChild(moveTowardsTargetEntity);

        // Fall back to wandering
        hunterSeeker.addChild(createWanderBehavior());

        return hunterSeeker;
    }

}
