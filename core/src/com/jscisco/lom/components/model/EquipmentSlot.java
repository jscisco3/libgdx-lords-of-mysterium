package com.jscisco.lom.components.model;

public class EquipmentSlot {
    private int item;
    private EquipmentType type;

    public EquipmentSlot(EquipmentType type) {
        this.item = Integer.MIN_VALUE;
        this.type = type;
    }

    public EquipmentSlot(int item, EquipmentType type) {
        this.item = item;
        this.type = type;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public EquipmentType getType() {
        return type;
    }
}
