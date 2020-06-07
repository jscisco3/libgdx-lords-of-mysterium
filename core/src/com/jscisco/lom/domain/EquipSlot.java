package com.jscisco.lom.domain;

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

    public boolean canEquip(Item item) {
        return item.isEquippable() && item.equippable().getSlot().equals(this.type);
    }


    public Item equip(Item item) {
        if (!canEquip(item)) {
            throw new IllegalArgumentException("Cannot equip item to this slot");
        }
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
