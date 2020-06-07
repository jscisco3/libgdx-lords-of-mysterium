package com.jscisco.lom.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EquipSlotTest {

    @Test
    public void equippingAnItemToThatSlotWorks() {
        EquipSlot slot = new EquipSlot(Equipment.EquipmentType.HAND);
        slot.equip(ItemRepository.createSword());
        assertThat(slot.item).isNotNull();
        assertThat(slot.item.name.name).isEqualTo("Bronze shortsword");
    }

    @Test
    public void unequippingASlotReturnsAnItemThere() {
        EquipSlot slot = new EquipSlot(Equipment.EquipmentType.HAND);
        slot.equip(ItemRepository.createSword());
        GameObject item = slot.unequip();
        assertThat(slot.getItem()).isNull();
        assertThat(item.name.name).isEqualTo("Bronze shortsword");
    }

}
