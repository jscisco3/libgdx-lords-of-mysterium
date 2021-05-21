package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@javax.persistence.Entity
@DiscriminatorValue("N")
public class NPC extends Entity {

    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
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
