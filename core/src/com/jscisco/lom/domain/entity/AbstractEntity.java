package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.combat.Damage;
import com.jscisco.lom.domain.component.Health;
import com.jscisco.lom.domain.component.Inventory;
import com.jscisco.lom.domain.item.Item;

import java.util.Objects;

public abstract class AbstractEntity {
    EntityName name;
    Position position;
    // TODO: Are any of these optional?
    Inventory inventory;
    Health health;

    public void pickUpItem(Item item) {
        if (Objects.isNull(inventory)) {
            throw new EntityCannotPickItemUpException(this, item);
        }
        inventory.addItem(item);
        item.setPosition(null);
    }

    public Item dropItem(int index) {
        if (Objects.isNull(inventory)) {
            throw new RuntimeException("Entity does not have an inventory, so cannot drop item");
        }
        Item item = inventory.removeItem(index);
        item.setPosition(this.position);
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
