package com.jscisco.lom.items;

import com.jscisco.lom.attributes.Equipment;

public class ItemType {

    private String name;
    private String description;
    private int value;
    private Equipment.EquipmentSlot equipSlot;

    public ItemType(String name, String description, int value, Equipment.EquipmentSlot equipSlot) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.equipSlot = equipSlot;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public Equipment.EquipmentSlot getEquipSlot() {
        return equipSlot;
    }
}
