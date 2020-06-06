package com.jscisco.lom.domain;

import com.jscisco.lom.domain.GameObject;
import com.jscisco.lom.domain.ItemRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    @Test
    public void itemOnlyEquippableIfItHasRequisiteComponent() {
        GameObject item = ItemRepository.createSword();
        assertTrue(item.item.isEquippable());
    }

    @Test
    public void itemWithoutEquippableComponentIsNotEquippable() {
        GameObject item = ItemRepository.createPotion();
        assertFalse(item.item.isEquippable());
    }

}
