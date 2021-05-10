package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Transient;

@javax.persistence.Entity
@DiscriminatorValue("N")
public class NPC extends Entity {

    @Transient
    private AIController aiController;

    public static class Builder extends Entity.Builder<Builder> {

        private AIController controller;

        public Builder withController(AIController controller) {
            this.controller = controller;
            return this;
        }

        public NPC build() {
            NPC npc = new NPC();
            npc.name = this.name;
            npc.position = this.position;
            npc.asset = this.asset;
            npc.aiController = controller;
            return npc;
        }
    }

    @Override
    public Action nextAction() {
        return aiController.getNextAction();
    }

    @Override
    public void onDied() {
        super.onDied();
        this.level.removeEntity(this);
    }

    public AIController getAiController() {
        return aiController;
    }

    public void setAiController(AIController aiController) {
        this.aiController = aiController;
    }
}
