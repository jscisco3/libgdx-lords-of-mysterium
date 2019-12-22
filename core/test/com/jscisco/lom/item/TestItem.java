package com.jscisco.lom.item;

import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.Slot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestItem {

    @Test
    void itemIsEquippableIfItHasASlot() {
        Item item = new Item.Builder()
                .withEquipmentSlot(Slot.HAND)
                .build();
        assertTrue(item.equippable());
    }

    @Test
    void itemIsNotEquippableIfItDoesNotHaveASlot() {
        Item item = new Item.Builder()
                .build();
        assertFalse(item.equippable());
    }

}
