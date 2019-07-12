package com.jscisco.lom.attributes;

import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEquipment {

    private Equipment equipment;

    @BeforeEach
    public void setUp() {
        this.equipment = new Equipment();
    }

    @Test
    public void whenNothingIsEquippedNumberOfEquippedItemsIsZero() {
        Assertions.assertThat(this.equipment.getNumberOfEquippedItems()).isEqualTo(0);
    }

    @Test
    public void numberOfSlotsIsEqualToNumberOfSlotTypes() {
        Assertions.assertThat(this.equipment.getSlots().size()).isEqualTo(this.equipment.getSlotTypes().size());
    }

    /**
     * Given an item
     * With an EquipmentSlot that is in the equipment's slotTypes
     * It should be equippable
     */
    @Test
    public void canEquipItemIfSlotIsAvailable() {
        Item item = new Item(
                new ItemType(
                        "Test",
                        "Test Description",
                        1,
                        Equipment.EquipmentSlot.HAND
                ),
                null,
                null
        );

        Assertions.assertThat(this.equipment.canEquip(item)).isTrue();
    }

    /**
     * Given an Item
     * With an EquipmentSlot that is not in the equipment's SlotTypes
     * It should not be equippable
     */
    @Test
    public void cannotEquipItemIfSlotNotAvailable() {
        Item item = new Item(
                new ItemType(
                        "Test",
                        "Test Description",
                        1,
                        Equipment.EquipmentSlot.TAIL
                ),
                null,
                null
        );
        Assertions.assertThat(this.equipment.canEquip(item)).isFalse();
    }

    /**
     *
     */

}
