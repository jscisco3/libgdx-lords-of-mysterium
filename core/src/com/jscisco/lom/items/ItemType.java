package com.jscisco.lom.items;

import com.jscisco.lom.entity.Equipment;

public class ItemType {

    private String name;
    private String description;
    private int value;
    private Equipment.EquipmentSlot equipSlot;

    private ItemType() {
    }

    public Equipment.EquipmentSlot getEquipSlot() {
        return equipSlot;
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

    public static class Builder {
        private String name;
        private String description;
        private int value;
        private Equipment.EquipmentSlot equipSlot;

        public Builder() {

        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withValue(int value) {
            this.value = value;
            return this;
        }

        public Builder withEquipSlot(Equipment.EquipmentSlot equipSlot) {
            this.equipSlot = equipSlot;
            return this;
        }

        public ItemType build() {
            ItemType itemType = new ItemType();
            itemType.name = this.name;
            itemType.description = this.description;
            itemType.value = this.value;
            itemType.equipSlot = this.equipSlot;
            return itemType;
        }

    }
}
