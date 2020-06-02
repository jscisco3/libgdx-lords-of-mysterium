package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.component.Health;

public class EntityFactory {

    public static NPC npc() {
        NPC npc = new NPC();
        npc.health = new Health(100);
        npc.name = new EntityName("Test NPC");
        npc.position = null;
        npc.inventory = null;
        return npc;
    }

}
