package com.jscisco.lom.domain;

import com.jscisco.lom.domain.EntityName;
import com.jscisco.lom.domain.GameObject;
import com.jscisco.lom.domain.Health;

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
