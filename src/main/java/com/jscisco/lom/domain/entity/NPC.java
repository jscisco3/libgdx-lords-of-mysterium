package com.jscisco.lom.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jscisco.lom.ai.AIController;
import com.jscisco.lom.ai.RestAIController;
import com.jscisco.lom.ai.WanderAIController;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.raws.RawNPC;

public class NPC extends Entity {

    @JsonIgnore
    private AIController aiController;

    public static NPC from(RawNPC raw) {
        NPC npc = new NPC();
        npc.name = Name.of(raw.name);
        npc.glyph = raw.glyph;
        // TODO: More raws
        npc.aiController = new RestAIController(npc);
        npc.inventory = new Inventory();
        return npc;
    }

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
            npc.setInventory(new Inventory());
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
