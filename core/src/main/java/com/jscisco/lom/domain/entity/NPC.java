package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public class NPC extends Entity {

    private AIController aiController;

    public static class Builder extends Entity.Builder {

        public NPC build() {
            NPC npc = new NPC();
            npc.name = this.name;
            npc.position = this.position;
            npc.texture = this.texture;
            npc.aiController = new WanderAIController();
            return npc;
        }
    }

    @Override
    public Action nextAction() {
        return aiController.getNextAction(this);
    }
}
