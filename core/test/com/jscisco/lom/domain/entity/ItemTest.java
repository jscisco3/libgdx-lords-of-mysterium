package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.entity.GameObject;
import com.jscisco.lom.domain.entity.Item;
import com.jscisco.lom.domain.entity.ItemRepository;
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
