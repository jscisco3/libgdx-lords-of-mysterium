package com.jscisco.lom.domain.entity;

public class EquipSlot {
    Equipment.EquipmentType type;
    GameObject item;

    public EquipSlot(Equipment.EquipmentType type) {
        this.type = type;
        this.item = null;
    }

    public Equipment.EquipmentType getType() {
        return type;
    }

    public GameObject getItem() {
        return item;
    }

    public boolean canEquip(GameObject item) {
        return item.item.isEquippable() && item.item.equippable().getSlot().equals(this.type);
    }


    public GameObject equip(GameObject item) {
        if (!canEquip(item)) {
            throw new IllegalArgumentException("Cannot equip item to this slot");
        }
        GameObject unequipped = unequip();
        this.item = item;
        return unequipped;
    }

    public GameObject unequip() {
        if (this.item == null) {
            return null;
        }
        GameObject unequipped = this.item;
        this.item = null;
        return unequipped;
    }

}
