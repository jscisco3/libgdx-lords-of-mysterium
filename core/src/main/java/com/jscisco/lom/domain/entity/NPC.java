package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public class NPC extends Entity {

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
            npc.glyph = this.glyph;
            npc.aiController = controller;
            return npc;
        }
    }

    @Override
    public Action nextAction() {
        return aiController.getNextAction(this);
    }

    @Override
    public void onDied() {
        super.onDied();
        this.level.removeEntity(this);
    }
}
