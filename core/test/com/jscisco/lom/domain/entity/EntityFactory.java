package com.jscisco.lom.domain.entity;

public class EntityFactory {

    public static GameObject npc() {
        GameObject npc = new GameObject();
        npc.health = new Health(100);
        npc.name = new EntityName("Test NPC");
        npc.position = null;
        npc.inventory = null;
        return npc;
    }

}
