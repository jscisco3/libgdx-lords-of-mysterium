package com.jscisco.lom.domain;

import com.jscisco.lom.domain.combat.Damage;

import javax.annotation.Nullable;
import java.util.Objects;

public class Entity extends GameObject {
    @Nullable
    Inventory inventory;
    @Nullable
    Equipment equipment;
    @Nullable
    Health health;

    InputComponent input;


    private Entity() {
    }

    private Entity(EntityName name) {
        this.name = name;
    }

    public static Entity player(EntityName name, Health health) {
        Entity player = new Entity(name);
        player.inventory = new Inventory();
        player.equipment = new Equipment();
        player.health = health;
        player.input = new PlayerInputComponent();
        return player;
    }

    public static Entity npc(EntityName name, Health health) {
        Entity entity = new Entity(name);
        entity.inventory = new Inventory();
        entity.equipment = new Equipment();
        entity.health = health;
        entity.input = new AIInputComponent();
        return entity;
    }


    public void pickUpItem(GameObject item) {
        if (Objects.isNull(inventory)) {
            throw new EntityCannotPickItemUpException(this, item);
        }
        inventory.addItem(item);
        item.position = null;
    }

    public GameObject dropItem(int index) {
        if (Objects.isNull(inventory)) {
            throw new RuntimeException("Entity does not have an inventory, so cannot drop item");
        }
        GameObject item = inventory.removeItem(index);
        item.position = this.position;
        return item;
    }

    public void damage(Damage damage) {
        assert health != null;
        this.health.decrease(damage.getDamage());
    }

    public Health getHealth() {
        return health;
    }

}
