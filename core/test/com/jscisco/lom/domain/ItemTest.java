package com.jscisco.lom.domain;

import com.jscisco.lom.domain.GameObject;
import com.jscisco.lom.domain.ItemRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    @Test
    public void itemOnlyEquippableIfItHasRequisiteComponent() {
        Item item = ItemRepository.createSword();
        assertTrue(item.isEquippable());
    }

    @Test
    public void itemWithoutEquippableComponentIsNotEquippable() {
        Item item = ItemRepository.createPotion();
        assertFalse(item.isEquippable());
    }

}
