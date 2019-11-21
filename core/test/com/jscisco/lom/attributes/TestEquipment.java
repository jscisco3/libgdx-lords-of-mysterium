package com.jscisco.lom.attributes;

import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.entity.Equipment;
import com.jscisco.lom.items.EquipmentSlot;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemCannotBeEquippedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Item item = new Item.Builder()
                .withEquipmentSlot(EquipmentSlot.BODY)
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
        Item item = new Item.Builder()
                .withEquipmentSlot(EquipmentSlot.TAIL)
                .withPosition(null)
                .withGlyph(null)
                .build();
        Assertions.assertThat(this.equipment.canEquip(item)).isFalse();
    }

    /**
     * Given a set of equipment
     * When I try to get the weapons
     * I should get back a list of items that have an Attack
     */
    @Test
    void getWeaponsFromEquipment() throws ItemCannotBeEquippedException {
        Item item = new Item.Builder()
                .withEquipmentSlot(EquipmentSlot.HAND)
                .withAttack(new Attack(10, new Damage(DamageType.FIRE, 5)))
                .build();
        equipment.equip(item);
        Assertions.assertThat(equipment.getWeapons()).isNotEmpty();
    }

    /**
     * Given an item that does not have an EquipmentSlot
     * When I try to equip that item
     * An exception is thrown.
     */
    @Test
    void itemCannotBeEquippedExceptionThrownIfItemIsUnequippable() {
        Item item = new Item.Builder().build();
        assertThrows(ItemCannotBeEquippedException.class, () -> {
            equipment.equip(item);
        });
    }
}
