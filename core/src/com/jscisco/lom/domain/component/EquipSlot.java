package com.jscisco.lom.domain.component;

import com.jscisco.lom.domain.item.Item;

public class EquipSlot {
    Equipment.EquipmentType type;
    Item item;

    public EquipSlot(Equipment.EquipmentType type) {
        this.type = type;
        this.item = null;
    }

    public Equipment.EquipmentType getType() {
        return type;
    }

    public Item getItem() {
        return item;
    }

    public Item equip(Item item) {
        Item unequipped = unequip();
        this.item = item;
        return unequipped;
    }

    public Item unequip() {
        if (this.item == null) {
            return null;
        }
        Item unequipped = this.item;
        this.item = null;
        return unequipped;
    }

}
