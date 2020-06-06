package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.combat.Damage;

import java.util.Objects;

public class GameObject {
    EntityName name;
    Position position;
    // TODO: Are any of these optional?
    Inventory inventory;
    Health health;
    Item item;

    public GameObject() {
    }

    public GameObject(EntityName name) {
        this.name = name;
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

    public void moveTo(Position position) {
        this.position = position;
    }

}
