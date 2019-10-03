package com.jscisco.lom.attributes;

import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestEquipment {

    private Equipment equipment;

    @BeforeEach
    void setUp() {
        this.equipment = new Equipment();
    }

    @Test
    void whenNothingIsEquippedNumberOfEquippedItemsIsZero() {
        Assertions.assertThat(this.equipment.getNumberOfEquippedItems()).isEqualTo(0);
    }

    @Test
    void numberOfSlotsIsEqualToNumberOfSlotTypes() {
        Assertions.assertThat(this.equipment.getSlots().size()).isEqualTo(this.equipment.getSlotTypes().size());
    }

    /**
     * Given an item
     * With an EquipmentSlot that is in the equipment's slotTypes
     * It should be equippable
     */
    @Test
    void canEquipItemIfSlotIsAvailable() {
        Item item = new Item.Builder(
                new ItemType.Builder()
                        .withName("Test")
                        .withDescription("Test Description")
                        .withValue(1)
                        .withEquipSlot(Equipment.EquipmentSlot.HAND).build())
                .withPosition(null)
                .withGlyph(null)
                .build();

        Assertions.assertThat(this.equipment.canEquip(item)).isTrue();
    }

    /**
     * Given an Item
     * With an EquipmentSlot that is not in the equipment's SlotTypes
     * It should not be equippable
     */
    @Test
    void cannotEquipItemIfSlotNotAvailable() {
        Item item = new Item.Builder(
                new ItemType.Builder()
                        .withName("Test")
                        .withDescription("Test Description")
                        .withValue(1)
                        .withEquipSlot(Equipment.EquipmentSlot.TAIL).build())
                .withPosition(null)
                .withGlyph(null)
                .build();
        Assertions.assertThat(this.equipment.canEquip(item)).isFalse();
    }
}
