package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.entity.EquipSlot;
import com.jscisco.lom.domain.entity.Equipment;
import com.jscisco.lom.domain.entity.GameObject;
import com.jscisco.lom.domain.entity.ItemRepository;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquipmentTest {

    /**
     * Given an equipment with one hand slot
     * and an Item that takes a single hand
     * Then it can be equipped
     */
    @Test
    public void canEquipItemInAvailableSlot() {
        GameObject item = ItemRepository.createSword();
        Equipment equipment = new Equipment(new EquipSlot(Equipment.EquipmentType.HAND));
        assertTrue(equipment.canEquip(item));
    }

    /**
     * Given an equipment with multiple hand slots
     * and an Item that takes a single hand
     * Then we can get all possible slots
     */
    @Test
    public void possibleEquipmentSlotsForAnItem() {
        GameObject item = ItemRepository.createSword();

        Equipment equipment = new Equipment(new EquipSlot(Equipment.EquipmentType.HAND),
                new EquipSlot(Equipment.EquipmentType.HAND),
                new EquipSlot(Equipment.EquipmentType.HAND));

        Map<Integer, EquipSlot> slots = equipment.possibleSlots(item);
        assertThat(slots).isNotEmpty();
        assertThat(slots.values().size()).isEqualTo(3);
    }
}
