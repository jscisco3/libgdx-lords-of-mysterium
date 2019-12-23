package com.jscisco.lom.attributes;

import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.entity.Equipment;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemCannotBeEquippedException;
import com.jscisco.lom.items.ItemName;
import com.jscisco.lom.items.Slot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    /**
     * Given an item
     * With an EquipmentSlot that is in the equipment's slotTypes
     * It should be equippable
     */
    @Test
    void canEquipItemIfSlotIsAvailable() {
        Item item = new Item.Builder()
                .withEquipmentSlot(Slot.BODY)
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
                .withEquipmentSlot(Slot.TAIL)
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
                .withName(new ItemName("Sword"))
                .withEquipmentSlot(Slot.HAND)
                .withAttack(new Attack(10, new Damage(DamageType.FIRE, 20)))
                .build();
        equipment.getSlotsByType(Slot.HAND).get(0).equip(item);
        Assertions.assertThat(equipment.getWeapons()).isNotEmpty();
    }
}
