package com.jscisco.lom.domain;


public class Equippable {
    private Equipment.EquipmentType slot;

    public Equippable(Equipment.EquipmentType slot) {
        this.slot = slot;
    }

    public Equipment.EquipmentType getSlot() {
        return slot;
    }
}
