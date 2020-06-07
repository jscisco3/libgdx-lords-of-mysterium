package com.jscisco.lom.domain;

import com.jscisco.lom.domain.combat.Damage;

import javax.annotation.Nullable;
import java.util.Objects;

public class GameObject {
    EntityName name;
    Position position;
    @Nullable
    Inventory inventory;
    @Nullable
    Equipment equipment;
    @Nullable
    Health health;
    @Nullable
    Item item;

    InputComponent input;

    private GameObject() {
    }

    public static GameObject player(EntityName name, Health health) {
        GameObject player = new GameObject(name);
        player.inventory = new Inventory();
        player.equipment = new Equipment();
        player.health = health;
        player.input = new PlayerInputComponent();
        return player;
    }

    public static GameObject npc(EntityName name, Health health) {
        GameObject entity = new GameObject(name);
        entity.inventory = new Inventory();
        entity.equipment = new Equipment();
        entity.health = health;
        entity.input = new AIInputComponent();
        return entity;
    }

    public static GameObject item(EntityName name, Item item) {
        GameObject anItem = new GameObject(name);
        anItem.item = item;
        return anItem;
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
