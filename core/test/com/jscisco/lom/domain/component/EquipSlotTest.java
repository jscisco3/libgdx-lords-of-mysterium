package com.jscisco.lom.domain.component;

import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.item.ItemRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EquipSlotTest {

    @Test
    public void equippingAnItemToThatSlotWorks() {
        EquipSlot slot = new EquipSlot(Equipment.EquipmentType.HAND);
        slot.equip(ItemRepository.createSword());
        assertThat(slot.item).isNotNull();
        assertThat(slot.item.getName()).isEqualTo("Bronze shortsword");
    }

    @Test
    public void unequippingASlotReturnsAnItemThere() {
        EquipSlot slot = new EquipSlot(Equipment.EquipmentType.HAND);
        slot.equip(ItemRepository.createSword());
        Item item = slot.unequip();
        assertThat(slot.getItem()).isNull();
        assertThat(item.getName()).isEqualTo("Bronze shortsword");
    }

}
